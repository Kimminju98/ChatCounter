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

public class DataReaderThread implements Runnable{

	File file;
	ArrayList<String> messages = new ArrayList<String>();

	public DataReaderThread(File file) {
		this.file = file;

	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		messages = readFiles(file);

	}
	
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
