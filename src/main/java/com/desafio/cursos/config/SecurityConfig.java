package com.desafio.cursos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http ) throws Exception {
        http
            .authorizeHttpRequests((requests ) -> requests
                .requestMatchers( "/api/**",
                "/cursos-web/**", 
                "/cursos-web/excluir/**",
                "/cursos-web/detalhes/**",
                "/cursos-web/salvar", 
                "/cursos-web/lista", 
                "/cursos-web/cadastro"
                //"/H2-console/**")
                )
                .permitAll() 
                .anyRequest().authenticated()
            )
            .headers(headers -> headers.frameOptions(frame -> frame.disable()))
            .formLogin((form) -> form
                .defaultSuccessUrl("/cursos-web/lista", true) 
                .permitAll()
            )
            .logout((logout) -> logout.permitAll())
            .csrf(csrf -> csrf.disable()); 

        return http.build( );
    }

    @Bean
    public UserDetailsService userDetailsService() {
      
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}

