package com.pmark.ticketingtool.model.entity;

import com.pmark.ticketingtool.model.abstractmodel.JSONBuilder;
import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name="GROUPS")
@NamedQuery(name="Group.findAll", query="SELECT c FROM Group c")
@Data
public class Group extends JSONBuilder {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private int id;
	
	@Column(name="NAME")
	private String name;
	
	@JoinColumn(name="CUSTOMER_ID")
	@OneToOne(fetch=FetchType.EAGER)
	private Customer customer;
	
	@JoinColumn(name="MANAGER")
	@OneToOne(fetch=FetchType.LAZY)
	private User manager;
	
	@Column(name="MAIL")
	private String mail;


	public Group() {
	}

	private Group(Builder builder) {
		setName(builder.name);
		setCustomer(builder.customer);
		setManager(builder.manager);
		setMail(builder.mail);
	}

	public static final class Builder {
		private String name;
		private Customer customer;
		private User manager;
		private String mail;

		public Builder() {
		}


		public Builder withName(String val) {
			name = val;
			return this;
		}

		public Builder withCustomer(Customer val) {
			customer = val;
			return this;
		}

		public Builder withManager(User val) {
			manager = val;
			return this;
		}

		public Builder withMail(String val) {
			mail = val;
			return this;
		}

		public Group build() {
			return new Group(this);
		}
	}
}
