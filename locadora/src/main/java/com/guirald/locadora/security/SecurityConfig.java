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
	 * marca o método abaixo como de inicialização, isto é, será chamado assim
	 * que o objeto for criado
	 */
	@Inject
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		// usuários armazenados em memória
		auth.inMemoryAuthentication()
				// declara usuários com a respectiva senha e papel
				.withUser("op").password("password").roles("OPERATOR").and()
				.withUser("admin").password("password").roles("ADMIN");
	}

	protected void configure(HttpSecurity http) throws Exception {
		// desabilita proteção contra CSRF, pois JSF 2.2 já trata isto
		http.csrf().disable();
		// configura página de acesso negado
		http.exceptionHandling().accessDeniedPage("/acesso-negado.xhtml")
		.and()
		// configura acesso restrito às páginas do sistema
		.authorizeRequests()
		.antMatchers("/administracao/**").hasRole("ADMIN")
		// usuário deve estar autenticado para acessar qualquer página do sistema
		.anyRequest().authenticated()
		// and => equivalente a uma tag de fechamento de XML
		.and()
		.logout().logoutSuccessUrl("/login.xhtml?logout")
		// qualquer usuário terá acesso à página login.xhtml após a chamada após o logout
		.permitAll()
		.and()
		.formLogin().loginPage("/login.xhtml")
		.failureUrl("/login.xhtml?erro")
		.permitAll();
		//.and()
		// usuários forçados a se autenticar através de uma página de login
		//.formLogin()
		//.and()
		// método de autenticação http basic
		//.httpBasic();
	}

}
