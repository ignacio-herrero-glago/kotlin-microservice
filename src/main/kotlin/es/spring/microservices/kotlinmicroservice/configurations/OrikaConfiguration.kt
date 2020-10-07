package es.spring.microservices.kotlinmicroservice.configurations;

import ma.glasnost.orika.MapperFacade
import ma.glasnost.orika.MapperFactory
import ma.glasnost.orika.impl.DefaultMapperFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
class OrikaConfiguration {

    @Bean
    fun defaultMapper() : MapperFacade {

        val factory : MapperFactory = DefaultMapperFactory.Builder().build()

        /* custom mappings here */

        return factory.mapperFacade

    }

}