package org.synesthesia.spring;

import java.io.Serializable;
import java.util.Date;

public class SpringVertex implements Serializable{

	private static final long serialVersionUID = -7074524454215049106L;
	private String packageName;
	private String className;
	public String beanName;
	private String projectName;
	private Date date;
	
	@Override
	public String toString(){
		return getBeanName();
	} 
	
	@Override
	public int hashCode(){
		return getBeanName().hashCode();
	}
	@Override
	public boolean equals(Object o){
		boolean r = o != null &&
		       getClass().getCanonicalName().equals(o.getClass().getCanonicalName()) &&
		       this.getBeanName().equals(((SpringVertex) o).getBeanName());
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

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public String getBeanName() {
		return beanName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return date;
	}
	
}
