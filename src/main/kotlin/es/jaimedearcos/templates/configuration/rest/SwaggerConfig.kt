package es.jaimedearcos.templates.configuration.rest

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2


@Configuration
@EnableSwagger2
class SwaggerConfig {

    private fun apiInfo(): ApiInfo {
        return ApiInfoBuilder()
            .title("Template API")
            .version("V1")
            .contact(Contact("Template", "https://www.jaimedearcos.es/", "jaime.dearcos@gmail.com"))
            .build()
    }

    @Bean
    fun viewerApi(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("es.jaimedearcos.templates.controller"))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(apiInfo())
    }


}