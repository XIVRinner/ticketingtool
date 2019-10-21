package com.pmark.ticketingtool.model.entity;

import com.pmark.ticketingtool.model.abstractmodel.JSONBuilder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="SEVERITY")
@NamedQuery(name="Severity.findAll", query="SELECT c FROM Severity c")
@Data
public class Severity extends JSONBuilder {

	public static final int CRITICAL = 1;
	public static final int HIGH_RISK = 2;
	public static final int MEDIUM_RISK = 3;
	public static final int LOW_RISK = 4;
	public static final int NOT_MEASURED = -1;
	
	@Id
	@Column(name="CODE")
	private int id;
	
	@Column(name="name")
	private String name;

	public Severity() {
	}



}
