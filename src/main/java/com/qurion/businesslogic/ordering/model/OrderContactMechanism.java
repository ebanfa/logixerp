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
import com.qurion.businesslogic.ordering.model.Orders;
import com.qurion.businesslogic.party.model.ContactMechanism;
import com.qurion.businesslogic.party.model.ContactMechanismPurposeType;

/**
 * OrderContactMechanism 
 * @author Edward Banfa
 */
@Entity
@Table(name="ORDER_CONTACT_MECHANISM"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class OrderContactMechanism  extends BaseEntity implements java.io.Serializable {
	private Orders orders;
	private ContactMechanism contactMechanism;
	private ContactMechanismPurposeType contactMechanismPurposeType;

    public OrderContactMechanism() {
    }

    public OrderContactMechanism(Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public OrderContactMechanism(Orders orders, ContactMechanism contactMechanism, ContactMechanismPurposeType contactMechanismPurposeType, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.orders = orders;
        this.contactMechanism = contactMechanism;
        this.contactMechanismPurposeType = contactMechanismPurposeType;
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
    @JoinColumn(name="ORDER_ID")
    @JsonIgnore
    public Orders getOrders() 
    {
        return this.orders;
    }
    
    public void setOrders(Orders orders)
    {
        this.orders = orders;
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
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CONT_MECH_PURP_ID")
    @JsonIgnore
    public ContactMechanismPurposeType getContactMechanismPurposeType() 
    {
        return this.contactMechanismPurposeType;
    }
    
    public void setContactMechanismPurposeType(ContactMechanismPurposeType contactMechanismPurposeType)
    {
        this.contactMechanismPurposeType = contactMechanismPurposeType;
    }
    
    

}
