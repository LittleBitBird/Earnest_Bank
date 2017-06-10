
import Bank.Banktransaction;
import Bank.Register;
import Bank.Transaction;
import Bank.helper.RegisterHelper;
import Bank.helper.TransactionHelper;
import java.util.Iterator;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lenovo
 */
public class NewClass {
         public static void main(String[] args){
              TransactionHelper th= new TransactionHelper();
              List <Banktransaction> transaction2=th.getgetter("nancy");
              List <Banktransaction> transaction1=th.getsender("nancy");
         for(Iterator it=transaction2.iterator();it.hasNext();){
          Banktransaction tx=(Banktransaction)it.next();
            Transaction t= new Transaction();
            String getname,sendername;
            getname=tx.getAccountgetter();//收款人姓名
            sendername=tx.getAccountsender();//发款人姓名

            //收款人信息
            RegisterHelper rh=new RegisterHelper();
            Iterator it1=rh.getRegister(getname).iterator();
            Register r=(Register)it1.next();
            t.setBankaccountnumberGet(r.getBankaccountnumber());
            t.setCustomergetname(r.getCumstomername());
                                 
            //发款人信息
            Iterator it2=rh.getRegister(sendername).iterator();
            Register r1=(Register)it2.next();
            t.setBankaccountnumberSend(r1.getBankaccountnumber());
            t.setCustomersendname(r1.getCumstomername());
            
            t.setAccountid(tx.getAccountid());
            t.setBanktransaction(tx.getBanktransaction());
            t.setTransactiondate(tx.getTransactiondate());
            t.setTransactiondmoney(tx.getTransactiondmoney());
            
            System.out.println(t.toString());
         }
         for(Iterator it=transaction1.iterator();it.hasNext();){
          Banktransaction tx=(Banktransaction)it.next();
            Transaction t= new Transaction();
            String getname,sendername;
            getname=tx.getAccountgetter();//收款人姓名
            sendername=tx.getAccountsender();//发款人姓名

            //收款人信息
            RegisterHelper rh=new RegisterHelper();
            Iterator it1=rh.getRegister(getname).iterator();
            Register r=(Register)it1.next();
            t.setBankaccountnumberGet(r.getBankaccountnumber());
            t.setCustomergetname(r.getCumstomername());
                                 
            //发款人信息
            Iterator it2=rh.getRegister(sendername).iterator();
            Register r1=(Register)it2.next();
            t.setBankaccountnumberSend(r1.getBankaccountnumber());
            t.setCustomersendname(r1.getCumstomername());
            
            t.setAccountid(tx.getAccountid());
            t.setBanktransaction(tx.getBanktransaction());
            t.setTransactiondate(tx.getTransactiondate());
            t.setTransactiondmoney(tx.getTransactiondmoney());
            
            System.out.println(t.toString());}
}

}
