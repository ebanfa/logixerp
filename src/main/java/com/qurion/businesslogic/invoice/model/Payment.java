/**
 *  Business Logic.
 */
package com.qurion.businesslogic.invoice.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.qurion.businesslogic.invoice.model.FinancialAccount;
import com.qurion.businesslogic.invoice.model.PaymentType;
import com.qurion.businesslogic.invoice.model.PaymentMethodType;

import java.math.BigDecimal;
import java.util.Set;

import com.qurion.businesslogic.hr.model.PerformanceReview;

import java.util.Set;

import com.qurion.businesslogic.hr.model.Deduction;

import java.util.Set;

import com.qurion.businesslogic.invoice.model.PaymentBudgetApplication;

import java.util.Set;

import com.qurion.businesslogic.invoice.model.PaymentApplication;

import java.util.Set;

import com.qurion.businesslogic.accounting.model.AccountingTransaction;
import com.qurion.businesslogic.application.model.BaseEntity;

/**
 * Payment 
 * @author Edward Banfa
 */
@Entity
@Table(name="PAYMENT"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Payment  extends BaseEntity implements java.io.Serializable {
	private FinancialAccount financialAccount;
	private PaymentType paymentType;
	private PaymentMethodType paymentMethodType;
	private String description;
	private BigDecimal amount;
	private Set<PerformanceReview> performanceReviews;
	private Set<Deduction> deductions;
	private Set<PaymentBudgetApplication> paymentBudgetApplications;
	private Set<PaymentApplication> paymentApplications;
	private Set<AccountingTransaction> accountingTransactions;

    public Payment() {
    }

    public Payment(BigDecimal amount, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.amount = amount;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public Payment(FinancialAccount financialAccount, PaymentType paymentType, PaymentMethodType paymentMethodType, String description, BigDecimal amount, Set performanceReviews, Set deductions, Set paymentBudgetApplications, Set paymentApplications, Set accountingTransactions, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.financialAccount = financialAccount;
        this.paymentType = paymentType;
        this.paymentMethodType = paymentMethodType;
        this.description = description;
        this.amount = amount;
        this.performanceReviews = performanceReviews;
        this.deductions = deductions;
        this.paymentBudgetApplications = paymentBudgetApplications;
        this.paymentApplications = paymentApplications;
        this.accountingTransactions = accountingTransactions;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.versionNo = versionNo;
        this.rowTs = rowTs;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
        this.lastModifiedDt = lastModifiedDt;
        this.lastModifiedUsr = lastModifiedUsr;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FIN_ACCT_ID")
    @JsonIgnore
    public FinancialAccount getFinancialAccount() 
    {
        return this.financialAccount;
    }
    
    public void setFinancialAccount(FinancialAccount financialAccount)
    {
        this.financialAccount = financialAccount;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PAYMENT_TY_ID")
    @JsonIgnore
    public PaymentType getPaymentType() 
    {
        return this.paymentType;
    }
    
    public void setPaymentType(PaymentType paymentType)
    {
        this.paymentType = paymentType;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="METHOD_TY_ID")
    @JsonIgnore
    public PaymentMethodType getPaymentMethodType() 
    {
        return this.paymentMethodType;
    }
    
    public void setPaymentMethodType(PaymentMethodType paymentMethodType)
    {
        this.paymentMethodType = paymentMethodType;
    }
    
    @Column(name="DESCRIPTION", length=150)
    public String getDescription() 
    {
        return this.description;
    }
    
    public void setDescription(String description) 
    {
        this.description = description;
    }
	
    @Column(name="AMOUNT", nullable=false)
    public BigDecimal getAmount() 
    {
        return this.amount;
    }
    
    public void setAmount(BigDecimal amount) 
    {
        this.amount = amount;
    }
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="payment")
    @JsonIgnore
    public Set<PerformanceReview> getPerformanceReviews() 
    {
        return this.performanceReviews;
    }
    
    public void setPerformanceReviews(Set<PerformanceReview> performanceReviews) 
    {
        this.performanceReviews = performanceReviews;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="payment")
    @JsonIgnore
    public Set<Deduction> getDeductions() 
    {
        return this.deductions;
    }
    
    public void setDeductions(Set<Deduction> deductions) 
    {
        this.deductions = deductions;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="payment")
    @JsonIgnore
    public Set<PaymentBudgetApplication> getPaymentBudgetApplications() 
    {
        return this.paymentBudgetApplications;
    }
    
    public void setPaymentBudgetApplications(Set<PaymentBudgetApplication> paymentBudgetApplications) 
    {
        this.paymentBudgetApplications = paymentBudgetApplications;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="payment")
    @JsonIgnore
    public Set<PaymentApplication> getPaymentApplications() 
    {
        return this.paymentApplications;
    }
    
    public void setPaymentApplications(Set<PaymentApplication> paymentApplications) 
    {
        this.paymentApplications = paymentApplications;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="payment")
    @JsonIgnore
    public Set<AccountingTransaction> getAccountingTransactions() 
    {
        return this.accountingTransactions;
    }
    
    public void setAccountingTransactions(Set<AccountingTransaction> accountingTransactions) 
    {
        this.accountingTransactions = accountingTransactions;
    }			
		
    

}
