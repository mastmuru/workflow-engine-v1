/**
 * 
 */
package com.fa.workflowengine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fa.workflowengine.entity.WfModuleEntity;

/**
 * @author Muruganandam, FA Softwares
 *
 */
@Repository
public interface WfModuleRepository extends JpaRepository<WfModuleEntity, Long> {

	List<WfModuleEntity> findByTypeAndIsActive(String claimType, Boolean true1);

	List<WfModuleEntity> findByIsActive(Boolean isActive);

	WfModuleEntity findByIdAndIsActive(Long id, Boolean isActive);
}
