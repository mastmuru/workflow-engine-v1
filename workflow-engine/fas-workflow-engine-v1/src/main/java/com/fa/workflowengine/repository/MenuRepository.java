/**
 * 
 */
package com.fa.workflowengine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fa.workflowengine.entity.UIMenuEntity;

/**
 * @author Muruganandam, FA Softwares
 *
 */
@Repository
public interface MenuRepository extends JpaRepository<UIMenuEntity, Long> {

	UIMenuEntity findByIdAndIsActive(Long id, Boolean isActive);

	List<UIMenuEntity> findByIsActiveOrderByOrderNoAsc(Boolean isActive);

	List<UIMenuEntity> findByTypeAndIsActive(String type, Boolean isActive);
}