/**
 * 
 */
package com.fa.workflowengine.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.fa.workflowengine.action.ActionListener;
import com.fa.workflowengine.dto.BusinessProcessDto;
import com.fa.workflowengine.reqres.dto.WfRequest;

/**
 * @author Muruganandam, FA Softwares
 *
 */
public class DynamicFile {

	@Autowired
	private ApplicationContext appContext;

	public static void main(String[] args) {

		DynamicFile dynamicFile = new DynamicFile();

		BusinessProcessDto dto = new BusinessProcessDto();
		dto.setName("ASSIGNED");
		dto.setModuleName("Investigation");
		String bomObject = dynamicFile.createBusinessProcessClass(dto);
	}

	public boolean isBeanExist(String beanVal) {
		try {
			ActionListener bean1 = appContext.getBean(beanVal, ActionListener.class);
			if (bean1 == null) {
				return false;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
		return true;
	}

	public String createBusinessProcessClass(BusinessProcessDto bom) {
		String response = null;
		try {
			String name = "";
			String[] names = bom.getName().replaceAll("[^\\w\\s]", "").toLowerCase().trim().split(" ");

			for (String nm : names) {
				name = name + StringUtils.capitalize(nm);
			}
			if (!isBeanExist(name.toUpperCase())) {
				name = name.length() > 20 ? name.substring(0, 20) : name;
				name = StringUtils.capitalize(name) + "Action";
				String module = bom.getModuleName().trim().replaceAll(" ", "").toLowerCase();

				Map<String, Class<?>> fields = new HashMap<>();
				String content = createClass(bom,
						"src/main/java/" + "com.fa.workflowengine." + module + ".action.impl" + "." + name, fields,
						bom.getName().toUpperCase());
				response = "Success";
			} else {
				response = "Error:Bean already exist";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	public String createClass(BusinessProcessDto bom, String className, Map<String, Class<?>> fields, String actionCode)
			throws IOException {
		String content = "";
		StringBuilder builder = new StringBuilder();

		String packageName;
		int idx = className.lastIndexOf('.');
		if (idx >= 0) {
			packageName = className.substring(0, idx);
			className = className.substring(idx + 1);

		} else
			packageName = null;

		if (packageName != null) {
			String pack = packageName.split("/")[3];
			builder.append("package ").append(pack).append(";\n");
		}
		String beanName = bom.getModuleName().trim().replaceAll(" ", "_").toUpperCase() + "_"
				+ actionCode.replaceAll(" ", "_");
		beanName = bom.getModuleId() + "_" + bom.getId();

		builder.append("\n");
		builder.append("import org.springframework.stereotype.Service;");
		builder.append("\n");

		builder.append("import org.slf4j.Logger;");
		builder.append("\n");

		builder.append("import org.slf4j.LoggerFactory;");
		builder.append("\n");
		builder.append("\n");

		builder.append("import com.fa.workflowengine.action.ActionListener;");
		builder.append("\n");
		builder.append("\n");

		builder.append("import com.fa.workflowengine.reqres.dto.WfRequest;");
		builder.append("\n");
		builder.append("import com.fa.workflowengine.reqres.dto.WfResponse;");
		builder.append("\n");
		builder.append("\n");

		builder.append("/***");
		builder.append("\n");
		builder.append("* @author Muruganandam, FA Softwares");
		builder.append("\n");
		builder.append("*");
		builder.append("\n");
		builder.append("*/");
		builder.append("\n");
		builder.append("\n");

		builder.append("/***");
		builder.append("\n");
		builder.append("* @bean ModuleId_BusinessProcessId:(" + beanName + ")");
		builder.append("\n");
		builder.append("* @name Business Process : " + bom.getName());
		builder.append("\n");
		builder.append("* @description :" + bom.getDescription());
		builder.append("\n");
		builder.append("* @createdDate :" + bom.getCreatedDate());
		builder.append("\n");
		builder.append("*/");
		builder.append("\n");
		builder.append("\n");

		builder.append("@Service(\"" + beanName + "\")");
		builder.append("\n");
		builder.append("public class ").append(className).append(" implements ActionListener  {\n");

		builder.append("private static final Logger logger = LoggerFactory.getLogger(" + className + ".class);");
		builder.append("\n");
		builder.append("\n");

		builder.append("\t@Override");
		builder.append("\n");
		builder.append("\tpublic WfResponse<?> action(WfRequest<?> request) {");
		builder.append("\n");
		builder.append("\t\tlogger.info(\"start\");");
		builder.append("\n");
		builder.append("\t\tWfResponse<?> wfResponse = new WfResponse<>();");
		builder.append("\n");
		builder.append("\t\t//Your Business Process steps");
		builder.append("\n");
		builder.append("\t\tlogger.info(\"end\");");
		builder.append("\n");
		builder.append("\t\treturn wfResponse;");
		builder.append("\n");
		builder.append("\t}");
		builder.append("\n");
		builder.append("\n");
		boolean flag = false;
		for (Map.Entry<String, Class<?>> field : fields.entrySet()) {
			String name = field.getKey();
			Class<?> type = field.getValue();
			String returnType = type.getName();
			String nameCapitalized = Character.toUpperCase(name.charAt(0)) + name.substring(1);

			if (flag)
				builder.append("\n");
			flag = true;
			builder.append("    private ").append(returnType).append(" ").append(name).append(";\n");
			builder.append("\n");
			builder.append("    public ").append(returnType).append(" get").append(nameCapitalized).append(" ()\n");
			builder.append("    {\n");
			builder.append("        return ").append(name).append(";\n");
			builder.append("    }\n");
			builder.append("\n");
			builder.append("    public void set").append(nameCapitalized).append(" (").append(returnType).append(" ")
					.append(name).append(")\n");
			builder.append("    {\n");
			builder.append("        this.").append(name).append(" = ").append(name).append(";\n");
			builder.append("    }\n");
		}

		builder.append("}\n");
		if (packageName != null) {
			File dir = new File(
					packageName.replaceAll("\\.", Matcher.quoteReplacement(System.getProperty("file.separator"))));

			dir.mkdirs();

			BufferedWriter writer = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(new File(dir.getPath(), className + ".java"))));

			writer.write(builder.toString());
			writer.close();
			System.out.println("Java file created");
			try {
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return content;

		// dir.c
	}
}
