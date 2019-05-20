package com.pmark.ticketingtool.model.entity;

import java.security.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="TICKETS")
@NamedQuery(name="Ticket.findAll", query="SELECT c FROM Ticket c")
public class Ticket {

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@JoinColumn(name="RESPONSIBLE")
	@OneToOne(fetch=FetchType.EAGER)
	private User responsible;
	
	@Column(name="SHORT")
	private String shortDescription;
	
	@Column(name="LONG")
	private String longDescription;
	
	@JoinColumn(name="STATUS_CODE")
	@OneToOne(fetch=FetchType.EAGER)
	private Status status;
	
	@JoinColumn(name="GROUP")
	@OneToOne(fetch=FetchType.EAGER)
	private Group group;
	
	@JoinColumn(name="SEV")
	@OneToOne(fetch=FetchType.LAZY)
	private Severity severity;	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getResponsible() {
		return responsible;
	}

	public void setResponsible(User responsible) {
		this.responsible = responsible;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Severity getSeverity() {
		return severity;
	}

	public void setSeverity(Severity severity) {
		this.severity = severity;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public Timestamp getDeadline() {
		return deadline;
	}

	public void setDeadline(Timestamp deadline) {
		this.deadline = deadline;
	}

	@Column(name="RESOLUTION")
	private String resolution;
	
	@Column(name="CREATED")
	private Timestamp created;
	
	@Column(name="DEADLINE")
	private Timestamp deadline;
}
