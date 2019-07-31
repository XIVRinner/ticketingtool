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


}
