package drug.commons.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import drug.action.BaseAction;
import drug.dto.UsersFunction;

/**
 * 登录拦截器
 * @author Evan
 *
 */
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		Object object = request.getSession().getAttribute("user");
		
		String uri = request.getRequestURI();
		System.out.println("uri   " + uri);
		if (object == null) { //未登录
			response.sendRedirect(request.getContextPath() + "/index.html");
			return false;
		} else {
//			System.out.println("已登录用户: " + ((UsersFunction)object).getUsername());
			if (HandlerMethod.class.equals(handler.getClass())) {
				HandlerMethod method = (HandlerMethod) handler;
				Object controller = method.getBean();
				// 判断是否为登录接口实现类
				if (controller instanceof BaseAction) {
					BaseAction action = (BaseAction) controller;
					action.setUser((UsersFunction)object);
				}
			}
		}
		return true;
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
