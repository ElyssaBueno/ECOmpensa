package br.edu.ifsuldeminas.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.ifsuldeminas.dao.DAO;
import br.edu.ifsuldeminas.dao.GruposDAO;
import br.edu.ifsuldeminas.modelo.Funcionalidades;
import br.edu.ifsuldeminas.modelo.Grupos;

@ManagedBean
@ViewScoped
public class gruposController {
	private Grupos grupos = new Grupos();
	private Integer funcionalidadesId;
	
	public Grupos getGrupos() {
		return grupos;
	}

	public Integer getFuncionalidadesId() {
		return funcionalidadesId;
	}

	public void setFuncionalidadesId(Integer funcionalidadesId) {
		this.funcionalidadesId = funcionalidadesId;
	}
	
	public List<Grupos> getTodosGrupos(){
		return new DAO<Grupos>(Grupos.class).listaTodos();
	}
	
	public List<Funcionalidades> getTodasFuncionalidades(){
		return new DAO<Funcionalidades>(Funcionalidades.class).listaTodos();
	}
	
	public void gravarFuncionalidades(){
		Funcionalidades f = new DAO<Funcionalidades>(Funcionalidades.class).listaPorId(funcionalidadesId);
		grupos.addFuncionalidades(f);
		funcionalidadesId=null;
	}
	
	public void gravar(){
		if (this.grupos.getId() == null) {
			new DAO<Grupos>(Grupos.class).adiciona(this.grupos);
		} else {
			new DAO<Grupos>(Grupos.class).atualiza(this.grupos);
		}
		this.grupos = new Grupos();
	}
	
	public List<Funcionalidades>getFuncionalidadesDoGrupos(){
		return grupos.getFuncionalidades();
	}
	
	public void remover(Funcionalidades f){
		this.grupos.getFuncionalidades().remove(f);
	}
	
	public void remover(Grupos g){
		new DAO<Grupos>(Grupos.class).remove(g.getId());
	}
	
	public void carregar(Grupos g){
		g=new GruposDAO().listaPorId(g);
		grupos=g;
	}
}
