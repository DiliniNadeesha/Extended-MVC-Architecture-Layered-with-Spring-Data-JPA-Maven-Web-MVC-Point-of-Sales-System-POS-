package lk.ijse.dep.pos.datajpa.business.custom;

import lk.ijse.dep.pos.datajpa.business.SuperBO;
import lk.ijse.dep.pos.datajpa.dto.CustomerDTO;

import java.util.List;

public interface CustomerBO extends SuperBO {

    void saveCustomer(CustomerDTO customer);

    void updateCustomer(CustomerDTO customer);

    void deleteCustomer(String customerId) ;

    List<CustomerDTO> findAllCustomers() ;

    String getLastCustomerId();

    CustomerDTO findCustomer(String customerId) ;

    List<String> getAllCustomerIDs() ;

}
