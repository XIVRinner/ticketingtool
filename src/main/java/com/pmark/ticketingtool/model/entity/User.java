package com.pmark.ticketingtool.model.entity;

import com.pmark.ticketingtool.model.abstractmodel.JSONBuilder;
import com.pmark.ticketingtool.model.abstractmodel.JSONBuilderSkipper;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name="USERS")
@Entity
@NamedQuery(name="User.findAll", query="SELECT c FROM User c")
@Data
@Builder
@NoArgsConstructor
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

}
