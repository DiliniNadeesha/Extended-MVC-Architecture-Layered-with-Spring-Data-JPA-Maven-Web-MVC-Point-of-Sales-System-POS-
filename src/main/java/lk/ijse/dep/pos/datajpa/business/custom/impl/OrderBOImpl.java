package lk.ijse.dep.pos.datajpa.business.custom.impl;
import lk.ijse.dep.pos.datajpa.business.custom.OrderBO;
import lk.ijse.dep.pos.datajpa.dao.custom.*;
import lk.ijse.dep.pos.datajpa.dto.OrderDTO;
import lk.ijse.dep.pos.datajpa.dto.OrderDTO2;
import lk.ijse.dep.pos.datajpa.dto.OrderDetailDTO;
import lk.ijse.dep.pos.datajpa.entity.CustomEntity;
import lk.ijse.dep.pos.datajpa.entity.Item;
import lk.ijse.dep.pos.datajpa.entity.Order;
import lk.ijse.dep.pos.datajpa.entity.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional(readOnly = true)
@Component
public class OrderBOImpl implements OrderBO {

    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private OrderDetailDAO orderDetailDAO;
    @Autowired
    private ItemDAO itemDAO;
    @Autowired
    private QueryDAO queryDAO;
    @Autowired
    private CustomerDAO customerDAO;

    @Transactional(readOnly = true)
    @Override
    public int getLastOrderId()  {
//        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
//        orderDAO.setEntityManager(em);
//        em.getTransaction().begin();
        int lastOrderId = orderDAO.getLastOrderId();
//        em.getTransaction().commit();
//        em.close();
        return lastOrderId;
    }

    @Transactional(readOnly = true)
    @Override
    public void placeOrder(OrderDTO order)  {
//        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
//        orderDAO.setEntityManager(em);
//        itemDAO.setEntityManager(em);
//        orderDetailDAO.setEntityManager(em);
//        em.getTransaction().begin();
        int oId = order.getId();
        orderDAO.save(new Order(oId, new java.sql.Date(new Date().getTime()),
                customerDAO.find(order.getCustomerId())));
        for (OrderDetailDTO orderDetail : order.getOrderDetails()) {
            orderDetailDAO.save(new OrderDetail(oId, orderDetail.getCode(),
                    orderDetail.getQty(), orderDetail.getUnitPrice()));
            Item item = itemDAO.find(orderDetail.getCode());
            item.setQtyOnHand(item.getQtyOnHand() - orderDetail.getQty());
            itemDAO.update(item);
        }
//        em.getTransaction().commit();
//        em.close();
    }

    @Transactional(readOnly = true)
    @Override
    public List<OrderDTO2> getOrderInfo(String query)  {
//        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
//        queryDAO.setEntityManager(em);
//        em.getTransaction().begin();

        List<CustomEntity> ordersInfo = queryDAO.getOrdersInfo(query + "%");
//        em.getTransaction().commit();
//        em.close();

        List<OrderDTO2> dtos = new ArrayList<>();
        for (CustomEntity info : ordersInfo) {
            dtos.add(new OrderDTO2(info.getOrderId(),
                    new java.sql.Date(info.getOrderDate().getTime()), info.getCustomerId(), info.getCustomerName(), info.getOrderTotal()));
        }
        return dtos;
    }
}
