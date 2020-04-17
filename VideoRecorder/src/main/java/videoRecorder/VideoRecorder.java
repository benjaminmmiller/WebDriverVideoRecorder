package videoRecorder;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.bytedeco.javacpp.avcodec;
import org.bytedeco.javacpp.opencv_imgcodecs;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.openqa.selenium.WebDriver;

import fileUtils.TestingFrameworkFileUtils;
import screenshotTaker.Base64Screenshot;
import screenshotTaker.BytesScreenshot;
import screenshotTaker.FileScreenshot;
import screenshotTaker.RobotScreenshot;
import screenshotTaker.Screenshot;

/**
 * A individual video recorder that can be applied on a WebDriver instance.
 * Used for scheduling screenshot tasks and packing them into a video.
 */
public class VideoRecorder {
	private WebDriver wd;
	private final ScheduledExecutorService schedule;
	private long startTimeMillis;
	private long endTimeMillis;
	private long recordingTimeMillis;
	private int framerate;
	//All the needed filepath directories
	private String tempImageDirectory;
	private String outputVideoDirectory;
	private String videoRecordingDirectory;
	private String videoID;
	private Screenshot screenshotScheduler;
	private RecordType recordType;
	
	/**
	 * The type of screenshot to take.
	 * WebDriver providers three different types (Base64, File, Bytes).
	 * The ROBOT record type is used to record the entire desktop.
	 */ 
	public enum RecordType{
		/**
		 * WebDriver screenshot using Base64. Saves screenshots as String.
		 */
		BASE64,
		/**
		 * WebDriver screenshot using File. Saves screenshots as as File.
		 */
		FILE,
		/**
		 * WebDriver screenshot using Bytes. Saves screenshots as Bytes[].
		 */
		BYTES,
		/**
		 * Non-WebDriver screenshot that records the entire desktop. Saves screenshots as BufferedImage.
		 */
		ROBOT
	}
	
	/**
	 * The builder pattern is applied here to construct VideoRecorders
	 */
	public static class Builder{
		private int framerate;
		private WebDriver wd;
		private String videoID;
		private RecordType recordType;
		
		public Builder(){
			this.framerate=24;
			this.videoID = UUID.randomUUID().toString().replace("-", "");
			this.recordType = RecordType.BASE64;
		}
		
		public Builder recordType(RecordType type) {
			this.recordType = type;
			return this;
		}
		
		public Builder framerate(int framerate) {
			this.framerate=framerate;
			return this;
		}
		
		public Builder webdriver(WebDriver wd) {
			this.wd=wd;
			return this;
		}
		
		public Builder videoID(String videoID) {
			this.videoID=videoID;
			return this;
		}
		
		public VideoRecorder build(){
			return new VideoRecorder(this);
		}
		
	}
	
	private VideoRecorder(Builder builder) {
		this.wd=builder.wd;
		this.framerate = builder.framerate;
		this.videoID = builder.videoID;
		this.recordType = builder.recordType;
		this.schedule = Executors.newScheduledThreadPool(100);
		this.videoRecordingDirectory = TestingFrameworkFileUtils.getProjectFilePath()+"\\test-output\\recordings";
		this.tempImageDirectory = videoRecordingDirectory+"\\images"+this.videoID;
		this.outputVideoDirectory = videoRecordingDirectory+"\\videos";
		// Setup all the necessary file directories.
		setupDirectories();
		// Initialize the screenshotScheduler based on the RecordType set.
		setScreenshotBasedOnRecordType();
	}
	
	private void setScreenshotBasedOnRecordType() {
		if(this.recordType==RecordType.BASE64) {
			this.screenshotScheduler = new Base64Screenshot(this.wd, this.tempImageDirectory);
		}
		else if (this.recordType==RecordType.BYTES) {
			this.screenshotScheduler = new FileScreenshot(this.wd, this.tempImageDirectory);
		}
		else if(this.recordType==RecordType.FILE) {
			this.screenshotScheduler = new BytesScreenshot(this.wd, this.tempImageDirectory);
		}
		else if(this.recordType==RecordType.ROBOT) {
			this.screenshotScheduler= new RobotScreenshot(this.wd, this.tempImageDirectory);
		}
	}	
	
	private void setupDirectories() {
		TestingFrameworkFileUtils.createDirectory(this.videoRecordingDirectory, 
				false);
		TestingFrameworkFileUtils.createDirectory(this.tempImageDirectory, 
				true);
		TestingFrameworkFileUtils.createDirectory(this.outputVideoDirectory, 
				false);
	}
	
	private void cleanUpTempImages() {
		TestingFrameworkFileUtils.deleteDirectory(this.tempImageDirectory);
	}
	
	/**
	 * Starts the video recording.
	 */
	public void start() {
		this.startTimeMillis = System.currentTimeMillis();
		// Schedules the screenshot to 
		this.schedule.scheduleAtFixedRate(screenshotScheduler, 0, (1000/this.framerate), TimeUnit.MILLISECONDS);
	}
	
	/**
	 * Stops the video recording. The start() method must be called before the video can be stopped.
	 */
	public void stop() {
		if(Objects.isNull(this.startTimeMillis)) {
			throw new RuntimeException("The recording cannot be stopped since it has not been started."
					+ "Please call the .start() method before attemtping to stop the recording.");
		}
		else {
			this.endTimeMillis = System.currentTimeMillis();
			this.schedule.shutdown();
			this.recordingTimeMillis = this.endTimeMillis - this.startTimeMillis;
			System.out.println("Recording Time: "+this.recordingTimeMillis+"ms");
		}
	}
	
	/**
	 * Writes all the screenshots to the disk and then constructs an output video. 
	 */
	public void writeAndPackageVideo() {
		try {
			this.schedule.awaitTermination(1, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		screenshotScheduler.writeFiles();
		packageVideo();
		cleanUpTempImages();
	}
	
	/**
	 * Converts all the image files in a given directory to a video. 
	 */
	private void packageVideo() {
		//https://cooltrickshome.blogspot.com/2016/12/images-to-movie-converter-using-java.html
		File[] files = TestingFrameworkFileUtils.retrieveFilesInDirectory(this.tempImageDirectory);
		
		List<File> filesList = Arrays.asList(files);
		TestingFrameworkFileUtils.sortFiles(filesList);
		
		OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
		
		int videoWidth = 0;
		int videoHeight = 0;
		try {
			BufferedImage bimg = ImageIO.read(filesList.get(0));
			videoWidth = bimg.getWidth();
			videoHeight = bimg.getHeight();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String tempFilePath = this.outputVideoDirectory+"\\"+TestingFrameworkFileUtils.generateDateForFilename()+".mp4";
		FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(tempFilePath, videoWidth, videoHeight);
		try {
			recorder.setFrameRate(filesList.size()/(this.recordingTimeMillis/1000));
			recorder.setVideoCodec(avcodec.AV_CODEC_ID_MPEG4);  
	        recorder.setVideoBitrate(9000);  
	        recorder.setFormat("mp4"); 
	        recorder.setVideoQuality(0);
	        recorder.start();
	        for(int i=0;i<filesList.size();i++) {
	        	recorder.record(converter.convert(opencv_imgcodecs.cvLoadImage(filesList.get(i).getAbsolutePath())));
	        }
	        recorder.stop();
		}
		catch (org.bytedeco.javacv.FrameRecorder.Exception e){
			e.printStackTrace();  
		}
	}
	
	
}
