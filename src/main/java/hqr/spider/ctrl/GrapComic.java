package hqr.spider.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hqr.spider.service.GetAllComicByTag;

@RestController
public class GrapComic {
	
	@Autowired
	private GetAllComicByTag getAllComicByTag;
	
	@RequestMapping(value = "/getAllComicByTag")
	public String grap(String tagUrl, String tagName) {
		getAllComicByTag.getAllComicByTag(tagUrl, tagName);
		return "index.html";
	}
}
