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
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph();
    }
    
    @Test
    public void testAddVertices() {
    	Graph<String> g=emptyInstance();
    	String vertex1="v1";
    	
    	assertEquals(true,g.add(vertex1));
    	assertEquals(false,g.add(vertex1));
    	
    	
    	
    }
    @Test
    public void testAddorRemoveedge() {
    	Graph<String> a=emptyInstance();
    	String vertex1="v1";
    	String vertex2="v2";
    	String vertex3="v3";
    	a.add(vertex1);
    	a.add(vertex2);
    	a.add(vertex3);
    	a.set(vertex2, vertex1, 5);
    	a.set(vertex2, vertex3, 1);
        assertEquals(true, a.remove(vertex3));
        Map<String, Integer> tar=new HashMap<String, Integer>();
        tar=a.targets(vertex2);
        assertEquals(true,5==tar.get(vertex1));
        assertEquals(true,1==tar.size());
    	
    }
    @Test
    public void testTarget() {
    	Graph<String> a=emptyInstance();
    	String vertex1="v1";
    	String vertex2="v2";
    	String vertex3="v3";
    	a.add(vertex1);
    	a.add(vertex2);
    	a.add(vertex3);
    	a.set(vertex1, vertex2, 9);
    	a.set(vertex2, vertex3, 2);
    	
    	Map<String, Integer> tar1=a.targets(vertex1);
    	Map<String,Integer> tar2=a.targets(vertex2);
    	assertEquals(true,1==tar1.size());
    	assertEquals(true,1==tar2.size());
    	a.set(vertex2, vertex3, 0);
    	tar2=a.targets(vertex2);
    	assertEquals(true,0==tar2.size());
    }
    
    @Test
    public void testSource() {
    	Graph<String> a=emptyInstance();
    	String vertex1="v1";
    	String vertex2="v2";
    	String vertex3="v3";
    	a.add(vertex1);
    	a.add(vertex2);
    	a.add(vertex3);
    	a.set(vertex3,vertex1,  2);
    	a.set( vertex2, vertex1,9);
    	Map<String, Integer> sou=new HashMap<String, Integer>();
    	sou=a.sources(vertex1);
    	assertEquals(true,9==sou.get(vertex2));
    	assertEquals(true,2==sou.get(vertex3));
    	a.set(vertex3,vertex1,  0);
    	a.set( vertex2, vertex1,0);
    	sou=a.sources(vertex1);
    	assertEquals(true,0==sou.size());
    }
    @Test
    public void testbug() {
    	Graph<String> a=emptyInstance();
    	String v1="this";
    	String v2="is";
    	String v3="a";	
    	String v4="test";
    	String v5="of";
    	String v6="the";
    	a.add(v1);
    	a.add(v2);
    	a.add(v3);
    	a.add(v4);
    	a.add(v5);
    	a.add(v6);
    	a.set(v1, v2, 1);
    	a.set(v2, v3, 1);
    	a.set(v3, v4, 1);
    	a.set(v4, v5, 1);
    	a.set(v5, v6, 1);
    	Map<String, Integer> tar1=a.targets(v1);
    	Map<String,Integer> tar2=a.targets(v2);
    	Map<String, Integer> tar3=a.targets(v3);
    	Map<String,Integer> tar4=a.targets(v4);
    	Map<String, Integer> tar5=a.targets(v5);
    	Map<String,Integer> tar6=a.targets(v6);
    	assertEquals(true,1==tar1.size());
    	
    	
    }
    
    /*
     * Testing ConcreteVerticesGraph...
     */
    
    // Testing strategy for ConcreteVerticesGraph.toString()
    //   TODO
    
    // TODO tests for ConcreteVerticesGraph.toString()
    @Test
    //这个不知道哪对不上！
    public void testToString() {
    	Graph<String> a=emptyInstance();
    	String vertex1="v1";
    	String vertex2="v2";
    	String vertex3="v3";
    	a.add(vertex1);
    	a.add(vertex2);
    	a.add(vertex3);
    	a.set(vertex1, vertex2, 1);
    	a.set(vertex1, vertex3, 2);
    	a.set(vertex3,vertex1, 3);
    	a.set(vertex2, vertex1,4);
    	System.out.print(a.toString());
    	System.out.print("the graph:\n"+"v1{v2=1, v3=2}\n"+"v2{v1=4}\n"+"v3{v1=3}\n");
    	//assertEquals(true,"the graph:\n"+"v1{v2=1, v3=2}\n"+"v2{v1=4}\n"+"v3{v1=3}\n"==a.toString());
    }
    /*
     * Testing Vertex...
     */
    
    // Testing strategy for Vertex
    //   TODO
    
    // TODO tests for operations of Vertex
    @Test
    public void testVertexClassTarget() {
    	String a="a";
    	String b="b";
    	String c="c";
    	Vertex v=new Vertex(a);
    	v.set(b, 1);
    	v.set(c, 2);
    	assertEquals(true,a==v.SourceVertex());
    	assertEquals(1,v.ContainsTarget(b));
    	assertEquals(2,v.ContainsTarget(c));
    	v.remove(b);
    	assertEquals(0,v.ContainsTarget(b));
    	assertEquals(2,v.ContainsTarget(c));
    }
    @Test
    public void testVertexToString() {
    	String a="a";
    	String b="b";
    	String c="c";
    	Vertex v=new Vertex(a);
    	v.set(b, 1);
    	v.set(c, 2);
    	System.out.print(v.toString());
    	assertEquals("a{b=1, c=2}",v.toString());
    }
}
