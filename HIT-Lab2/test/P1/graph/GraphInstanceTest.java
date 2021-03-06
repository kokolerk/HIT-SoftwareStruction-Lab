/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.*;

/**
 * Tests for instance methods of Graph.
 * 
 * <p>PS2 instructions: you MUST NOT add constructors, fields, or non-@Test
 * methods to this class, or change the spec of {@link #emptyInstance()}.
 * Your tests MUST only obtain Graph instances by calling emptyInstance().
 * Your tests MUST NOT refer to specific concrete implementations.
 */
public abstract class GraphInstanceTest {
    
    // Testing strategy
    //   TODO
    
    /**
     * Overridden by implementation-specific test classes.
     * 
     * @return a new empty graph of the particular implementation being tested
     */
    public abstract Graph<String> emptyInstance();
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testInitialVerticesEmpty() {
        // TODO you may use, change, or remove this test
        assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), emptyInstance().vertices());
    }
    
    @Test
    public void testAddOrRemovevertices() {
    	Graph<String> a=emptyInstance();
    	String vertex1="v1";
    	a.add(vertex1);
    	Set<String> graph=new HashSet <String>();
    	graph=a.vertices();
    	assertEquals(true,graph.contains(vertex1));
    	a.remove(vertex1);
    	graph=a.vertices();
    	assertEquals(false,graph.contains(vertex1));
    	
    }
    @Test
    public void testAddorRemoveedge() {
    	Graph<String> a=emptyInstance();
    	String vertex1="v1";
    	String vertex2="v2";
    	a.add(vertex1);
    	a.add(vertex2);
    	a.set(vertex1, vertex2, 3);
    	a.set(vertex2, vertex1, 5);
    	Map<String, Integer> sou=new HashMap<String, Integer>();
    	Map<String, Integer> tar=new HashMap<String, Integer>();
    	sou=a.sources(vertex1);//v1????sourse
    	tar=a.targets(vertex1);
    	Integer x=5;
    	Integer y=3;
    	assertEquals(x,sou.get(vertex2));
    	assertEquals(y,tar.get(vertex2));
    	a.set(vertex1, vertex2, 0);
    	tar=a.targets(vertex1);
    	assertEquals(0,tar.size());
    	
    	
    	
    	
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
    	a.set(vertex1, vertex3, 2);
    	a.set(vertex1, vertex2, 9);
    	Map<String, Integer> sou=new HashMap<String, Integer>();
    	sou=a.targets(vertex1);
    	Integer x=9;
    	Integer y=2;
    	assertEquals(x,sou.get(vertex2));
    	assertEquals(y,sou.get(vertex3));
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
    	Map<String, Integer> tar=new HashMap<String, Integer>();
    	tar=a.sources(vertex1);
    	Integer x=9;
    	Integer y=2;
    	assertEquals(x,tar.get(vertex2));
    	assertEquals(y,tar.get(vertex3));
    }
    @Test
    public void testTargetVerticesGraph() {
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
    	assertEquals(true,1==tar2.size());
    	assertEquals(true,1==tar3.size());
    	assertEquals(true,1==tar4.size());
    	assertEquals(true,1==tar5.size());
    	assertEquals(true,0==tar6.size());
    }
    // TODO other tests for instance methods of Graph
    
}
