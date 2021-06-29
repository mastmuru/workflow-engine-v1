/**
 * 
 */
package com.fa.workflowengine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fa.workflowengine.entity.WfStepsActionsEntity;

/**
 * @author Muruganandam, FA Softwares
 *
 */
@Repository
public interface WfStepsActionsRepository extends JpaRepository<WfStepsActionsEntity, Long> {
	WfStepsActionsEntity findByIdAndIsActive(Long id, Boolean isActive);

	List<WfStepsActionsEntity> findByIsActive(Boolean isActivve);

	List<WfStepsActionsEntity> findByActionTransitionsEntityId(Long id);
}
