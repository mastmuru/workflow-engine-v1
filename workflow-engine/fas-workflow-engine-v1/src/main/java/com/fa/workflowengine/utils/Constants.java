/**
 * 
 */
package com.fa.workflowengine.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Muruganandam, FA Softwares
 *
 */
public interface Constants {
	String javaFilePath = "D:\\FAS Projects\\BAGIC-ILM\\Development\\ILM-Projects\\fas-workflow-engine-v1\\src\\main\\java\\";
	String packagePath = "com\\fa\\workflowengine\\action\\impl";

	public static Map<String, String> getActions() {
		Map<String, String> map = new HashMap<String, String>();

		map.put("1", "Accept");
		map.put("2", "Allocate");
		map.put("3", "Approve");
		map.put("4", "Decline");
		map.put("5", "Failed Allocation");
		map.put("6", "New");
		map.put("7", "Pending Allocation");
		map.put("8", "Quick Update");
		map.put("9", "Submit Report");
		map.put("10", "Sendback");
		map.put("11", "Non-Potential");
		map.put("12", "Withdraw");
		map.put("13", "Triggers");
		map.put("14", "Deterence");
		map.put("15", "Set Reserve");
		map.put("16", "Request for Payment");
		// map.put("17", "NA");
		map.put("18", "Invoice Submission");
		map.put("19", "Non-Potential");
		map.put("20", "Desktop Investigation");
		map.put("21", "Closed");
		map.put("22", "Deterrence -Accepted with More Docs Required");
		map.put("23", "Accept Deterrence");
		map.put("24", "Reject Deterrence");
		map.put("25", "Sendback Deterrence");
		map.put("26", "Approve Invoice");
		map.put("27", "Sent Back Invoice");
		map.put("28", "Approve Report");
		map.put("29", "Aprrove Report and Invoice");
		map.put("30", "Allocate");
		map.put("31", "Send Back");
		map.put("32", "Withdraw");
		map.put("33", "Approve Invoice Payment");
		map.put("34", "Submit Invoice");
		map.put("35", "Submit Invoice");

		// Alternative way
		Map<String, String> result2 = new LinkedHashMap<>();
		map.entrySet().stream().sorted(Map.Entry.<String, String>comparingByValue())
				.forEachOrdered(x -> result2.put(x.getKey(), x.getValue()));

		return result2;

	}

	public static List<String> getActionList() {
		List<String> list = new ArrayList<String>();
		list.add("Accept");
		list.add("Allocate");
		list.add("Approve");
		list.add("Decline");
		list.add("Failed Allocation");
		list.add("New");
		list.add("Pending Allocation");
		list.add("Quick Update");
		list.add("Submit Report");
		list.add("Sendback");
		list.add("Non-Potential");
		list.add("Withdraw");
		list.add("Triggers");
		list.add("Deterence");
		list.add("Set Reserve");
		list.add("Request for Payment");
		// list.add("NA");
		list.add("Invoice Submission");
		list.add("Non-Potential");
		list.add("Desktop Investigation");
		list.add("Closed");
		list.add("Deterrence -Accepted with More Docs Required");
		list.add("Accept Deterrence");
		list.add("Reject Deterrence");
		list.add("Sendback Deterrence");
		list.add("Approve Invoice");
		list.add("Sent Back Invoice");
		list.add("Approve Report");
		list.add("Aprrove Report and Invoice");
		list.add("Allocate");
		list.add("Send Back");
		list.add("Withdraw");
		list.add("Approve Invoice Payment");
		list.add("Submit Invoice");
		list.add("Submit Invoice");
		return list;
	}

	public static List<String> dropdownItems() {
		List<String> list = new ArrayList<String>();
		list.add("Module");
		list.add("Status");
		list.add("Authority Role");
		list.add("Action Master");
		list.add("Task Steps");
		list.add("Auth Role/Task/Action Mapping");
		list.add("Action Transition");
		list.add("UI Pages");
		list.add("UI Action/Link Mapping");
		return list;
	}

}
