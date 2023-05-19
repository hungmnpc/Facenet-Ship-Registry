package com.facenet.shipsregistry.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */

@Repository
@Transactional
public class FormDAO {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     *
     * @param tableName
     * @param formId
     * @param newIndex
     */
    public void updateFormIndex(String tableName, Long formId, Integer newIndex) {
        String query = "UPDATE " + tableName + " SET form_index = :newIndex WHERE id = :formId";
        entityManager.createNativeQuery(query)
                .setParameter("newIndex", newIndex)
                .setParameter("formId", formId)
                .executeUpdate();
    }
}