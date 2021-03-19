package com.revature.models;

import java.time.LocalDateTime;
import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Reimbursement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int reimbId;
	private double reimbAmount;
	private LocalDateTime reimbSubmitted;
	private LocalDateTime reimbResolved;
	private String reimbDescription;
	private byte[] reimbReceipt;
	
	@OneToOne
	@JoinColumn(name = "reimbAuthor")
	private User reimbAuthor;

	@OneToOne
	@JoinColumn(name = "reimbResolver")
	private User reimbResolver;
	
	@ManyToOne
	@JoinColumn(name = "reimbStatusId")
	private ReimbStatus reimbStatus;
	
	@ManyToOne
	@JoinColumn(name = "reimbTypeId")
	private ReimbType reimbType;
	
	public Reimbursement() {
		// TODO Auto-generated constructor stub
	}
	
	public Reimbursement(double reimbAmount, LocalDateTime reimbSubmitted, String reimbDescription, User reimbAuthor,
			ReimbStatus reimbStatus, ReimbType reimbType) {
		super();
		this.reimbAmount = reimbAmount;
		this.reimbSubmitted = reimbSubmitted;
		this.reimbDescription = reimbDescription;
		this.reimbAuthor = reimbAuthor;
		this.reimbStatus = reimbStatus;
		this.reimbType = reimbType;
	}
	
	

	public Reimbursement(double reimbAmount, LocalDateTime reimbSubmitted, String reimbDescription, byte[] reimbReceipt,
			User reimbAuthor, ReimbStatus reimbStatus, ReimbType reimbType) {
		super();
		this.reimbAmount = reimbAmount;
		this.reimbSubmitted = reimbSubmitted;
		this.reimbDescription = reimbDescription;
		this.reimbReceipt = reimbReceipt;
		this.reimbAuthor = reimbAuthor;
		this.reimbStatus = reimbStatus;
		this.reimbType = reimbType;
	}
	
	

	public Reimbursement(int reimbId, double reimbAmount, ReimbStatus reimbStatus) {
		super();
		this.reimbId = reimbId;
		this.reimbAmount = reimbAmount;
		this.reimbStatus = reimbStatus;
	}

	
	
	public Reimbursement(int reimbId, double reimbAmount, LocalDateTime reimbSubmitted, LocalDateTime reimbResolved,
			String reimbDescription, User reimbAuthor, User reimbResolver, ReimbStatus reimbStatus,
			ReimbType reimbType) {
		super();
		this.reimbId = reimbId;
		this.reimbAmount = reimbAmount;
		this.reimbSubmitted = reimbSubmitted;
		this.reimbResolved = reimbResolved;
		this.reimbDescription = reimbDescription;
		this.reimbAuthor = reimbAuthor;
		this.reimbResolver = reimbResolver;
		this.reimbStatus = reimbStatus;
		this.reimbType = reimbType;
	}

	public int getReimbId() {
		return reimbId;
	}

	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}

	public double getReimbAmount() {
		return reimbAmount;
	}

	public void setReimbAmount(double reimbAmount) {
		this.reimbAmount = reimbAmount;
	}

	public LocalDateTime getReimbSubmitted() {
		return reimbSubmitted;
	}

	public void setReimbSubmitted(LocalDateTime reimbSubmitted) {
		this.reimbSubmitted = reimbSubmitted;
	}

	public LocalDateTime getReimbResolved() {
		return reimbResolved;
	}

	public void setReimbResolved(LocalDateTime reimbResolved) {
		this.reimbResolved = reimbResolved;
	}

	public String getReimbDescription() {
		return reimbDescription;
	}

	public void setReimbDescription(String reimbDescription) {
		this.reimbDescription = reimbDescription;
	}

	public byte[] getReimbReceipt() {
		return reimbReceipt;
	}

	public void setReimbReceipt(byte[] reimbReceipt) {
		this.reimbReceipt = reimbReceipt;
	}

	public ReimbStatus getReimbStatus() {
		return reimbStatus;
	}

	public void setReimbStatus(ReimbStatus reimbStatus) {
		this.reimbStatus = reimbStatus;
	}

	public ReimbType getReimbType() {
		return reimbType;
	}

	public void setReimbType(ReimbType reimbType) {
		this.reimbType = reimbType;
	}


	public User getReimbAuthor() {
		return reimbAuthor;
	}


	public void setReimbAuthor(User reimbAuthor) {
		this.reimbAuthor = reimbAuthor;
	}


	public User getReimbResolver() {
		return reimbResolver;
	}


	public void setReimbResolver(User reimbResolver) {
		this.reimbResolver = reimbResolver;
	}

	@Override
	public String toString() {
		return "Reimbursement [reimbId=" + reimbId + ", reimbAmount=" + reimbAmount + ", reimbSubmitted="
				+ reimbSubmitted + ", reimbResolved=" + reimbResolved + ", reimbDescription=" + reimbDescription
				+ ", reimbReceipt=" + Arrays.toString(reimbReceipt) + ", reimbAuthor=" + reimbAuthor
				+ ", reimbResolver=" + reimbResolver + ", reimbStatus=" + reimbStatus + ", reimbType=" + reimbType
				+ "]";
	}
	
}
