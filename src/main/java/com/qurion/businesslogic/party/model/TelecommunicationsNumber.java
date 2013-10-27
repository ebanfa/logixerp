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
import com.qurion.businesslogic.party.model.ContactMechanism;

/**
 * TelecommunicationsNumber 
 * @author Edward Banfa
 */
@Entity
@Table(name="TELECOMMUNICATIONS_NUMBER"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class TelecommunicationsNumber  extends BaseEntity implements java.io.Serializable {
	private ContactMechanism contactMechanism;
	private String areaCode;
	private String contactNo;
	private String countryCode;

    public TelecommunicationsNumber() {
    }

    public TelecommunicationsNumber(String areaCode, String countryCode, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.areaCode = areaCode;
        this.countryCode = countryCode;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public TelecommunicationsNumber(ContactMechanism contactMechanism, String areaCode, String contactNo, String countryCode, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.contactMechanism = contactMechanism;
        this.areaCode = areaCode;
        this.contactNo = contactNo;
        this.countryCode = countryCode;
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
    @JoinColumn(name="CONT_MECH_ID")
    @JsonIgnore
    public ContactMechanism getContactMechanism() 
    {
        return this.contactMechanism;
    }
    
    public void setContactMechanism(ContactMechanism contactMechanism)
    {
        this.contactMechanism = contactMechanism;
    }
    
    @Column(name="AREA_CODE", nullable=false, length=15)
    public String getAreaCode() 
    {
        return this.areaCode;
    }
    
    public void setAreaCode(String areaCode) 
    {
        this.areaCode = areaCode;
    }
	
    @Column(name="CONTACT_NO", length=15)
    public String getContactNo() 
    {
        return this.contactNo;
    }
    
    public void setContactNo(String contactNo) 
    {
        this.contactNo = contactNo;
    }
	
    @Column(name="COUNTRY_CODE", nullable=false, length=15)
    public String getCountryCode() 
    {
        return this.countryCode;
    }
    
    public void setCountryCode(String countryCode) 
    {
        this.countryCode = countryCode;
    }
	
    

}
