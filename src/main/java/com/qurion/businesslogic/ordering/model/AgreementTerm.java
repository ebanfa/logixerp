/**
 *  Business Logic.
 */
package com.qurion.businesslogic.ordering.model;

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
import com.qurion.businesslogic.businessdata.model.TermType;
import com.qurion.businesslogic.ordering.model.AgreementItem;
import com.qurion.businesslogic.ordering.model.Agreement;

import java.util.Date;
import java.util.Date;

/**
 * AgreementTerm 
 * @author Edward Banfa
 */
@Entity
@Table(name="AGREEMENT_TERM"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class AgreementTerm  extends BaseEntity implements java.io.Serializable {
	private TermType termType;
	private AgreementItem agreementItem;
	private Agreement agreement;
	private Integer termValue;
	private Date fromDt;
	private Date toDt;

    public AgreementTerm() {
    }

    public AgreementTerm(Integer termValue, Date fromDt, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.termValue = termValue;
        this.fromDt = fromDt;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public AgreementTerm(TermType termType, AgreementItem agreementItem, Agreement agreement, Integer termValue, Date fromDt, Date toDt, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.termType = termType;
        this.agreementItem = agreementItem;
        this.agreement = agreement;
        this.termValue = termValue;
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
    @JoinColumn(name="TERM_TY_ID")
    @JsonIgnore
    public TermType getTermType() 
    {
        return this.termType;
    }
    
    public void setTermType(TermType termType)
    {
        this.termType = termType;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="AGREEMENT_ITEM_ID")
    @JsonIgnore
    public AgreementItem getAgreementItem() 
    {
        return this.agreementItem;
    }
    
    public void setAgreementItem(AgreementItem agreementItem)
    {
        this.agreementItem = agreementItem;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="AGREEMENT_ID")
    @JsonIgnore
    public Agreement getAgreement() 
    {
        return this.agreement;
    }
    
    public void setAgreement(Agreement agreement)
    {
        this.agreement = agreement;
    }
    
    @Column(name="TERM_VALUE", nullable=false)
    public Integer getTermValue() 
    {
        return this.termValue;
    }
    
    public void setTermValue(Integer termValue) 
    {
        this.termValue = termValue;
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
