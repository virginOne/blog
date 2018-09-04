package pri.zjj.blog.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class DefaultController {
	
	@RequestMapping("/")
	public ModelAndView defaultPage() {
		return new ModelAndView("redirect:/view/index.html");
	}
}
