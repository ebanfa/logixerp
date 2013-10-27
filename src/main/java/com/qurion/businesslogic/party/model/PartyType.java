/**
 *  Business Logic.
 */
package com.qurion.businesslogic.party.model;

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
import com.qurion.businesslogic.party.model.PartyType;

import java.util.Set;

import com.qurion.businesslogic.party.model.PartyClassification;

import java.util.Set;

import com.qurion.businesslogic.party.model.MarketInterest;

import java.util.Set;

import com.qurion.businesslogic.party.model.PartyType;

import java.util.Set;

import com.qurion.businesslogic.product.model.ProductPriceComponent;

/**
 * PartyType 
 * @author Edward Banfa
 */
@Entity
@Table(name="PARTY_TYPE"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PartyType  extends BaseEntity implements java.io.Serializable {
	private PartyType partyType;
	private String name;
	private String description;
	private Set<PartyClassification> partyClassifications;
	private Set<MarketInterest> marketInterests;
	private Set<PartyType> partyTypes;
	private Set<ProductPriceComponent> productPriceComponents;

    public PartyType() {
    }

    public PartyType(String name, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public PartyType(PartyType partyType, String name, String description, Set partyClassifications, Set marketInterests, Set partyTypes, Set productPriceComponents, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.partyType = partyType;
        this.name = name;
        this.description = description;
        this.partyClassifications = partyClassifications;
        this.marketInterests = marketInterests;
        this.partyTypes = partyTypes;
        this.productPriceComponents = productPriceComponents;
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
    @JoinColumn(name="PARENT_TYPE_ID")
    @JsonIgnore
    public PartyType getPartyType() 
    {
        return this.partyType;
    }
    
    public void setPartyType(PartyType partyType)
    {
        this.partyType = partyType;
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
	
    @Column(name="DESCRIPTION", length=150)
    public String getDescription() 
    {
        return this.description;
    }
    
    public void setDescription(String description) 
    {
        this.description = description;
    }
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="partyType")
    @JsonIgnore
    public Set<PartyClassification> getPartyClassifications() 
    {
        return this.partyClassifications;
    }
    
    public void setPartyClassifications(Set<PartyClassification> partyClassifications) 
    {
        this.partyClassifications = partyClassifications;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="partyType")
    @JsonIgnore
    public Set<MarketInterest> getMarketInterests() 
    {
        return this.marketInterests;
    }
    
    public void setMarketInterests(Set<MarketInterest> marketInterests) 
    {
        this.marketInterests = marketInterests;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="partyType")
    @JsonIgnore
    public Set<PartyType> getPartyTypes() 
    {
        return this.partyTypes;
    }
    
    public void setPartyTypes(Set<PartyType> partyTypes) 
    {
        this.partyTypes = partyTypes;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="partyType")
    @JsonIgnore
    public Set<ProductPriceComponent> getProductPriceComponents() 
    {
        return this.productPriceComponents;
    }
    
    public void setProductPriceComponents(Set<ProductPriceComponent> productPriceComponents) 
    {
        this.productPriceComponents = productPriceComponents;
    }			
		
    

}
