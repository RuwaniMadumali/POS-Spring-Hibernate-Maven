package lk.ijse.dep.pos.dao.custom.impl;

import lk.ijse.dep.pos.dao.CrudDAOImpl;
import lk.ijse.dep.pos.dao.custom.ItemDAO;
import lk.ijse.dep.pos.entity.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemDAOImpl extends CrudDAOImpl<Item,String>implements ItemDAO {


    public String getLastItemCode() throws Exception {
      //HQL
//        return (String) session.createQuery("SELECT i.code FROM entity.Item i ORDER BY i.code DESC").
//                setMaxResults(1).list().get(0);
        return (String)session.createNativeQuery("SELECT code FROM Item ORDER BY code DESC LIMIT 1 ").uniqueResult();
    }


}
