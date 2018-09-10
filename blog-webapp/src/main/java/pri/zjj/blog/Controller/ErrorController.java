package pri.zjj.blog.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@Api(value="error",tags="错误接口")
@RestController
@RequestMapping("/error")
public class ErrorController {
	
	@RequestMapping("/403")
	@ApiOperation(value="403",code=403,notes="403错误")
	public Object error403(@ApiIgnore HttpServletRequest request,@ApiIgnore HttpServletResponse response) {
		if(request.getHeader("X-Requested-With")!=null && request.getHeader("X-Requested-With").equalsIgnoreCase("XMLHttpRequest")) {
			Map<String, Object> result=new HashMap<>();
			result.put("code", 403);
			response.setStatus(403);
			return result;
		}
		return new ModelAndView("error/403",HttpStatus.FORBIDDEN);
	}
}
