package com.revature.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Super_Prison")
public class SuperPrison {
	
	@Id
	@Column(name="sp_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int spId;
	
	@Column(name="sp_name")
	private String spName;
	
	@Column(name="sp_location")
	private String location;
	
	// We will signal to hibernate that this is a OneToMany relationship....
	@OneToMany(mappedBy="superPrisonHolder", fetch = FetchType.LAZY)
	private List<SuperVillain> villList = new ArrayList<SuperVillain>();

	
	public SuperPrison() {
		
	}
	
	public SuperPrison(int spId, String spName, String location, List<SuperVillain> villList) {
		super();
		this.spId = spId;
		this.spName = spName;
		this.location = location;
		this.villList = villList;
	}

	public SuperPrison(String spName, String location, List<SuperVillain> villList) {
		super();
		this.spName = spName;
		this.location = location;
		this.villList = villList;
	}
	
	public SuperPrison(String spName, String location) {
		super();
		this.spName = spName;
		this.location = location;
	}
	

	public int getSpId() {
		return spId;
	}

	public void setSpId(int spId) {
		this.spId = spId;
	}

	public String getSpName() {
		return spName;
	}

	public void setSpName(String spName) {
		this.spName = spName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<SuperVillain> getVillList() {
		return villList;
	}

	public void setVillList(List<SuperVillain> villList) {
		this.villList = villList;
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + spId;
		result = prime * result + ((spName == null) ? 0 : spName.hashCode());
		result = prime * result + ((villList == null) ? 0 : villList.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SuperPrison other = (SuperPrison) obj;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (spId != other.spId)
			return false;
		if (spName == null) {
			if (other.spName != null)
				return false;
		} else if (!spName.equals(other.spName))
			return false;
		if (villList == null) {
			if (other.villList != null)
				return false;
		} else if (!villList.equals(other.villList))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SuperPrison [spId=" + spId + ", spName=" + spName + ", location=" + location + ", villList=" + villList
				+ "]";
	}

}
