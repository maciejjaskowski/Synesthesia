package org.synesthesia;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.synesthesia.mimport.ImportVertex;
import org.synesthesia.model.EdgeFactory;
import org.synesthesia.model.MyGraph;
import org.synesthesia.model.VertexFactory;
import org.synesthesia.spring.SpringEdge;
import org.synesthesia.spring.SpringVertex;

public class MyGraphFactory {
	
	private static Logger logger = Logger.getLogger(MyGraphFactory.class);
	
	static public MyGraph<SpringVertex, SpringEdge> create(
			BufferedReader br, 
			VertexFactory<SpringVertex> vertexFactory, 
			EdgeFactory<SpringEdge> edgeFactory){
		MyGraph<SpringVertex, SpringEdge> graph = new MyGraph<SpringVertex, SpringEdge>(vertexFactory, edgeFactory);
		
		Pattern autowiring = Pattern.compile("([- :,0-9]*) [^<]*.Autowiring by type from bean name '([#.a-zA-Z_0-9]*)' to bean named '([.#a-zA-Z_0-9]*)'");
		String line;
			try {
				while((line = br.readLine()) != null){
					Matcher m; 
					if((m = autowiring.matcher(line)).find()){
						graph.addEdge(vertexFactory.create(m.group(1), m.group(2)), 
								      vertexFactory.create(m.group(1), m.group(3)));
					}
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		return graph;
	}
	
	static Pattern patterns[] = new Pattern[]{
		Pattern.compile("workspace/([^/]*)/(.*)/(com.syncron[_0-9a-zA-Z/]*).java. *import ([._0-9a-zA-Z]*);"),
		Pattern.compile("workspace/([^/]*)/(.*)/(se.sync[_0-9a-zA-Z/]*).java. *import ([._0-9a-zA-Z]*);"),
		Pattern.compile("workspace/([^/]*)/(.*)/(org.junit[_0-9a-zA-Z/]*).java. *import ([._0-9a-zA-Z]*);"),
		Pattern.compile("workspace/([^/]*)/(.*)/(org.seleniuminspector[_0-9a-zA-Z/]*).java. *import ([._0-9a-zA-Z]*);")
	};
	
	
	static public String[] importToStrings(String line){
		String[] result = new String[4];
		try{
			for(int i = 0; i < patterns.length; i++){
				Matcher matcher = patterns[i].matcher(line);
				if(matcher.find()){
					result[0] = matcher.group(3).replace("/", ".");
					result[1] = result[0].substring(0, result[0].lastIndexOf("."));
					result[2] = matcher.group(1);
					result[3] = matcher.group(4);
					return result;
				}
			}

		}catch(Exception e){
			throw new RuntimeException("Failed on line: " + line, e);
		}
		throw new RuntimeException("Not supported yet! " + line);
	}
	
	static public MyGraph<ImportVertex,SpringEdge> createImportVertices(BufferedReader br,
			MyGraph<ImportVertex, SpringEdge> graph){
		
		try {
			String line;
			int i = 0;
			while((line = br.readLine()) != null){
				i++;
				if(line.contains("//")){
					logger .warn("Ommiting line " + i);
					continue;
				}
				String[] params = importToStrings(line);
				ImportVertex v = graph.getVertexFactory().create(params);
				graph.addVertex(v);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return graph;
	}
	
	static public MyGraph<ImportVertex, SpringEdge> createImport(
			BufferedReader br,
			MyGraph<ImportVertex, SpringEdge> graph){		
		
		try {
			String line;
			
			while((line = br.readLine()) != null){	
				if(line.contains("//")){
					continue;
				}
				String[] params = importToStrings(line);
				ImportVertex father = graph.getVertexFactory().create(params);
				
				String fullyQualifiedClassName = params[3];
				
				ImportVertex son = graph.getVertexFactory().create(fullyQualifiedClassName, "", "");
				if(graph.containsVertex(son))
					graph.addEdge(graph.getEdgeFactory().create(), father, son);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return graph;
	}
}
