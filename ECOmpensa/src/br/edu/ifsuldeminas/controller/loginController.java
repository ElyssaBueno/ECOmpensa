package br.edu.ifsuldeminas.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.edu.ifsuldeminas.dao.PontuacaoDAO;

import br.edu.ifsuldeminas.modelo.Funcionalidades;

import br.edu.ifsuldeminas.modelo.Usuarios;

@ManagedBean
@ViewScoped
public class loginController {
private Usuarios usuario = new Usuarios();
	
	public Usuarios getUsuario() {
		return usuario;
	}
	
	public String logar(){
		this.usuario = new PontuacaoDAO().buscarPorCpfESenha(usuario.getCpf(), usuario.getSenha());
		FacesContext context = FacesContext.getCurrentInstance();
		if(usuario!=null){ //logou
			context.getExternalContext().getSessionMap().put("usuarioLogado", usuario);
		List<Funcionalidades> lista = usuario.getGrupo().getFuncionalidades();
		for(Funcionalidades f: lista){
			context.getExternalContext().getSessionMap().put(f.getPagina(), f);
			}
			return "index?faces-redirect=true";
		}else{
			context.getExternalContext().getFlash().setKeepMessages(true);
			context.addMessage(null, new FacesMessage("Login e/ou senha incorretos!"));
			return "login?faces-redirect=true";
		}
	}
	public String deslogar(){
		FacesContext context = FacesContext.getCurrentInstance();
		//context.getExternalContext().getSessionMap().remove("usuariologado");
		context.getExternalContext().getSessionMap().clear();
		return "login?faces-redirect=true";
	}
	
	public boolean temAcesso(String nomePagina){
		FacesContext context = FacesContext.getCurrentInstance();
		Funcionalidades func = (Funcionalidades)
		context.getExternalContext().getSessionMap().get("/" + nomePagina + ".xhtml");
		if(func==null){
			return false;
		}
		return true;
		
	}
}
