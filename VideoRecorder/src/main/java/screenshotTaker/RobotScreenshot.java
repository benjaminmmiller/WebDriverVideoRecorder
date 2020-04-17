package screenshotTaker;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


/**
 * Subclass for taking Robot screenshots. Takes a screenshot of the entire screen. 
 */
public class RobotScreenshot extends Screenshot<BufferedImage>{

	public RobotScreenshot(WebDriver wd, String outputDirectory) {
		super(wd, outputDirectory);
		this.screenshots = new ArrayList<BufferedImage>();
	}
	
	@Override
	public void takeScreenshot() {
		BufferedImage image = null;
		try {
			image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		screenshots.add(image);
	}
	
	
	@Override
	public void writeFiles() {
		System.out.println("Files Found: "+this.screenshots.size());
		for(int i=0;i<this.screenshots.size();i++) {
			try {
				ImageIO.write(screenshots.get(i), "jpg", new File(this.retrieveImageFilepath(i)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Files have been written to disk!");
	}
}
