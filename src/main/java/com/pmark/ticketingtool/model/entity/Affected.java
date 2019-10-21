package com.pmark.ticketingtool.model.entity;

import com.pmark.ticketingtool.model.abstractmodel.AffectedType;
import com.pmark.ticketingtool.model.abstractmodel.JSONBuilder;
import com.pmark.ticketingtool.model.abstractmodel.JSONBuilderSkipper;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="AFFECTED")
@NamedQuery(name="Affected.findAll", query="SELECT c FROM Affected c")
@Data
public class Affected extends JSONBuilder {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="OBJECT_NAME")
	private String objectName;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CHANGE_ID")
	private Change change;


	@Transient
	@JSONBuilderSkipper
	public AffectedType affectedType;

	private Affected(Builder builder) {
		setObjectName(builder.objectName);
		setChange(builder.change);
	}

	public Affected() {
	}

	public static final class Builder {
		private String objectName;
		private Change change;

		public Builder() {
		}

		public Builder withObjectName(String val) {
			objectName = val;
			return this;
		}

		public Builder withChange(Change val) {
			change = val;
			return this;
		}

		public Affected build() {
			return new Affected(this);
		}
	}
}
