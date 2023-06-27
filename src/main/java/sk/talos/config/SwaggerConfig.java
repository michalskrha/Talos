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
    public static final String DEFAULT_INCLUDE_PATTERN = "/api/.*";


    private Environment env;

    public SwaggerConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public Docket api(ServletContext servletContext) {
        return new Docket(DocumentationType.SWAGGER_2)
                .tags(
                        new Tag(SwaggerTags.POSTS_TAG, SwaggerTags.POSTS_TAG_DESC, 1)
                )
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
//                .useDefaultResponseMessages(false)
//                .globalOperationParameters(globalParameterList())
                .select()
//                .apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage( "com.talos" ))
//                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.apiInfo());
    }



    private List<Parameter> globalParameterList() {
        Parameter authTokenHeader = new ParameterBuilder()
                        .name("X-tenant")
                        .modelRef(new ModelRef("string"))
                        .required(true)
                        .parameterType("header")
                        .description("Tenant ID")
                        .build();

        return Collections.singletonList(authTokenHeader);
    }



    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Trip Management System App B2B REST API",
                "This REST API provide to travel agency many possibilities to manage customer trips with their events.",
                "REST API 1.0",
                "Terms of service",
                new Contact("Travelblocks s.r.o.", "https://www.travelblocks.sk", "travelblocks@travelblocks.sk"),
                "License of API", "API license URL", Collections.emptyList());
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
//                .forPaths(PathSelectors.regex(DEFAULT_INCLUDE_PATTERN))
                .build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(
                new SecurityReference("JWT", authorizationScopes));
    }
}