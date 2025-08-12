package com.academico.models.service.components;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

public class EmailTemplate {
	
	private String templateId;
	private String template;
	private Map<String, Object> parameters;

	public EmailTemplate(String templateId) {
		
		this.templateId = templateId;
		try {
			this.template = loadTemplate(templateId);
		} catch (Exception e) {
			this.template = "Empty";
		}
		
		
	}
	
	public String getTemplate(Map<String, String> replacements) {
		String cTemplate = this.template;
 		for (Map.Entry<String, String> entry : replacements.entrySet()) {
			cTemplate = cTemplate.replace("{{" + entry.getKey() + "}}", entry.getValue());
		}
		return cTemplate;
	}
	
	private String loadTemplate(String templateId) throws Exception {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(templateId).getFile());
		String content = "Empty";
		try {
			content = new String(Files.readAllBytes(file.toPath()));
		} catch (IOException e) {
			throw new Exception("Could not read template with ID = " + templateId);
		}
		return content;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public Map<String, Object> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}
 
	

}
