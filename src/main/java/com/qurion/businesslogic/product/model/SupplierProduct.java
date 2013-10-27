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
import com.qurion.businesslogic.product.model.PreferenceType;
import com.qurion.businesslogic.product.model.RatingType;
import com.qurion.businesslogic.product.model.Product;
import com.qurion.businesslogic.party.model.Party;

import java.util.Date;
import java.util.Date;

/**
 * SupplierProduct 
 * @author Edward Banfa
 */
@Entity
@Table(name="SUPPLIER_PRODUCT"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class SupplierProduct  extends BaseEntity implements java.io.Serializable {
	private PreferenceType preferenceType;
	private RatingType ratingType;
	private Product product;
	private Party party;
	private Date availFromDt;
	private Date availToDt;
	private String remarks;

    public SupplierProduct() {
    }

    public SupplierProduct(Date availFromDt, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.availFromDt = availFromDt;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public SupplierProduct(PreferenceType preferenceType, RatingType ratingType, Product product, Party party, Date availFromDt, Date availToDt, String remarks, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.preferenceType = preferenceType;
        this.ratingType = ratingType;
        this.product = product;
        this.party = party;
        this.availFromDt = availFromDt;
        this.availToDt = availToDt;
        this.remarks = remarks;
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
    @JoinColumn(name="PREF_ID")
    @JsonIgnore
    public PreferenceType getPreferenceType() 
    {
        return this.preferenceType;
    }
    
    public void setPreferenceType(PreferenceType preferenceType)
    {
        this.preferenceType = preferenceType;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="RATING_ID")
    @JsonIgnore
    public RatingType getRatingType() 
    {
        return this.ratingType;
    }
    
    public void setRatingType(RatingType ratingType)
    {
        this.ratingType = ratingType;
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
    @JoinColumn(name="SUPPLIER_ID")
    @JsonIgnore
    public Party getParty() 
    {
        return this.party;
    }
    
    public void setParty(Party party)
    {
        this.party = party;
    }
    
    @Column(name="AVAIL_FROM_DT", nullable=false, length=10)
    public Date getAvailFromDt() 
    {
        return this.availFromDt;
    }
    
    public void setAvailFromDt(Date availFromDt) 
    {
        this.availFromDt = availFromDt;
    }
	
    @Column(name="AVAIL_TO_DT", length=10)
    public Date getAvailToDt() 
    {
        return this.availToDt;
    }
    
    public void setAvailToDt(Date availToDt) 
    {
        this.availToDt = availToDt;
    }
	
    @Column(name="REMARKS", length=150)
    public String getRemarks() 
    {
        return this.remarks;
    }
    
    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
    }
	
    

}
