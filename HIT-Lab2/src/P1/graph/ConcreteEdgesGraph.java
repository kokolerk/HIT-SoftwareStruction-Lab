/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.*;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteEdgesGraph<L> implements Graph<L> {
    
    private final Set<L> vertices = new HashSet<>();
    private final List<Edge<L>> edges = new ArrayList<>();
    
    // Abstraction function:
    //  L are the vertex int vertices,edge are the Edge<L> in edges
    //  
    
    // Representation invariant:
    //  the weight on edges is positive integer(int) 
    //  the vertex of edges should in the vertices(checkeRep)
   
    // Safety from rep exposure:
    // the field are both private final
    // public Set<String> vertices() return defensive copy
    
    
    // TODO constructor
    public ConcreteEdgesGraph() {
    vertices.clear();
    edges.clear();
    checkRep();
    }
   
    
    // TODO checkRep
    public void checkRep() {
    	int n=edges.size();
    	if(n==0) return;
    	for(int i=0;i<n;i++) {
    		assert vertices.contains(edges.get(i).getSource())==true;
    		assert vertices.contains(edges.get(i).getTarget())==true;
    		assert edges.get(i).getWeight()>0;
    		
    	}
    	return;
    }
    /**
     * Add a vertex to this graph.
     * 
     * @param vertex label for the new vertex
     * @return true if this graph did not already include a vertex with the
     *         given label; otherwise false (and this graph is not modified)
     */
    @Override public boolean add(L vertex) {
        
    	if(vertices.contains(vertex)==false)
    	{
        	vertices.add(vertex);
        	checkRep();
        	return true;
        }
        else {
        	
        	return false;
        }
    	
    }
    
    /**
     * Add, change, or remove a weighted directed edge in this graph.
     * If weight is nonzero, add an edge or update the weight of that edge;
     * vertices with the given labels are added to the graph if they do not
     * already exist.
     * If weight is zero, remove the edge if it exists (the graph is not
     * otherwise modified).
     * 
     * @param source label of the source vertex
     * @param target label of the target vertex
     * @param weight nonnegative weight of the edge
     * @return the previous weight of the edge, or zero if there was no such
     *         edge
     */
    @Override public int set(L source, L target, int weight) {
        
    	if(weight>0) {
    		boolean flag=false;
    		if(!vertices.contains(source)) {
    			vertices.add(source);
    			flag=true;
    		}
        	if(!vertices.contains(target)) {
        		vertices.add(target);
        		flag=true;
        	}
        	Edge<L> e=new Edge<L>(source,target,weight);
        	if(flag==true) {//加点,直接把新边写入，
        		
            	edges.add(e);
            	checkRep();
            	return 0;
        	}
        	else {//点已经在了，可能边已经在了，考虑重复问题
        		int n=edges.size();
        		if(n==0) {
        			
                	edges.add(e);
                	checkRep();
                	return 0;
        		}
        		else {
        			boolean tag=false;	
        			for(int i=0;i<n;i++) {
        				//这一步太关键了，之前一直判断是false
        				if(edges.get(i).getSource().equals(source) && edges.get(i).getTarget().equals(target)) {
        			//	if(edges.get(i).getSource()==source && edges.get(i).getTarget()==target) {
        					int res=edges.get(i).getWeight();
        					edges.set(i,e);
        					checkRep();
        					tag=true;
        					return res;//边已经在了，重新写权重
        				}
        			}
        			//边不在里面，直接写入
        			if(tag==false) {
        				edges.add(e);
        				checkRep();
        				return 0;
        			}
        		}
        	}
        	
    	}
    	else if(weight==0) {
    		if(!vertices.contains(source)||!vertices.contains(target)) return 0;
    		int n=edges.size();
    		for(int i=0;i<n;i++) {
    			//改了
				if(edges.get(i).getSource().equals(source) && edges.get(i).getTarget().equals(target)) {
    			//if(edges.get(i).getSource()==source && edges.get(i).getTarget()==target) {	
    			//把已经在的边移除
					int res=edges.get(i).getWeight();
					edges.remove(i);
					checkRep();
					return res;
				}
			}
    		return 0;
    	}
    	checkRep();
    	return 0;
    	
    }
    /**
     * Remove a vertex from this graph; any edges to or from the vertex are
     * also removed.
     * 
     * @param vertex label of the vertex to remove
     * @return true if this graph included a vertex with the given label;
     *         otherwise false (and this graph is not modified)
     */
    @Override public boolean remove(L vertex) {
       if(!vertices.contains(vertex)) return false;
       vertices.remove(vertex);
       Iterator<Edge<L>> iter=edges.iterator();
       while(iter.hasNext()) {
    	   Edge<L> tmp=iter.next();
    	   //改了
    	 if(tmp.getSource().equals(vertex) || tmp.getTarget().equals(vertex))
    	  // if(tmp.getSource()==vertex|| tmp.getTarget()==vertex)   
    		   iter.remove();
    	}
       checkRep();
       return true;
       
        
    }
    /**
     * return all the vertices 
     * @param none
     * @return a set with all the vertices
     */
    @Override public Set<L> vertices() {
        return new HashSet<L>(vertices);//防御式拷贝
    }
    /**
     * Get the source vertices with directed edges to a target vertex 
     * and the weights of those edges.
     * @param the target vertex which should in the graph
     * @return a Map which has source and the edge weight
     */
    @Override public Map<L, Integer> sources(L target) {
         Map<L, Integer> res= new HashMap <L, Integer>();
    	if(!vertices.contains(target)) {
    		//System.out.println(target+" is not in the graph!");
    		return res;
    	}
    	Iterator<Edge<L>> iter=edges.iterator();
    	while(iter.hasNext()) {
    		Edge<L> tmp=iter.next();
    		//if(tmp.getTarget()==target)
    		if(tmp.getTarget().equals(target))
    		
    			res.put(tmp.getSource(), tmp.getWeight());
    		
    	}
    	checkRep();
    	return res;
    }
    /**
     * Get the target vertices with directed edges from the source vertex 
     * and the weights of those edges.
     * @param the source vertex which should in the graph
     * @return a Map which has target and the edge weight
     */
    @Override public Map<L, Integer> targets(L source) {
    	  Map<L, Integer> res= new HashMap <L, Integer>();
      	if(!vertices.contains(source)) {
      		//System.out.println(source+" is not in the graph!");
      		return res;
      	}
      	Iterator<Edge<L>> iter=edges.iterator();
      	while(iter.hasNext()) {
      		Edge<L> tmp=iter.next();
      		//if(tmp.getSource()==source)
      		if(tmp.getSource().equals(source))
      			res.put(tmp.getTarget(), tmp.getWeight());
      		
      	}
      	checkRep();
      	return res;

    }
    
    // TODO toString()
    //这没写
    public String toString() {
    String res="";	
    int n=edges.size();
    for(int i=0;i<n;i++) {
      res=res+edges.get(i).toString();
    }
    return res;
    
    }
}

/**
 * TODO specification
 * Immutable.
 * This class is internal to the rep of ConcreteEdgesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Edge<L> {
    
    // TODO fields
    private final L sou;
    private final L tar;
    private final Integer wei;
    // Abstraction function:
    //   sou is the source, tar is the target, wei is the weight on the directed edge from source to target
    // Representation invariant:
    //   true
    // Safety from rep exposure:
    //   the fields are all private final
    
    // TODO constructor
    public Edge(L source,L target,Integer weight) {
    	sou=source;
    	tar=target;
    	wei=weight;
    	checkRep();
    }
    // TODO checkRep
    public void checkRep() {
   
    	assert wei>0;
    	assert sou!=null;
    	assert tar!=null;
    
    }
    
    // TODO methods
    //防御式拷贝
    public L getSource() {
    	checkRep();
    	return sou;
    }
   
    public L getTarget() {
    	checkRep();
    	return tar;
    }
    public Integer getWeight() {
    	checkRep();
    	return wei;
    }
  
    // TODO toString()
    public String toString() {
    	checkRep();
    	return this.sou+"->"+this.tar+" weight:"+this.wei+"\n";
    }
    
}
