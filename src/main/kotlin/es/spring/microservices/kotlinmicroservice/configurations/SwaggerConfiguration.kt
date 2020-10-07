package es.spring.microservices.kotlinmicroservice.configurations;

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

const val API_BASE_PATH = "/api"

@Configuration
@EnableSwagger2
class SwaggerConfiguration {

    private val API_TITLE = "Kotlin Microservice"
    private val API_DESCRIPTION = "Kotlin Microservice API"

    @Bean
    @Primary
    fun apiV1() : Docket {
        return Docket(DocumentationType.SWAGGER_2) //
                .useDefaultResponseMessages(false) //
                .apiInfo(apiInfo()) //
                .useDefaultResponseMessages(false).select() //
                .paths(PathSelectors.regex(".*$API_BASE_PATH/.*"))
                .build()
    }

    private fun apiInfo(): ApiInfo? {
        return ApiInfoBuilder() //
                .title(API_TITLE) //
                .description(API_DESCRIPTION) //
                .build()
    }

}