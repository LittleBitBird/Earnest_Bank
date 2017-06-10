/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bank;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author lenovo
 */
public class Transaction {
     private String accountid;
     private String customergetname;
     private String bankaccountnumberGet;
     private String customersendname;
     private String bankaccountnumberSend;
     private String banktransaction;
     private Date transactiondate;
     private BigDecimal transactiondmoney;
     
     public String toString(){
         return accountid+" "+customergetname+" "+bankaccountnumberGet+" "+customersendname+" "+bankaccountnumberSend+" "+banktransaction+" "+transactiondate+" "+transactiondmoney;
     }
     
    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid;
    }


    public String getCustomergetname() {
        return customergetname;
    }

    public void setCustomergetname(String customergetname) {
        this.customergetname = customergetname;
    }

    public String getBankaccountnumberGet() {
        return bankaccountnumberGet;
    }

    public void setBankaccountnumberGet(String bankaccountnumberGet) {
        this.bankaccountnumberGet = bankaccountnumberGet;
    }


    public String getCustomersendname() {
        return customersendname;
    }

    public void setCustomersendname(String customersendname) {
        this.customersendname = customersendname;
    }

    public String getBankaccountnumberSend() {
        return bankaccountnumberSend;
    }

    public void setBankaccountnumberSend(String bankaccountnumberSend) {
        this.bankaccountnumberSend = bankaccountnumberSend;
    }

    public String getBanktransaction() {
        return banktransaction;
    }

    public void setBanktransaction(String banktransaction) {
        this.banktransaction = banktransaction;
    }

    public Date getTransactiondate() {
        return transactiondate;
    }

    public void setTransactiondate(Date transactiondate) {
        this.transactiondate = transactiondate;
    }

    public BigDecimal getTransactiondmoney() {
        return transactiondmoney;
    }

    public void setTransactiondmoney(BigDecimal transactiondmoney) {
        this.transactiondmoney = transactiondmoney;
    }
     
     
}
