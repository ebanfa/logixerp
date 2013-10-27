/**
 *  Business Logic.
 */
package com.qurion.businesslogic.shipment.model;

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
import com.qurion.businesslogic.shipment.model.ItemIssuanceRoleType;
import com.qurion.businesslogic.shipment.model.ItemIssuanceRole;
import com.qurion.businesslogic.party.model.Party;

import java.util.Set;

import com.qurion.businesslogic.shipment.model.ItemIssuanceRole;

/**
 * ItemIssuanceRole 
 * @author Edward Banfa
 */
@Entity
@Table(name="ITEM_ISSUANCE_ROLE"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ItemIssuanceRole  extends BaseEntity implements java.io.Serializable {
	private ItemIssuanceRoleType itemIssuanceRoleType;
	private ItemIssuanceRole itemIssuanceRole;
	private Party party;
	private String name;
	private String description;
	private Set<ItemIssuanceRole> itemIssuanceRoles;

    public ItemIssuanceRole() {
    }

    public ItemIssuanceRole(String name, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public ItemIssuanceRole(ItemIssuanceRoleType itemIssuanceRoleType, ItemIssuanceRole itemIssuanceRole, Party party, String name, String description, Set itemIssuanceRoles, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.itemIssuanceRoleType = itemIssuanceRoleType;
        this.itemIssuanceRole = itemIssuanceRole;
        this.party = party;
        this.name = name;
        this.description = description;
        this.itemIssuanceRoles = itemIssuanceRoles;
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
    @JoinColumn(name="ISSUANCE_ROLE_TY_ID")
    @JsonIgnore
    public ItemIssuanceRoleType getItemIssuanceRoleType() 
    {
        return this.itemIssuanceRoleType;
    }
    
    public void setItemIssuanceRoleType(ItemIssuanceRoleType itemIssuanceRoleType)
    {
        this.itemIssuanceRoleType = itemIssuanceRoleType;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ITEM_ISSUANCE_ID")
    @JsonIgnore
    public ItemIssuanceRole getItemIssuanceRole() 
    {
        return this.itemIssuanceRole;
    }
    
    public void setItemIssuanceRole(ItemIssuanceRole itemIssuanceRole)
    {
        this.itemIssuanceRole = itemIssuanceRole;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PARTY_ID")
    @JsonIgnore
    public Party getParty() 
    {
        return this.party;
    }
    
    public void setParty(Party party)
    {
        this.party = party;
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
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="itemIssuanceRole")
    @JsonIgnore
    public Set<ItemIssuanceRole> getItemIssuanceRoles() 
    {
        return this.itemIssuanceRoles;
    }
    
    public void setItemIssuanceRoles(Set<ItemIssuanceRole> itemIssuanceRoles) 
    {
        this.itemIssuanceRoles = itemIssuanceRoles;
    }			
		
    

}
