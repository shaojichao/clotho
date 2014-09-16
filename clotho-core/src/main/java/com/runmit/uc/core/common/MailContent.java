package com.runmit.uc.core.common;

import lombok.Data;

/**
 * Created by gg on 2014/8/26.
 */
@Data
public class MailContent{
    String to;
    String subject;
    String text;
    boolean IsHtmlMail=true;//默认发送html邮件
}
