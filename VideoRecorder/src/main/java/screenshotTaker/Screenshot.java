package screenshotTaker;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import org.openqa.selenium.WebDriver;


/**
 * This class is the parent class that all screenshot subclasses inherent from.
 * Used to take and store screenshots
 *
 * @param <T> the file type of the screenshot set in all subclasses
 */
public class Screenshot<T> implements Runnable{
	protected WebDriver wd;
	protected List<T> screenshots;
	protected String outputDirectory;
	protected int counter;
	
	public Screenshot(WebDriver wd, String outputDirectory) {
		this.wd = wd;
		this.outputDirectory = outputDirectory;
	}
	
	public void run() {
		takeScreenshot();
		this.counter++;
		System.out.println("Times Run: "+counter);
		System.out.println(Thread.currentThread());
	}
	
	/**
	 * Method for taking the screenshot.
	 * Empty here but the is overridden in all subclasses
	 */
	public void takeScreenshot() {
		
	}
	
	public List<T> getScreenshots() {
		return this.screenshots;
	}
	
	public void writeFiles() {
		
	}
	/**
	 * File path + output name for screenshot
	 * @param index
	 * @return
	 */
	public String retrieveImageFilepath(int index) {
		return this.outputDirectory+"\\"+"image"+index+".jpg";
	}
	
	/**
	 * May remove this method, testing writing data to a text file instead of a image. 
	 * @param index
	 */
	protected void writeToTextFile(int index) {
		try {
			PrintWriter out = new PrintWriter(this.outputDirectory+"\\"+"text"+index+".txt");;
			out.println(this.screenshots.get(index));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
