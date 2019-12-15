package com.pmark.ticketingtool.model.entity;

import com.pmark.ticketingtool.model.abstractmodel.JSONBuilder;
import com.pmark.ticketingtool.model.abstractmodel.JSONBuilderRenamer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="INCIDENTS")
@NamedQuery(name="Ticket.findAll", query="SELECT c FROM Ticket c")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ticket extends JSONBuilder {

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@JoinColumn(name="RESPONSIBLE")
	@OneToOne(fetch=FetchType.EAGER, targetEntity = User.class)
	private User responsible;
	
	@Column(name="SHORT")
	private String shortDescription;
	
	@Column(name="LONGDESC")
	private String longDescription;
	
	@JoinColumn(name="STATUS_CODE")
	@OneToOne(fetch=FetchType.EAGER, targetEntity = Status.class)
	private Status status;
	
	@JoinColumn(name="GROUP_ID")
	@OneToOne(fetch=FetchType.EAGER, targetEntity = Group.class)
	private Group group;
	
	@JoinColumn(name="SEV")
	@OneToOne(fetch=FetchType.LAZY)
	private Severity severity;

	@Column(name="RESOLUTION")
	private String resolution;
	
	@Column(name="CREATED")
	private Timestamp created;
	
	@Column(name="DEADLINE")
	private Timestamp deadline;

}
