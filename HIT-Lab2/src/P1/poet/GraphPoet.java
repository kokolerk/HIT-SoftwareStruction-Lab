/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import P1.graph.Graph;

/**
 * A graph-based poetry generator.
 * 
 * <p>GraphPoet is initialized with a corpus of text, which it uses to derive a
 * word affinity graph.
 * Vertices in the graph are words. Words are defined as non-empty
 * case-insensitive strings of non-space non-newline characters. They are
 * delimited in the corpus by spaces, newlines, or the ends of the file.
 * Edges in the graph count adjacencies: the number of times "w1" is followed by
 * "w2" in the corpus is the weight of the edge from w1 to w2.
 * 
 * <p>For example, given this corpus:
 * <pre>    Hello, HELLO, hello, goodbye!    </pre>
 * <p>the graph would contain two edges:
 * <ul><li> ("hello,") -> ("hello,")   with weight 2
 *     <li> ("hello,") -> ("goodbye!") with weight 1 </ul>
 * <p>where the vertices represent case-insensitive {@code "hello,"} and
 * {@code "goodbye!"}.
 * 
 * <p>Given an input string, GraphPoet generates a poem by attempting to
 * insert a bridge word between every adjacent pair of words in the input.
 * The bridge word between input words "w1" and "w2" will be some "b" such that
 * w1 -> b -> w2 is a two-edge-long path with maximum-weight weight among all
 * the two-edge-long paths from w1 to w2 in the affinity graph.
 * If there are no such paths, no bridge word is inserted.
 * In the output poem, input words retain their original case, while bridge
 * words are lower case. The whitespace between every word in the poem is a
 * single space.
 * 
 * <p>For example, given this corpus:
 * <pre>    This is a test of the Mugar Omni Theater sound system.    </pre>
 * <p>on this input:
 * <pre>    Test the system.    </pre>
 * <p>the output poem would be:
 * <pre>    Test of the system.    </pre>
 * 
 * <p>PS2 instructions: this is a required ADT class, and you MUST NOT weaken
 * the required specifications. However, you MAY strengthen the specifications
 * and you MAY add additional methods.
 * You MUST use Graph in your rep, but otherwise the implementation of this
 * class is up to you.
 */
public class GraphPoet {
    
    private final Graph<String> graph = Graph.empty();
    
    // Abstraction function:
    // Vertices in the graph are all words in the text file. 
    // Edges in the graph count adjacencies: the number of times "w1" is followed by
    // "w2" in the corpus is the weight of the edge from w1 to w2.
    // Representation invariant:
    //   the weight of the edge must be positive number
    //   the vertex is different from each other
    // Safety from rep exposure:
    //   the field is private final
    
    /**
     * Create a new poet with the graph from corpus (as described above).
     * 
     * @param corpus text file from which to derive the poet's affinity graph
     * @throws IOException if the corpus file cannot be found or read
     */
    
    public GraphPoet(File corpus) throws IOException {
    	
    	 BufferedReader in = null;
         in = new BufferedReader(new FileReader(corpus));
         List<String[]> list = new ArrayList<>();
         String string;
         while ((string = in.readLine()) != null) {
             list.add(string.split("\\s+"));
         }
         in.close();
         int n=list.size();
         for(int i=0;i<n;i++) {
        	 int m=list.get(i).length;
        	 for(int j=0;j<m;j++) {
        		 list.get(i)[j]= list.get(i)[j].toLowerCase();
        		 graph.add(list.get(i)[j]);
        	 }
         }
         for (int i = 0; i < n; i++) {
        	 int m=list.get(i).length;
        	 if(m==1) continue;
        	 for(int j=0;j<m-1;j++) {
        		int weight=graph.set(list.get(i)[j],list.get(i)[j+1],1);
        		if(weight==0) continue;
        		else {
        			graph.set(list.get(i)[j],list.get(i)[j+1],weight+1);
        		}
             }
         }
         checkRep();
       }
    
    // TODO checkRep
    public void checkRep() {
    assert  graph!=null;	
    }
  
    /**
     * Given an input string, GraphPoet generates a poem by attempting to
	 * insert a bridge word between every adjacent pair of words in the input.
	 * The bridge word between input words "w1" and "w2" will be some "b" such that
	 * w1 -> b -> w2 is a two-edge-long path with maximum-weight weight among all
	 * the two-edge-long paths from w1 to w2 in the affinity graph.
	 * If there are no such paths, no bridge word is inserted.
	 * In the output poem, input words retain their original case, while bridge
	 * words are lower case. The whitespace between every word in the poem is a
	 * single space.
     * @param String input 
     * @return String output
     */
    public String poem(String input) {
        String string = "";
        String bridge;
        List<String> list = new ArrayList<>(Arrays.asList(input.split(" ")));
        int n=list.size();
        if(n==1) return input;
        for (int i = 0; i < n - 1; i++) {
            String source = list.get(i).toLowerCase();
            String target = list.get(i + 1).toLowerCase();
            string=string+list.get(i)+" ";
            bridge=findMiddleString(source,target);
            if(bridge=="0") continue;
            else {
            	string=string+bridge+" ";
            }
        }
        string=string+(list.get(list.size() - 1));
        checkRep();
        return string;
    }
    
    
    /**
     * The bridge word between input words "w1" and "w2" will be some "b" such that
	 * w1 -> b -> w2 is a two-edge-long path with maximum-weight weight among all
	 * the two-edge-long paths from w1 to w2 in the affinity graph.
	 * If there are no such paths, no bridge word is inserted.
     * @param a
     * @param b
     * @return the bridge string between 
     */
    public String findMiddleString(String a,String b) {
    	Map<String,Integer> atarget=graph.targets(a);
    	Map<String,Integer> bsource=graph.sources(b);
    	//如果a没有
    	if(atarget.size()==0||bsource.size()==0) return "0";
    	int max=0;
    	int temp=0;
    	String res="0";
    	//将a的target变为数组
    	
    	for(String i:atarget.keySet()) {
    		if(bsource.containsKey(i))	{
    			temp=atarget.get(i)+bsource.get(i);
    			if(temp>max) {
    				res=i;
    				max=temp;
    			}
    			
    		}
    	}
       checkRep();
       return res;
    	
    }
    // TODO toString()
    public String toString() {
    	return graph.toString();
    }
}
