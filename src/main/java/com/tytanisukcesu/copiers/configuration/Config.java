package com.tytanisukcesu.copiers.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableCaching
public class Config {


    //    dokumentacja swaggera - exclude errors
    @Bean
    public Docket swaggerApi() {
        return new Docket(DocumentationType. SWAGGER_2 )
                .select()
                .paths(PathSelectors. regex ( "^(?!/(error).*$).*$"))
                .paths(PathSelectors. regex ( "^(?!/(entity).*$).*$"))
                .build();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }






}
