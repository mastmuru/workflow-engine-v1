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
@Table(name = "wf_transition_activities")
@Getter
@Setter
public class WfTransitionActivitiesEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "template_id", length = 20)
	private Long templateId;

	@Column(name = "created_by", length = 10)
	private Long createdBy;

	@Column(name = "created_date")
	private Date createdDate;

}
