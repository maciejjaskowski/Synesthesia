package org.synesthesia.spring;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.synesthesia.model.VertexFactory;

public class SpringVertexFactory implements VertexFactory<SpringVertex>{

	private static final long serialVersionUID = -5472415537636994406L;

	public SpringVertex create(Date date, String beanName){
		SpringVertex bVertex = new SpringVertex();
		bVertex.setDate(date);
		bVertex.setBeanName(beanName);
		return bVertex;
	}
	
	public SpringVertex create(String className, String packageName, String projectName){
		SpringVertex bVertex = new SpringVertex();
		bVertex.setClassName(className);
		bVertex.setPackageName(packageName);
		bVertex.setProjectName(projectName);
		return bVertex;
	}
	
	static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss,SSS");

	public SpringVertex create(String... args){
		try {
			return create(dateFormat.parse(args[0]), args[1]);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
}
