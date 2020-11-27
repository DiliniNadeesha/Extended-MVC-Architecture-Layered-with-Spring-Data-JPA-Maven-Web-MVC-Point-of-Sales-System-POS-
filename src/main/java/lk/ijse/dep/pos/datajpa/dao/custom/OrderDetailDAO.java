package lk.ijse.dep.pos.datajpa.dao.custom;

import lk.ijse.dep.pos.datajpa.dao.CrudDAO;
import lk.ijse.dep.pos.datajpa.entity.OrderDetail;
import lk.ijse.dep.pos.datajpa.entity.OrderDetailPK;

public interface OrderDetailDAO extends CrudDAO<OrderDetail, OrderDetailPK> {

    boolean existsByItemCode(String itemCode) ;

}
