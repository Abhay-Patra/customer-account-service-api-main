package com.hsbc.api.customer.account.config;

import com.atlassian.oai.validator.springmvc.OpenApiValidationFilter;
import io.swagger.v3.oas.models.headers.Header;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import jakarta.servlet.Filter;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Value("${project.artifactId}")
    private String projectArtifactId;

    @Value("${project.name}")
    private String projectName;

    @Value("${project.description}")
    private String projectDesc;

    @Value("${project.version}")
    private String projectVersion;

    private static final String CONTROLLER_PACKAGE = "com.hsbc.api.customer.account.controller";

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group(projectArtifactId)
                .packagesToScan(CONTROLLER_PACKAGE)
                .addOpenApiCustomizer(openApiCustomizer)
                .build();
    }

    private final OpenApiCustomizer openApiCustomizer = openApi -> {
        final String securitySchemeName = "bearerAuth";
        openApi.info(new Info().title(projectName).description(projectDesc).version(projectVersion))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName));
    };

    @Bean
    public Filter validationFilter() {
        return new OpenApiValidationFilter(true, false);
    }
}
