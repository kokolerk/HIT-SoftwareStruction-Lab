/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P2.turtle;

import static org.junit.Assert.assertEquals;

import java.io.*;
import java.util.*;
import java.lang.Math;

public class TurtleSoup {

    /**
     * Draw a square.
     * 
     * @param turtle the turtle context
     * @param sideLength length of each side
     */
    public static void drawSquare(Turtle turtle, int sideLength) {
        turtle.color(PenColor.BLACK);
        for(int i=0;i<4;i++) {
        	turtle.forward(sideLength);
        	turtle.turn(90);
        }
    }

    /**
     * Determine inside angles of a regular polygon.
     * 
     * There is a simple formula for calculating the inside angles of a polygon;
     * you should derive it and use it here.
     * 
     * @param sides number of sides, where sides must be > 2
     * @return angle in degrees, where 0 <= angle < 360
     */
    public static double calculateRegularPolygonAngle(int sides) {
       try {
    	   return 180.0-(360.0/sides);
       }catch(Exception e) {
    	   throw new RuntimeException("implement me!");
       }
    	
    }

    /**
     * Determine number of sides given the size of interior angles of a regular polygon.
     * 
     * There is a simple formula for this; you should derive it and use it here.
     * Make sure you *properly round* the answer before you return it (see java.lang.Math).
     * HINT: it is easier if you think about the exterior angles.
     * 
     * @param angle size of interior angles in degrees, where 0 < angle < 180
     * @return the integer number of sides
     */
    public static int calculatePolygonSidesFromAngle(double angle) {
       
    	return (int)Math.ceil(360.0/(180.0-angle));
     }

    /**
     * Given the number of sides, draw a regular polygon.
     * 
     * (0,0) is the lower-left corner of the polygon; use only right-hand turns to draw.
     * 
     * @param turtle the turtle context
     * @param sides number of sides of the polygon to draw
     * @param sideLength length of each side
     */
    public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
        
