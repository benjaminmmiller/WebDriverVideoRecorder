package screenshotTaker;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * Subclass for taking Base64 screenshots. 
 */
public class Base64Screenshot extends Screenshot<String>{	
	public Base64Screenshot(WebDriver wd, String outputDirectory) {
		super(wd, outputDirectory);
		this.screenshots = new ArrayList<String>();
	}
	
	@Override
	public void takeScreenshot() {
		String data = ((TakesScreenshot)this.wd).getScreenshotAs(OutputType.BASE64);
		this.screenshots.add(data);
		//writeBase64File(data);
	}
	
	@Override
	public void writeFiles() {
		System.out.println("Files Found: "+this.screenshots.size());
		for(int i=0;i<this.screenshots.size();i++) {
			writeBase64FileBasedOnIndex(i);
		}
		System.out.println("Files have been written to disk!");
	}
	
	private void writeBase64File(String data) {
		byte[] decodedImg = Base64.getDecoder()
                .decode(data.getBytes(StandardCharsets.UTF_8));
		Path destFile = Paths.get(this.retrieveImageFilepath(this.counter));
		try {
			Files.write(destFile, decodedImg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void writeBase64FileBasedOnIndex(int fileIndex) {
		byte[] decodedImg = Base64.getDecoder()
                .decode(this.screenshots.get(fileIndex).getBytes(StandardCharsets.UTF_8));
		Path destFile = Paths.get(this.retrieveImageFilepath(fileIndex));
		try {
			Files.write(destFile, decodedImg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
