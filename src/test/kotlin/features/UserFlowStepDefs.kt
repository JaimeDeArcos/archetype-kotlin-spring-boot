package features

import acceptance.utils.bodyTo
import acceptance.utils.getValidResponse
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
    private val DETAIL_PATH = "/:idUser"

    private val client = WebClient.builder()
            .baseUrl(TEST_BASE_URL)
            .defaultHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
            .build()

    init {
        Given("The user {string}") { email:String  ->
            userEmail = email
        }

        When("Request the user info") {

            val path = DETAIL_PATH
                    .replace(":idUser", userEmail)

            serviceResponse = client.get().uri(path)
                    .getValidResponse()
                    .bodyTo(String::class.java)
        }

        Then("The info of the user is correct") { dataTable: DataTable ->

            val input = dataTable.asMap<String, String>(String::class.java, String::class.java)

            val userInfo = UserDto(
                    id =  input["id"]!!.toLong(),
                    email = input["email"] as String
            )

            check(userInfo.id == 1L)
        }
    }
}