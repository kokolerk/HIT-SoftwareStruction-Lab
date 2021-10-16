/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import java.util.*;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteVerticesGraph<L> implements Graph<L> {
    
    private final List<Vertex<L>> vertices = new ArrayList<>();
    
    // Abstraction function:
    //  link graph ,Vertex contains source ,all targets and its edge weight
    
    // Representation invariant:
    //   the source of each Vertex is different from each other
    //   the edge weight is positive
   
    // Safety from rep exposure:
    //   all field is private final 
    //   return mutable function using defensive copy
    
    // constructor
    public ConcreteVerticesGraph() {
    	vertices.clear();
    }
    
    // TODO checkRep这还没写呢
    public void checkRep() {
    	int n=vertices.size();
    	for(Vertex<L> i:vertices) {
    		//还是不知道怎么写，map，list用不对
    		//assert vertices.get(i).SourceVertex()!=null;
    	}
    	
    }
    /**
     * Add a vertex to this graph.
     * 
     * @param vertex label for the new vertex
     * @return true if this graph did not already include a vertex with the
     *         given label; otherwise false (and this graph is not modified)
     */
    @Override public boolean add(L vertex) {
            
    	Iterator<Vertex<L>> iter=vertices.iterator();
        while(iter.hasNext()) {
     	   Vertex<L> tmp=iter.next();
     	   if(tmp.SourceVertex().equals(vertex))
     		   return false;
     	}
    	    Vertex<L> v=new Vertex<L>(vertex);
            vertices.add(v);
            checkRep();
            return true;
    	
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
      if(weight<0) {
    	  return 0;
      }
     int preweight=0;
     //weight==0
     this.add(source);
     this.add(target);
     for(Vertex<L> i:vertices) {
		if(i.SourceVertex().equals(source)) {
			preweight=i.ContainsTarget(target);  
			if(weight>0)
			  {   
				  i.set(target, weight);
			  }
			       
			  else {
				   
			       i.remove(target);
			  }
			  break;
			    			  
	    }
	}
    checkRep();
    return preweight;
      
     
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
    	 Iterator<Vertex<L>> iter=vertices.iterator();
    	 boolean flag=false;
         while(iter.hasNext()) {
      	   Vertex<L> tmp=iter.next();
      	   tmp.remove(vertex);
      	  //删以vertex为source的Class
      	   if(tmp.SourceVertex().equals(vertex)) {
      		   iter.remove();
      		   flag=true;
      		   break;
      	   }
         }
         return flag;
    }
    
    /**
     * Get all the vertices in this graph.
     * 
     * @return the set of labels of vertices in this graph
     */
    @Override public Set<L> vertices() {
       Set<L> res=new HashSet<L>();
       Iterator<Vertex<L>> iter=vertices.iterator();
       while(iter.hasNext()) {
    	   Vertex<L> tmp=iter.next();
    	   res.add(tmp.SourceVertex());
       }
       checkRep();
    	return res;
    }
    
    /**
     * Get the source vertices with directed edges to a target vertex and the
     * weights of those edges.
     * 
     * @param target a label
     * @return a map where the key set is the set of labels of vertices such
     *         that this graph includes an edge from that vertex to target, and
     *         the value for each key is the (nonzero) weight of the edge from
     *         the key to target
     */
    @Override public Map<L, Integer> sources(L target) {
    	Map<L ,Integer> res=new HashMap<L,Integer> ();
    	for(Vertex<L> i:vertices) {
    		int weight=i.ContainsTarget(target);
    		if(weight==0) continue;
    		else {
    			res.put(i.SourceVertex(), weight);
    		}
    	}
    	return res;
    }
    
    /**
     * Get the target vertices with directed edges from a source vertex and the
     * weights of those edges.
     * 
     * @param source a label
     * @return a map where the key set is the set of labels of vertices such
     *         that this graph includes an edge from source to that vertex, and
     *         the value for each key is the (nonzero) weight of the edge from
     *         source to the key
     */
    @Override public Map<L, Integer> targets(L source) {
    	Map<L ,Integer> res=new HashMap<L,Integer> ();
    	for(Vertex<L> i:vertices) {
    		if(i.SourceVertex().equals(source)) {
    			res=i.linkedge();
    			break;
    		}
    	}
    	return res;
    }
    
    // TODO toString()
    public String toString() {
    	String res="the graph: ";
    	res+="\n";
    	Iterator<Vertex<L>> iter=vertices.iterator();
    	while(iter.hasNext()) {
    		Vertex<L> tmp=iter.next();
    		res=res+tmp.SourceVertex()+tmp.linkedge().toString()+"\n";
    	}
    	return res;
    }
    
}

/**
 * TODO specification
 * Mutable.
 * This class is internal to the rep of ConcreteVerticesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Vertex<L> {
    
    // TODO fields
	private final L source;
    private final Map<L,Integer> link=new HashMap <L,Integer> ();
    // Abstraction function:
    //   source is the source vertex
    //   link is the collection of edge from the source vertex,
    //   each pair has the target vertex and the edge weight
    
    // Representation invariant:
    //   Integer must be positive
    //   the String in link is different from each other
    
    //   Safety from rep exposure:
    //   the fields both private;
    //   Map<String,Integer> linkedge() use defensive copy 
    //   TODO
    
    // TODO constructor
    public Vertex(L sour) {
    	this.source=sour;
    	link.clear();
    }
    
    // TODO checkRep
    public void checkRep() {
    	assert source!=null;
       
    }
    // TODO methods
    /**
     * add a edge to the link
     * @param target
     * @param weight
     * @return true if successful add, false else
     */
    public boolean set(L target ,Integer weight) {
    	if(weight>0) {
    	    link.put(target,weight);//target相同重写
    	    checkRep();
    	    return true;
    	    
    	}
    	else return false;
    }
    /**
     * remove the target vertex and its edge
     * @param target
     * @return true if remove ; false else;
     */
    public boolean remove(L target) {
    	if(!link.containsKey(target)) return false;
    	else {
    		link.remove(target);
    		checkRep();
    		return true;
    	}
    	
    }
   /**
    * check if there is the edge whose target vertex is target
    * @param target
    * @return weight if it has the target; 0 if don't has
    */
    public int ContainsTarget(L target) {
    	if(link.containsKey(target)) {
    		return link.get(target);
    	}
    	else {
    		return 0;
    	}
    }
    /**
     * return all the edge whose source is the source vertex
     * @return Map<String,Integer>
     */
    public Map<L,Integer> linkedge(){
        return new HashMap<L,Integer>(link);//防御式拷贝
       
    }
    /**
     * return the source vertex;
     * @return source vertex
     */
    public L SourceVertex() {
    	return this.source;
    }
    // TODO toString()
    public String toString() {
    	 
         return this.source+link.toString();
    }
    
}
