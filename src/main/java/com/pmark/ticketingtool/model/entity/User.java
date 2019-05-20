package com.pmark.ticketingtool.model.entity;

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
public class User {

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


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}


	public String getPass() {
		return pass;
	}


	public void setPass(String pass) {
		this.pass = pass;
	}


	public int getPermission() {
		return permission;
	}


	public void setPermission(int permission) {
		this.permission = permission;
	}
	
	
	
}
