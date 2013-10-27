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
import com.qurion.businesslogic.ordering.model.AgreementType;
import com.qurion.businesslogic.party.model.PartyRelationship;

import java.util.Date;
import java.util.Date;
import java.util.Date;
import java.util.Set;

import com.qurion.businesslogic.ordering.model.AgreementTerm;

import java.util.Set;

import com.qurion.businesslogic.ordering.model.Addendum;

import java.util.Set;

import com.qurion.businesslogic.ordering.model.AgreementRole;

import java.util.Set;

import com.qurion.businesslogic.ordering.model.AgreementItem;

/**
 * Agreement 
 * @author Edward Banfa
 */
@Entity
@Table(name="AGREEMENT"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Agreement  extends BaseEntity implements java.io.Serializable {
	private AgreementType agreementType;
	private PartyRelationship partyRelationship;
	private String name;
	private String description;
	private String text;
	private Date agreementDt;
	private Date fromDt;
	private Date toDt;
	private Set<AgreementTerm> agreementTerms;
	private Set<Addendum> addendums;
	private Set<AgreementRole> agreementRoles;
	private Set<AgreementItem> agreementItems;

    public Agreement() {
    }

    public Agreement(String name, String description, Date agreementDt, Date fromDt, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.description = description;
        this.agreementDt = agreementDt;
        this.fromDt = fromDt;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public Agreement(AgreementType agreementType, PartyRelationship partyRelationship, String name, String description, String text, Date agreementDt, Date fromDt, Date toDt, Set agreementTerms, Set addendums, Set agreementRoles, Set agreementItems, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.agreementType = agreementType;
        this.partyRelationship = partyRelationship;
        this.name = name;
        this.description = description;
        this.text = text;
        this.agreementDt = agreementDt;
        this.fromDt = fromDt;
        this.toDt = toDt;
        this.agreementTerms = agreementTerms;
        this.addendums = addendums;
        this.agreementRoles = agreementRoles;
        this.agreementItems = agreementItems;
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
    @JoinColumn(name="AGREEMENT_TY_ID")
    @JsonIgnore
    public AgreementType getAgreementType() 
    {
        return this.agreementType;
    }
    
    public void setAgreementType(AgreementType agreementType)
    {
        this.agreementType = agreementType;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PARTY_REL_ID")
    @JsonIgnore
    public PartyRelationship getPartyRelationship() 
    {
        return this.partyRelationship;
    }
    
    public void setPartyRelationship(PartyRelationship partyRelationship)
    {
        this.partyRelationship = partyRelationship;
    }
    
    @Column(name="NAME", nullable=false, length=75)
    public String getName() 
    {
        return this.name;
    }
    
    public void setName(String name) 
    {
        this.name = name;
    }
	
    @Column(name="DESCRIPTION", nullable=false, length=150)
    public String getDescription() 
    {
        return this.description;
    }
    
    public void setDescription(String description) 
    {
        this.description = description;
    }
	
    @Column(name="TEXT")
    public String getText() 
    {
        return this.text;
    }
    
    public void setText(String text) 
    {
        this.text = text;
    }
	
    @Column(name="AGREEMENT_DT", nullable=false, length=10)
    public Date getAgreementDt() 
    {
        return this.agreementDt;
    }
    
    public void setAgreementDt(Date agreementDt) 
    {
        this.agreementDt = agreementDt;
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
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="agreement")
    @JsonIgnore
    public Set<AgreementTerm> getAgreementTerms() 
    {
        return this.agreementTerms;
    }
    
    public void setAgreementTerms(Set<AgreementTerm> agreementTerms) 
    {
        this.agreementTerms = agreementTerms;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="agreement")
    @JsonIgnore
    public Set<Addendum> getAddendums() 
    {
        return this.addendums;
    }
    
    public void setAddendums(Set<Addendum> addendums) 
    {
        this.addendums = addendums;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="agreement")
    @JsonIgnore
    public Set<AgreementRole> getAgreementRoles() 
    {
        return this.agreementRoles;
    }
    
    public void setAgreementRoles(Set<AgreementRole> agreementRoles) 
    {
        this.agreementRoles = agreementRoles;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="agreement")
    @JsonIgnore
    public Set<AgreementItem> getAgreementItems() 
    {
        return this.agreementItems;
    }
    
    public void setAgreementItems(Set<AgreementItem> agreementItems) 
    {
        this.agreementItems = agreementItems;
    }			
		
    

}
