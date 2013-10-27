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

import com.qurion.businesslogic.invoice.model.Payment;
import com.qurion.businesslogic.accounting.model.Budget;
import com.qurion.businesslogic.application.model.BaseEntity;

/**
 * PaymentBudgetApplication 
 * @author Edward Banfa
 */
@Entity
@Table(name="PAYMENT_BUDGET_APPLICATION"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PaymentBudgetApplication  extends BaseEntity implements java.io.Serializable {
	private Payment payment;
	private Budget budget;
	private Integer amount;

    public PaymentBudgetApplication() {
    }

    public PaymentBudgetApplication(Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public PaymentBudgetApplication(Payment payment, Budget budget, Integer amount, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.payment = payment;
        this.budget = budget;
        this.amount = amount;
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
    @JoinColumn(name="PAYMENT_ID")
    @JsonIgnore
    public Payment getPayment() 
    {
        return this.payment;
    }
    
    public void setPayment(Payment payment)
    {
        this.payment = payment;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="BUDGET_ID")
    @JsonIgnore
    public Budget getBudget() 
    {
        return this.budget;
    }
    
    public void setBudget(Budget budget)
    {
        this.budget = budget;
    }
    
    @Column(name="AMOUNT")
    public Integer getAmount() 
    {
        return this.amount;
    }
    
    public void setAmount(Integer amount) 
    {
        this.amount = amount;
    }
	
    

}
