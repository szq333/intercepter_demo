package jlxj.interceptor_demo.interceptor;

import com.alibaba.fastjson.JSON;
import jlxj.interceptor_demo.common.BaseContext;
import jlxj.interceptor_demo.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @ClassName MyInterceptor
 * @Description TODO
 * @Author 阿强
 * @Date2025/3/24 19:28
 * @Version 1.0
 **/
@Slf4j
public class MyInterceptor implements HandlerInterceptor {

    //路径匹配器，支持通配符
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException, ServletException{
        //2、获取本次请求url
        String requestURI = request.getRequestURI();
        log.info("拦截到的请求：{}",requestURI);
        //定义不需要过滤的路径,Filter会拦截所有请求，包括前端资源
        String[] urls = new String[]{
                "/employee/login",
                "/front/**"
        };
        //3、判断本次请求是否需要处理
        boolean check = check(urls, requestURI);

        //4、如果不需要处理直接放行
        if(check){
            log.info("不需要拦截的请求：{}",requestURI);
            return true;
        }
        //判断登录状态，如果已经登录直接放行
        if(request.getSession().getAttribute("employee") != null){
            log.info("该请求：id为{}用户已登录",request.getSession().getAttribute("employee"));

            Long empId = (Long) request.getSession().getAttribute("employee");
            //这里将用户id放入当前线程中，方便后续使用
            BaseContext.setCurrentId(empId);

            return true;
        }
        log.info("用户未登陆了");
        //5、如果未登录则返回未登录结果，通过输出流方式向客户端页面响应数据
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return false;
    }

    /**
     * 路径匹配，检查本次请求是否放行
     */
    public boolean check(String[] urls, String requestURI){
        for(String url : urls){
            boolean match = PATH_MATCHER.match(url, requestURI);
            if(match){
                return true;
            }
        }
        return false;
    }
}
