package lk.ijse.dep.pos.datajpa.dao.custom.impl;

import lk.ijse.dep.pos.datajpa.dao.custom.QueryDAO;
import lk.ijse.dep.pos.datajpa.entity.CustomEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class QueryDAOImpl implements QueryDAO {

    @PersistenceContext
    private EntityManager entityManager;

//    @Override
//    public void setEntityManager(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }

    @Override
    public CustomEntity getOrderInfo(int orderId)  {
        return null;
//        ResultSet rst = CrudUtil.execute("SELECT C.customerId, C.name, O.date  FROM Customer C INNER JOIN `Order` O ON C.customerId=O.customerId WHERE O.id=?", orderId);
//        if (rst.next()){
//            return new CustomEntity(orderId,
//                    rst.getString(1),
//                    rst.getString(2),
//                    rst.getDate(3));
//        }else{
//            return null;
//        }
    }

    @Override
    public CustomEntity getOrderInfo2(int orderId)  {
        return null;
//        ResultSet rst = CrudUtil.execute("SELECT O.id, C.customerId, C.name, O.date, SUM(OD.qty * OD.unitPrice) AS Total  FROM Customer C INNER JOIN `Order` O ON C.customerId=O.customerId\" +\n" +
//                "                \" INNER JOIN OrderDetail OD on O.id = OD.orderId WHERE O.id=? GROUP BY orderId", orderId);
//        if (rst.next()){
//            return new CustomEntity(rst.getInt(1),
//                    rst.getString(2),
//                    rst.getString(3),
//                    rst.getDate(4),
//                    rst.getDouble(5));
//        }else{
//            return null;
//        }
    }

    @Override
    public List<CustomEntity> getOrdersInfo(String query)  {
        List<Object[]> resultList = entityManager.createNativeQuery("SELECT o.id as orderId, c.id as customerId, c.name as customerName, o.date as orderDate,\n" +
                "SUM(od.qty * od.unit_Price) AS orderTotal " +
                "FROM customer C INNER JOIN `Order` O ON C.id = O.customer_id INNER JOIN orderdetail OD on O.id = OD.order_id where o.id LIKE ?1 OR c.id LIKE ?1" +
                " OR c.name LIKE ?1 GROUP BY O.id")
                .setParameter(1, query)
                .getResultList();
        List<CustomEntity> al = new ArrayList<>();
        for (Object[] cols : resultList) {
            al.add(new CustomEntity((int) cols[0], (String) cols[1], (String) cols[2],
                    (Date) cols[3], (Double) cols[4]));
        }
        return al;
    }
}
