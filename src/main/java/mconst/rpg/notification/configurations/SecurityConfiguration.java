package mconst.rpg.notification.configurations;

import mconst.rpg.notification.services.KeycloakTokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class SecurityConfiguration {
    private Environment environment;

    public SecurityConfiguration(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public KeycloakTokenService keycloakTokenService() {
        return new KeycloakTokenService(
                environment.getProperty("custom.keycloak.uri"),
                environment.getProperty("custom.keycloak.client-id"),
                environment.getProperty("custom.keycloak.username"),
                environment.getProperty("custom.keycloak.password")
        );
    }
}
