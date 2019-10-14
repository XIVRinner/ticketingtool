package com.pmark.ticketingtool.model.entity;

import com.pmark.ticketingtool.model.abstractmodel.JSONBuilder;
import lombok.Data;

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
@Table(name="APPROVALS")
@NamedQuery(name="Approval.findAll", query="SELECT c FROM Approval c")
@Data
public class Approval extends JSONBuilder {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private int id;
	
	@JoinColumn(name="CHANGE_ID")
	@OneToOne(fetch=FetchType.LAZY)
	private Change change;
	
	
	@JoinColumn(name="STATUS_CODE")
	@OneToOne(fetch=FetchType.LAZY)
	private Status status;

	private Approval(Builder builder) {
		setChange(builder.change);
		setStatus(builder.status);
	}

	public Approval() {
	}

	public static final class Builder {
		private Change change;
		private Status status;

		public Builder() {
		}

		public Builder withChange(Change val) {
			change = val;
			return this;
		}

		public Builder withStatus(Status val) {
			status = val;
			return this;
		}

		public Approval build() {
			return new Approval(this);
		}
	}
}
