package com.digitalbusinessevaluation.digitalbusinessapi.service.base;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;

public interface BaseService<E> {
	List<E> findAll();

	Optional<E> findById(String id);

	void delete(E entity);

	void deleteById(String id);

	E save(E entity) throws DataIntegrityViolationException;

}
