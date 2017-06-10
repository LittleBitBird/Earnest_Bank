/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bank.helper;

import Bank.Account;
import Bank.NewHibernateUtil;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author lenovo
 */
public class AccountHelper {
        Session session;
        
        public AccountHelper(){
            session=NewHibernateUtil.getSessionFactory().openSession();
        }
        
       public  List <Account>  getdetail(String username){
           org.hibernate.Transaction tx=session.beginTransaction();
           Criteria ct= session.createCriteria(Account.class).add(Restrictions.eq("username",username));
           List <Account> account= new ArrayList<Account>();
           account=ct.list();
           tx.commit();
           return account;
       }
       
       public  void savedetail(Account account){
           org.hibernate.Transaction tx=session.beginTransaction();
           session.save(account);
           tx.commit();
       }
       
       public  void updatedetail(Account account){
           org.hibernate.Transaction tx=session.beginTransaction();
           session.update(account);
           tx.commit();
       }
}
