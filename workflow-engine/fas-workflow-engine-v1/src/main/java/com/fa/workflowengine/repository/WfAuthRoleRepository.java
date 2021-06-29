/**
 * 
 */
package com.fa.workflowengine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fa.workflowengine.entity.WfActionsEntity;
import com.fa.workflowengine.entity.WfAuthRoleEntity;

/**
 * @author Muruganandam, FA Softwares
 *
 */
@Repository
public interface WfAuthRoleRepository extends JpaRepository<WfAuthRoleEntity, Long> {

	WfAuthRoleEntity findByModuleRoleNameAndIsActive(String code, Boolean isActive);

	WfAuthRoleEntity findByIdAndIsActive(Long id, Boolean isActive);

	List<WfAuthRoleEntity> findByIsActive(Boolean isActive);

	List<WfAuthRoleEntity> findByIdInAndIsActive(List<Long> ids, Boolean isActive);

	List<WfAuthRoleEntity> findByModuleRoleIdInAndIsActive(List<Long> ids, Boolean isActive);
	
	WfAuthRoleEntity findByModuleRoleIdAndIsActive(Long id, Boolean isActive);

	List<WfAuthRoleEntity> findByWfModuleEntityIdAndIsActive(Long id, Boolean isActive);

}