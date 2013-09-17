package com.jaus.persistence;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Sets;

@Repository
public class PruebaDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public Set<Prueba> getAll(){
		return Sets.newHashSet(entityManager.createQuery("SELECT p from Prueba p", Prueba.class).getResultList());
	}

	public String getAsGeoJson(Integer id){
		return (String)entityManager.createNativeQuery("SELECT ST_AsGeoJSON(p.way) from prueba p WHERE p.p_id=:id")
				.setParameter("id", id).getSingleResult();
	}
	
	public List<String> getAsGeoJson(){
		return entityManager.createNativeQuery("SELECT ST_AsGeoJSON(p.way) from prueba p").getResultList();
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	
}
