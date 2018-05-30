package edu.handong.csee.java.hw3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

/**
 * This is Runner class. (like a main class)
 * It has inputpath(type : String), outputpath(type : String), help(type : boolean) members.
 * It has main, run, parseOptions, createOptions, printHelp, sortByValue methods.
 * This class run ChatCounter, this class call all the class.
 * You put the options and args, and then it read the contents of all files that contains directory
 * and, it count the number of messages per user. 
 * and then, it save the information to file. 
 * @author MinJu
 *
 */
public class Runner {

	String inputpath;
	String outputpath;
	boolean help;

	/**
	 * This is main method.
	 * It call run method.
	 * @param args
	 */
	public static void main(String[] args) {

		Runner myRunner = new Runner();
		myRunner.run(args);

	}

	private void run(String[] args) {
		Options options = createOptions();

		if(parseOptions(options, args)){
			if (help){
				printHelp(options);
				return;
			}

			ArrayList<String> messages = new ArrayList<String>();
			ArrayList<MessageLists> list = new ArrayList<MessageLists>();
			HashMap<String,Integer> nameAndCountOfMessage = new HashMap<String,Integer>();

			DataReader reader = new DataReader();
			MessageParser parser = new MessageParser();
			MessageFilter filter = new MessageFilter();
			DataWriter righter = new DataWriter();

			messages = reader.getData(inputpath);
			//System.out.println("1");
			list = parser.runMessageParser(messages);
			//System.out.println("2");
			nameAndCountOfMessage = filter.countMessagePerUser(list);

			List<String> ids = sortByValue(nameAndCountOfMessage);
			ArrayList<String> linesTowrite = new ArrayList<String>();
			for(String key : ids) {
				linesTowrite.add(key + " , " + nameAndCountOfMessage.get(key));
				System.out.println(key + " , " + nameAndCountOfMessage.get(key));
			}

			//System.out.println("3");
			righter.getOutputFile(linesTowrite,outputpath);
			//System.out.println("4");
		}
	}

	private boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();

		try {
			CommandLine cmd = parser.parse(options, args);

			inputpath = cmd.getOptionValue("i");
			outputpath = cmd.getOptionValue("o");
			help = cmd.hasOption("h");

		} catch (Exception e) {
			printHelp(options);
			return false;
		}

		return true;
	}

	// Definition Stage
	private Options createOptions() {
		Options options = new Options();

		options.addOption(Option.builder("i").longOpt("inputdir")
				.desc("Set a directory path that contains input files")
				.hasArg()
				.argName("Directory path")
				.required()
				.build());

		// add options by using OptionBuilder
		options.addOption(Option.builder("o").longOpt("outputdir")
				.desc("Set a filename(.csv)")
				.hasArg()
				.argName("filename to display")
				.required()
				.build());

		// add options by using OptionBuilder
		options.addOption(Option.builder("h").longOpt("help")
				.desc("Help")
				.build());

		return options;
	}

	private void printHelp(Options options) {
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		String header = "ChatCounter program";
		String footer ="\nPlease report issues to 21700097@handong.edu (emails)"; //옵션 설명 후 등장!
		formatter.printHelp("CLIExample", header, options, footer, true);
	}

	/**
	 * This is sortByValue method.
	 * Put HashMap<String,Integer>, and then it sorts the HashMap in decending order.
	 * It saves the key value in decending order, and returns the list of it.
	 * @param map
	 * @return List<String> list
	 */
	public List<String> sortByValue(final HashMap<String,Integer> map){

		List<String> list = new ArrayList<String>();
		list.addAll(map.keySet());


		Collections.sort(list,new Comparator<Object>() {

			@SuppressWarnings("unchecked")
			public int compare(Object o1, Object o2) {
				Object v1 = map.get(o1);
				Object v2 = map.get(o2);

				return ((Comparable<Object>) v1).compareTo(v2);
			}
		});

		Collections.reverse(list);
		return list;
	}

}