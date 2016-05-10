package nexfi.cn.nexfi_android_sdk.bean;

import java.io.Serializable;

/**
 * @author gengbaolong.
 */

public class UserMessage implements Serializable {
    private static final long serialVersionUID = 4L;
    public String userId;
    public long nodeId;
    public String userNick;
    public int userAge;
    public String userGender;
    public int userAvatar;

    @Override
    public String toString() {
        return "userId=" + userId + ",userNick=" + userNick + ",nodeId=" + nodeId;
    }
}
