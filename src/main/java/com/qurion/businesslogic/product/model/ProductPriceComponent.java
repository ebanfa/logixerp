/**
 *  Business Logic.
 */
package com.qurion.businesslogic.product.model;

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
import com.qurion.businesslogic.product.model.ProductCategory;
import com.qurion.businesslogic.product.model.Product;
import com.qurion.businesslogic.party.model.PartyType;
import com.qurion.businesslogic.businessdata.model.Uom;
import com.qurion.businesslogic.product.model.ProductPriceComponentType;
import com.qurion.businesslogic.ordering.model.SaleType;
import com.qurion.businesslogic.ordering.model.QuantityBreak;
import com.qurion.businesslogic.product.model.ProductFeature;
import com.qurion.businesslogic.ordering.model.OrderValue;
import com.qurion.businesslogic.party.model.Party;
import com.qurion.businesslogic.businessdata.model.GeoBoundry;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Date;

/**
 * ProductPriceComponent 
 * @author Edward Banfa
 */
@Entity
@Table(name="PRODUCT_PRICE_COMPONENT"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ProductPriceComponent  extends BaseEntity implements java.io.Serializable {
	private AgreementItem agreementItem;
	private ProductCategory productCategory;
	private Product product;
	private PartyType partyType;
	private Uom uom;
	private ProductPriceComponentType productPriceComponentType;
	private SaleType saleType;
	private QuantityBreak quantityBreak;
	private ProductFeature productFeature;
	private OrderValue orderValue;
	private Party party;
	private GeoBoundry geoBoundry;
	private String name;
	private String description;
	private String comment;
	private BigDecimal price;
	private Integer percentage;
	private Date fromDt;
	private Date toDt;

    public ProductPriceComponent() {
    }

    public ProductPriceComponent(String name, Date fromDt, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.fromDt = fromDt;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public ProductPriceComponent(AgreementItem agreementItem, ProductCategory productCategory, Product product, PartyType partyType, Uom uom, ProductPriceComponentType productPriceComponentType, SaleType saleType, QuantityBreak quantityBreak, ProductFeature productFeature, OrderValue orderValue, Party party, GeoBoundry geoBoundry, String name, String description, String comment, BigDecimal price, Integer percentage, Date fromDt, Date toDt, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.agreementItem = agreementItem;
        this.productCategory = productCategory;
        this.product = product;
        this.partyType = partyType;
        this.uom = uom;
        this.productPriceComponentType = productPriceComponentType;
        this.saleType = saleType;
        this.quantityBreak = quantityBreak;
        this.productFeature = productFeature;
        this.orderValue = orderValue;
        this.party = party;
        this.geoBoundry = geoBoundry;
        this.name = name;
        this.description = description;
        this.comment = comment;
        this.price = price;
        this.percentage = percentage;
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
    @JoinColumn(name="PROD_CAT_ID")
    @JsonIgnore
    public ProductCategory getProductCategory() 
    {
        return this.productCategory;
    }
    
    public void setProductCategory(ProductCategory productCategory)
    {
        this.productCategory = productCategory;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PROD_ID")
    @JsonIgnore
    public Product getProduct() 
    {
        return this.product;
    }
    
    public void setProduct(Product product)
    {
        this.product = product;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PARTY_TY_ID")
    @JsonIgnore
    public PartyType getPartyType() 
    {
        return this.partyType;
    }
    
    public void setPartyType(PartyType partyType)
    {
        this.partyType = partyType;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="UOM_ID")
    @JsonIgnore
    public Uom getUom() 
    {
        return this.uom;
    }
    
    public void setUom(Uom uom)
    {
        this.uom = uom;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PRICE_COMP_ID")
    @JsonIgnore
    public ProductPriceComponentType getProductPriceComponentType() 
    {
        return this.productPriceComponentType;
    }
    
    public void setProductPriceComponentType(ProductPriceComponentType productPriceComponentType)
    {
        this.productPriceComponentType = productPriceComponentType;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="SALE_TY_ID")
    @JsonIgnore
    public SaleType getSaleType() 
    {
        return this.saleType;
    }
    
    public void setSaleType(SaleType saleType)
    {
        this.saleType = saleType;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="QUANTITY_BRK_ID")
    @JsonIgnore
    public QuantityBreak getQuantityBreak() 
    {
        return this.quantityBreak;
    }
    
    public void setQuantityBreak(QuantityBreak quantityBreak)
    {
        this.quantityBreak = quantityBreak;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PROD_FEAT_ID")
    @JsonIgnore
    public ProductFeature getProductFeature() 
    {
        return this.productFeature;
    }
    
    public void setProductFeature(ProductFeature productFeature)
    {
        this.productFeature = productFeature;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ORDER_VAL_ID")
    @JsonIgnore
    public OrderValue getOrderValue() 
    {
        return this.orderValue;
    }
    
    public void setOrderValue(OrderValue orderValue)
    {
        this.orderValue = orderValue;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ORGANIZATION_ID")
    @JsonIgnore
    public Party getParty() 
    {
        return this.party;
    }
    
    public void setParty(Party party)
    {
        this.party = party;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="GEO_BOUNDRY_ID")
    @JsonIgnore
    public GeoBoundry getGeoBoundry() 
    {
        return this.geoBoundry;
    }
    
    public void setGeoBoundry(GeoBoundry geoBoundry)
    {
        this.geoBoundry = geoBoundry;
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
	
    @Column(name="COMMENT", length=150)
    public String getComment() 
    {
        return this.comment;
    }
    
    public void setComment(String comment) 
    {
        this.comment = comment;
    }
	
    @Column(name="PRICE")
    public BigDecimal getPrice() 
    {
        return this.price;
    }
    
    public void setPrice(BigDecimal price) 
    {
        this.price = price;
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
