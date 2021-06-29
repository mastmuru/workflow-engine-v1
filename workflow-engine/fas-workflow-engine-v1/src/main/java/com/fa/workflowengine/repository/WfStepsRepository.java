/**
 * 
 */
package com.fa.workflowengine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fa.workflowengine.entity.WfActionsEntity;
import com.fa.workflowengine.entity.WfStepsEntity;

/**
 * @author Muruganandam, FA Softwares
 *
 */
@Repository
public interface WfStepsRepository extends JpaRepository<WfStepsEntity, Long> {

	WfStepsEntity findByDescription(String stepCode);

	WfStepsEntity findByIdAndIsActive(Long id, Boolean isActive);

	List<WfStepsEntity> findByDescriptionNotIn(List<String> description);

	List<WfStepsEntity> findByDescriptionNotInAndIsActive(List<String> description, Boolean isActive);

	List<WfStepsEntity> findByIsActive(Boolean isActive);

	List<WfStepsEntity> findByWfModuleEntityIdAndIsActive(Long id, Boolean isActive);

}
