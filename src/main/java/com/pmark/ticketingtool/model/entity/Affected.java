package com.pmark.ticketingtool.model.entity;

import com.pmark.ticketingtool.model.abstractmodel.AffectedType;
import com.pmark.ticketingtool.model.abstractmodel.JSONBuilder;
import com.pmark.ticketingtool.model.abstractmodel.JSONBuilderSkipper;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="AFFECTED")
@NamedQuery(name="Affected.findAll", query="SELECT c FROM Affected c")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Affected extends JSONBuilder {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="OBJECT_NAME")
	private String objectName;
	
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="CHANGE_ID")
	private List<Change> change;


	@Transient
	@JSONBuilderSkipper
	public AffectedType affectedType;



}
