package br.edu.ifsuldeminas.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.ifsuldeminas.dao.DAO;
import br.edu.ifsuldeminas.dao.PontuacaoDAO;
import br.edu.ifsuldeminas.modelo.Grupos;
import br.edu.ifsuldeminas.modelo.Usuarios;

@ManagedBean
@ViewScoped
public class usuariosController {
private String cpf;
private Integer id;
private Integer pontuacao;
private Integer valor;
private Integer grupoId;
private Usuarios usuarios=new Usuarios();

	
	public Usuarios getUsuarios() {
		return usuarios;
	}

	public String getCpf() {
		return cpf;
	}
	

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(Integer pontuacao) {
		this.pontuacao = pontuacao;
	}
	

	public Integer getGrupoId() {
		return grupoId;
	}

	public void setGrupoId(Integer grupoId) {
		this.grupoId = grupoId;
	}

	public void gravar(){
		if(this.usuarios.getId()==null){
			new DAO<Usuarios>(Usuarios.class).adiciona(usuarios);
		}
		else{
			new DAO<Usuarios>(Usuarios.class).atualiza(usuarios);
		}
		this.usuarios = new Usuarios();
	}
	
	public void todospontuacao(){
		usuarios= new PontuacaoDAO().listarusuarios(cpf);
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<Usuarios> getTodosUsuarios(){
		return new DAO<Usuarios>(Usuarios.class).listaTodos();
	}
	public void remover(Usuarios p){
		new DAO<Usuarios>(Usuarios.class).remove(p.getId());
	}
	public void carregar(Usuarios p){
		this.usuarios = p;

	}
	public void atribuir() {
		usuarios.setPontuacao(usuarios.getPontuacao()+valor);
		new DAO<Usuarios>(Usuarios.class).atualiza(usuarios);
		usuarios = new Usuarios();
		valor=null;
	}

	public void descontar() {
		usuarios.setPontuacao(usuarios.getPontuacao()-valor);
		new DAO<Usuarios>(Usuarios.class).atualiza(usuarios);
		usuarios = new Usuarios();
		valor=null;
	}
	public List<Grupos> getTodosGrupos() {
		return new DAO<Grupos>(Grupos.class).listaTodos();
	}

}
