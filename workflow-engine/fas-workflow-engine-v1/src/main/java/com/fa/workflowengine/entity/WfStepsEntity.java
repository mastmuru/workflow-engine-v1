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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Muruganandam, FA Softwares
 *
 */

@Entity
@Table(name = "wf_steps")
@Getter
@Setter
public class WfStepsEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "status_id", length = 20)
	private Long statusId;

	@Column(name = "status_name", length = 300)
	private String statusName;

	@Column(name = "description", length = 1000)
	private String description;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "role_id", length = 20)
	private Long roleId;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "created_by")
	private Long createdBy;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "module_id")
	private WfModuleEntity wfModuleEntity;

	@OneToMany(mappedBy = "stepsEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<WfStepsActionsEntity> wfStepsActionsEntities;

//	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = WfStatusEntity.class)
//	@JoinColumn(name = "status_id")
//	private WfStatusEntity statusEntity;
}
