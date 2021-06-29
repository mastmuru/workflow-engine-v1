/**
 * 
 */
package com.fa.workflowengine.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Muruganandam, FA Softwares
 *
 */
@Entity
@Table(name = "wf_task_status")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WfStatusEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "module_status_id")
	private Long moduleStatusId;

	@Column(name = "module_status_name", length = 255)
	private String moduleStatusName;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "module_id")
	private WfModuleEntity wfModuleEntity;

//	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "statusEntity")
//	private WfStepsEntity stepsEntity;
}
