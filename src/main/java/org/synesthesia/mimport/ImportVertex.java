package org.synesthesia.mimport;

import java.io.Serializable;

public class ImportVertex implements Serializable{

	private static final long serialVersionUID = -7074524454215049106L;
	private String packageName;
	private String className; //fully Qualified
	private String projectName;
	
	@Override
	public String toString(){
		return getProjectName() + " " + getClassName();
	} 
	
	@Override
	public int hashCode(){
		return getClassName().hashCode();
	}
	@Override
	public boolean equals(Object o){
		boolean r = o != null &&
		       getClass().getCanonicalName().equals(o.getClass().getCanonicalName()) &&
		       this.getClassName().equals(((ImportVertex) o).getClassName());
		return r;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassName() {
		return className;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectName() {
		return projectName;
	}
}
