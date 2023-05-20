package com.example.vuespring2.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

@OpenAPIDefinition
@Configuration
public class SpringdocConfig {
    @Bean
    public OpenAPI baseOpenApi(){
        ApiResponse badRequestAPI=new ApiResponse().content(
                new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE,new io.swagger.v3.oas.models.media.MediaType().addExamples("default",
                        new Example().value("{\"code\":400,\"Status\":\"Bad Request\",\"Message\",: \"Bad Request\"}")))
        ).description("Bad Request!");
        ApiResponse internalServerErrorAPI=new ApiResponse().content(
                new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE,new io.swagger.v3.oas.models.media.MediaType().addExamples("default",
                        new Example().value("{\"code\":500,\"Status\":\"Internal Server Error!\",\"Message\",: \"Internal Server Error!\"}")))
        ).description("Internal Server Error!");
        ApiResponse successFully=new ApiResponse().content(
                new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE,new io.swagger.v3.oas.models.media.MediaType().addExamples("default",
                        new Example().value("{\"code\":200,\"Status\":\"Ok\",\"Message\",: \"Ok Successfully  \"}")))
        ).description("Successfully!");
        Components components =new Components();
        components.addResponses("badRequestAPI",badRequestAPI);
        components.addResponses("internalServerErrorAPI",internalServerErrorAPI);
        components.addResponses("successFully",successFully);
        return new OpenAPI().components(components)
                .info(new Info()
                        .title("Spring Doc")
                        .version("1.0.0").
                        description("Spring Doc")

                ).schemaRequirement("bearerAuth",new SecurityScheme()
                .scheme("Bearer")
                                .type(SecurityScheme.Type.HTTP)
                                .bearerFormat("jwt") //if it is your case
                                .name("Bearer")
                                
                );
    }
}
