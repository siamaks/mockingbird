package com.smx.service;

import com.smx.entity.Person;
import javax.persistence.EntityManager;

/**
 *
 * @author SiamaX
 */
public class PersonService {
    private EntityManager em;

    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    public Person find(Long id) {
//        em.contains(id);
//        return null;
        return em.find(Person.class, id);
    }
}
