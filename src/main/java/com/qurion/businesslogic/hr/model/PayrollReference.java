/**
 *  Business Logic.
 */
package com.qurion.businesslogic.hr.model;

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

import com.qurion.businesslogic.application.model.BaseEntity;
import com.qurion.businesslogic.party.model.Party;
import com.qurion.businesslogic.hr.model.DeductionType;
import com.qurion.businesslogic.party.model.Party;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Date;

/**
 * PayrollReference 
 * @author Edward Banfa
 */
@Entity
@Table(name="PAYROLL_REFERENCE"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PayrollReference  extends BaseEntity implements java.io.Serializable {
	private Party partyByEmployeeId;
	private DeductionType deductionType;
	private Party partyByOrganizationId;
	private Integer percentage;
	private BigDecimal flatAmount;
	private String routingNo;
	private String accountNo;
	private String bankNm;
	private Date fromDt;
	private Date toDt;

    public PayrollReference() {
    }

    public PayrollReference(Date fromDt, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.fromDt = fromDt;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public PayrollReference(Party partyByEmployeeId, DeductionType deductionType, Party partyByOrganizationId, Integer percentage, BigDecimal flatAmount, String routingNo, String accountNo, String bankNm, Date fromDt, Date toDt, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.partyByEmployeeId = partyByEmployeeId;
        this.deductionType = deductionType;
        this.partyByOrganizationId = partyByOrganizationId;
        this.percentage = percentage;
        this.flatAmount = flatAmount;
        this.routingNo = routingNo;
        this.accountNo = accountNo;
        this.bankNm = bankNm;
        this.fromDt = fromDt;
        this.toDt = toDt;
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
    @JoinColumn(name="EMPLOYEE_ID")
    @JsonIgnore
    public Party getPartyByEmployeeId() 
    {
        return this.partyByEmployeeId;
    }
    
    public void setPartyByEmployeeId(Party partyByEmployeeId)
    {
        this.partyByEmployeeId = partyByEmployeeId;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="DEDUCTION_TY_ID")
    @JsonIgnore
    public DeductionType getDeductionType() 
    {
        return this.deductionType;
    }
    
    public void setDeductionType(DeductionType deductionType)
    {
        this.deductionType = deductionType;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ORGANIZATION_ID")
    @JsonIgnore
    public Party getPartyByOrganizationId() 
    {
        return this.partyByOrganizationId;
    }
    
    public void setPartyByOrganizationId(Party partyByOrganizationId)
    {
        this.partyByOrganizationId = partyByOrganizationId;
    }
    
    @Column(name="PERCENTAGE")
    public Integer getPercentage() 
    {
        return this.percentage;
    }
    
    public void setPercentage(Integer percentage) 
    {
        this.percentage = percentage;
    }
	
    @Column(name="FLAT_AMOUNT")
    public BigDecimal getFlatAmount() 
    {
        return this.flatAmount;
    }
    
    public void setFlatAmount(BigDecimal flatAmount) 
    {
        this.flatAmount = flatAmount;
    }
	
    @Column(name="ROUTING_NO", length=35)
    public String getRoutingNo() 
    {
        return this.routingNo;
    }
    
    public void setRoutingNo(String routingNo) 
    {
        this.routingNo = routingNo;
    }
	
    @Column(name="ACCOUNT_NO", length=35)
    public String getAccountNo() 
    {
        return this.accountNo;
    }
    
    public void setAccountNo(String accountNo) 
    {
        this.accountNo = accountNo;
    }
	
    @Column(name="BANK_NM", length=75)
    public String getBankNm() 
    {
        return this.bankNm;
    }
    
    public void setBankNm(String bankNm) 
    {
        this.bankNm = bankNm;
    }
	
    @Column(name="FROM_DT", nullable=false, length=10)
    public Date getFromDt() 
    {
        return this.fromDt;
    }
    
    public void setFromDt(Date fromDt) 
    {
        this.fromDt = fromDt;
    }
	
    @Column(name="TO_DT", length=10)
    public Date getToDt() 
    {
        return this.toDt;
    }
    
    public void setToDt(Date toDt) 
    {
        this.toDt = toDt;
    }
	
    

}
