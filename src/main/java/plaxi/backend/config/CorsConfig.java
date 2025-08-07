package plaxi.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // Orígenes permitidos
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200", "http://tudominio.com"));
        // Métodos permitidos
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        // Encabezados permitidos
        corsConfiguration.setAllowedHeaders(Arrays.asList("*")); // Permitir todos los encabezados
        // Permitir credenciales
        corsConfiguration.setAllowCredentials(true);
        // Tiempo máximo de cacheo de preflight (opcional)
        corsConfiguration.setMaxAge(3600L); // Cachea la respuesta de preflight por 1 hora

        // Configura el origen y los paths para los que se aplicará esta configuración
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return new CorsFilter(source);
    }
}
