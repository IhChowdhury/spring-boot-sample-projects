package com.ibrahim.sample.rest.service.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {
    @Value("${spring.application.name}") private String APPLICATION_NAME;
    @Value("${info.app.version:unknown}") private String VERSION;
    @Value("${info.terms.url}") private String TERMS_URL;
    @Value("${info.server.url}") private String DEV_SERVER_URL;
    @Value("${info.server.desc}") private String SERVER_URL_DESCRIPTION;
    @Value("${info.contact.nam}") private String CONTACT_NAME;
    @Value("${info.contact.email}") private String CONTACT_EMAIL;
    @Value("${info.contact.url}") private String CONTACT_URL;
    @Value("${info.mit.license.url}") private String MIT_LICENSE_URL;
    @Value("${info.mit.license.name}") private String MIT_LICENSE_NAME;

    @Bean
    public OpenAPI openAPI() {
        Server devServer = getServer();
        Info info = getInfo();
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("basicScheme",securityScheme()))
                .info(info).servers(List.of(devServer));
    }

    public SecurityScheme securityScheme(){
        return new SecurityScheme()
                .name("basicSecurity")
                .scheme("basic")
                .type(SecurityScheme.Type.HTTP)
                .in(SecurityScheme.In.HEADER);
    }

    private Info getInfo() {
        String apiDocumentationDesc = "This API exposes endpoint to manage rest APIs";

        Contact contact = getContact();
        License mitLicense = getLicense();
        return new Info()
                .title(APPLICATION_NAME)
                .version(VERSION)
                .contact(contact)
                .description(apiDocumentationDesc).termsOfService(TERMS_URL)
                .license(mitLicense);
    }

    private License getLicense() {
        return new License().name(MIT_LICENSE_NAME).url(MIT_LICENSE_URL);
    }

    private Server getServer() {
        Server devServer = new Server();
        devServer.setUrl(DEV_SERVER_URL);
        devServer.setDescription(SERVER_URL_DESCRIPTION);
        return devServer;
    }

    private Contact getContact() {
        Contact contact = new Contact();
        contact.setEmail(CONTACT_EMAIL);
        contact.setName(CONTACT_NAME);
        contact.setUrl(CONTACT_URL);
        return contact;
    }
}
