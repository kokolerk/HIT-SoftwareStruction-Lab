/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

/**
 * Tests for ConcreteEdgesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph();
    }
    
    /*
     * Testing ConcreteEdgesGraph...
     */
  
    @Test
    public void testAddRemoveVertex() {
    	Graph<String> g=emptyInstance();
    	String a="a";
    	String b="b";
    	//g.add(a);
    	assertEquals(true,g.add(a));
    	assertEquals(false,g.add(a));
    	int n=g.vertices().size();
    	assertEquals(false,g.vertices().contains(b));
    	assertEquals(true,g.vertices().contains(a));
    	assertEquals(1,n);
    	assertEquals(true,g.add(b));
    	assertEquals(2,g.vertices().size());
    	assertEquals(true,g.remove(b));
    	assertEquals(false,g.vertices().contains(b));
    	assertEquals(true,g.vertices().contains(a));
    }
    
    @Test
    public void testSetEdge() {
    	Graph<String> g=emptyInstance();
    	String a="a";
    	String b="b";
    	String c="c";
    	g.add(a);
    	g.add(b);
    	g.add(c);
    	assertEquals(0,g.set(a, b, 1));
    	assertEquals(1,g.set(a, b, 2));
    	assertEquals(0,g.set(a,"d", 4));
    	assertEquals(0,g.set(b, "d", -1));
    	assertEquals(4,g.set(a, "d", 3));
    	assertEquals(0,g.set(a,c,10));
    	assertEquals(10,g.set(a, c, 9));
    	int n=g.vertices().size();
    	assertEquals(4,n);
    	Map<String, Integer> res=g.targets(a);
    	assertEquals(true,2==res.get(b));
    	assertEquals(true,3==res.get("d"));
    	assertEquals(true,9==res.get(c));
    	assertEquals(2,g.set(a,b,0));
    	res=g.targets(a);
    
    }
    @Test
    public void testRemove() {
    	Graph<String> g=emptyInstance();
    	String a="a";
    	String b="b";
    	String c="c";
    	g.add(a);
    	g.add(b);
    	g.add(c);
    	g.set(a, b, 1);
    	g.set(c, a, 2);
    	g.set(a, c, 3);
    	g.set(c, b, 4);
    	g.remove(a);
    	Set<String> ver= g.vertices();
    	assertEquals(true,2==ver.size());
    	Map<String, Integer> res=g.targets(c);
    	assertEquals("{b=4}",res.toString());
    	
    }
    
    @Test
    public void testVertices() {
    	Graph<String> g=emptyInstance();
    	String a="a";
    	String b="b";
    	String c="c";
    	g.add(a);
    	g.add(b);
    	g.add(c);
    	assertEquals(3,g.vertices().size());
    }
    
    @Test
    public void testSourcesAndTarget() {
    	Graph<String> g=emptyInstance();
    	String a="a";
    	String b="b";
    	String c="c";
    	g.add(a);
    	g.add(b);
    	g.add(c);
    	g.set(a, b, 1);
    	g.set(a, c, 1);
    	g.set(c, b, 1);
    	Map<String, Integer> res=g.targets(a);
    	assertEquals(true,1==res.get(b));
    	assertEquals(true,1==res.get(c));
    	Map<String, Integer> res1=g.sources(b);
    	assertEquals(true,1==res1.get(a));
    	assertEquals(true,1==res1.get(c));
    	
    }
    
    //’‚√ª–¥
    // Testing strategy for ConcreteEdgesGraph.toString()
    //   TODO
    @Test
    public void testtoString() {
    	Graph<String> g=emptyInstance();
    	String a="a";
    	String b="b";
    	String c="c";
    	g.add(a);
    	g.add(b);
    	g.add(c);
    	g.set(a, b, 1);
    	g.set(a, c, 1);
    	g.set(c, b, 1);
    	assertEquals("a->b weight:1\na->c weight:1\nc->b weight:1\n",g.toString());
    	
    }
    
    // TODO tests for ConcreteEdgesGraph.toString()
    
    /*
     * Testing Edge...
     */
    
    // Testing strategy for Edge
    //   TODO
    @Test
    public void TestEdgeClass() {
    	String a="a";
    	Edge e=new Edge(a,a,1);
    	assertEquals(a,e.getSource());
    	assertEquals(a,e.getTarget());
    	assertEquals(true,1==e.getWeight());
    	
    }
    // TODO tests for operations of Edge
    @Test
    public void TestEdgeToString() {
    	String a="a";
    	String b="b";
    	
    	Edge e=new Edge(a,b,1);
    	assertEquals("a->b weight:1\n",e.toString());
    	
    }
    
}
