package com.pmark.ticketingtool.model.entity;

import com.pmark.ticketingtool.model.abstractmodel.JSONBuilder;
import com.pmark.ticketingtool.model.abstractmodel.JSONBuilderSkipper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="SEVERITY")
@NamedQuery(name="Severity.findAll", query="SELECT c FROM Severity c")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Severity extends JSONBuilder {

	@JSONBuilderSkipper
	public static final int CRITICAL = 1;
	@JSONBuilderSkipper
	public static final int HIGH_RISK = 2;
	@JSONBuilderSkipper
	public static final int MEDIUM_RISK = 3;
	@JSONBuilderSkipper
	public static final int LOW_RISK = 4;
	@JSONBuilderSkipper
	public static final int NOT_MEASURED = -1;
	
	@Id
	@Column(name="CODE")
	private int id;
	
	@Column(name="name")
	private String name;


}
