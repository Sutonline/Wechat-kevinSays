package cn.kevin.wechat.domain;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 文本消息
 * Created by yongkang.zhang on 2017/8/28.
 */
@XmlRootElement(name = "xml")
@Builder
@AllArgsConstructor
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class TextMessage {

    @XmlElement(name = "ToUserName")
    private String ToUserName;
    @XmlElement(name = "FromUserName")
    private String FromUserName;
    @XmlElement(name = "CreateTime")
    private String CreateTime;
    @XmlElement(name = "MsgType")
    private String MsgType;
    @XmlElement(name = "Content")
    private String Content;
    @XmlElement(name = "MsgId")
    private String MsgId;

    public TextMessage() {}
}
