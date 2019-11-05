package com.pmark.ticketingtool.model.entity;

import com.pmark.ticketingtool.model.abstractmodel.JSONBuilder;
import com.pmark.ticketingtool.model.abstractmodel.JSONBuilderRenamer;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="TICKETS")
@NamedQuery(name="Ticket.findAll", query="SELECT c FROM Ticket c")
@Data
public class Ticket extends JSONBuilder {

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

	public Ticket() {
	}

	@Column(name="RESOLUTION")
	private String resolution;
	
	@Column(name="CREATED")
	private Timestamp created;
	
	@Column(name="DEADLINE")
	private Timestamp deadline;

	private Ticket(Builder builder) {
		setResponsible(builder.responsible);
		setShortDescription(builder.shortDescription);
		setLongDescription(builder.longDescription);
		setStatus(builder.status);
		setGroup(builder.group);
		setSeverity(builder.severity);
		setResolution(builder.resolution);
		setCreated(builder.created);
		setDeadline(builder.deadline);
	}

	public static final class Builder {
		private User responsible;
		private String shortDescription;
		private String longDescription;
		private Status status;
		private Group group;
		private Severity severity;
		private String resolution;
		private Timestamp created;
		private Timestamp deadline;

		public Builder() {
		}

		public Builder withResponsible(User val) {
			responsible = val;
			return this;
		}

		public Builder withShortDescription(String val) {
			shortDescription = val;
			return this;
		}

		public Builder withLongDescription(String val) {
			longDescription = val;
			return this;
		}

		public Builder withStatus(Status val) {
			status = val;
			return this;
		}

		public Builder withGroup(Group val) {
			group = val;
			return this;
		}

		public Builder withSeverity(Severity val) {
			severity = val;
			return this;
		}

		public Builder withResolution(String val) {
			resolution = val;
			return this;
		}

		public Builder withCreated(Timestamp val) {
			created = val;
			return this;
		}

		public Builder withDeadline(Timestamp val) {
			deadline = val;
			return this;
		}

		public Ticket build() {
			return new Ticket(this);
		}
	}
}
