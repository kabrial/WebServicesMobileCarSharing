package com.carSharing.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Model group
 * 
 * @author Kevin ABRIAL & Amine IDIR & Alexis BARTHELEMY
 *
 */
@Entity
@Table(name = "group")
public class Group implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1210174642121971942L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	@OneToMany(mappedBy = "group")
	private List<Child> childs;
	@OneToMany(mappedBy = "group")
	private List<ExcursionGroup> excursionGroups;

	public Group() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Child> getChilds() {
		return childs;
	}

	public void setChilds(List<Child> childs) {
		this.childs = childs;
	}

	public List<ExcursionGroup> getExcursionGroups() {
		return excursionGroups;
	}

	public void setExcursionGroups(List<ExcursionGroup> excursionGroups) {
		this.excursionGroups = excursionGroups;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
