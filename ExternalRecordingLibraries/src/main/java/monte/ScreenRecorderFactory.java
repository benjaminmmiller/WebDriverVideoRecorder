package monte;

import java.awt.AWTException;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.io.IOException;

import org.monte.media.Format;
import org.monte.media.FormatKeys.MediaType;
import org.monte.media.Registry;
import org.monte.screenrecorder.ScreenRecorder;
import static org.monte.media.AudioFormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;
import org.monte.media.math.Rational;


/**
 * Factory build pattern used to construct Monte screen recorders.
 * Parameters can be changed here to modify output video settings.
 */
public class ScreenRecorderFactory {
	public static ScreenRecorder createNewScreenRecorder(int displayIndex){
		GraphicsConfiguration gc = GraphicsEnvironment
				.getLocalGraphicsEnvironment()
				.getScreenDevices()[displayIndex]
				.getDefaultConfiguration();
		ScreenRecorder screenRecorder;
		try {
			screenRecorder = new ScreenRecorder(gc,
			           new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
			           new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
			                CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
			                DepthKey, 24, FrameRateKey, Rational.valueOf(15),
			                QualityKey, 1.0f,
			                KeyFrameIntervalKey, 15 * 60),
			           new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black",
			                FrameRateKey, Rational.valueOf(30)),
			           null);
			return screenRecorder;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
