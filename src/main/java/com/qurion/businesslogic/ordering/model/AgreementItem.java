/**
 *  Business Logic.
 */
package com.qurion.businesslogic.ordering.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
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
import com.qurion.businesslogic.product.model.ProductPriceComponent;

/**
 * AgreementItem 
 * @author Edward Banfa
 */
@Entity
@Table(name="AGREEMENT_ITEM"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class AgreementItem  extends BaseEntity implements java.io.Serializable {
	private AgreementItem agreementItem;
	private AgreementItemType agreementItemType;
	private Agreement agreement;
	private String name;
	private String description;
	private String agreementText;
	private byte agreementImage;
	private Date agreementDt;
	private Date fromDt;
	private Date toDt;
	private Set<AgreementItem> agreementItems;
	private Set<ProductPriceComponent> productPriceComponents;
	private Set<AgreementGeoboundryApplicability> agreementGeoboundryApplicabilities;
	private Set<AgreementOrganizationApplicability> agreementOrganizationApplicabilities;
	private Set<AgreementProductApplicability> agreementProductApplicabilities;
	private Set<Addendum> addendums;
	private Set<AgreementTerm> agreementTerms;

    public AgreementItem() {
    }

    public AgreementItem(String name, String description, Date agreementDt, Date fromDt, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
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

    public AgreementItem(AgreementItem agreementItem, AgreementItemType agreementItemType, Agreement agreement, String name, String description, String agreementText, byte agreementImage, Date agreementDt, Date fromDt, Date toDt, Set agreementItems, Set productPriceComponents, Set agreementGeoboundryApplicabilities, Set agreementOrganizationApplicabilities, Set agreementProductApplicabilities, Set addendums, Set agreementTerms, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.agreementItem = agreementItem;
        this.agreementItemType = agreementItemType;
        this.agreement = agreement;
        this.name = name;
        this.description = description;
        this.agreementText = agreementText;
        this.agreementImage = agreementImage;
        this.agreementDt = agreementDt;
        this.fromDt = fromDt;
        this.toDt = toDt;
        this.agreementItems = agreementItems;
        this.productPriceComponents = productPriceComponents;
        this.agreementGeoboundryApplicabilities = agreementGeoboundryApplicabilities;
        this.agreementOrganizationApplicabilities = agreementOrganizationApplicabilities;
        this.agreementProductApplicabilities = agreementProductApplicabilities;
        this.addendums = addendums;
        this.agreementTerms = agreementTerms;
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
    @JoinColumn(name="AGREEMENT_ITEM_TY_ID")
    @JsonIgnore
    public AgreementItemType getAgreementItemType() 
    {
        return this.agreementItemType;
    }
    
    public void setAgreementItemType(AgreementItemType agreementItemType)
    {
        this.agreementItemType = agreementItemType;
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
	
    @Column(name="AGREEMENT_TEXT")
    public String getAgreementText() 
    {
        return this.agreementText;
    }
    
    public void setAgreementText(String agreementText) 
    {
        this.agreementText = agreementText;
    }
	
    @Column(name="AGREEMENT_IMAGE")
    public byte getAgreementImage() 
    {
        return this.agreementImage;
    }
    
    public void setAgreementImage(byte agreementImage) 
    {
        this.agreementImage = agreementImage;
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
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="agreementItem")
    @JsonIgnore
    public Set<AgreementItem> getAgreementItems() 
    {
        return this.agreementItems;
    }
    
    public void setAgreementItems(Set<AgreementItem> agreementItems) 
    {
        this.agreementItems = agreementItems;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="agreementItem")
    @JsonIgnore
    public Set<ProductPriceComponent> getProductPriceComponents() 
    {
        return this.productPriceComponents;
    }
    
    public void setProductPriceComponents(Set<ProductPriceComponent> productPriceComponents) 
    {
        this.productPriceComponents = productPriceComponents;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="agreementItem")
    @JsonIgnore
    public Set<AgreementGeoboundryApplicability> getAgreementGeoboundryApplicabilities() 
    {
        return this.agreementGeoboundryApplicabilities;
    }
    
    public void setAgreementGeoboundryApplicabilities(Set<AgreementGeoboundryApplicability> agreementGeoboundryApplicabilities) 
    {
        this.agreementGeoboundryApplicabilities = agreementGeoboundryApplicabilities;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="agreementItem")
    @JsonIgnore
    public Set<AgreementOrganizationApplicability> getAgreementOrganizationApplicabilities() 
    {
        return this.agreementOrganizationApplicabilities;
    }
    
    public void setAgreementOrganizationApplicabilities(Set<AgreementOrganizationApplicability> agreementOrganizationApplicabilities) 
    {
        this.agreementOrganizationApplicabilities = agreementOrganizationApplicabilities;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="agreementItem")
    @JsonIgnore
    public Set<AgreementProductApplicability> getAgreementProductApplicabilities() 
    {
        return this.agreementProductApplicabilities;
    }
    
    public void setAgreementProductApplicabilities(Set<AgreementProductApplicability> agreementProductApplicabilities) 
    {
        this.agreementProductApplicabilities = agreementProductApplicabilities;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="agreementItem")
    @JsonIgnore
    public Set<Addendum> getAddendums() 
    {
        return this.addendums;
    }
    
    public void setAddendums(Set<Addendum> addendums) 
    {
        this.addendums = addendums;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="agreementItem")
    @JsonIgnore
    public Set<AgreementTerm> getAgreementTerms() 
    {
        return this.agreementTerms;
    }
    
    public void setAgreementTerms(Set<AgreementTerm> agreementTerms) 
    {
        this.agreementTerms = agreementTerms;
    }			
		
    

}
