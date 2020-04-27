package utils;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class MiscUtils {
	public static void delay(long delayTimeInMillis) {
		long currentTime = System.currentTimeMillis();
		long stopTime = currentTime+delayTimeInMillis;
		
		System.out.println("Starting Delay");
		while(currentTime<stopTime) {
			currentTime = System.currentTimeMillis();
		}
		System.out.println("Delay is done");
	}
	
	
	public static <T> List<T> iteratorToList(Iterator<T> iterator){
		List<T> list = new ArrayList<T>();
		iterator.forEachRemaining(list::add);
		return list;
	}
	
	/**
	 * Utility method that returns the distance between two points as an int value
	 * Uses pythagoreans theorem to do this
	 * @param point1
	 * @param point2
	 * @return
	 */
	public static int getDistanceBetweenPoints(Point point1, Point point2) {
		//Get the x and y differences
		int xDifference = point2.x - point1.x;
		int yDifference = point2.y - point1.y;
		
		//Square the x and y differences
		xDifference*=xDifference;
		yDifference*=yDifference;
		
		//Get the square root of the sum of the x and y differences
		Long distance = Math.round(Math.sqrt((xDifference+yDifference)));
		//Return the distance as a int value;
		return distance.intValue();
	}
}
