/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bank.helper;

import Bank.Account;
import Bank.Loan;
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
public class LoanHelper {
    Session session;
        
        public LoanHelper(){
            session=NewHibernateUtil.getSessionFactory().openSession();
        }
        
        public  int getint(String username){
             org.hibernate.Transaction tx=session.beginTransaction();
           Criteria ct= session.createCriteria(Loan.class).add(Restrictions.eq("username",username));
           List <Loan> loan= new ArrayList<Loan>();
           loan=ct.list();
           tx.commit();
           if(loan.size()==0){
               return 0;
           }
           else{
               return 1;
           }
        }
        
         public List <Loan> getDetail(String username){
           org.hibernate.Transaction tx=session.beginTransaction();
           Criteria ct= session.createCriteria(Loan.class).add(Restrictions.eq("username",username));
           List <Loan> loan= new ArrayList<Loan>();
           loan=ct.list();
           tx.commit();
           return loan;
        }
        
        public void saveLoan(Loan loan){
           org.hibernate.Transaction tx=session.beginTransaction();
           session.save(loan);
           tx.commit();
        }
        
          public void updateLoan(Loan loan){
           org.hibernate.Transaction tx=session.beginTransaction();
           session.update(loan);
           tx.commit();
        }
}
