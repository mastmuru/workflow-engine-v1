/**
 * 
 */
package com.fa.workflowengine.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Muruganandam, FA Softwares
 *
 */
@Entity
@Table(name = "wf_business_process")
@Getter
@Setter
public class WfBusinessProcessEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name", length = 255)
	private String name;

	@Column(name = "description", length = 255)
	private String description;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "created_by")
	private Long createdBy;

	@Column(name = "action_code")
	private String actionCode;

	@Column(name = "module_id")
	private Long moduleId;

	@Column(name = "module_name")
	private String moduleName;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "businessProcessEntity")
	private List<WfActionTransitionsEntity> wfActionTransitionsEntities;
}
