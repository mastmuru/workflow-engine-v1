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
@Table(name = "wf_action_transitions")
@Getter
@Setter
public class WfActionTransitionsEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "action_code", length = 255)
	private String actionCode;

	@Column(name = "current_step_id", length = 20)
	private Long currentStepId;

	@Column(name = "current_step_code", length = 255)
	private String currentStepCode;

	@Column(name = "next_step_id", length = 20)
	private Long nextStepId;

	@Column(name = "next_step_code", length = 255)
	private String nextStepCode;

	@Column(name = "rule_result", length = 255)
	private String rule_result;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "created_by", length = 10)
	private Long createdBy;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "process_type", length = 100)
	private String processType;

	@Column(name = "message", length = 100)
	private String message;

	@Column(name = "module_id", length = 20)
	private Long moduleId;

	@Column(name = "rule_description", length = 5000)
	private String ruleDescription;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = WfBusinessProcessEntity.class)
	@JoinColumn(name = "business_step_id")
	private WfBusinessProcessEntity businessProcessEntity;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = WfActionsEntity.class)
	@JoinColumn(name = "action_id")
	private WfActionsEntity actionsEntity;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "actionTransitionsEntity")
	private List<WfRulesEntity> wfRulesEntities;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "actionTransitionsEntity")
	private List<WfStepsActionsEntity> stepsActionsEntities;
}
