package hqr.spider.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Index {
	
	@RequestMapping(value = {"/","/index.html"})
	public String index1() {
		
		return "index";
	}
	
}
