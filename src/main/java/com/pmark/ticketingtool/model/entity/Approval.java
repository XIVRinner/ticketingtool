package com.pmark.ticketingtool.model.entity;

import com.pmark.ticketingtool.model.abstractmodel.JSONBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="APPROVALS")
@NamedQuery(name="Approval.findAll", query="SELECT c FROM Approval c")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Approval extends JSONBuilder {
	/**
	 * smth
	 */

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private int id;
	
	@JoinColumn(name="CHANGE_ID")
	@OneToOne(fetch=FetchType.LAZY)
	private Change change;
	
	
	@JoinColumn(name="STATUS_CODE")
	@OneToOne(fetch=FetchType.LAZY)
	private Status status;



}
