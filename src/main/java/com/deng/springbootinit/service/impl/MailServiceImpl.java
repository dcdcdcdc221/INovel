package com.deng.springbootinit.service.impl;

import com.deng.springbootinit.common.ErrorCode;
import com.deng.springbootinit.exception.BusinessException;
import com.deng.springbootinit.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service//没有这个注解，邮箱可能出现空指针异常
@Slf4j
public class MailServiceImpl implements MailService {

    @Resource
    private MailProperties mailProperties;

    @Resource
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public boolean sendSimpleText(String to, String subject, String content) {
        log.info("Ready to send simple email");
        SimpleMailMessage message = new SimpleMailMessage();
        //邮件发送来源
        message.setFrom(from);
        //邮件接收人
        message.setTo(to);
        //设置标题
        message.setSubject(subject);
        //设置内容
        message.setText(content);
        try{
            javaMailSender.send(message);
            log.info("Send the Simple email success");
        } catch (BusinessException e) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"普通发送邮件失败");
        }
        return true;
    }

    @Override
    public boolean sendWithHtml(String to, String subject, String html) {
//        log.info("Ready to send html mail");
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=null;
        try{
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            //邮件发送来源
            mimeMessageHelper.setFrom(from);
            //邮件发送目标
            mimeMessageHelper.setTo(to);
            //设置标题
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(html,true);
            javaMailSender.send(mimeMessage);
            log.info("Send html mail success");
        }catch (BusinessException | MessagingException e){
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"html 邮件发送失败");
        }
        return true;
    }

    @Override
    public boolean sendWithImageHtml(String to, String subject, String html, String[] cids, String[] filePaths) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=null;
        try{
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(to);

            // 设置标题
            mimeMessageHelper.setSubject(subject);
            // 设置内容，并设置内容 html 格式为 true
            mimeMessageHelper.setText(html, true);
        }catch(BusinessException | MessagingException e){
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"发送带图片的邮件失败");
        }
        return true;
    }

    @Override
    public boolean sendWithWithEnclosure(String to, String subject, String content, String[] filePaths) {
        return false;
    }
}
