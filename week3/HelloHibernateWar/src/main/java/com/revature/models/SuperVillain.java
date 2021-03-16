package com.revature.models;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/*
 * JPA (Java Persistence API) This is the specification and Hibernate is the implementation
 * 
 * We communicate with the Schema through Hibernate by using annotaions to define our tables, columns, and
 * relationships from the JPA
 */


@Cacheable
@Entity // we can use HQL to target this Object
@Table(name="Super_Villain") // tables can be capitalized
public class SuperVillain {

	@Id
	@Column(name="svill_id")
	@GeneratedValue(strategy = GenerationType.AUTO) // this acts like SERIAL datatype in SQL
	private int sVillId;
	
	@Column(name="name", unique = true, nullable=false)
	private String name; // HQL targets this
	
	@Column(name="superpower")
	private String superPower;
	
	@Column(name="bounty")
	private double bounty;

	// Many to Many relationship
	@ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	// a separate joins table will be created in memory 
	private List<Crime> crimes;
	// 2 ways to retireve data
	
	// 1. Lazy fetching means that the associated data is not loaded into memory UNTIL we call getCrimes();
	// 2. eager Fetching means that the associated data will be readily loaded into memory and available.
	
	@ManyToOne(cascade =CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "Prison_FK")
	private SuperPrison superPrisonHolder;
	
	
	public SuperVillain() {
		
	}


	public SuperVillain(int sVillId, String name, String superPower, double bounty, List<Crime> crimes,
			SuperPrison superPrisonHolder) {
		super();
		this.sVillId = sVillId;
		this.name = name;
		this.superPower = superPower;
		this.bounty = bounty;
		this.crimes = crimes;
		this.superPrisonHolder = superPrisonHolder;
	}
	
	public SuperVillain(String name, String superPower, double bounty, List<Crime> crimes,
			SuperPrison superPrisonHolder) {
		super();
		this.name = name;
		this.superPower = superPower;
		this.bounty = bounty;
		this.crimes = crimes;
		this.superPrisonHolder = superPrisonHolder;
	}


	public int getsVillId() {
		return sVillId;
	}


	public void setsVillId(int sVillId) {
		this.sVillId = sVillId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSuperPower() {
		return superPower;
	}


	public void setSuperPower(String superPower) {
		this.superPower = superPower;
	}


	public double getBounty() {
		return bounty;
	}


	public void setBounty(double bounty) {
		this.bounty = bounty;
	}


	public List<Crime> getCrimes() {
		return crimes;
	}


	public void setCrimes(List<Crime> crimes) {
		this.crimes = crimes;
	}


	public SuperPrison getSuperPrisonHolder() {
		return superPrisonHolder;
	}


	public void setSuperPrisonHolder(SuperPrison superPrisonHolder) {
		this.superPrisonHolder = superPrisonHolder;
	}
	
	@Override
	public String toString() {
		return "SuperVillain [sVillId=" + sVillId + ", name=" + name + ", superPower=" + superPower + ", bounty="
				+ bounty + ", crimes=" + crimes + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(bounty);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((crimes == null) ? 0 : crimes.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + sVillId;
		result = prime * result + ((superPower == null) ? 0 : superPower.hashCode());
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
		SuperVillain other = (SuperVillain) obj;
		if (Double.doubleToLongBits(bounty) != Double.doubleToLongBits(other.bounty))
			return false;
		if (crimes == null) {
			if (other.crimes != null)
				return false;
		} else if (!crimes.equals(other.crimes))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (sVillId != other.sVillId)
			return false;
		if (superPower == null) {
			if (other.superPower != null)
				return false;
		} else if (!superPower.equals(other.superPower))
			return false;
		return true;
	}
	
	
	
	
	
	
}
