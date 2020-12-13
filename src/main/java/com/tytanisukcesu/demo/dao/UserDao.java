package com.tytanisukcesu.demo.dao;

import com.tytanisukcesu.demo.entity.UserEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class UserDao implements DAO<UserEntity>{

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public Optional<UserEntity> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<UserEntity> getAll() {
        return entityManager.createQuery("FROM UserEntity", UserEntity.class).getResultList();
    }

    @Override
    public void create(UserEntity userEntity) {

    }

    @Override
    public void update(UserEntity userEntity) {

    }

    @Override
    public void delete(UserEntity userEntity) {

    }
}
