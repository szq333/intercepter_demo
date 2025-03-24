package jlxj.interceptor_demo.config;

import jlxj.interceptor_demo.interceptor.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName InterceptorConfig
 * @Description TODO
 * @Author 阿强
 * @Date2025/3/24 19:47
 * @Version 1.0
 **/
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    //注册拦截器。
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        MyInterceptor myInterceptor = new MyInterceptor();
        //实例添加到拦截器注册表中,addPathPatterns设置该拦截器拦截的路径
        registry.addInterceptor(myInterceptor).addPathPatterns("/**");

    }

}
