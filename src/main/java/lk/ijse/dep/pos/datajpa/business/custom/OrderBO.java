package lk.ijse.dep.pos.datajpa.business.custom;

import lk.ijse.dep.pos.datajpa.business.SuperBO;
import lk.ijse.dep.pos.datajpa.dto.OrderDTO;
import lk.ijse.dep.pos.datajpa.dto.OrderDTO2;

import java.util.List;

public interface OrderBO extends SuperBO {

    int getLastOrderId();

    void placeOrder(OrderDTO orderDTO);

    List<OrderDTO2> getOrderInfo(String query);

}
