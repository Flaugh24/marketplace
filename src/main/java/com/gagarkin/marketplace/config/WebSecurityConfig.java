package com.gagarkin.marketplace.config;

import com.gagarkin.marketplace.model.User;
import com.gagarkin.marketplace.repository.UserRepository;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.Optional;

@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/", "/login**", "/js/**", "/error**")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .logout().logoutSuccessUrl("/").permitAll()
                .and()
                .csrf().disable();
    }

    @Bean
    public PrincipalExtractor principalExtractor(UserRepository userRepository) {
        return map -> {
            String id = String.valueOf(map.get("sub"));
            Optional<User> userOptional = userRepository.findById(id);
            return userOptional.orElseGet(() -> {
                User user = new User();
                user.setId(id);
                user.setName(String.valueOf(map.get("given_name")));
                user.setSurname(String.valueOf(map.get("family_name")));
                user.setEmail(String.valueOf(map.get("email")));
                user.setUserpic(String.valueOf(map.get("picture")));
                user.setLocale(String.valueOf(map.get("locale")));
                return userRepository.save(user);
            });
        };
    }
}
