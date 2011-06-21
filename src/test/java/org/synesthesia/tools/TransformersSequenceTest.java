package org.synesthesia.tools;

import static org.junit.Assert.*;

import org.apache.commons.collections15.Transformer;
import org.junit.Assert;
import org.junit.Test;
import org.synesthesia.tools.TransformersSequence;

public class TransformersSequenceTest {

	TransformersSequence<String, String> tSeq;

	private void whenNoTransformerIsSet(){
		tSeq = new TransformersSequence<String, String>();
	}
	
	static private void shouldAlwaysReturnNull(TransformersSequence<String, String> tSeq){
		Assert.assertEquals(null, tSeq.transform(""));
		Assert.assertEquals(null, tSeq.transform(null));
		Assert.assertEquals(null, tSeq.transform("dupa"));
		Assert.assertEquals(null, tSeq.transform(" "));
		Assert.assertEquals(null, tSeq.transform(" cos tam "));
	}
	
	private TransformersSequence<String, String> theTransformerSequence(){
		return tSeq;
	}
	
	private Transformer<String, String> dupaTransformer = new Transformer<String, String>(){
		public String transform(String i) {
			if(i == null) return null;
			if(i.equals("dupa")){
				return "dupcia";
			}
			return null;
		}
	};
	
	private Transformer<String, String> czlowiekTransformer = new Transformer<String, String>(){
		public String transform(String i) {
			if(i == null) return null;
			if(i.equals("czlowiek")){
				return "czlowieczek";
			}
			if(i.equals("dupa")){
				return "dupenka";
			}
			return null;
		}
	};
	
	private void whenSingleTransformerIsSet(){
		tSeq = new TransformersSequence<String, String>();
		tSeq.add(dupaTransformer);
	}
	
	private void whenMoreTransformersAreSet(){
		tSeq = new TransformersSequence<String, String>();
		tSeq.add(dupaTransformer);
		tSeq.add(czlowiekTransformer);
	}
	
	private void aNullTransformer(){
		tSeq = new TransformersSequence<String, String>();
		tSeq.add(null);
	}

	@Test
	public void test(){
		whenNoTransformerIsSet(); shouldAlwaysReturnNull(theTransformerSequence());
	}
	
	@Test
	public void test2(){
		whenSingleTransformerIsSet(); 
		assertEquals(null,theTransformerSequence().transform("costam"));
		assertEquals("dupcia", theTransformerSequence().transform("dupa"));
	}
	
	@Test
	public void test3(){
		whenMoreTransformersAreSet();
		assertEquals(null, theTransformerSequence().transform("costam"));
		assertEquals("dupcia", theTransformerSequence().transform("dupa"));
		assertEquals("czlowieczek", theTransformerSequence().transform("czlowiek"));
	}
	
	@Test
	public void test4(){
		aNullTransformer(); shouldAlwaysReturnNull(theTransformerSequence());
	}
}
