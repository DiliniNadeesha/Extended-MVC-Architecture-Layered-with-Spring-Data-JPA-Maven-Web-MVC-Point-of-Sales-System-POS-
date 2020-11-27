package lk.ijse.dep.pos.datajpa.dao.custom.impl;
import lk.ijse.dep.pos.datajpa.dao.CrudDAOImpl;
import lk.ijse.dep.pos.datajpa.dao.custom.OrderDetailDAO;
import lk.ijse.dep.pos.datajpa.entity.OrderDetail;
import lk.ijse.dep.pos.datajpa.entity.OrderDetailPK;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDetailDAOImpl extends CrudDAOImpl<OrderDetail, OrderDetailPK> implements OrderDetailDAO {

    @Override
    public boolean existsByItemCode(String itemCode)  {
       return entityManager.createNativeQuery("SELECT * FROM OrderDetail WHERE item_code=?1")
                .setParameter(1, itemCode).getResultList().size() > 0;
    }
}
