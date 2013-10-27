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
import com.qurion.businesslogic.party.model.PartyRelationship;

import java.util.Date;

/**
 * UnemploymentClaim 
 * @author Edward Banfa
 */
@Entity
@Table(name="UNEMPLOYMENT_CLAIM"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class UnemploymentClaim  extends BaseEntity implements java.io.Serializable {
	private PartyRelationship partyRelationship;
	private String description;
	private Date claimTyDt;

    public UnemploymentClaim() {
    }

    public UnemploymentClaim(Date claimTyDt, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.claimTyDt = claimTyDt;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public UnemploymentClaim(PartyRelationship partyRelationship, String description, Date claimTyDt, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.partyRelationship = partyRelationship;
        this.description = description;
        this.claimTyDt = claimTyDt;
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
    @JoinColumn(name="FROM_REL_ID")
    @JsonIgnore
    public PartyRelationship getPartyRelationship() 
    {
        return this.partyRelationship;
    }
    
    public void setPartyRelationship(PartyRelationship partyRelationship)
    {
        this.partyRelationship = partyRelationship;
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
	
    @Column(name="CLAIM_TY_DT", nullable=false, length=10)
    public Date getClaimTyDt() 
    {
        return this.claimTyDt;
    }
    
    public void setClaimTyDt(Date claimTyDt) 
    {
        this.claimTyDt = claimTyDt;
    }
	
    

}
