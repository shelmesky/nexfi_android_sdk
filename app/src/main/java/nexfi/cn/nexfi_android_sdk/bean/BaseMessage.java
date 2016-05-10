package nexfi.cn.nexfi_android_sdk.bean;

import java.io.Serializable;

/**
 * @author gengbaolong.
 */

public class BaseMessage implements Serializable {
    private static final long serialVersionUID = 1L;
    public int messageType;
    public String sendTime;
    public String chat_id;//会话id
    public UserMessage userMessage;//消息实体
    //标记
    public String uuid;
}
