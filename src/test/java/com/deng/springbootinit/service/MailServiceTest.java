package com.deng.springbootinit.service;

import com.deng.springbootinit.model.vo.useFreeMarker;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.ui.Model;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.ui.freemarker.SpringTemplateLoader;
import org.springframework.web.servlet.ModelAndView;


import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class MailServiceTest {

    @Autowired(required = false)
    private MailService mailService;


    @Autowired
    private ResourceLoader resourceLoader;

    @Test
    public void sendSimpleText() throws IOException, TemplateException {
        Map<String, String> map = new HashMap<>();
        ModelAndView modelAndView = new ModelAndView();
        map.put("title", "title");
        modelAndView.addAllObjects(map);
        SpringTemplateLoader springTemplateLoader = new SpringTemplateLoader(resourceLoader,
                "classpath:templates/mail");
        Configuration configuration = new Configuration(new Version("2.3.1"));
        configuration.setTemplateLoader(springTemplateLoader);
        Template template = configuration.getTemplate("mail.ftl");
        String s = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        String to = "18013750986@163.com";
        String title = "标题，简单文本发送测试";
        String content = s;
        Assert.assertTrue(mailService.sendWithHtml(to, title, content));
    }
}
