/**
 * 
 */
package com.fa.workflowengine.entity;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "wf_rules")
@Getter
@Setter
public class WfRulesEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "code", length = 100)
	private String code;

	@Column(name = "min", length = 20)
	private Double min;

	@Column(name = "max", length = 20)
	private Double max;

	@Column(name = "result", length = 100)
	private String result;

	@Column(name = "line_of_business", length = 100)
	private String lineOfBusiness;

	@Column(name = "claim_type", length = 100)
	private String claimType;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "created_by")
	private Long createdBy;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "rule_description", length = 5000)
	private String ruleDescription;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = WfActionTransitionsEntity.class)
	@JoinColumn(name = "action_trans_id")
	private WfActionTransitionsEntity actionTransitionsEntity;

}
