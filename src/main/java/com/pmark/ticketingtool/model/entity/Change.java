package com.pmark.ticketingtool.model.entity;

import com.pmark.ticketingtool.model.abstractmodel.JSONBuilder;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name="CHANGES")
@NamedQuery(name="Change.findAll", query="SELECT c FROM Change c")
@Data
@Builder(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class Change extends JSONBuilder {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@JoinColumn(name="RESPONSIBLE")
	@OneToOne(fetch=FetchType.EAGER)
	private User responsible;
	
	@Column(name="SHORT")
	private String shortDescription;
	
	@Column(name="LONG")
	private String longDescription;
	
	@JoinColumn(name="STATUS_CODE")
	@OneToOne(fetch=FetchType.EAGER)
	private Status status;
	
	@JoinColumn(name="GROUP_ID")
	@OneToOne(fetch=FetchType.EAGER)
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

	@Transient
	List<Approval> approvals;




}
