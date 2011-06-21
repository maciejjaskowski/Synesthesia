package org.synesthesia.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.springframework.beans.factory.annotation.Autowired;

public class ReaderFactory {
	
	
	@Autowired
	static public BufferedReader fileReader(String filename) throws FileNotFoundException{
		return new BufferedReader(new FileReader(new File(filename)));
	}
}
