package drug.commons.interceptor;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import drug.dto.AjaxResult;
import drug.dto.UsersFunction;

public class FunctionInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String url = request.getRequestURI();
		String projectUrl = request.getContextPath();
		url = url.substring(projectUrl.length());
		System.out.println("URL:       " + url);
		
		if (url.indexOf("selection") != -1 || url.indexOf("login") != -1) {
			return true;
		}
		
		UsersFunction user = (UsersFunction) request.getSession().getAttribute("user");
		List<String> functions = user.getFunctions();
		if (functions.contains(url)) {
			return true;
		} else {
			AjaxResult ajaxResult = new AjaxResult(false, "没有进行该操作的权限!!");
			String str = new ObjectMapper().writeValueAsString(ajaxResult);
			PrintWriter writer = response.getWriter();
			writer.write(str);
			writer.flush();
			writer.close();
			return false;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
