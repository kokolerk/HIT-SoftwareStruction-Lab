package P3;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class FriendshipGraphTest {
	/**
	 * 单向图
	 */
	@Test
	public void testdistance() {
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
		assertEquals(1,graph.getDistance(a, b));
		assertEquals(4,graph.getDistance(b,a));
		assertEquals(2,graph.getDistance(a, c));
		assertEquals(3,graph.getDistance(a, d));
		assertEquals(4,graph.getDistance(a, e));
		assertEquals(1,graph.getDistance(e, a));
		
		
	}
	

}
