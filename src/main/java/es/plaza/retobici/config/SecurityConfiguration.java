package es.plaza.retobici.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(PasswordEncoder encoder){
        UserDetails user =  User.withDefaultPasswordEncoder()
                .username("user")
                .password(encoder.encode("password"))
                .roles("USER")
                .build();
        UserDetails admin =  User.withDefaultPasswordEncoder()
                .username("admin")
                .password(encoder.encode("password"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user,admin);
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception{
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeRequests(auth -> {
                    auth.antMatchers("/").permitAll();
                    auth.antMatchers("/api/v1/rewards/").hasRole("ADMIN");
                })
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
