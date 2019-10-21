package com.pmark.ticketingtool.model.entity;

import com.pmark.ticketingtool.model.abstractmodel.JSONBuilder;
import com.pmark.ticketingtool.model.abstractmodel.JSONBuilderSkipper;
import lombok.Data;

import javax.persistence.*;

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

	@Transient
	@JSONBuilderSkipper
	private String token;

	public User() {
	}

	private User(Builder builder) {
		setUser(builder.user);
		setPass(builder.pass);
		setPermission(builder.permission);
	}

	public static final class Builder {
		private String user;
		private String pass;
		private int permission;

		public Builder() {
		}

		public Builder withUser(String val) {
			user = val;
			return this;
		}

		public Builder withPass(String val) {
			pass = val;
			return this;
		}

		public Builder withPermission(int val) {
			permission = val;
			return this;
		}

		public User build() {
			return new User(this);
		}
	}
}
