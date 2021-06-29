/**
 * 
 */
package com.fa.workflowengine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fa.workflowengine.entity.WfBusinessProcessEntity;

/**
 * @author Muruganandam, FA Softwares
 *
 */
@Repository
public interface WfBusinessProcessRepository extends JpaRepository<WfBusinessProcessEntity, Long> {
	List<WfBusinessProcessEntity> findByIsActive(Boolean isActive);

	WfBusinessProcessEntity findTop1ByNameAndModuleNameAndIsActiveOrderByIdDesc(String name, String moduleName,
			Boolean isActive);

	List<WfBusinessProcessEntity> findByModuleIdAndIsActive(Long moduleId, Boolean isActive);
}
