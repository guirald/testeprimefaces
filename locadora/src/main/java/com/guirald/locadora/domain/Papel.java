package com.guirald.locadora.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Papel implements Serializable, GrantedAuthority {

	/*
	 * GrantedAuthority ==> representa a base para manipulação
	 * de papéis no Spring Security
	 * */
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "papel_id_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "papel_id_seq", sequenceName = "papel_id_seq", allocationSize = 1)
	private Integer id;

	@Column(name = "nome")
	private String authority;

	// mapeia o nome do papel
	// lê o nome do papel no banco de dados
	@Override
	public String getAuthority() {
		return authority;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Papel other = (Papel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
