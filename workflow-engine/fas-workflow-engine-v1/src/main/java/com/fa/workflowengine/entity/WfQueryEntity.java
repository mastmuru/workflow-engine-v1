/**
 * 
 */
package com.fa.workflowengine.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "wf_query")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WfQueryEntity implements Serializable {

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

	@Column(name = "name", length = 255)
	private String name;

	@Column(name = "query", length = 3000)
	private String query;

	@Column(name = "is_active")
	private Boolean isActive;

//	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "statusEntity")
//	private WfStepsEntity stepsEntity;
}
