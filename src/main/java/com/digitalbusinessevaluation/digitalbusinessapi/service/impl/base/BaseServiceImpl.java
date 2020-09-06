package com.digitalbusinessevaluation.digitalbusinessapi.service.impl.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import com.digitalbusinessevaluation.digitalbusinessapi.entity.base.BaseEntity;
import com.digitalbusinessevaluation.digitalbusinessapi.repository.base.BaseRepository;
import com.digitalbusinessevaluation.digitalbusinessapi.service.base.BaseService;

public class BaseServiceImpl<E extends BaseEntity, R extends BaseRepository<E>> implements BaseService<E> {

	@Autowired
	private R repository;

	@Override
	public List<E> findAll() {
		return (List<E>) repository.findAll();
	}

	@Override
	public Optional<E> findById(String id) {
		return repository.findById(id);
	}

	@Override
	public void delete(E entity) {
		repository.delete(entity);

	}

	@Override
	public void deleteById(String id) {
		repository.deleteById(id);

	}

	@Override
	public E save(E entity) throws DataIntegrityViolationException {
		return repository.save(entity);
	}

	@SuppressWarnings("unchecked")
	public List<E> replicate(E entity, int amoutOfTimesToReplicate) {
		List<E> replicas = new ArrayList<E>();
		try {
			for (int amout = 1; amout <= amoutOfTimesToReplicate; amout++) {
				replicas.add((E) entity.clone());
			}
		} catch (CloneNotSupportedException e) {
			throw new ServiceException("Impossible to replicate.");
		}
		return replicas;
	}

	public R getRepository() {
		return this.repository;
	}
}
