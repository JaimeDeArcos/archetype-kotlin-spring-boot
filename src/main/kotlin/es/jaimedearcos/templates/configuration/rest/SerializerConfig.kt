package es.jaimedearcos.templates.configuration.rest

import com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature.FAIL_ON_EMPTY_BEANS
import com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS
import com.fasterxml.jackson.databind.util.StdDateFormat
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate


@Configuration
class SerializerConfig {

    @Bean
    fun restTemplate(): RestTemplate {
        return RestTemplate()
    }

    @Bean
    fun objectMapper(): ObjectMapper {

        val mapper = ObjectMapper()
        mapper.registerModule(KotlinModule())

        // ISO8601
        mapper.dateFormat = StdDateFormat().withColonInTimeZone(true)
        mapper.registerModule(JavaTimeModule())
        mapper.disable(WRITE_DATES_AS_TIMESTAMPS)

        mapper.configure(FAIL_ON_EMPTY_BEANS, false)
        mapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);


        return mapper
    }


}