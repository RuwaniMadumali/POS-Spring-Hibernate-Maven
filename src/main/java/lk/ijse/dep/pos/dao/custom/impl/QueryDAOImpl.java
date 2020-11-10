package lk.ijse.dep.pos.dao.custom.impl;

import lk.ijse.dep.pos.dao.custom.QueryDAO;
import lk.ijse.dep.pos.entity.CustomEntity;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;

@Component
public class QueryDAOImpl implements QueryDAO {
private Session session;
    @Override
    public CustomEntity getOrderDetail(String orderId) throws Exception {

        return (CustomEntity) session.createNativeQuery("SELECT o.id AS orderId,c.name AS " +
                "customerName,o.date AS OrderDate FROM `Order o INNER JOIN Customer c ON o.customerId = c.id WHERE o.id=?1`").
                setParameter(1,orderId).setResultTransformer(Transformers.aliasToBean(CustomEntity.class)).
                uniqueResult();

    }

    @Override
    public CustomEntity getOrderDetail2(String orderId) throws Exception {
        return (CustomEntity)session.createQuery("SELECT NEW entity.CustomEntity(C.id,C.name,O.id)" +
                "FROM Order O INNER JOIN O.customer C WHERE O.id=:id ").setParameter("id",orderId).
                uniqueResult();


    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }
}
