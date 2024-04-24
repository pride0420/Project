package com.example.demo.security.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.security.filter.JwtAuthenticationFilter;
import com.example.demo.security.service.MemberDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http,
			
			JwtAuthenticationFilter authFilter) throws Exception{
		System.out.println(false);
		http.authorizeHttpRequests(registry->
		registry
		//hasAnyAuthority(admin)任何帶有admin的都可使用  hasAuthority(admin,user)需同時具備這些地才能使用
		.requestMatchers(HttpMethod.GET,"/member/queryAll").hasAnyAuthority ("admin","user")
		.requestMatchers(HttpMethod.GET, 
				"/member/**","/member/*","/chat/*","/car/*",
				"/comment/*","/shop/*","/porder/*").permitAll()
		.requestMatchers(HttpMethod.POST,
				"/member/**","/member/*","/member2/*","/chat/*","/car/*",
				"/comment/*","/download/*","/shop/*","/member2/**",
				"/goodchat/*","/goodComment/*","/jasp/*").permitAll()
		
		.anyRequest().authenticated()//對於所有其他未指定的請求，需要進行身份驗證
		)
		.csrf(AbstractHttpConfigurer::disable)//禁用 CSRF（Cross-Site Request Forgery，跨站請求偽造）防護
		.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
		//將自定義的 JWT 身份驗證過濾器添加到 Spring Security 過濾器鏈中，並指定它在 UsernamePasswordAuthenticationFilter 之前執行。
		return http.build();
	}
	
	/**將密碼加密和解密 */
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	/** 對使用者跟密碼進行驗證*/
    @Bean
    public AuthenticationProvider authenticationProvider(
    		MemberDetailsService memberDetailsService,
    		PasswordEncoder passwordEncoder) {
        var provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(memberDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }
}

