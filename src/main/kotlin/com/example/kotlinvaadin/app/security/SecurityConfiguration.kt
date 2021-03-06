package com.example.kotlinvaadin.app.security

import com.example.kotlinvaadin.app.security.SecurityUtils.isFrameworkInternalRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.util.matcher.RequestMatcher


/**
 * Configures spring security, doing the following:
 *  * Bypass security checks for static resources,
 *  * Restrict access to the application, allowing only logged in users,
 *  * Set up the login form
 */
@EnableWebSecurity
@Configuration
class SecurityConfiguration : WebSecurityConfigurerAdapter() {

    companion object {
        private const val LOGIN_PROCESSING_URL = "/login"
        private const val LOGIN_FAILURE_URL = "/login"
        private const val LOGIN_URL = "/login"
        private const val LOGOUT_SUCCESS_URL = "/login"
    }

    /**
     * Require login to access internal pages and configure login form.
     */
    override fun configure(http: HttpSecurity) {
        // Not using Spring CSRF here to be able to use plain HTML for the login page
        http.csrf().disable()

                // Register our CustomRequestCache, that saves unauthorized access attempts, so
                // the user is redirected after login.
                .requestCache().requestCache(CustomRequestCache())

                // Restrict access to our application.
                .and().authorizeRequests()

                // Allow all flow internal requests.
                .requestMatchers(RequestMatcher { r -> isFrameworkInternalRequest(r) }).permitAll()

                // Allow all requests by logged in users.
                .anyRequest().authenticated()

                // Configure the login page.
                .and().formLogin().loginPage(LOGIN_URL).permitAll().loginProcessingUrl(LOGIN_PROCESSING_URL)
                .failureUrl(LOGIN_FAILURE_URL).successForwardUrl("/home")

                // Configure logout
                .and().logout().logoutUrl("/perform_logout").logoutSuccessUrl(LOGOUT_SUCCESS_URL)
    }

    @Bean
    public override fun userDetailsService(): UserDetailsService {
        val user = User.withUsername("user")
                .password("{noop}password")
                .roles("USER")
                .build()

        return InMemoryUserDetailsManager(user)
    }

    /**
     * Allows access to static resources, bypassing Spring security.
     */
    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers(
                // Vaadin Flow static resources
                "/VAADIN/**",

                // the standard favicon URI
                "/favicon.ico",

                // the robots exclusion standard
                "/robots.txt",

                // web application manifest
                "/manifest.webmanifest",
                "/sw.js",
                "/offline-page.html",

                // icons and images
                "/icons/**",
                "/images/**",

                // (development mode) static resources
                "/frontend/**",

                // (development mode) webjars
                "/webjars/**",

                // (development mode) H2 debugging console
                "/h2-console/**",

                // (production mode) static resources
                "/frontend-es5/**", "/frontend-es6/**"

        )
    }

}