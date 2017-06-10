/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Bank.*;
import Bank.helper.AccountHelper;
import Bank.helper.LoanHelper;
import Bank.helper.TransactionHelper;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.Calendar;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author lenovo
 */
@ManagedBean
@SessionScoped
public class LoanBean {
     private String accountid;
     private String username;
     private String loantype;
     private String rates;
     private String showloan;
     private String warm;
     private BigDecimal payment;
     private BigDecimal loanPayment;
    /**
     * Creates a new instance of LoanBean
     */
    public LoanBean() {
    }
    
    public void submit(){
        FacesContext facesContext = FacesContext.getCurrentInstance();  
        LoginBean LoginBean = (LoginBean) facesContext.getApplication()  
      .getVariableResolver().resolveVariable(facesContext, "loginBean");  
        String nancy= LoginBean.getUsername();
        System.out.println(this.getLoantype());

        
        //贷款向自己账户加钱
        AccountHelper ah=new AccountHelper();
        List <Account> a=new ArrayList<Account>();
        a=ah.getdetail(nancy);
        Iterator it=a.iterator();
        Account account=(Account)it.next();
        BigDecimal bd=account.getBalacnce();
        bd=bd.add(this.getLoanPayment());
        account.setBalacnce(bd);
        ah.updatedetail(account);
        
        //如果贷款信息已存在则更新，不存在则创建、
        LoanHelper lh=new LoanHelper();
        int i=lh.getint(nancy);
        System.err.println(i);
        if(i==0){
            //贷款信息不存在则创建
            Loan loan=new Loan();
            Calendar calendar = Calendar.getInstance();
            loan.setLoantype(this.getLoantype());
            loan.setPayment(this.getLoanPayment());
            loan.setUsername(nancy);
            if(this.getLoantype().equals("委托贷款")){
                loan.setRates("0.0020");
            }
            else if(this.getLoantype().equals("信用贷款")){
                 loan.setRates("0.0015");
            }
            else{
                loan.setRates("0.0012");
            }
            lh.saveLoan(loan);
            //创建银行事件；
            Banktransaction b=new Banktransaction();
            java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
            b.setTransactiondate(currentDate); 
            b.setAccountid("1"+calendar.getTime().getTime());
            b.setAccountgetter(nancy);
            b.setAccountsender("Bank");
            b.setBanktransaction("贷款");
            b.setTransactiondmoney(this.getLoanPayment());
            TransactionHelper t=new TransactionHelper();
            t.savedetail(b);
        }
        else{
                //贷款信息存在则更新
                List <Loan> l=new ArrayList<Loan>();
                l=lh.getDetail(nancy);
                Iterator its=l.iterator();
                Loan loan=(Loan)its.next();
                loan.setPayment(loan.getPayment().add(this.getLoanPayment()));
                lh.updateLoan(loan);
                //创建银行事件；
                Calendar calendar = Calendar.getInstance();
                Banktransaction b=new Banktransaction();
                java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
                b.setTransactiondate(currentDate); 
                b.setAccountid("1"+calendar.getTime().getTime());
                b.setAccountgetter(nancy);
                b.setAccountsender("Bank");
                b.setBanktransaction("贷款");
                b.setTransactiondmoney(this.getLoanPayment());
                TransactionHelper t=new TransactionHelper();
                t.savedetail(b);
       }
    }
    //还款功能
    public void repay(){       
                FacesContext facesContext = FacesContext.getCurrentInstance();  
        LoginBean LoginBean = (LoginBean) facesContext.getApplication()  
      .getVariableResolver().resolveVariable(facesContext, "loginBean");  
        String nancy= LoginBean.getUsername();

        AccountHelper ah=new AccountHelper();
        LoanHelper lh=new LoanHelper();
        List <Account> a=new ArrayList<Account>();
        a=ah.getdetail(nancy);
        Iterator it=a.iterator();
        Account account=(Account)it.next();
        BigDecimal bd=account.getBalacnce();
        bd=bd.subtract(this.getLoanPayment());
        int r=bd.compareTo(BigDecimal.ZERO);
        if(r==1||r==0){
            //余额足够//贷款向自己账户减钱
             account.setBalacnce(bd);
             ah.updatedetail(account);
             
             //减去对应的贷款数目
             List<Loan> l=lh.getDetail(nancy);
             Iterator its= l.iterator();
             Loan loan=(Loan)its.next();
             BigDecimal bd1=loan.getPayment().subtract(this.getLoanPayment());
            int r1=bd1.compareTo(BigDecimal.ZERO);
            if(r1==1||r1==0){
                //还钱数小于等于贷款数目
                loan.setPayment(bd1);
                lh.updateLoan(loan);
                
                Calendar calendar = Calendar.getInstance();
                Banktransaction b=new Banktransaction();
                java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
                b.setTransactiondate(currentDate); 
                b.setAccountid("1"+calendar.getTime().getTime());
                b.setAccountgetter(nancy);
                b.setAccountsender("Bank");
                b.setBanktransaction("还款");
                b.setTransactiondmoney(this.getLoanPayment());
                TransactionHelper t=new TransactionHelper();
                t.savedetail(b);
                 this.setWarm("恭喜你，还款成功，贷款还剩"+bd1);
            }else{
                //还钱数大于等于贷款数目，把钱还回账户
                account.setBalacnce(bd.add(this.getLoanPayment()));
                ah.updatedetail(account);
                this.setWarm("您还款钱数大于贷款");
            }
        }else{
            //余额不足
             this.setWarm("您余额不足");
        }
        
        this.showLoan();
    }
    
    public String showLoan(){
        FacesContext facesContext = FacesContext.getCurrentInstance();  
        LoginBean LoginBean = (LoginBean) facesContext.getApplication()  
      .getVariableResolver().resolveVariable(facesContext, "loginBean");  
        String nancy= LoginBean.getUsername();
        
        LoanHelper lh=new LoanHelper();
        int i=lh.getint(nancy);
        System.err.println(i);
        if(i==0){
            //没有贷款
            this.setShowloan("0");
             return "repayment";
        }else{
            //存在贷款
            List <Loan> l=lh.getDetail(nancy);
            Iterator it=l.iterator();
            Loan loan=(Loan)it.next(); 
            this.setShowloan(""+loan.getPayment());
            return "repayment";
        }
    }

    public String getWarm() {
        return warm;
    }

    public void setWarm(String warm) {
        this.warm = warm;
    }

    public String getShowloan() {
        return showloan;
    }

    public void setShowloan(String showloan) {
        this.showloan = showloan;
    }
    
    public BigDecimal getLoanPayment() {
        return loanPayment;
    }

    public void setLoanPayment(BigDecimal loanPayment) {
        this.loanPayment = loanPayment;
    }

    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLoantype() {
        return loantype;
    }

    public void setLoantype(String loantype) {
        this.loantype = loantype;
    }

    public String getRates() {
        return rates;
    }

    public void setRates(String rates) {
        this.rates = rates;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }
    
    
    
}
