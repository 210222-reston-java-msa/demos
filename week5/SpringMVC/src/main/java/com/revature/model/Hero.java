package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

// As Jesus L. said, you can simply just write "import javax.persistence.*" to import all necessary annotations from the JPA


// Spring ORM is a module(library) that wraps around the JPA and works with Hibernate
@Entity
@Table(name="HERO")
public class Hero {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="heroSequence")
	@SequenceGenerator(name="heroSequence", sequenceName="HERO_SEQ", allocationSize=1)
	private int id;
	
	@Column(name="H_NAME", unique=true, nullable=false)
	private String name;
	
	@Column(name="H_SUPER_POWER")
	private String superPower;
	
	@Column(name="H_HAS_CAPE")
	private boolean hasCape;
	
	public Hero() {
		// a no args constructor (a.k.a a default constructor)
	};

	public Hero(int id, String name, String superPower, boolean hasCape) {
		super();
		this.id = id;
		this.name = name;
		this.superPower = superPower;
		this.hasCape = hasCape;
	}
	
	public Hero(String name, String superPower, boolean hasCape) {
		this.name = name;
		this.superPower = superPower;
		this.hasCape = hasCape;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public boolean isHasCape() {
		return hasCape;
	}

	public void setHasCape(boolean hasCape) {
		this.hasCape = hasCape;
	}
	
	// alt + shift + s to shorthand create constructors
	
	

	@Override
	public String toString() {
		return "Hero [id=" + id + ", name=" + name + ", superPower=" + superPower + ", hasCape=" + hasCape + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (hasCape ? 1231 : 1237);
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Hero other = (Hero) obj;
		if (hasCape != other.hasCape)
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (superPower == null) {
			if (other.superPower != null)
				return false;
		} else if (!superPower.equals(other.superPower))
			return false;
		return true;
	}

}
