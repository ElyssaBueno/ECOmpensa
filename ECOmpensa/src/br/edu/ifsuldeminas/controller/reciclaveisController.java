package br.edu.ifsuldeminas.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.ifsuldeminas.dao.DAO;
import br.edu.ifsuldeminas.modelo.Reciclaveis;

@ManagedBean
@ViewScoped
public class reciclaveisController {
private Reciclaveis reciclaveis=new Reciclaveis();
	
	public Reciclaveis getReciclaveis() {
		return reciclaveis;
	}

	public void gravar(){
		if(this.reciclaveis.getId()==null){
			new DAO<Reciclaveis>(Reciclaveis.class).adiciona(reciclaveis);
		}
		else{
			new DAO<Reciclaveis>(Reciclaveis.class).atualiza(reciclaveis);
		}
		this.reciclaveis = new Reciclaveis();
	}
	
	public List<Reciclaveis> getTodosReciclaveis(){
		return new DAO<Reciclaveis>(Reciclaveis.class).listaTodos();
	}
	public void remover(Reciclaveis p){
		new DAO<Reciclaveis>(Reciclaveis.class).remove(p.getId());
	}
	public void carregar(Reciclaveis p){
		this.reciclaveis = p;

	}


}
