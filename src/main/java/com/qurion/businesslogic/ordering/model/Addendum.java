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
import com.qurion.businesslogic.ordering.model.AgreementItem;
import com.qurion.businesslogic.ordering.model.Agreement;

/**
 * Addendum 
 * @author Edward Banfa
 */
@Entity
@Table(name="ADDENDUM"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Addendum  extends BaseEntity implements java.io.Serializable {
	private AgreementItem agreementItem;
	private Agreement agreement;
	private String addendumText;

    public Addendum() {
    }

    public Addendum(String addendumText, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.addendumText = addendumText;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public Addendum(AgreementItem agreementItem, Agreement agreement, String addendumText, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.agreementItem = agreementItem;
        this.agreement = agreement;
        this.addendumText = addendumText;
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
    
    @Column(name="ADDENDUM_TEXT", nullable=false)
    public String getAddendumText() 
    {
        return this.addendumText;
    }
    
    public void setAddendumText(String addendumText) 
    {
        this.addendumText = addendumText;
    }
	
    

}
