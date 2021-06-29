INSERT INTO wf1.menu_items (is_active,link,"name",order_no,"role","type") VALUES
	 (true,'/module','Module',10,NULL,'Component'),
	 (true,'/status','Status',20,NULL,'Component'),
	 (true,'/auth-role','Authority Role',30,NULL,'Component'),
	 (true,'/action-mst','Action Master',40,NULL,'Component'),
	 (true,'/tasks','Task Steps',50,NULL,'Component'),
	 (true,'/action-transition','Action Transition',70,NULL,'Component'),
	 (true,'/ui-page','UI Pages',90,NULL,'Component'),
	 (true,'/ui-action-link','UI Action/Link Mapping',80,NULL,'Component'),
	 (true,'/step-action','Task/Action/Auth Role Mapping',60,NULL,'Component'),
	 (false,'/query','SQL Query',100,NULL,'Component');
INSERT INTO wf1.menu_items (is_active,link,"name",order_no,"role","type") VALUES
	 (true,'/query/ui-action-query','UI Action/Link Query',20,NULL,'Query'),
	 (true,'/query/workflow-task-step','Workflow Task Step Query',10,NULL,'Query'),
	 (true,'#','User',1,NULL,'Master'),
	 (true,'#','User Role',2,NULL,'Master'),
	 (true,'#','Menu',3,NULL,'Master');
	 
	 
--
	 INSERT INTO wf.wf_query (code,is_active,"name",query) VALUES
	 ('WFTASKSTEP',true,NULL,'SELECT * FROM ilm_workflow_config.wf_action_transitions wfat 
INNER JOIN ilm_workflow_config.wf_step_actions wfsa ON 
wfat.next_step_id=wfsa.step_id 
and wfat.action_id=wfsa.action_id 
and (wfsa.role_id=?1 or wfsa.role_id isnull) 
and wfat.current_step_id = ?2 
and wfsa.action_id=?3 
and wfat.is_active=?4 
and wfat.rule_result=?5  
and wfat.process_type=?6'),
	 ('UIACTIONLINK',true,NULL,'select * from ilm_workflow_config.inv_actions invactione0_ 
where invactione0_.role_id=?1  
and invactione0_.page_code=?2 
and invactione0_.status_id=?3 
and (invactione0_.org_type in (?4)) 
and is_active=?5 
order by order_no,id');