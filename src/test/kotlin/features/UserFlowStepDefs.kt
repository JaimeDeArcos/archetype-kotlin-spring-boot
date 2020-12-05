package features

import acceptance.utils.bodyTo
import acceptance.utils.getValidResponse
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.util.StdDateFormat
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import es.jaimedearcos.templates.configuration.rest.SerializerConfig
import es.jaimedearcos.templates.controller.model.response.UserDto
import io.cucumber.datatable.DataTable
import io.cucumber.java8.En
import org.springframework.http.HttpHeaders.CONTENT_TYPE
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.reactive.function.client.WebClient


class UserFlowStepDefs : En {

    private lateinit var userEmail: String
    private lateinit var serviceResponse: String

    private val TEST_BASE_URL = "http://localhost:5013/api/v1/users"
    private val DETAIL_PATH = "/:email"
    private val mapper = SerializerConfig().objectMapper()

    private val client = WebClient.builder()
            .baseUrl(TEST_BASE_URL)
            .defaultHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
            .build()


    init {
        Given("The user {string}") { email:String  ->
            userEmail = email
        }

        When("Request the user info") {

            val path = DETAIL_PATH.replace(":email", userEmail)

            serviceResponse = client.get().uri(path)
                    .getValidResponse()
                    .bodyTo(String::class.java)
        }

        Then("The info of the user is correct") { dataTable: DataTable ->

            val valueMap = dataTable.asMap<String, String>(String::class.java, String::class.java)
            val expected = mapper.readValue(mapper.writeValueAsString(valueMap), UserDto::class.java)

            val result = mapper.readValue(serviceResponse, UserDto::class.java)

            check(expected.id == result.id)
            check(expected.firstName == result.firstName)
        }
    }
}