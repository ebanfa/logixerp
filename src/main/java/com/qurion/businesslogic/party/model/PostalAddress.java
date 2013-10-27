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

import java.util.Set;

import com.qurion.businesslogic.party.model.PostalAddressBoundry;

import java.util.Set;

import com.qurion.businesslogic.party.model.PartyPostalAddress;

/**
 * PostalAddress 
 * @author Edward Banfa
 */
@Entity
@Table(name="POSTAL_ADDRESS"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PostalAddress  extends BaseEntity implements java.io.Serializable {
	private ContactMechanism contactMechanism;
	private String address1;
	private String address2;
	private String directions;
	private Set<PostalAddressBoundry> postalAddressBoundries;
	private Set<PartyPostalAddress> partyPostalAddresses;

    public PostalAddress() {
    }

    public PostalAddress(String address1, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.address1 = address1;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public PostalAddress(ContactMechanism contactMechanism, String address1, String address2, String directions, Set postalAddressBoundries, Set partyPostalAddresses, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.contactMechanism = contactMechanism;
        this.address1 = address1;
        this.address2 = address2;
        this.directions = directions;
        this.postalAddressBoundries = postalAddressBoundries;
        this.partyPostalAddresses = partyPostalAddresses;
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
    
    @Column(name="ADDRESS1", nullable=false, length=75)
    public String getAddress1() 
    {
        return this.address1;
    }
    
    public void setAddress1(String address1) 
    {
        this.address1 = address1;
    }
	
    @Column(name="ADDRESS2", length=75)
    public String getAddress2() 
    {
        return this.address2;
    }
    
    public void setAddress2(String address2) 
    {
        this.address2 = address2;
    }
	
    @Column(name="DIRECTIONS", length=75)
    public String getDirections() 
    {
        return this.directions;
    }
    
    public void setDirections(String directions) 
    {
        this.directions = directions;
    }
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="postalAddress")
    @JsonIgnore
    public Set<PostalAddressBoundry> getPostalAddressBoundries() 
    {
        return this.postalAddressBoundries;
    }
    
    public void setPostalAddressBoundries(Set<PostalAddressBoundry> postalAddressBoundries) 
    {
        this.postalAddressBoundries = postalAddressBoundries;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="postalAddress")
    @JsonIgnore
    public Set<PartyPostalAddress> getPartyPostalAddresses() 
    {
        return this.partyPostalAddresses;
    }
    
    public void setPartyPostalAddresses(Set<PartyPostalAddress> partyPostalAddresses) 
    {
        this.partyPostalAddresses = partyPostalAddresses;
    }			
		
    

}
