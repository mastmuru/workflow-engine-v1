/**
 * 
 */
package com.fa.workflowengine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fa.workflowengine.entity.WfUIActionOrLinkEntity;

/**
 * @author Muruganandam, FA Softwares
 *
 */
@Repository
public interface WfUIActionOrLinkRepository extends JpaRepository<WfUIActionOrLinkEntity, Long> {

	WfUIActionOrLinkEntity findTop1ByActionIdAndRoleIdAndStatus(Integer actionId, Long roleId, Long statusId);

	List<WfUIActionOrLinkEntity> findByRoleIdAndPageCodeAndStatusAndIsActiveOrderByOrderNoAsc(Long roleId,
			String pageCode, Long statusId, Boolean isActive);

	List<WfUIActionOrLinkEntity> findByIsActive(Boolean isActive);

}
