package com.guirald.locadora.managedbean;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.guirald.locadora.domain.Filme;

@Named
@RequestScoped
public class FilmeBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private Filme filme;

	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}

	public String cadastrarFilme() {
		// adiciona mensagem de sucesso
		FacesMessage fm = new FacesMessage();
		fm.setSummary("Sucesso ao salvar o filme " + filme.getTitulo());
		fm.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage("form", fm);

		return "/inicio.xhtml";
	}

	public String alugarFilme() {
		// adiciona mensagem de sucesso
		FacesMessage fm = new FacesMessage();
		fm.setSummary("Sucesso ao alugar o filme " + filme.getTitulo());
		fm.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage("form", fm);

		return "/inicio.xhtml";
	}

}
