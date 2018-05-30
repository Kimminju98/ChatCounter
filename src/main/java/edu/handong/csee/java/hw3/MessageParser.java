package edu.handong.csee.java.hw3;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * This is MessageParser Class
 * It has ArrayList<MessageLists> member.
 * It has runMessageParser, renewMessages, parseFile, parseFileOfTXT, parseFileOfCSV methods.
 * It gets ArrayList<String> and it removes unnecessary lines and parses the lines to match MessageLists type.
 * Finally, it saves objects that type is MessageLists to ArrayList<MessageLists> and return it. 
 * @author User
 *
 */
public class MessageParser {


	ArrayList<MessageLists> list = new ArrayList<MessageLists>();

	/**
	 * This runMessageParser method.
	 * It runs MessageParser class
	 * First, It calls renewMessages method to remove unnecessary lines
	 * Second, It calls parseFile method to parse the line to match each of lines to MessageLists type. 
	 * And, the parsed lines are saved in ArrayList<MessageLists> list.
	 * @param messages, the type is ArrayList<String>
	 * @return list , the type is ArrayList<MessageLists>
	 */
	public ArrayList<MessageLists> runMessageParser(ArrayList<String> messages) {

		messages = renewMessages(messages);
		
		parseFile(messages);

		return list;
	}

	private ArrayList<String> renewMessages(ArrayList<String> messages) {


		int length = messages.size();
		int i=length-1;

		String txtLinepattern = "-+\\s([0-9]+).\\s([0-9]).\\s([0-9]+).";
		String txtpattern = "\\[(.+)\\]\\s\\[(.+)\\s([0-9]+)\\:([0-9]+)\\]\\s(.+)";
		String csvpattern ="([0-9]+)\\-([0-9]+)\\-([0-9]+)\\s([0-9]+)\\:([0-9]+)\\:..\\,\\\"(.+)\\\"\\,\\\"(.+)";
		Pattern r1 = Pattern.compile(txtLinepattern);
		Pattern r2 = Pattern.compile(txtpattern);
		Pattern r3 = Pattern.compile(csvpattern);

		while(i>=0) {

			String s = messages.get(i);
			//System.out.println(s);

			Matcher m1 = r1.matcher(s);
			Matcher m2 = r2.matcher(s);			
			Matcher m3 = r3.matcher(s);



			if(!m1.find() && !m2.find() && !m3.find()) {
				//System.out.println("remove"+s);
				messages.remove(i);
				i--;
				continue;
			}

			//System.out.println(s);
			i--;
		}

		return messages;

	}

	private void parseFile(ArrayList<String> messages) {



		int year=0;
		int month=0;
		int day=0;


		for(String s : messages) {
			//System.out.println(s);
			int count=0;
			char c = s.charAt(0);
			if(c=='-') {

				String firstLine = s;
				String fistpattern = "-+\\s([0-9]+).\\s([0-9]).\\s([0-9]+).";
				Pattern r1 = Pattern.compile(fistpattern);
				Matcher m1 = r1.matcher(firstLine);

				if(m1.find()) {
					year = Integer.parseInt(m1.group(1));
					month = Integer.parseInt(m1.group(2));
					day = Integer.parseInt(m1.group(3));
				}
			}
			if(c=='[') {
				parseFileOfTXT(count,s,year,month,day);
				count++;
			}

			if(c=='2') {
				parseFileOfCSV(count,s);
				count++;
			}
		}


	}


	private void parseFileOfTXT(int count,String s,int year,int month, int day) {

		String name="";
		String halfday="";
		int time;
		int hour =0;
		int minute =0;
		String message="";


		String pattern = "\\[(.+)\\]\\s\\[(.+)\\s([0-9]+)\\:([0-9]+)\\]\\s(.+)";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(s);

		if(m.find()) {
			name = m.group(1);
			halfday = m.group(2);
			hour = Integer.parseInt(m.group(3));
			minute = Integer.parseInt(m.group(4));
			message = m.group(5);
		}

		if(halfday.equals("오후")) {
			hour+=12;
		}
		time= hour*60 + minute;

		
		//System.out.println(name + message + year + month + day + time);
		list.add(count,new MessageLists(name,message,year,month, day, time));


	}

	private void parseFileOfCSV(int count,String s) {

		String name="";
		String message="";
		int year=0;
		int month=0;
		int day=0;
		int hour=0;
		int minute=0;
		int time=0;

		String line = s;
		String pattern = "";
		
		int end = s.length();
		if(s.charAt(end-1)=='"') {
			pattern ="([0-9]+)\\-([0-9]+)\\-([0-9]+)\\s([0-9]+)\\:([0-9]+)\\:..\\,\\\"(.+)\\\"\\,\\\"(.+)\\\"";
		}
		else {
		pattern ="([0-9]+)\\-([0-9]+)\\-([0-9]+)\\s([0-9]+)\\:([0-9]+)\\:..\\,\\\"(.+)\\\"\\,\\\"(.+)";
		}
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(line);

		if(m.find()) {
			year = Integer.parseInt(m.group(1));
			month = Integer.parseInt(m.group(2));
			day = Integer.parseInt(m.group(3));
			hour = Integer.parseInt(m.group(4));
			minute = Integer.parseInt(m.group(5));
			name = m.group(6);
			message = m.group(7);
		}
		time=hour*60+minute;
		
		//System.out.println(name + message + year + month + day + time);
		list.add(count,new MessageLists(name,message,year,month, day, time));


	}

}
