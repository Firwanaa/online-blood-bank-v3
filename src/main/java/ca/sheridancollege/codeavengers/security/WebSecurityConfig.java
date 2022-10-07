package ca.sheridancollege.codeavengers.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import ca.sheridancollege.codeavengers.service.impl.UserServiceImpl;
import ca.sheridancollege.codeavengers.web.rest.HomeController;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		// securedEnabled = true,
		// jsr250Enabled = true,
		prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	private Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	UserServiceImpl userService;

	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;

	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(passwordEncoder());
	}

	// @Bean
	// public DaoAuthenticationProvider authenticationProvider() {
	// DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

	// authProvider.setUserDetailsService(userService);
	// authProvider.setPasswordEncoder(passwordEncoder());

	// return authProvider;
	// }

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		LOGGER.info("00011*********************************************************");
		http.cors().and().csrf().disable()
				.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.authorizeRequests()
				.antMatchers("/api/donor/**", "/**").permitAll()
				//.antMatchers("/api/donor/all/**", "/all/**").hasRole("ADMIN")
				.anyRequest().authenticated();

		LOGGER.info("00022222*********************************************************");

		// http.addFilterBefore(authenticationJwtTokenFilter(),UsernamePasswordAuthenticationFilter.class);
		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	// @Bean
	// public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	// http.cors().and().csrf().disable()
	// .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
	// .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
	// .authorizeRequests().antMatchers("/api/auth/**").permitAll()
	// .antMatchers("/api/test/**").permitAll()
	// .anyRequest().authenticated();

	// http.authenticationProvider(authenticationProvider());

	// http.addFilterBefore(authenticationJwtTokenFilter(),
	// UsernamePasswordAuthenticationFilter.class);

	// return http.build();
	// }
}
