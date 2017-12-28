package com.carSharing.model;

import java.io.Serializable;

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
	@ManyToOne
	private Group group;
	@ManyToOne
	private Excursion excursion;

}
