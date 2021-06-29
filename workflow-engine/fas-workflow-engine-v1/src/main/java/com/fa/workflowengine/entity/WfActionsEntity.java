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
@Table(name = "wf_actions")
@Getter
@Setter
public class WfActionsEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "code", length = 255)
	private String code;

	@Column(name = "display_name", length = 255)
	private String displayName;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "created_by")
	private Long createdBy;

//	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JoinColumn(name = "module_id")
//	private WfModuleEntity wfModuleEntity;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "actionsEntity")
	private List<WfActionTransitionsEntity> wfActionTransitionsEntities;

}
