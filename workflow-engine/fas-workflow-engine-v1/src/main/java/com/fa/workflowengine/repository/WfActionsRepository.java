/**
 * 
 */
package com.fa.workflowengine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fa.workflowengine.entity.WfActionsEntity;

/**
 * @author Muruganandam, FA Softwares
 *
 */
@Repository
public interface WfActionsRepository extends JpaRepository<WfActionsEntity, Long> {

	WfActionsEntity findByCodeAndIsActive(String code, Boolean isActive);

	WfActionsEntity findByIdAndIsActive(Long code, Boolean isActive);

	List<WfActionsEntity> findByIsActive(Boolean isActive);

	// List<WfActionsEntity> findByWfModuleEntityIdAndIsActive(Long id, Boolean
	// isActive);
}