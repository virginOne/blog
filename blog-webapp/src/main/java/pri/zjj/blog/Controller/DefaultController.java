package pri.zjj.blog.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@Api(value="testClass",tags="测试类")
@RestController
@RequestMapping("/")
public class DefaultController {
	
	@ApiIgnore
	@RequestMapping("/")
	public ModelAndView defaultPage() {
		return new ModelAndView("index");
	}
	
	@RequestMapping("/test/{id}")
	@ApiOperation(value="test",httpMethod="GET",notes="测试",response=java.lang.String.class,consumes="application/json",tags="测试类")
	@ApiImplicitParam(name="id",value="ID",required=false,paramType="path",dataType="String")
	@Cacheable(value="index")
	public String test_get(@ApiIgnore HttpSession  session,@PathVariable("id") int id) {
		session.setAttribute("id", id);
		return String.valueOf(id);
	}
}
