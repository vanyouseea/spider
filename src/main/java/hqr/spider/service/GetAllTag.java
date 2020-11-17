package hqr.spider.service;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hqr.spider.dao.MhTagRepo;
import hqr.spider.domain.MhTag;
import hqr.spider.util.Browser;

@Service
public class GetAllTag {
	
	private ChromeDriver chromeDriver = Browser.getChromeInstance();
	
	@Autowired
	private MhTagRepo mhTagRepo;
	
	public void getAllTag() {
		chromeDriver.get("https://m.svipmh.com/tag");
		WebElement ul = chromeDriver.findElementByClassName("search-class");
		List<WebElement> lis = ul.findElements(By.tagName("li"));
		for (WebElement li : lis) {
			WebElement a = li.findElement(By.tagName("a"));
			WebElement p = li.findElement(By.tagName("p"));

			int cnt = mhTagRepo.checkTagExist(a.getAttribute("href"), p.getText());
			if(cnt==0) {
				MhTag mhTag = new MhTag();
				mhTag.setTagName(p.getText());
				mhTag.setTagUrl(a.getAttribute("href"));
				mhTagRepo.save(mhTag);
				mhTagRepo.flush();
			}
			else {
				System.out.println("Tag:"+p.getText()+" exist, do not insert");
			}
		}
	}
	
}
