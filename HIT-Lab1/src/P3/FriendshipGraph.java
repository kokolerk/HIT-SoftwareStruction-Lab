package P3;

import java.util.*;
import java.io.*;

public class FriendshipGraph {
     private int numberOfPeople;
     private Map<String,Integer> nameToNum;//人名和矩阵映射
     private ArrayList<ArrayList<Integer>>list;//关系对
     
     public FriendshipGraph() {
    	 this.numberOfPeople=0;
         this.nameToNum=new HashMap<String,Integer>();
         this.list=new ArrayList<ArrayList<Integer>>();
         
     }
     
     /**
      * add vertex to the graph
      * @param person 
      *            one person in the graph 
      * @return void
      */
	public void addVertex(Person person) {
	//如果存在同名，返回异常
		if(nameToNum.containsKey(person.getname()) ){
			System.out.println(person.getname()+" has been used!");
			System.exit(-1);
		}
		else {
			nameToNum.put(person.getname(),numberOfPeople);
			numberOfPeople++;
		}
	    
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
	if(!nameToNum.containsKey(person1.getname()) ){
		System.out.println(person1.getname()+"is not exit!");
		return ;
	}	
	if(!nameToNum.containsKey(person2.getname()) ){
		System.out.println(person2.getname()+"is not exit!");
		return ;
	}	
    Integer one=nameToNum.get(person1.getname());
	Integer two=nameToNum.get(person2.getname());
	if(one==two) return;
	ArrayList<Integer> temp= new ArrayList<Integer>();
	temp.add(one);
	temp.add(two);
	list.add(temp);
	}
	/**
	 *  The getDistance method should take two people (as Person) as arguments and return the shortest distance (an 
     *  int) between the people, or -1 if the two people are not connected
	 * @param person1
	 * @param person2
	 * @return void 
	 */
	public int getDistance(Person person1,Person person2) {
		if(!nameToNum.containsKey(person1.getname()) ){
			System.out.println(person1.getname()+"is not exit!");
			return -1;
		}	
		if(!nameToNum.containsKey(person2.getname()) ){
			System.out.println(person2.getname()+"is not exit!");
			return -1;
		}	
		int one=nameToNum.get(person1.getname());
		int two=nameToNum.get(person2.getname());
		if(one==two)
		{   
			return 0;
		}
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
		int m=list.size();
		for(int i=0;i<m;i++) {
			int row=list.get(i).get(0);
			int col=list.get(i).get(1);
			trix[row][col]=1;
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
		/* FriendshipGraph graph = new FriendshipGraph();
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
		 //should print -1*/
		FriendshipGraph graph = new FriendshipGraph();
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
		System.out.println(graph.getDistance(b,a)); 
	    
	}

}
