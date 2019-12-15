package com.pmark.ticketingtool.model.entity;

import com.pmark.ticketingtool.model.abstractmodel.JSONBuilder;
import com.pmark.ticketingtool.model.abstractmodel.JSONBuilderSkipper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name="USERS")
@Entity
@NamedQuery(name="User.findAll", query="SELECT c FROM User c")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends JSONBuilder {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	public Integer id;
	
	@Column(name="USER")
	private String user;
	
	@Column(name="PASSWORD")
	@JSONBuilderSkipper
	private String pass;
	
	
	@Column(name="PERMISSION")
	@JSONBuilderSkipper
	private int permission;

	@Transient
	@JSONBuilderSkipper
	private String token;

}
