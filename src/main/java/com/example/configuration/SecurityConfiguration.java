package com.example.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private DataSource dataSource;
	
	@Value("${spring.queries.users-query}")
	private String usersQuery;
	
	@Value("${spring.queries.roles-query}")
	private String rolesQuery;
	
	
	@Value("${spring.queries.passenger-query}")
	private String passengerQuery;
	

	@Value("${spring.queries.wallet-query}")
	private String walletQuery;
	
	@Value("${spring.queries.passengerTrip-query}")
	private String passengerTripQuery;
	
	
	@Value("${spring.queries.debitCard-query}")
	private String debitCardQuery;
	
	@Value("${spring.queries.netbanking-query}")
	private String netbankingQuery;
	
	@Value("${spring.queries.otherPay-query}")
	private String otherPayQuery;
	
	@Value("${spring.queries.conRegister-query}")
	private String conRegisterQuery;
	
	@Value("${spring.queries.conLogin-query}")
	private String conLoginQuery;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.
			jdbcAuthentication()
				.usersByUsernameQuery(usersQuery)
				.dataSource(dataSource)
				.passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.
			authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/login").permitAll()
				.antMatchers("/registration").permitAll()
				.antMatchers("/passenger").permitAll()
				.antMatchers("/passengerOtp").permitAll()
				.antMatchers("/passengerTrip").permitAll()
				.antMatchers("/passengerPay").permitAll()
				.antMatchers("/successfulPayed").permitAll()
				.antMatchers("/conTktbook").permitAll()
				.antMatchers("/wallet").permitAll()
				.antMatchers("/walletDebit").permitAll()
				.antMatchers("/passengerTrips").permitAll()
				.antMatchers("/debitCard").permitAll()
				.antMatchers("/netbanking").permitAll()
				.antMatchers("/otherPay").permitAll()
				.antMatchers("/conRegister").permitAll()
				.antMatchers("/conLogin").permitAll()
				.antMatchers("/admin/**").hasAuthority("ADMIN").anyRequest()
				.authenticated().and().csrf().disable().formLogin()
				.loginPage("/login").failureUrl("/login?error=true")
				.defaultSuccessUrl("/admin/home")
				.usernameParameter("email")
				.passwordParameter("password")
				.and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/").and().exceptionHandling()
				.accessDeniedPage("/access-denied");
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web
	       .ignoring()
	       .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**","/vendor/**","/data/**","/dist/**","/less/**");
	}

}