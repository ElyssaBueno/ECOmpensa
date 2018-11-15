package br.edu.ifsuldeminas.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.edu.ifsuldeminas.dao.DAO;
import br.edu.ifsuldeminas.modelo.Funcionalidades;

@ManagedBean
@ViewScoped
public class funcionalidadesController {
private Funcionalidades funcionalidades = new Funcionalidades();
	
	public Funcionalidades getFuncionalidades() {
		return funcionalidades;
	}
	
	public void gravar(){
		if (this.funcionalidades == null) {
			new DAO<Funcionalidades>(Funcionalidades.class).adiciona(this.funcionalidades);
		} else {
			new DAO<Funcionalidades>(Funcionalidades.class).atualiza(this.funcionalidades);
		}
		
		this.funcionalidades = new Funcionalidades();
	}
	
	public List<Funcionalidades> getTodasFuncionalidadess(){
		return new DAO<Funcionalidades>(Funcionalidades.class).listaTodos();
	}
	
	public void carregar(Funcionalidades f){
		this.funcionalidades = f;
	}
	
	public void remover(Funcionalidades f){
		try{
			new DAO<Funcionalidades>(Funcionalidades.class).remove(f.getId());
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("funcionaliddae", new FacesMessage("Impossível remover: Funcionalidades associada a grupo."));
		}
	}
}
