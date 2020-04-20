package videoRecorder;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

import videoRecorder.VideoRecorder.RecordType;

/**
 * A VideoManager is used to manage VideoRecorder. Useful when an individual test contains multiple WebDrivers.
 * Every time a new WebDriver is added to a VideoManger, a VideoRecorder is created for that WebDriver.
 * It is optional to use this class, you can also create VideoRecorders directly. 
 */
public class VideoManager {
	private List<VideoRecorder> videoRecorders;
	private boolean hasStartedRecording;
	private boolean hasRecordedVideo;
	
	public VideoManager() {
		this.videoRecorders = new ArrayList<VideoRecorder>();
		this.hasStartedRecording = false;
		this.hasRecordedVideo = false;
	}
	
	/**
	 * Adds a WebDriver to the VideoManager so it can be recorded. 
	 * @param wd the WebDriver to record.
	 */
	public void addWebDriver(WebDriver wd) {
		VideoRecorder videoRecorder =  new VideoRecorder.Builder().webdriver(wd).build();
		videoRecorders.add(videoRecorder);
	}
	
	/**
	 * Adds a WebDriver to the VideoManager so it can be recorded. 
	 * @param wd the WebDriver to record.
	 * @param framerate optional parameter to set the desired framerate. 
	 * Due to the slowness of the WebDriver takesScreenshot() method, this framerate is not guaranteed. 
	 */
	public void addWebDriver(WebDriver wd, int framerate) {
		VideoRecorder videoRecorder =  new VideoRecorder.Builder().webdriver(wd).framerate(framerate).build();
		videoRecorders.add(videoRecorder);
	}
	
	/**
	 * Adds a WebDriver to the VideoManager so it can be recorded. 
	 * @param wd the WebDriver to record.
	 * @param recordType optional parameter to set the screenshot taking method using the RecordType Enum. Defaulted to BASE64. 
	 */
	public void addWebDriver(WebDriver wd, RecordType recordType) {
		VideoRecorder videoRecorder =  new VideoRecorder.Builder().webdriver(wd).recordType(recordType).build();
		videoRecorders.add(videoRecorder);
	}
	
	/**
	 * Adds a WebDriver to the VideoManager so it can be recorded. 
	 * @param wd the WebDriver to record.
	 * @param framerate optional parameter to set the desired framerate. 
	 * Due to the slowness of the WebDriver takesScreenshot() method, this framerate is not guaranteed. 
	 * @param recordType optional parameter to set the screenshot taking method using the RecordType Enum. Defaulted to BASE64. 
	 */
	public void addWebDriver(WebDriver wd, int framerate, RecordType recordType) {
		VideoRecorder videoRecorder =  new VideoRecorder.Builder().webdriver(wd).recordType(recordType).build();
		videoRecorders.add(videoRecorder);
	}
	
	/**
	 * Start recording all WebDrivers in this VideoManager
	 */
	public void startRecordering() {
		if(this.hasStartedRecording){
			throw new RuntimeException("The recording has already started. Please only call the startRecording() method once.");
		}
		else {
			for(VideoRecorder videoRecorder:videoRecorders) {
				videoRecorder.start();
				this.hasStartedRecording=true;
			}
		}
	}
	
	/**
	 * Stop recording all WebDrivers in this VideoManager
	 */
	public void stopRecordering() {
		if(this.hasStartedRecording) {
			for(VideoRecorder videoRecorder:videoRecorders) {
				videoRecorder.stop();
			}
			this.hasRecordedVideo=true;
		}
		else {
			throw new RuntimeException("The recording has not been started yet. Please call the startRecording() method before stopping it.");
		}
	}
	
	/**
	 * Writes the temporary image files to disk and packages them into a video.
	 */
	public void packageVideo() {
		if(this.hasRecordedVideo) {
			for(VideoRecorder videoRecorder:videoRecorders) {
				videoRecorder.writeAndPackageVideo();
			}
		}
		else {
			throw new RuntimeException("No videos have been recorded yet. "
					+ "Please call the startRecording() method followed by the stopRecording() method before packaging the video.");
		}
	}
}