    	turtle.color(PenColor.BLACK);
    	double angle=180.0-calculateRegularPolygonAngle(sides);
    	for(int i=0;i<sides;i++) {
    		turtle.forward(sideLength);
        	turtle.turn(angle);
    	}
    }

    /**
     * Given the current direction, current location, and a target location, calculate the Bearing
     * towards the target point.(旋转角度)
     * 
     * The return value is the angle input to turn() that would point the turtle in the direction of
     * the target point (targetX,targetY), given that the turtle is already at the point
     * (currentX,currentY) and is facing at angle currentBearing. The angle must be expressed in
     * degrees, where 0 <= angle < 360. 
     *
     * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
     * 
     * @param currentBearing current direction as clockwise from north
     * @param currentX current location x-coordinate
     * @param currentY current location y-coordinate
     * @param targetX target point x-coordinate
     * @param targetY target point y-coordinate
     * @return adjustment to Bearing (right turn amount) to get to target point,
     *         must be 0 <= angle < 360
     */
    public static double calculateBearingToPoint(double currentBearing, int currentX, int currentY,
                                                 int targetX, int targetY) {
     try { 
	     double angle=Math.atan2(targetY-currentY, targetX-currentX)*180/Math.PI;
	    // if(angle<0.0) angle+=360.0;
	     angle=90.0-currentBearing-angle;
	     if(angle<0) angle+=360.0;
	     return angle;
     }catch(Exception e) {
    	 throw new RuntimeException("implement me!");
     }
     }

    /**
     * Given a sequence of points, calculate the Bearing adjustments needed to get from each point
     * to the next.
     * 
     * Assumes that the turtle starts at the first point given, facing up (i.e. 0 degrees).
     * For each subsequent point, assumes that the turtle is still facing in the direction it was
     * facing when it moved to the previous point.
     * You should use calculateBearingToPoint() to implement this function.
     * 
     * @param xCoords list of x-coordinates (must be same length as yCoords)
     * @param yCoords list of y-coordinates (must be same length as xCoords)
     * @return list of Bearing adjustments between points, of size 0 if (# of points) == 0,
     *         otherwise of size (# of points) - 1
     */
    public static List<Double> calculateBearings(List<Integer> xCoords, List<Integer> yCoords){
    try {
    	int xl=xCoords.size();
    	int yl=yCoords.size();
    	List<Double> res= new ArrayList<Double>();
    	if(xl<2||yl<2||xl!=yl) return res;//比较x，y长度合法性
    	double currentAngle=0.0,tempAngle;
    	for(int i=0;i<xl-1;i++) {
    		
    		tempAngle=calculateBearingToPoint(currentAngle,xCoords.get(i),yCoords.get(i),xCoords.get(i+1),yCoords.get(i+1));
    		currentAngle=(tempAngle+currentAngle)%360;//tempAngle是旋转角度
    		res.add(tempAngle);
    		
    	}
    	return res;	
    }catch(Exception e) {
    	throw new RuntimeException("implement me!");
    }
  
    }
    
    /**
     * Given a set of points, compute the convex hull, the smallest convex set that contains all the points 
     * in a set of input points. The gift-wrapping algorithm is one simple approach to this problem, and 
     * there are other algorithms too.
     * 
     * @param points a set of points with xCoords and yCoords. It might be empty, contain only 1 point, two points or more.
     * @return minimal subset of the input points that form the vertices of the perimeter of the convex hull
     */
    public static Set<Point> convexHull(Set<Point> points) {
       try {
        	if(points.size()>2) {
        		Set<Point> res=new HashSet<Point> ();
        		Point[] p=points.toArray(new Point[points.size()]);
        		boolean[] flag =new boolean[points.size()];
        		int i,start=0,next,end;
        		double minx=p[0].x();
        		int len=p.length;
        		//找左下
        		for(i=1;i<len;i++) {
        			flag[i]=false;
        			if(p[i].x()<minx||(p[i].x()==minx&&p[i].y()<p[start].y())) {
        				minx=p[i].x();
        				start=i;
        			}
        		}
        		end=start;
        		double currentBearing=0.0;
        		do {
        			double minangle=360.0;
        			next=start;
        			for(i=0;i<len;i++) {
        				if(i!=start) {
        					double angle=calculateBearingToPoint(currentBearing,(int)p[start].x(),(int)p[start].y(),(int)p[i].x(),(int)p[i].y());
        					double d1=Math.pow(p[i].x()-p[start].x(),2.0)+Math.pow( p[i].y()-p[start].y(),2.0);
                    		double d2=Math.pow(p[next].x()-p[start].x(), 2.0)+Math.pow(p[next].y()-p[start].y(), 2.0);
        					if(flag[i]==false&&(angle<minangle||(angle==minangle&&d1>d2))) {//点满足条件
                            	
                            		minangle=angle;
                            		next=i;
                            	
                            		}
                            	}
        			}
             
        			currentBearing=(currentBearing+minangle)%360;
        			start=next;
        			flag[next]=true;
        			
        		}while(end!=start);
        		flag[end]=true;
        		for(i=0;i<len;i++) {
        			if(flag[i]==true) {
        				res.add(p[i]);
        				
        			}
        		}
        		
        		return res;
        		
        		
               }
            else {
        		System.out.println("the point is less than 3!");
        		return points;
        	}
        	
        }catch(Exception e) {
        throw new RuntimeException("implement me!");
        }
    }
    
    /**
     * Draw your personal, custom art.
     * 
     * Many interesting images can be drawn using the simple implementation of a turtle.  For this
     * function, draw something interesting; the complexity can be as little or as much as you want.
     * 
     * @param turtle the turtle context
     */
    public static void drawPersonalArt(Turtle turtle) {
    	try {
    	turtle.color(PenColor.BLACK);
        for(int i=0;i<70;i++) {
        	if(i%4==0) turtle.color(PenColor.BLACK);
        	if(i%4==1) turtle.color(PenColor.PINK);
        	if(i%4==2) turtle.color(PenColor.BLUE);
        	if(i%4==3) turtle.color(PenColor.GREEN);
        	turtle.forward(10+i*4);
        	turtle.turn(80);
        }
    		
    	}catch(Exception e) {
    	throw new RuntimeException("implement me!");	
    	}
       
    }

    /**
     * Main method.
     * 
     * This is the method that runs when you run "java TurtleSoup".
     * 
     * @param args unused
     */
    public static void main(String args[]) {
    	Set<Point> points = new HashSet<Point>();
		Set<Point> convexHull1 = new HashSet<Point>();

		

		Point p11 = new Point(1, 1);
		Point p1010 = new Point(10, 10);
		Point p110 = new Point(1, 10);
		Point p12 = new Point(1, 2);
		Point p23 = new Point(2, 3);
		Point p32 = new Point(3, 2);

		points.add(p11);
		
		convexHull1=convexHull(points);

		points.add(p1010);
		convexHull1=convexHull(points);

		points.add(p110);
		convexHull1=convexHull(points);

		points.add(p12);
		convexHull1=convexHull(points);
		points.add(p23);
		convexHull1=convexHull(points);

		points.add(p32);
		convexHull1=convexHull(points);

}
}
