package utils;


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
}
