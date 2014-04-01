/**
 * 
 */
package com.qurion.businesslogic.application.service;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.qurion.businesslogic.application.model.BaseEntity;
import com.qurion.businesslogic.application.util.DateUtil;

/**
 * @author Edward Banfa
 *
 */
public class AbstractServiceImpl implements AbstractService {

    @Inject private EntityManager entityManager;

	/**
	 * @return the entityManager
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * @param entityManager the entityManager to set
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public BaseEntity initializeFields(BaseEntity entity) {
		entity.setCreatedDt(DateUtil.getCurrentDate());
		entity.setEffectiveDt(DateUtil.getCurrentDate());
		entity.setRecSt(AbstractEntityService.ENTITY_STATUS_ACTIVE);
		entity.setCreatedByUsr(AbstractEntityService.SYSTEM_USR_NAME);
		return entity;
	}

	/**
	 * @param attribute
	 */
	public BaseEntity save(BaseEntity instance) {
		getEntityManager().persist(instance);
		getEntityManager().flush();
		return instance;
	}

	/**
	 * @param component
	 */
	public void delete(BaseEntity instance) {
		this.getEntityManager().remove(instance);
		this.getEntityManager().flush();
	}


	/**
	 * Utility method to remove an object from
	 * storage. TODO: Refactor this into a persistence
	 * utility class.
	 * 
	 * @param object The object we are remove from storage.
	 */
	protected void removeObject(Object object) {
		getEntityManager().flush();
		getEntityManager().remove(object);
		getEntityManager().flush();
	}

}
