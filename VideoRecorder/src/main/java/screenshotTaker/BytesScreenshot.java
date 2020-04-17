package screenshotTaker;

import java.io.File;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * Subclass for taking Byte[] screenshots. 
 */
public class BytesScreenshot extends Screenshot<byte[]>{

	public BytesScreenshot(WebDriver wd, String outputDirectory) {
		super(wd, outputDirectory);
		this.screenshots = new ArrayList<byte[]>();
	}
	
	@Override
	public void takeScreenshot() {
		byte[] data = ((TakesScreenshot)this.wd).getScreenshotAs(OutputType.BYTES);
		screenshots.add(data);
	}
	
	
	@Override
	public void writeFiles() {
		System.out.println("Files Found: "+this.screenshots.size());
		for(int i=0;i<this.screenshots.size();i++) {
			try {
				Files.write(new File(this.retrieveImageFilepath(i)).toPath(), this.screenshots.get(i));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Files have been written to disk!");
	}
}
