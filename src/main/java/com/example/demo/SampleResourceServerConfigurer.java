package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class SampleResourceServerConfigurer implements ResourceServerConfigurer {

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(null);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // @formatter:off
		http.authorizeRequests()
			.mvcMatchers(HttpMethod.POST, "/v1/some-resource/**").access("#oauth2.hasScope('some-scope.write')")
			.mvcMatchers(HttpMethod.GET, "/v1/some-resource/**").access("#oauth2.hasScope('some-scope.read')")
			.antMatchers("/**").permitAll();
		// @formatter:on
    }
}