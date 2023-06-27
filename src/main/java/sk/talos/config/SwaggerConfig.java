package sk.talos.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import sk.talos.enums.SwaggerTags;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import javax.servlet.ServletContext;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebMvc
public class SwaggerConfig {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    @Bean
    public Docket api(ServletContext servletContext) {
        return new Docket(DocumentationType.SWAGGER_2)
                .tags(
                        new Tag(SwaggerTags.POSTS_TAG, SwaggerTags.POSTS_TAG_DESC, 1),
                        new Tag(SwaggerTags.USER_POSTS_TAG, SwaggerTags.USER_POSTS_TAG_DESC, 2)
                )
                .select()
                .apis(RequestHandlerSelectors.basePackage( "sk.talos" ))
                .build()
                .apiInfo(this.apiInfo());
    }




    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Talos App B2B REST API",
                "This REST API provide to talos post features.",
                "REST API 1.0",
                "Terms of service",
                new Contact("Kodeo Solutions s.r.o.", "https://www.kodeosolutions.sk", "kodeosolutions@kodeosolutions.sk"),
                "License of API", "API license URL", Collections.emptyList());
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
    }

}