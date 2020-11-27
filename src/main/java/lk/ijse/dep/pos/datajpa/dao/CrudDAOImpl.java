package lk.ijse.dep.pos.datajpa.dao;

import lk.ijse.dep.pos.datajpa.entity.SuperEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class CrudDAOImpl<T extends SuperEntity,ID extends Serializable> implements CrudDAO<T, ID> {

    @PersistenceContext
    protected EntityManager entityManager;
    private Class<T> entity;

//    @Override
//    public void setEntityManager(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }

    public CrudDAOImpl() {
        entity = (Class<T>)(((ParameterizedType)(this.getClass().getGenericSuperclass()))
                .getActualTypeArguments()[0]);
    }

    @Override
    public List<T> findAll(){
        return entityManager.createQuery("SELECT c FROM " + entity.getName() + " c", entity)
                .getResultList();
    }

    @Override
    public T find(ID id){
        return entityManager.find(entity, id);
    }

    @Override
    public void save(T entity){
        entityManager.persist(entity);
    }

    @Override
    public void update(T entity){
        entityManager.merge(entity);
    }

    @Override
    public void delete(ID id){
        entityManager.remove(entityManager.getReference(entity, id));
    }


}
