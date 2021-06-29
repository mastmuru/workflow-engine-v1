/**
 * 
 */
package com.fa.workflowengine.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


/**
 * @author Muruganandam, FA Softwares
 *
 */
@Entity
@Table(name = "wf_module")
@Getter
@Setter
public class WfModuleEntity implements Serializable {

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

	@Column(name = "description", length = 1000)
	private String description;

	@Column(name = "type", length = 255, unique = true)
	private String type;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "created_by", length = 10)
	private Long createdBy;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "modified_by", length = 10)
	private Long modifiedBy;

	@Column(name = "modified_date")
	private Date modifiedDate;
}
