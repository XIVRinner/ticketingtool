package com.pmark.ticketingtool.model.entity;

import com.pmark.ticketingtool.model.abstractmodel.JSONBuilder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="CUSTOMERS")
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
@Data
public class Customer extends JSONBuilder {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private int id;
	
	@Column(name="NAME")
	private String name;
	
	
	@Column(name="ORG")
	private String org;




	public Customer() {
	}

	private Customer(Builder builder) {
		setName(builder.name);
		setOrg(builder.org);
	}

	public static final class Builder {
		private String name;
		private String org;

		public Builder() {
		}

		public Builder withName(String val) {
			name = val;
			return this;
		}

		public Builder withOrg(String val) {
			org = val;
			return this;
		}

		public Customer build() {
			return new Customer(this);
		}
	}
}
