package br.edu.ifsuldeminas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;


import br.edu.ifsuldeminas.modelo.Usuarios;
import br.edu.ifsuldeminas.util.Utils;

public class PontuacaoDAO {
	public Usuarios listarusuarios(String cpf){
		EntityManager em = JPAUtil.getEntityManager();
		String jpql = "SELECT p FROM Usuarios p WHERE p.cpf = :pId";
		Query query = em.createQuery(jpql, Usuarios.class);
		query.setParameter("pId",cpf);
		Usuarios lista = (Usuarios)query.getSingleResult();
		em.close();
		return lista;
	}
	
	public Usuarios buscarPorCpfESenha(String cpf, String senha) {
		Usuarios usuario;
		
		String jpql = "SELECT DISTINCT f FROM Funcionario f "
				+ "LEFT JOIN FETCH f.grupo g LEFT JOIN FETCH g.funcionalidades "
				+ "WHERE f.usuario = :pCpf AND f.senha = :pSenha";
				
		EntityManager em = JPAUtil.getEntityManager();
		TypedQuery<Usuarios> query = em.createQuery(jpql, Usuarios.class);
		query.setParameter("pLogin", cpf);
		query.setParameter("pSenha", Utils.toMD5(senha));
		
		try {
			usuario = query.getSingleResult();
	    } catch (NoResultException ex) {
	        usuario = null;
	    }
		
		em.close();
		
		return usuario;
	}
	
	
}
