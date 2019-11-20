package com.pmark.ticketingtool.model.entity;

import com.pmark.ticketingtool.model.abstractmodel.JSONBuilder;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="CUSTOMERS")
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
@Data
@Builder
@NoArgsConstructor

public class Customer extends JSONBuilder {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private int id;
	
	@Column(name="NAME")
	private String name;
	
	
	@Column(name="ORG")
	private String org;


}
