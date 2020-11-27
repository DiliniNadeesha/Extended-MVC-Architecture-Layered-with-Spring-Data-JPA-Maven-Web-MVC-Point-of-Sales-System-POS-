package lk.ijse.dep.pos.datajpa.business.custom.impl;

import lk.ijse.dep.pos.datajpa.business.custom.ItemBO;
import lk.ijse.dep.pos.datajpa.dao.custom.ItemDAO;
import lk.ijse.dep.pos.datajpa.dao.custom.OrderDetailDAO;
import lk.ijse.dep.pos.datajpa.dto.ItemDTO;
import lk.ijse.dep.pos.datajpa.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Component
public class ItemBOImpl implements ItemBO {

    @Autowired
    private OrderDetailDAO orderDetailDAO;
    @Autowired
    private ItemDAO itemDAO;

    @Override
    public void saveItem(ItemDTO item)  {
//        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
//        itemDAO.setEntityManager(em);
//        em.getTransaction().begin();
        itemDAO.save(new Item(item.getCode(),
                item.getDescription(), item.getUnitPrice(), item.getQtyOnHand()));
//        em.getTransaction().commit();
//        em.close();
    }

    @Override
    public void updateItem(ItemDTO item)  {
//        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
//        itemDAO.setEntityManager(em);
//        em.getTransaction().begin();
        itemDAO.update(new Item(item.getCode(),
                item.getDescription(), item.getUnitPrice(), item.getQtyOnHand()));
//        em.getTransaction().commit();
//        em.close();
    }

    @Override
    public void deleteItem(String itemCode)  {
//        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
//        itemDAO.setEntityManager(em);
//        orderDetailDAO.setEntityManager(em);
//        em.getTransaction().begin();
        if (orderDetailDAO.existsByItemCode(itemCode)) {
            throw new RuntimeException("Item already exists in an order, hence unable to delete");
        }
        itemDAO.delete(itemCode);
//        em.getTransaction().commit();
//        em.close();
    }

    @Transactional(readOnly = true)
    @Override
    public List<ItemDTO> findAllItems()  {
//        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
//        itemDAO.setEntityManager(em);
//        em.getTransaction().begin();
        List<Item> allItems = itemDAO.findAll();
//        em.getTransaction().commit();
//        em.close();
        List<ItemDTO> dtos = new ArrayList<>();
        for (Item item : allItems) {
            dtos.add(new ItemDTO(item.getCode(),
                    item.getDescription(),
                    item.getQtyOnHand(),
                    item.getUnitPrice()));
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    @Override
    public String getLastItemCode()  {
//        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
//        itemDAO.setEntityManager(em);
//        em.getTransaction().begin();
        String lastItemCode = itemDAO.getLastItemCode();
//        em.getTransaction().commit();
//        em.close();
        return lastItemCode;
    }

    @Transactional(readOnly = true)
    @Override
    public ItemDTO findItem(String itemCode)  {
//        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
//        itemDAO.setEntityManager(em);
//        em.getTransaction().begin();
        Item item = itemDAO.find(itemCode);
//        em.getTransaction().commit();
//        em.close();
        return new ItemDTO(item.getCode(),
                item.getDescription(),
                item.getQtyOnHand(),
                item.getUnitPrice());
    }

    @Transactional(readOnly = true)
    @Override
    public List<String> getAllItemCodes()  {
//        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
//        itemDAO.setEntityManager(em);
//        em.getTransaction().begin();
        List<Item> allItems = itemDAO.findAll();
//        em.getTransaction().commit();
//        em.close();
        List<String> codes = new ArrayList<>();
        for (Item item : allItems) {
            codes.add(item.getCode());
        }
        return codes;
    }
}
