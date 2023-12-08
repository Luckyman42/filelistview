package com.example.filelistview.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.io.IOException;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/doc","/doc/index.html");
        File file;
        try {
            file = new ClassPathResource("static/doc").getFile();
            String[] names = file.list();

            for(String name : names)
            {
                if (new File(file.getAbsolutePath() + "/" + name).isDirectory())
                {
                    registry.addViewController("/" + name).setViewName("forward:doc/" + name);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
