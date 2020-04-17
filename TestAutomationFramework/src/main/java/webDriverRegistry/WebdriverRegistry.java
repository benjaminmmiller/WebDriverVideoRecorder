package webDriverRegistry;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import static java.lang.StackWalker.Option.RETAIN_CLASS_REFERENCE;

import java.util.HashMap;
import java.util.Map;


public class WebdriverRegistry {
	private static WebdriverRegistry registry;
	private Map<String, WebDriver> webdrivers;
	
	private WebdriverRegistry(){
		webdrivers = new HashMap<String, WebDriver>();
	}

	public void registerWebdriverUsingTestContext(ITestContext context, WebDriver wd) {
		String className = StackWalker.getInstance()
                .walk(s -> s.skip(1).findFirst())
                .get().getClassName();
		String methodName = StackWalker.getInstance()
                .walk(s -> s.skip(1).findFirst())
                .get()
                .getMethodName();
		String wdID = className+methodName;
		context.setAttribute(wdID, wd);
		System.out.println("Setup: "+wdID);
	}
	
	public void registerWebdriver(WebDriver wd) {
		String className = StackWalker.getInstance()
                .walk(s -> s.skip(1).findFirst())
                .get().getClassName();
		String methodName = StackWalker.getInstance()
                .walk(s -> s.skip(1).findFirst())
                .get()
                .getMethodName();
		String wdID = className+methodName;
		this.webdrivers.put(wdID, wd);
		System.out.println("Setup: "+wdID);
	}

	public WebDriver getWebDriver(String key) {
		return webdrivers.get(key);
	}
	
	public static WebdriverRegistry getInstance() {
		if(registry==null) {
			registry = new WebdriverRegistry();
		}
		return registry;
	}
	
}
