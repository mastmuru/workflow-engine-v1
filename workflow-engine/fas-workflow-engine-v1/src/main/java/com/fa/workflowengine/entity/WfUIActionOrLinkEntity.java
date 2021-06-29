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
@Table(name = "ui_actions_links")
@Getter
@Setter
public class WfUIActionOrLinkEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "status_id", length = 20)
	private Long status;

	@Column(name = "display_name", length = 50)
	private String displayName;

	@Column(name = "action_id", length = 20)
	private Long actionId;

	@Column(name = "is_enable", length = 20)
	private Boolean isEnable;

	@Column(name = "role_id", length = 20)
	private Long roleId;

	@Column(name = "page_code", length = 50)
	private String pageCode;

	@Column(name = "attribute", length = 3000)
	private String attribute;

	@Column(name = "type", length = 50)
	private String type;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "code", length = 50)
	private String code;

	@Column(name = "org_type", length = 50)
	private String orgType;

	@Column(name = "is_active", length = 50)
	private Boolean isActive;

	@Column(name = "order_no", length = 50)
	private Integer orderNo;

	@Column(name = "module_id", length = 50)
	private Long moduleId;

	@Column(name = "module_name")
	private String moduleName;

	@Column(name = "status_name")
	private String statusName;

	@Column(name = "action_name")
	private String actionName;

	@Column(name = "role_name")
	private String roleName;

	@Column(name = "wf_status_id")
	private Long wfStatusId;

	@Column(name = "wf_role_id")
	private Long wfRoleId;

	@Column(name = "bp_id")
	private Long bpId;

	@Column(name = "bp_name")
	private String bpName;
}
