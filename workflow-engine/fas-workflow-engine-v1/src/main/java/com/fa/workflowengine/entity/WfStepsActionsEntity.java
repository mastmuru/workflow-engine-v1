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

import lombok.Getter;
import lombok.Setter;

/**
 * @author Muruganandam, FA Softwares
 *
 */
@Entity
@Table(name = "wf_step_actions")
@Getter
@Setter
public class WfStepsActionsEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "role_id")
	private Long roleId;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = WfActionsEntity.class)
	@JoinColumn(name = "action_id")
	private WfActionsEntity actionsEntity;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = WfStepsEntity.class)
	@JoinColumn(name = "step_id")
	private WfStepsEntity stepsEntity;

//	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = WfAuthRoleEntity.class)
//	@JoinColumn(name = "role_id")
//	private WfAuthRoleEntity authRoleEntity;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = WfActionTransitionsEntity.class)
	@JoinColumn(name = "action_trans_id")
	private WfActionTransitionsEntity actionTransitionsEntity;

	@Column(name = "is_active")
	private Boolean isActive;

}
