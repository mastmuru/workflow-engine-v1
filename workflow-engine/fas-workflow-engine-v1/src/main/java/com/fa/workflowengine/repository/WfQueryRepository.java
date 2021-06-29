/**
 * 
 */
package com.fa.workflowengine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fa.workflowengine.entity.WfQueryEntity;

/**
 * @author Muruganandam, FA Softwares
 *
 */
@Repository
public interface WfQueryRepository extends JpaRepository<WfQueryEntity, Long> {

	WfQueryEntity findByCodeAndIsActive(String code, Boolean isActive);

	WfQueryEntity findByIdAndIsActive(Long id, Boolean isActive);

	List<WfQueryEntity> findByIsActive(Boolean isActive);
}