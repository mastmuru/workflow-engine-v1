/**
 * 
 */
package com.fa.workflowengine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fa.workflowengine.entity.WfStatusEntity;

/**
 * @author Muruganandam, FA Softwares
 *
 */
@Repository
public interface WfStatusRepository extends JpaRepository<WfStatusEntity, Long> {

	WfStatusEntity findByModuleStatusNameAndIsActive(String code, Boolean isActive);

	WfStatusEntity findByIdAndIsActive(Long id, Boolean isActive);

	List<WfStatusEntity> findByIsActive(Boolean isActive);

	List<WfStatusEntity> findByWfModuleEntityIdAndIsActive(Long id, Boolean isActive);
}