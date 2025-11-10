package web.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "web.controller")  
public class WebConfig implements WebMvcConfigurer {

    private final ApplicationContext ctx;

    public WebConfig(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        var r = new SpringResourceTemplateResolver();
        r.setApplicationContext(ctx);
        r.setPrefix("/WEB-INF/pages/");
        r.setSuffix(".html");
        r.setCharacterEncoding("UTF-8");
        r.setCacheable(false); // dev-friendly
        return r;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        var e = new SpringTemplateEngine();
        e.setTemplateResolver(templateResolver());
        e.setEnableSpringELCompiler(true);
        return e;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        var vr = new ThymeleafViewResolver();
        vr.setTemplateEngine(templateEngine());
        vr.setCharacterEncoding("UTF-8");
        registry.viewResolver(vr);
    }
}
