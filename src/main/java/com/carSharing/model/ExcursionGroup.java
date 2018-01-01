package com.carSharing.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Model ExcursionGroup
 * 
 * @author Kevin ABRIAL & Amine IDIR & Alexis BARTHELEMY
 *
 */
@Entity
@Table(name = "excursiongroup")
public class ExcursionGroup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2161417440033344643L;
	@EmbeddedId
	private ExcursionGroupPK id;
	@ManyToOne
	private Group group;
	@ManyToOne
	private Excursion excursion;

	public ExcursionGroup() {
	}

	public ExcursionGroupPK getId() {
		return id;
	}

	public void setId(ExcursionGroupPK id) {
		this.id = id;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Excursion getExcursion() {
		return excursion;
	}

	public void setExcursion(Excursion excursion) {
		this.excursion = excursion;
	}

}
