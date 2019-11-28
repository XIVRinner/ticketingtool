package com.pmark.ticketingtool.model.entity;

import com.pmark.ticketingtool.model.abstractmodel.JSONBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name="GROUPS")
@NamedQuery(name="Group.findAll", query="SELECT c FROM Group c")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
