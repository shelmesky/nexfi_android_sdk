package nexfi.cn.nexfi_android_sdk.bean;

import java.io.Serializable;

/**
 * @author gengbaolong.
 */

public class FileMessage extends TextMessage implements Serializable {
    private static final long serialVersionUID = 2L;
    public String fileName;
    public String fileSize;//文件大小
    public String fileData;//文件数据
    public int fileIcon;
    public int isPb;
    public String filePath;
}
