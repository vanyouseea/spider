package hqr.spider.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hqr.spider.service.GetAllTag;

@RestController
public class GrapTag {
	@Autowired
	private GetAllTag getAllTag;
	
	@RequestMapping(value = "/grapTag")
	public String grap() {
		getAllTag.getAllTag();
		return "index.html";
	}
}
