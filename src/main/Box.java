package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Box {

	private List<Integer> listContext=new ArrayList<Integer>();
	
	public Box() {
		
	}
	
	
	public void addToList(int value) {
		listContext.add(value);
	}
	public void validateReply() {//checked the values reply and normalize the list
		Set<Integer> query=listContext.stream().collect(Collectors.toSet());

		if(query.size()==listContext.size()) {
			return;
		}
		listContext.clear();
		listContext.addAll(query);
	}
	public void addOrganization() {//Organize of form ascendent
		Collections.sort(listContext);
	}
	public void deletRedundancy(int date) {
		listContext.removeIf(item -> item > date);
	}
//--------------------------------------------------getters and setters--------------------------------------------------
	public List<Integer> getBox(){
		return listContext;
	}
}
