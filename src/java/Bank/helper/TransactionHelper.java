/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bank.helper;

import Bank.Account;
import Bank.Banktransaction;
import Bank.NewHibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author lenovo
 */
public class TransactionHelper {
    Session session;
        
        public TransactionHelper(){
            session=NewHibernateUtil.getSessionFactory().openSession();           
        }
        
        public  List <Banktransaction> getsender(String sender){
           org.hibernate.Transaction tx=session.beginTransaction();
           Criteria ct= session.createCriteria(Banktransaction.class).add(Restrictions.eq("accountsender",sender));
           List <Banktransaction> transaction1= new ArrayList<Banktransaction>();
           transaction1=ct.list();
           tx.commit();
           return transaction1;
        }
        
        public  List <Banktransaction> getgetter(String getter){
           org.hibernate.Transaction tx=session.beginTransaction();
           Criteria ct= session.createCriteria(Banktransaction.class).add(Restrictions.eq("accountgetter",getter));
           List <Banktransaction> transaction2= new ArrayList<Banktransaction>();
           transaction2=ct.list();
           tx.commit();
           return transaction2;
        }
        
        public void savedetail(Banktransaction b){
            org.hibernate.Transaction tx=session.beginTransaction();
            session.save(b);
            tx.commit();
        }
}
