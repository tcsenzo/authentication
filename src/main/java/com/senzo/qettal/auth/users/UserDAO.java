package com.senzo.qettal.auth.users;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDAO implements Users {

	@Autowired
	private EntityManager em;
	
	@Override
	@Transactional
	public User save(User user) {
		em.persist(user);
		return user;
	}

	@Override
	public Optional<User> findByEmail(String email) {
		try{
			return Optional.of(em.createQuery("from User where email = :email", User.class)
					.setParameter("email", email)
					.getSingleResult());
		} catch (NonUniqueResultException | NoResultException e){
			return Optional.empty();
		}
	}

}
