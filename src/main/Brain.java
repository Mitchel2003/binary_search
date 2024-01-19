package main;

import java.util.ArrayList;
import java.util.List;

public class Brain {
	
	private List <Integer> list=new ArrayList<Integer>();
	private List <Integer> preset=new ArrayList<Integer>();//ads
	private int numSelected;

	public Brain() {
		
	}
	public Brain(List <Integer> list, int numSelected) {
		this.list=list;
		this.numSelected=numSelected;
		
		calculator(list,numSelected);
	}
	//
	
	//
	private void calculator(List<Integer> nameList, int inputUser) {
		int x=nameList.size();	
		int cyclePossible=nameList.size()-2;	int cycle=0;
		
		do {
			cycle++;
			int fromIndex=0;
			int toIndex=cycle;
			
			//form positive ++
			for(int a=0;a<x-cycle;a++) {//example: list[4], return 3 laps
				
				int sumContext=getSumContext(fromIndex, toIndex, nameList);
				
				for(int e=toIndex;e<x;e++) {//"toIndex" reference the context to select; for evading redundancy 
					if(sumContext+nameList.get(e)==inputUser) {
						System.out.println(nameList.subList(fromIndex, toIndex)+" + "+nameList.get(e));
					}
				}
				
				fromIndex++;
				toIndex++;
			}
			
			
			//form negative --			
			if(cycle>=2) {
				int fromIndexNegative=x-cycle;
				int toIndexNegative=x;
				
				for(int a=0;a<x-cycle-1;a++) {
				
					int sumContext=getSumContext(fromIndexNegative, toIndexNegative, nameList);
							
					for(int e=toIndexNegative-fromIndexNegative;e>=0;e--) {	
						
						if(e<fromIndexNegative&&e!=fromIndexNegative-1) {
							if(sumContext+nameList.get(e)==inputUser) {
								System.out.println(nameList.subList(fromIndexNegative, toIndexNegative)+" + "+nameList.get(e));
							}
						}
					}
					
					fromIndexNegative--;
					toIndexNegative--;
				}
			}
			
			cyclePossible--;
		}while(cyclePossible!=0);
	}
	public int getSumContext(int index, int cycle, List<Integer> list){
		List <Integer> send=new ArrayList<Integer>();
		int value;
		
		send=list.subList(index, cycle);
		value=send.stream().reduce(0, Integer::sum);
		return value;
	}
	
//--------------------------------------------------getters and setters--------------------------------------------------
	public List<Integer> getList(){
		return list;
	}
	public int getNumSelected() {
		return numSelected;
	}
	public List<Integer> getPreset() {
		return preset;
	}
	
	
}
