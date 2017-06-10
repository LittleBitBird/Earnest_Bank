/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bank.helper;

import Bank.Banktransaction;
import Bank.NewHibernateUtil;
import Bank.Register;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author lenovo
 */
public class RegisterHelper {
    Session session;
        
        public RegisterHelper(){
            session=NewHibernateUtil.getSessionFactory().openSession();
        }
        
        public  List <Register> getRegister(String username){
           org.hibernate.Transaction tx=session.beginTransaction();
           Criteria ct= session.createCriteria(Register.class).add(Restrictions.eq("username",username));
           List <Register> regist= new ArrayList<Register>();
           regist=ct.list();
           tx.commit();
           return regist;
        }
        
         public  int saveRegister(Register register,String username){
           org.hibernate.Transaction tx=session.beginTransaction();
           Criteria ct= session.createCriteria(Register.class).add(Restrictions.eq("username",username));
           List <Register> regist= new ArrayList<Register>();
           regist=ct.list();
           if(regist.size()==0){
          session.save(register);
           tx.commit();
           return 1;
           }
           else {
           return 0;
           }
        }
}
