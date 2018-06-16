package edu.handong.csee.java.hw3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This is DataReaderThread class
 * This class has run,getMessagesFromThread,readFiles methods and File, ArrayList<String> members.
 * This class implements Runnable
 * 
 * This class read file's contents and save it to ArrayList<String> messages
 * 
 * @author Minju
 *
 */
public class DataReaderThread implements Runnable{

	File file;
	ArrayList<String> messages = new ArrayList<String>();

	/**
	 * This is constructor of DataReaderThread.
	 * @param file
	 */
	public DataReaderThread(File file) {
		this.file = file;

	}
	/**
	 * This is run method.
	 * It call readFiles method and save message to ArrayList,messages
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		messages = readFiles(file);

	}
	/**
	 * This is getMessagesFromThread method
	 * It return messages.
	 * @return ArrayList<String> messages
	 */
	public ArrayList<String> getMessagesFromThread(){
		return messages;
	}


	private ArrayList<String> readFiles(File file){

		BufferedReader br = null;

		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
			String line ="";

			//System.out.println(f.getName());


			while((line=br.readLine())!=null) {
				//System.out.println(line);
				messages.add(line);
			}


		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			System.out.println("Cannot find file " + e);
		}


		return messages;
	}
}
