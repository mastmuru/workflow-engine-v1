/**
 * 
 */
package com.fa.workflowengine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fa.workflowengine.entity.UIPageEntity;


/**
 * @author Muruganandam, FA Softwares
 *
 */
@Repository
public interface UIPageRepository extends JpaRepository<UIPageEntity, Long> {

	UIPageEntity findByCodeAndIsActive(String code, Boolean isActive);

	UIPageEntity findByIdAndIsActive(Long id, Boolean isActive);

	List<UIPageEntity> findByIsActive(Boolean isActive);
}