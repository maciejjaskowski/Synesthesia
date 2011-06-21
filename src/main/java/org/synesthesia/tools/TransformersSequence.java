package org.synesthesia.tools;

import java.util.Collection;
import java.util.LinkedList;

import org.apache.commons.collections15.Transformer;

public class TransformersSequence<I,O> implements Transformer<I,O>{

	LinkedList<Transformer<I,O>> transformers = new LinkedList<Transformer<I,O>>();
	final LinkedList<Transformer<I,O>> defaultTransformers = new LinkedList<Transformer<I,O>>();
	
	public TransformersSequence(){
		super();
	}
	
	public TransformersSequence(Transformer<I,O> transformer){
		this();
		this.transformers.add(transformer);
		this.defaultTransformers.add(transformer);
	}
	
	public TransformersSequence(Collection<Transformer<I,O>> transformers){
		this();
		this.transformers.addAll(transformers);
		this.defaultTransformers.addAll(transformers);
	}
	
	public TransformersSequence<I, O> resetToDefaults(){
		transformers.clear();
		transformers.addAll(defaultTransformers);
		return this;
	}
	
	public TransformersSequence<I, O> addAll(Collection<Transformer<I,O>> transformers){
		this.transformers.addAll(transformers);
		return this;
	}
	
	public TransformersSequence<I, O> add(Transformer<I,O> transformer){
		transformers.add(transformer);
		return this;
	}

	public O transform(I input) {
		for(Transformer<I,O> t : transformers){
			if(t == null) continue;
			O output = t.transform(input);
			if(output != null){
				return output;
			}
		}
		return null;
	}
}
