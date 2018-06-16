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
 * This is DataReader class
 * This class has getData,getDirectory, getListOfFilesFromDirectory,readFiles methods.
 * public method is only getData method.
 * Enter the directory path that cotains files and then this class read the file and save them.
 * @author MinJu
 *
 */
public class DataReader {


	/**
	 * This is getData method.
	 * It has myDir the type of File , files the type of File[] , messages the type of ArrayList<String> type memebers.
	 * First, It gets directory
	 * Second, It gets list of files from directory
	 * Third, It reads files and save the contents by line in ArrayList<String> messages.
	 * @param strDir the type of String
	 * @return ArrayList reference value , messages (That contains all of contents in the files from directory)
	 */
	public ArrayList<String> getData(String strDir,String num){
		
		ArrayList<String> messages = new ArrayList<String>();

		//1. get Directory
		File myDir = getDirectory(strDir);
	
		//2. 
		File[] files = getListOfFilesFromDirectory(myDir);
		
		//3. read file's messages using threadpool
		
		int numOfThread = Integer.parseInt(num);
		
		ArrayList<DataReaderThread> sumDatas = new ArrayList<DataReaderThread>();
		ExecutorService executor = Executors.newFixedThreadPool(numOfThread);
		
		for(File f : files) {
			DataReaderThread datareader = new DataReaderThread(f);
			sumDatas.add(datareader);
			executor.execute(datareader);
		}
		
		executor.shutdown();
		
		while (!executor.isTerminated()) { // similiar to join, wait all the thread (whter all thread is terminate)
        }
		for(DataReaderThread worker : sumDatas) {
			messages.addAll(worker.messages);
		}
		

		return messages; 
	}

	private File getDirectory(String strDir) { 

		File myDirectory = new File(strDir);
		return myDirectory;
	}
	

	private File[] getListOfFilesFromDirectory(File dataDir) {

		for(File file : dataDir.listFiles()) {
			//System.out.println(file.getAbsolutePath());
		}

		return dataDir.listFiles();
	}


}
