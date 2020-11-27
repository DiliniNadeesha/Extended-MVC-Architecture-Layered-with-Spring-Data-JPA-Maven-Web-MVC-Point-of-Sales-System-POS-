package lk.ijse.dep.pos.datajpa.dao.custom;


import lk.ijse.dep.pos.datajpa.dao.CrudDAO;
import lk.ijse.dep.pos.datajpa.entity.Item;

public interface ItemDAO extends CrudDAO<Item, String> {

    String getLastItemCode() ;
}
