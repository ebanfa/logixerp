/**
 *  Business Logic.
 */
package com.qurion.businesslogic.businessdata.model;

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
import com.qurion.businesslogic.businessdata.model.GeoBoundry;
import com.qurion.businesslogic.businessdata.model.GeoBoundryType;

import java.util.Set;

import com.qurion.businesslogic.party.model.PostalAddressBoundry;

import java.util.Set;

import com.qurion.businesslogic.ordering.model.AgreementGeoboundryApplicability;

import java.util.Set;

import com.qurion.businesslogic.product.model.ProductCostComponent;

import java.util.Set;

import com.qurion.businesslogic.ordering.model.SalesTaxLookup;

import java.util.Set;

import com.qurion.businesslogic.product.model.ProductPriceComponent;

import java.util.Set;

import com.qurion.businesslogic.businessdata.model.GeoBoundry;

/**
 * GeoBoundry 
 * @author Edward Banfa
 */
@Entity
@Table(name="GEO_BOUNDRY"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class GeoBoundry  extends BaseEntity implements java.io.Serializable {
	private GeoBoundry geoBoundry;
	private GeoBoundryType geoBoundryType;
	private String geoCode;
	private String name;
	private String abbr;
	private String description;
	private Set<PostalAddressBoundry> postalAddressBoundries;
	private Set<AgreementGeoboundryApplicability> agreementGeoboundryApplicabilities;
	private Set<ProductCostComponent> productCostComponents;
	private Set<SalesTaxLookup> salesTaxLookups;
	private Set<ProductPriceComponent> productPriceComponents;
	private Set<GeoBoundry> geoBoundries;

    public GeoBoundry() {
    }

    public GeoBoundry(String name, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public GeoBoundry(GeoBoundry geoBoundry, GeoBoundryType geoBoundryType, String geoCode, String name, String abbr, String description, Set postalAddressBoundries, Set agreementGeoboundryApplicabilities, Set productCostComponents, Set salesTaxLookups, Set productPriceComponents, Set geoBoundries, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.geoBoundry = geoBoundry;
        this.geoBoundryType = geoBoundryType;
        this.geoCode = geoCode;
        this.name = name;
        this.abbr = abbr;
        this.description = description;
        this.postalAddressBoundries = postalAddressBoundries;
        this.agreementGeoboundryApplicabilities = agreementGeoboundryApplicabilities;
        this.productCostComponents = productCostComponents;
        this.salesTaxLookups = salesTaxLookups;
        this.productPriceComponents = productPriceComponents;
        this.geoBoundries = geoBoundries;
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
    @JoinColumn(name="PARENT_GEO_BOUNDRY_ID")
    @JsonIgnore
    public GeoBoundry getGeoBoundry() 
    {
        return this.geoBoundry;
    }
    
    public void setGeoBoundry(GeoBoundry geoBoundry)
    {
        this.geoBoundry = geoBoundry;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="GEO_BOUNDRY_TY_ID")
    @JsonIgnore
    public GeoBoundryType getGeoBoundryType() 
    {
        return this.geoBoundryType;
    }
    
    public void setGeoBoundryType(GeoBoundryType geoBoundryType)
    {
        this.geoBoundryType = geoBoundryType;
    }
    
    @Column(name="GEO_CODE", length=35)
    public String getGeoCode() 
    {
        return this.geoCode;
    }
    
    public void setGeoCode(String geoCode) 
    {
        this.geoCode = geoCode;
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
	
    @Column(name="ABBR", length=15)
    public String getAbbr() 
    {
        return this.abbr;
    }
    
    public void setAbbr(String abbr) 
    {
        this.abbr = abbr;
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
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="geoBoundry")
    @JsonIgnore
    public Set<PostalAddressBoundry> getPostalAddressBoundries() 
    {
        return this.postalAddressBoundries;
    }
    
    public void setPostalAddressBoundries(Set<PostalAddressBoundry> postalAddressBoundries) 
    {
        this.postalAddressBoundries = postalAddressBoundries;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="geoBoundry")
    @JsonIgnore
    public Set<AgreementGeoboundryApplicability> getAgreementGeoboundryApplicabilities() 
    {
        return this.agreementGeoboundryApplicabilities;
    }
    
    public void setAgreementGeoboundryApplicabilities(Set<AgreementGeoboundryApplicability> agreementGeoboundryApplicabilities) 
    {
        this.agreementGeoboundryApplicabilities = agreementGeoboundryApplicabilities;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="geoBoundry")
    @JsonIgnore
    public Set<ProductCostComponent> getProductCostComponents() 
    {
        return this.productCostComponents;
    }
    
    public void setProductCostComponents(Set<ProductCostComponent> productCostComponents) 
    {
        this.productCostComponents = productCostComponents;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="geoBoundry")
    @JsonIgnore
    public Set<SalesTaxLookup> getSalesTaxLookups() 
    {
        return this.salesTaxLookups;
    }
    
    public void setSalesTaxLookups(Set<SalesTaxLookup> salesTaxLookups) 
    {
        this.salesTaxLookups = salesTaxLookups;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="geoBoundry")
    @JsonIgnore
    public Set<ProductPriceComponent> getProductPriceComponents() 
    {
        return this.productPriceComponents;
    }
    
    public void setProductPriceComponents(Set<ProductPriceComponent> productPriceComponents) 
    {
        this.productPriceComponents = productPriceComponents;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="geoBoundry")
    @JsonIgnore
    public Set<GeoBoundry> getGeoBoundries() 
    {
        return this.geoBoundries;
    }
    
    public void setGeoBoundries(Set<GeoBoundry> geoBoundries) 
    {
        this.geoBoundries = geoBoundries;
    }			
		
    

}
