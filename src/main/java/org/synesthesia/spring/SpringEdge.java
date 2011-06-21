package org.synesthesia.spring;

import java.io.Serializable;


public class SpringEdge extends Edge implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7248189669822995163L;
	
	public final String name;
	
	public SpringEdge(String name){
		this.name = name;
	}
	
	@Override
	public String toString(){
		return getMultiplicity() + name;
	} 
	
	@Override
	public int hashCode(){
		return name.hashCode();
	}
	@Override
	public boolean equals(Object o){
		
		boolean r = o != null &&
		       getClass().getCanonicalName().equals(o.getClass().getCanonicalName()) &&
		       name.equals(((SpringEdge) o).name);
		       
		return r;
	}
}
