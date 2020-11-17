package hqr.spider.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Browser {
	private static ChromeDriver chromeDriver;
	
	public static ChromeDriver getChromeInstance() {
		if(chromeDriver==null) {
			ChromeOptions co = new ChromeOptions();
			co.addArguments(new String[] { "--headless" });
			co.addArguments(new String[] { "--disable-dev-shm-usage" });
			co.addArguments(new String[] { "--no-sandbox" });
			List<String> list = new ArrayList<>();
			list.add("user-agent=Mozilla/5.0 (Linux;Android 10) Chrome/80.0.3987.132 BiliApp/5560400 SearchCraft/3.6.4 (Baidu; P1 10) UCBrowser/12.9.2.1072");
			co.addArguments(list);
			chromeDriver = new ChromeDriver(co);
			chromeDriver.manage().window().maximize();
			chromeDriver.manage().deleteAllCookies();
			chromeDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		}
		return chromeDriver;
	}
}
