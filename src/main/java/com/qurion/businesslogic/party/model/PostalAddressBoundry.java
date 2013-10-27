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
import com.qurion.businesslogic.party.model.PostalAddress;
import com.qurion.businesslogic.businessdata.model.GeoBoundry;

/**
 * PostalAddressBoundry 
 * @author Edward Banfa
 */
@Entity
@Table(name="POSTAL_ADDRESS_BOUNDRY"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PostalAddressBoundry  extends BaseEntity implements java.io.Serializable {
	private PostalAddress postalAddress;
	private GeoBoundry geoBoundry;

    public PostalAddressBoundry() {
    }

    public PostalAddressBoundry(Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public PostalAddressBoundry(PostalAddress postalAddress, GeoBoundry geoBoundry, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.postalAddress = postalAddress;
        this.geoBoundry = geoBoundry;
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
    @JoinColumn(name="POSTAL_ADDR_ID")
    @JsonIgnore
    public PostalAddress getPostalAddress() 
    {
        return this.postalAddress;
    }
    
    public void setPostalAddress(PostalAddress postalAddress)
    {
        this.postalAddress = postalAddress;
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
    
    

}
