package br.edu.ifsuldeminas.controller;


import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.edu.ifsuldeminas.dao.DAO;
import br.edu.ifsuldeminas.modelo.Produtos;

@ManagedBean
@ViewScoped
public class produtosController {
	private Produtos produtos=new Produtos();
	
	public Produtos getProdutos() {
		return produtos;
	}

	public void gravar(){
		if(this.produtos.getId()==null){
			new DAO<Produtos>(Produtos.class).adiciona(produtos);
		}
		else{
			new DAO<Produtos>(Produtos.class).atualiza(produtos);
		}
		this.produtos = new Produtos();
	}
	
	public List<Produtos> getTodosProdutos(){
		return new DAO<Produtos>(Produtos.class).listaTodos();
	}
	public void remover(Produtos p){
		new DAO<Produtos>(Produtos.class).remove(p.getId());
	}
	public void carregar(Produtos p){
		this.produtos = p;

	}


}
