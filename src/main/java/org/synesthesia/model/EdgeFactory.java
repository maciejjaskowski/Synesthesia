package org.synesthesia.model;

import java.io.Serializable;


public interface EdgeFactory<E> extends Serializable{
	public E create();
	public E create(String from, String to);
}
