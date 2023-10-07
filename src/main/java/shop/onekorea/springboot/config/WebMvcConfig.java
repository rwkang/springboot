/**
 * 2023.10.04 Blank Url Path Controller, http://localhost:8088 여기까지만 주어진 Blank Url을 돌려주는 .html 파일 지정.
 */

package shop.onekorea.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 아래 2개 메소드 연구: zdx.monitor.WebMvcConfig.class
     * @author: rwkang on 2023.10.07
     *
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getMyInterceptor()).addPathPatterns("/**").excludePathPatterns("/loginForm", "/login","/register","/documentModel/**","/404.html","/css/**", "/js/**","/images/**","/boximages/**");
    }

    @Bean
    public AuthorizedInterceptor getMyInterceptor(){
        return new AuthorizedInterceptor();
    }
    // 이하는 "SignUp"을 위한 것임.
     */

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        WebMvcConfigurer.super.addViewControllers(registry);

        /** 2023.09.27 Conclusion. 만약 application.yml에서, thymeleaf.prefix 설정을 아래와 같이,
         * 맨 끝에, "/"가 없다면, 여기서 "/home" 처럼, 처음에 "/"를 넣어 주어야 한다.
         * spring:
         *   thymeleaf:
         *     cache: false # source reload, front-end reload nothing...
         *     prefix: file:src/main/resources/templates # classpath:/templates
          */

//        registry.addViewController("/home/home").setViewName("/create/form");
//        registry.addViewController("/home/home").setViewName("/crawling/weather");
//        registry.addViewController("/home/home").setViewName("/home/index");
//        registry.addViewController("/member/list").setViewName("/member/member_list");
        registry.addViewController("/").setViewName("/index");

        /** from zdx.monitor.WebMvcConfig.class
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/main.html").setViewName("dashboard");
         */

    }

}
