package ch.zli.m223.punchclock.security;

import ch.zli.m223.punchclock.filter.JWTAuthenticationFilter;
import ch.zli.m223.punchclock.filter.JWTAuthorizationFilter;
import ch.zli.m223.punchclock.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static ch.zli.m223.punchclock.security.SecurityConstants.SIGN_UP_URL;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
    private UserService userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurity(UserService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
                //.antMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated()
                //.and()
                //.headers().frameOptions().disable()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager(), this.userDetailsService))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        String[] headers = {
                "Access-Control-Allow-Origin",
                "Access-Control-Expose-Headers",
                "Authorization",
                "Cache-Control",
                "Content-Type",
                "Origin"
        };

        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.applyPermitDefaultValues();
        for (String header : headers){
            corsConfiguration.addExposedHeader(header);
        }
        corsConfiguration.addAllowedMethod("DELETE");
        corsConfiguration.addAllowedMethod("PUT");
        corsConfiguration.addAllowedMethod("OPTIONS");

        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }
}
