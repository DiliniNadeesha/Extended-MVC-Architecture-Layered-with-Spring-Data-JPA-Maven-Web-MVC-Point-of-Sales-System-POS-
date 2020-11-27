package lk.ijse.dep.pos.datajpa.business.custom.impl;
import lk.ijse.dep.pos.datajpa.business.custom.CustomerBO;
import lk.ijse.dep.pos.datajpa.dao.custom.CustomerDAO;
import lk.ijse.dep.pos.datajpa.dao.custom.OrderDAO;
import lk.ijse.dep.pos.datajpa.dto.CustomerDTO;
import lk.ijse.dep.pos.datajpa.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Component
public class CustomerBOImpl implements CustomerBO {

    @Autowired
    private CustomerDAO customerDAO;
    @Autowired
    private OrderDAO orderDAO;

    @Override
    public void saveCustomer(CustomerDTO customer)  {
//        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
//        customerDAO.setEntityManager(em);
//        em.getTransaction().begin();
        customerDAO.save(new Customer(customer.getId(), customer.getName(), customer.getAddress()));
//        em.getTransaction().commit();
//        em.close();
    }

    @Override
    public void updateCustomer(CustomerDTO customer)  {
//        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
//        customerDAO.setEntityManager(em);
//        em.getTransaction().begin();
        customerDAO.update(new Customer(customer.getId(), customer.getName(), customer.getAddress()));
//        em.getTransaction().commit();
//        em.close();
    }

    @Override
    public void deleteCustomer(String customerId)  {
//        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
//        customerDAO.setEntityManager(em);
//        orderDAO.setEntityManager(em);
//        em.getTransaction().begin();
        if (orderDAO.existsByCustomerId(customerId)) {
            throw new RuntimeException("Customer already exists in an order, hence unable to delete");
        }
        customerDAO.delete(customerId);
//        em.getTransaction().commit();
//        em.close();
    }

    @Transactional(readOnly = true)
    @Override
    public List<CustomerDTO> findAllCustomers()  {
//        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
//        customerDAO.setEntityManager(em);
//        em.getTransaction().begin();
        List<Customer> alCustomers = customerDAO.findAll();
        List<CustomerDTO> dtos = new ArrayList<>();
        for (Customer customer : alCustomers) {
            dtos.add(new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress()));
        }
//        em.getTransaction().commit();
//        em.close();
        return dtos;
    }

    @Transactional(readOnly = true)
    @Override
    public String getLastCustomerId()  {
//        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
//        customerDAO.setEntityManager(em);
//        em.getTransaction().begin();
        String lastCustomerId = customerDAO.getLastCustomerId();
//        em.getTransaction().commit();
//        em.close();
        return lastCustomerId;
    }

    @Transactional(readOnly = true)
    @Override
    public CustomerDTO findCustomer(String customerId)  {
//        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
//        customerDAO.setEntityManager(em);
//        em.getTransaction().begin();
        Customer customer = customerDAO.find(customerId);
//        em.getTransaction().commit();
//        em.close();
        return new CustomerDTO(customer.getId(),
                customer.getName(), customer.getAddress());
    }

    @Transactional(readOnly = true)
    @Override
    public List<String> getAllCustomerIDs()  {
//        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
//        customerDAO.setEntityManager(em);
//        em.getTransaction().begin();
        List<Customer> customers = customerDAO.findAll();
//        em.getTransaction().commit();
//        em.close();
        List<String> ids = new ArrayList<>();
        for (Customer customer : customers) {
            ids.add(customer.getId());
        }
        return ids;
    }
}
