package hqr.spider.service;

import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hqr.spider.dao.MhBookRepo;
import hqr.spider.dao.MhTagRepo;
import hqr.spider.domain.MhBook;
import hqr.spider.domain.MhTag;
import hqr.spider.util.Browser;

@Service
public class GetAllComicByTag {
	
	private ChromeDriver chromeDriver = Browser.getChromeInstance();
	
	@Autowired
	private MhTagRepo mhTagRepo;
	
	@Autowired
	private MhBookRepo mhBookRepo;

	public void getAllComicByTag(String tagUrl, String tagName) {
		System.out.println("TagName:"+tagName);
		
		MhTag tag = mhTagRepo.getEntityByTagName(tagName);
		if(tag!=null) {
			//one tag only can have 9999 books
			for(int i = tag.getPage()+1; i<=9999; i++) {
				System.out.println("Access Page:"+tag.getTagUrl()+"&page="+i);
				chromeDriver.get(tag.getTagUrl()+"&page="+i);
				WebElement ul = chromeDriver.findElementByClassName("manga-list-2");
				List<WebElement> lis = ul.findElements(By.tagName("li"));
				if(lis==null||lis.size()==0) {
					System.out.println("Collection completed!");
					break;
				}
				for (WebElement li : lis) {
					String bookCover = li.findElement(By.className("manga-list-2-cover")).findElement(By.tagName("img")).getAttribute("data-original");
					String bookUrl = li.findElement(By.className("manga-list-2-title")).findElement(By.tagName("a")).getAttribute("href");
					String bookName = li.findElement(By.className("manga-list-2-title")).findElement(By.tagName("a")).getText();
					String bookStatus = li.findElement(By.className("manga-list-2-tip")).findElement(By.tagName("a")).getText();
					System.out.println(bookCover+"|"+bookName+"|"+bookUrl+"|"+bookStatus);
					
					MhBook book = new MhBook();
					book.setBookUrl(bookUrl);
					book.setBookName(bookName);
					book.setBookCover(bookCover);
					book.setBookStatus(bookStatus);
					//author,bookDesc,bookAuthor,bookRegion only can be found in book details
					//bookstatus may update when book details status is completed
					book.setLastUpdateDt(new Date());
					book.setLastUpdateId("hqr");
					mhBookRepo.save(book);
					mhBookRepo.flush();
				}
				tag.setPage(i);
				mhTagRepo.save(tag);
				mhTagRepo.flush();
			}
		}
		else {
			System.out.println("No such tag");
		}
	}
}
