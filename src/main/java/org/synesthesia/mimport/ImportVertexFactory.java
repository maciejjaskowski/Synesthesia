package org.synesthesia.mimport;

import org.synesthesia.model.VertexFactory;

public class ImportVertexFactory implements VertexFactory<ImportVertex>{	
	private static final long serialVersionUID = 2638245307315549591L;

	public ImportVertex createS(String className, String packageName, String projectName){
		ImportVertex v = new ImportVertex();
		v.setClassName(className);
		v.setPackageName(packageName);
		v.setProjectName(projectName);
		return v;
	}
	
	public ImportVertex create(String... args) {
		return createS(args[0], args[1], args[2]);		
	}
}
