package lk.ijse.dep.pos.datajpa.dao.custom;

import lk.ijse.dep.pos.datajpa.dao.CrudDAO;
import lk.ijse.dep.pos.datajpa.entity.Order;

public interface OrderDAO extends CrudDAO<Order, Integer> {

    int getLastOrderId() ;

    boolean existsByCustomerId(String customerId) ;

}
