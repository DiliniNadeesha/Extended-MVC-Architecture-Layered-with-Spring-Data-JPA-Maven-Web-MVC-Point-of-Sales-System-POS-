package lk.ijse.dep.pos.datajpa.dao.custom;

import lk.ijse.dep.pos.datajpa.dao.CrudDAO;
import lk.ijse.dep.pos.datajpa.entity.Customer;

public interface CustomerDAO extends CrudDAO<Customer, String> {

    String getLastCustomerId();

}
