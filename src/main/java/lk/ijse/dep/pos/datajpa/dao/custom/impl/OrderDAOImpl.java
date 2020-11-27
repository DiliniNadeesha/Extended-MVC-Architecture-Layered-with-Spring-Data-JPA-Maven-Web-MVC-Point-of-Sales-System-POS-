package lk.ijse.dep.pos.datajpa.dao.custom.impl;
import lk.ijse.dep.pos.datajpa.dao.CrudDAOImpl;
import lk.ijse.dep.pos.datajpa.dao.custom.OrderDAO;
import lk.ijse.dep.pos.datajpa.entity.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class OrderDAOImpl extends CrudDAOImpl<Order, Integer> implements OrderDAO {

    @Override
    public int getLastOrderId()  {
        Query nativeQuery = entityManager.createNativeQuery("SELECT id FROM `Order` ORDER BY id DESC LIMIT 1");
        return nativeQuery.getResultList().size() > 0? (int) nativeQuery.getSingleResult() : 0;
    }

    @Override
    public boolean existsByCustomerId(String customerId)  {
        return entityManager.createNativeQuery("SELECT * FROM `Order` WHERE customer_id=?1")
                .setParameter(1, customerId).getResultList().size() > 0;
    }
}
