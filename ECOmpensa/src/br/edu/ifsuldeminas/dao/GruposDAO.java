package br.edu.ifsuldeminas.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.ifsuldeminas.modelo.Grupos;

public class GruposDAO {
	public Grupos listaPorId(Grupos g){
		EntityManager em = JPAUtil.getEntityManager();
		String jpql= "SELECT DISTINCT g from Grupo g left join fetch g.funcionalidades "
		+"WHERE g.id = :pId";
		TypedQuery<Grupos> query = em.createQuery(jpql,Grupos.class);
		query.setParameter("pId", g.getId());
		g= query.getSingleResult();
		em.close();
		return g;
	}

}
