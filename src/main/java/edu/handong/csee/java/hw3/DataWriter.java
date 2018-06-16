package edu.handong.csee.java.hw3;

import java.io.BufferedWriter;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map.Entry;
import java.io.PrintWriter;
import java.util.*;
import java.io.*;


/**
 * This is DataWriter class
 * It has getOutputFile method.
 * It makes file and write list of lines to file.
 * @author Minju
 *
 */
public class DataWriter {


	/**
	 * This is getOutputFile method.
	 * It makes file based on parameter, filename
	 * And writes lines to file until the lines end
	 * @param linesTowrite , the type is ArrayList<String>
	 * @param filename , the type if String
	 */
	public void getOutputFile(ArrayList<String> linesTowrite,String filename) {


		PrintWriter out = null;

		try {
			out = new PrintWriter(filename);
			out.println("kako_id, count");

			for(String s : linesTowrite) {
				//System.out.println(1);
				//System.out.println("1" + s);
				out.write(s +"\n");
			}

		} catch(FileNotFoundException e) {
			System.out.println("Error opening the file " + filename);
			System.exit(0);
		}

		out.close();
	}

}
