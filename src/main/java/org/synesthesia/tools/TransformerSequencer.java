package org.synesthesia.tools;

import java.util.Arrays;

import org.apache.commons.collections15.Transformer;

public class TransformerSequencer {

	static public <I,O>Transformer<I,O> sequence(final Transformer<I,O>... transformers){
		return new TransformersSequence<I, O>(Arrays.asList(transformers));
	}
}
