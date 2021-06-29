/**
 * 
 */
package com.fa.workflowengine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fa.workflowengine.entity.WfActionTransitionsEntity;

/**
 * @author Muruganandam, FA Softwares
 *
 */
@Repository
public interface WfActionTransitionsRepository extends JpaRepository<WfActionTransitionsEntity, Long> {

	List<WfActionTransitionsEntity> findByActionsEntityId(Long actionId);

	List<WfActionTransitionsEntity> findByIsActive(Boolean isActive);

	@Query(value = "SELECT * FROM ilm_workflow_config.wf_action_transitions wfat INNER JOIN ilm_workflow_config.wf_step_actions wfsa ON wfat.next_step_id=wfsa.step_id and wfat.action_id=wfsa.action_id and (wfsa.role_id=?1 or wfsa.role_id isnull) and wfat.current_step_id = ?2 and wfsa.action_id=?3 and wfat.is_active=?4 and wfat.rule_result=?5  and (wfat.process_type=?6); ", nativeQuery = true)
	WfActionTransitionsEntity findNextTaskStep(Long roleId, Long currentStepId, Long actionId, Boolean isActive,
			String ruleResult, String processType);
}
