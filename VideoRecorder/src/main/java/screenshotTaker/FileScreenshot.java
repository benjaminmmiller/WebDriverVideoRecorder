package screenshotTaker;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * Subclass for taking File screenshots. 
 */
public class FileScreenshot extends Screenshot<File>{

	public FileScreenshot(WebDriver wd, String outputDirectory) {
		super(wd, outputDirectory);
		this.screenshots = new ArrayList<File>();
	}
	
	@Override
	public void takeScreenshot() {
		File data = ((TakesScreenshot)this.wd).getScreenshotAs(OutputType.FILE);
		screenshots.add(data);
	}
	
	@Override
	public void writeFiles() {
		System.out.println("Files Found: "+this.screenshots.size());
		for(int i=0;i<this.screenshots.size();i++) {
			File destFile = new File(this.retrieveImageFilepath(i));
			try {
				FileUtils.copyFile(screenshots.get(0), destFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Files have been written to disk!");
	}
}
