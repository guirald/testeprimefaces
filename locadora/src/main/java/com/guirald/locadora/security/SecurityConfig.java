package com.guirald.locadora.security;

import javax.inject.Inject;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/*
	 * marca o m�todo abaixo como de inicializa��o, isto �, ser� chamado assim
	 * que o objeto for criado
	 */
	@Inject
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		// usu�rios armazenados em mem�ria
		auth.inMemoryAuthentication()
				// declara usu�rios com a respectiva senha e papel
				.withUser("op").password("password").roles("OPERATOR").and()
				.withUser("admin").password("password").roles("ADMIN");
	}

	protected void configure(HttpSecurity http) throws Exception {
		// desabilita prote��o contra CSRF, pois JSF 2.2 j� trata isto
		http.csrf().disable();
		// configura p�gina de acesso negado
		http.exceptionHandling().accessDeniedPage("/acesso-negado.xhtml")
		.and()
		// configura acesso restrito �s p�ginas do sistema
		.authorizeRequests()
		.antMatchers("/administracao/**").hasRole("ADMIN")
		// usu�rio deve estar autenticado para acessar qualquer p�gina do sistema
		.anyRequest().authenticated()
		// and => equivalente a uma tag de fechamento de XML
		.and()
		.logout().logoutSuccessUrl("/login.xhtml?logout")
		// qualquer usu�rio ter� acesso � p�gina login.xhtml ap�s a chamada ap�s o logout
		.permitAll()
		.and()
		.formLogin().loginPage("/login.xhtml")
		.failureUrl("/login.xhtml?erro")
		.permitAll();
		//.and()
		// usu�rios for�ados a se autenticar atrav�s de uma p�gina de login
		//.formLogin()
		//.and()
		// m�todo de autentica��o http basic
		//.httpBasic();
	}

}
