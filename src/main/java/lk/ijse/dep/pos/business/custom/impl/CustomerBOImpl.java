package lk.ijse.dep.pos.business.custom.impl;

import lk.ijse.dep.pos.business.custom.CustomerBO;
import lk.ijse.dep.pos.dao.custom.CustomerDAO;
import lk.ijse.dep.pos.db.HibernateUtil;
import lk.ijse.dep.pos.entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import lk.ijse.dep.pos.util.CustomerTM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class CustomerBOImpl implements CustomerBO {

    @Autowired
    private CustomerDAO customerDAO;

    public List<CustomerTM> getAllCustomers() throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        customerDAO.setSession(session);
        Transaction tx = null;
        List<Customer> allCustomers = null;
        try {
            tx = session.beginTransaction();
            allCustomers = customerDAO.findAll();
            tx.commit();
        } catch (Throwable t) {
            tx.rollback();
            throw t;
        }finally {
            session.close();
        }
        List<CustomerTM>customers = new ArrayList<>();
        for (Customer customer:allCustomers) {
            customers.add(new CustomerTM(customer.getId(),customer.getName(),customer.getAddress()));

        }
        return customers;
    }

    public void saveCustomer(String id, String name, String address) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        customerDAO.setSession(session);
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            customerDAO.save(new Customer(id,name,address));
            tx.commit();
        } catch (Throwable t) {
            tx.rollback();
            throw t;
        }finally {
            session.close();
        }
    }

    public void deleteCustomer(String customerId) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        customerDAO.setSession(session);
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            customerDAO.delete(customerId);
            tx.commit();
        } catch (Throwable t) {
            tx.rollback();
            throw t;
        }finally {
            session.close();
        }
    }

    public void updateCustomer(String name, String address, String customerId) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        customerDAO.setSession(session);
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            customerDAO.update(new Customer(customerId,name,address));
            tx.commit();
        } catch (Throwable t) {
            tx.rollback();
            throw t;
        }finally {
            session.close();
        }


    }

    public String getNewCustomerId() throws Exception {

        Session session = HibernateUtil.getSessionFactory().openSession();
        customerDAO.setSession(session);
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String lastCustomerId = customerDAO.getLastCustomerId();
            tx.commit();

            if (lastCustomerId == null) {
                return "C001";
            } else {
                int maxId = Integer.parseInt(lastCustomerId.replace("C", ""));
                maxId = maxId + 1;
                String id = "";
                if (maxId < 10) {
                    id = "C00" + maxId;
                } else if (maxId < 100) {
                    id = "C0" + maxId;
                } else {
                    id = "C" + maxId;
                }
                return id;
            }
        } catch (Throwable t) {
            tx.rollback();
            throw t;
        } finally {
            session.close();
        }

    }
}
