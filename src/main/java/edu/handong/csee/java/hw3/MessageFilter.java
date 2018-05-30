package edu.handong.csee.java.hw3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * This is MessageFilter class.
 * It has HashMap<String,Integer> member and countMessagePerUser and compare methods.
 * It counts message per user 
 * And it doesn't count redundancy message
 * Finally it return HashMap that contains user name and number of messages
 * @author Minju
 *
 */
public class MessageFilter {
	
	HashMap<String,Integer> nameAndCountOfMessage = new HashMap<String,Integer>();
	/**
	 * This is countMessagePerUser method.
	 * First,It saves the size of parameter to length member.
	 * Second, It sets critera to count the number of messages per user.
	 * Third, It call compare method to compare whether the object is unique
	 * If the object is unique, count +1 
	 * Finally, it saves the count until it travers all object of ArrayList
	 * And return HashMap that contains user's name and the number of messages per user
	 * @param list , the type is ArrayList<MessageLists>
	 * @return nameAndCountOfMessage , the type is HashMap<String,Integer>
	 */
	public HashMap<String,Integer> countMessagePerUser(ArrayList<MessageLists> list) {

		int length = list.size();
		int count;
		int realcount;

		// Set criteria
		for(int i=0 ; i<length ; i++){

			MessageLists person = list.get(i);

			if(nameAndCountOfMessage.containsKey(person.name)) continue;

			System.out.println();
			System.out.println(   person.name + person.message);

			realcount=0;
			int check=0;


			for(int j=i+1 ; j<length ; j++) {
				
				//Find person that the name is same as criteria's name
				if((person.name).equals((list.get(j)).name)) {
					MessageLists child =list.get(j);
					count=0;
					check++;
	
					//Check whether the person has same contents to other
					for(int k=j+1; k<length ; k++) {
						if((child.name).equals((list.get(k)).name) && compare(child, list.get(k))==0) count++;
					}
					
					//If the person is unique compare to others, Add the data
					if(count==0) {
						realcount++;
						System.out.println(child.message);
					}
				}
			}
			// If nothing to have the same name with Criteria, Add  
			if(check==0) realcount=1;

			
			System.out.println(person.name + realcount);
			nameAndCountOfMessage.put(person.name, realcount);
		}
		

		return nameAndCountOfMessage;

	}
	
	

	private int compare(MessageLists person, MessageLists other) {

		if((person.message).equals("Photo") && (other.message).equals("사진") || (other.message).equals("Photo") && (person.message).equals("사진"))
		{
			if(person.year == other.year && person.month == other.month && person.date == other.date && person.time == other.time)
			{
				//System.out.println("same");
				return 0;
			}
			else return 1;
		}
		else {
			if((person.message).equals(other.message) && person.year == other.year
					&& person.month == other.month && person.date == other.date && person.time == other.time)
			{
				//System.out.println("same");
				return 0;
			}
			else return 1;
		}
	}

}
