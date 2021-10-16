package P2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import P1.graph.Graph;
import P2.FriendshipGraph;
import P2.Person;
import java.io.*;
import java.util.*;

public class FriendshipGraph {

	private int numberOfPeople;
    private Graph<Person> graph;
    
    public FriendshipGraph() {
   	    numberOfPeople=0;
    	graph=Graph.empty();
    }    
    
	
	
	
	/**
     * add vertex to the graph
     * @param person 
     *            one person in the graph 
     * @return void
     */
	public void addVertex(Person person) {
	    numberOfPeople++;
		graph.add(person);
	}
	
	/**
	 * add person1 to person2 an edge means 1 know 2; 
	 * 
	 * @param person1
	 *           one vertex in the graph 
	 * @param person2
	 *  		 another vertex in the graph
	 * @return void
	 */
    public void addEdge(Person person1,Person person2) {
    	graph.set(person1, person2, 1);
    }
	
	
	/**
	 *  The getDistance method should take two people (as Person) as arguments and return the shortest distance (an 
     *  int) between the people, or -1 if the two people are not connected
	 * @param person1
	 * @param person2
	 * @return void 
	 */
	public int getDistance(Person person1,Person person2) {
		Set<Person> vertex=graph.vertices();
		
		if(!vertex.contains(person1)){
		   
			System.out.println(person1.getname()+"is not exit!");
			return -1;
		}	
		if(!vertex.contains(person2) ){
			System.out.println(person2.getname()+"is not exit!");
			return -1;
		}
		//如果两个人是一个人，直接返回0，不用算法
		if(person1.equals(person2)) return 0;
		int inf=Integer.MAX_VALUE/2;//注意数据的范围
		int n=numberOfPeople;
		int[][] trix=new int[numberOfPeople][numberOfPeople];
		//floyd算法初始化
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(i==j) trix[i][j]=0;
				else trix[i][j]=inf;
				
			}
		}
		//认识置1
		List<Person> people=new ArrayList<> (vertex);
		int one=0;
		int two=0;
		int m=vertex.size();
		for(int i=0;i<m;i++) {
			if(people.get(i).equals(person1)) {
				one=i;
			}
			if(people.get(i).equals(person2)) {
				two=i;
			}
		}
		Map<Person, Integer> knownPeople;
		for(int i=0;i<m;i++) {
			knownPeople=graph.targets(people.get(i));
			for(int j=0;j<m;j++) {
				if(knownPeople.containsKey(people.get(j))) {
					trix[i][j]=1;
				}
			}
		}
		//floyd算法主体
		for(int k=0;k<n;k++) {
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++){
					if(trix[i][j]>trix[i][k]+trix[k][j]) {
					 trix[i][j]=trix[i][k]+trix[k][j];
					}
				}
			}
		}
		if(trix[one][two]<inf)
		    return trix[one][two];
		else{
			return -1;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 FriendshipGraph graph = new FriendshipGraph();
		 Person rachel = new Person("Rachel");
		 Person ross = new Person("Ross");
		 Person ben = new Person("Ben");
		 Person kramer = new Person("Kramer");
		 graph.addVertex(rachel);
	     graph.addVertex(ross);
		 graph.addVertex(ben);
		 graph.addVertex(kramer);
		 graph.addEdge(rachel, ross);
		 graph.addEdge(ross, rachel);
		 graph.addEdge(ross, ben);
		 graph.addEdge(ben, ross);
	     System.out.println(graph.getDistance(rachel, ross)); 
		 //should print 1
		 System.out.println(graph.getDistance(rachel, ben)); 
		 //should print 2
		 System.out.println(graph.getDistance(rachel, rachel)); 
		 //should print 0
		 System.out.println(graph.getDistance(rachel, kramer)); 
		 //should print -1
		/*FriendshipGraph graph = new FriendshipGraph();
	    Person a=new Person("a");
		Person b= new Person("b");
		Person c= new Person("c");
		Person d= new Person("d");
		Person e= new Person("e");
		graph.addVertex(a);
		graph.addVertex(b);
		graph.addVertex(c);
		graph.addVertex(d);
		graph.addVertex(e);
		graph.addEdge(a, b);
		graph.addEdge(b, c);
		graph.addEdge(c, d);
		graph.addEdge(d, e);
		graph.addEdge(e, a);
		System.out.println(graph.getDistance(a, b)); 
		System.out.println(graph.getDistance(a, c));
		System.out.println(graph.getDistance(a, d));
		System.out.println(graph.getDistance(a, e));
		System.out.println(graph.getDistance(e,a)); 
		System.out.println(graph.getDistance(b,a)); */
	    
	}

}
