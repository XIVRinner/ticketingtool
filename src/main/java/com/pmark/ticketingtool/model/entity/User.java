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

@Table(name="USERS")
@Entity
@NamedQuery(name="User.findAll", query="SELECT c FROM User c")
@Data
public class User extends JSONBuilder {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private int id;
	
	@Column(name="USER")
	private String user;
	
	@Column(name="PASSWORD")
	private String pass;
	
	
	@Column(name="PERMISSION")
	private int permission;


	
}
