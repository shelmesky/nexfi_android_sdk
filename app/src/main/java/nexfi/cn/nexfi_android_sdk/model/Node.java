package nexfi.cn.nexfi_android_sdk.model;

import android.app.Activity;
import android.util.Log;

import org.slf4j.impl.StaticLoggerBinder;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Random;

import io.underdark.Underdark;
import io.underdark.transport.Link;
import io.underdark.transport.Transport;
import io.underdark.transport.TransportKind;
import io.underdark.transport.TransportListener;
import io.underdark.util.nslogger.NSLogger;
import io.underdark.util.nslogger.NSLoggerAdapter;
import nexfi.cn.nexfi_android_sdk.listener.ReceiveTextMsgListener;
import nexfi.cn.nexfi_android_sdk.operation.TextMsgOperation;
import nexfi.cn.nexfi_android_sdk.util.Debug;


/**
 * @author Mark, gengbaolong.
 */

public class Node implements TransportListener {
    private boolean running;
    private Activity activity;
    private long nodeId;
    private Transport transport;

    private ArrayList<Link> links = new ArrayList<>();
    private int framesCount = 0;
    TextMsgOperation textMsgOperation = new TextMsgOperation();
    ReceiveTextMsgListener mReceiveTextMsgListener = null;

    public void setReceiveTextMsgListener(ReceiveTextMsgListener receiveTextMsgListener) {
        this.mReceiveTextMsgListener = receiveTextMsgListener;
    }

    private String userSelfId;

    public Node(Activity activity) {
        this.activity = activity;

        do {
            nodeId = new Random().nextLong();
        } while (nodeId == 0);

        if (nodeId < 0)
            nodeId = -nodeId;

        configureLogging();

        EnumSet<TransportKind> kinds = EnumSet.of(TransportKind.BLUETOOTH, TransportKind.WIFI);
        //kinds = EnumSet.of(TransportKind.WIFI);
        //kinds = EnumSet.of(TransportKind.BLUETOOTH);

        this.transport = Underdark.configureTransport(
                234235,
                nodeId,
                this,
                null,
                activity.getApplicationContext(),
                kinds
        );
    }

    private void configureLogging() {
        NSLoggerAdapter adapter = (NSLoggerAdapter)
                StaticLoggerBinder.getSingleton().getLoggerFactory().getLogger(Node.class.getName());
        adapter.logger = new NSLogger(activity.getApplicationContext());
        adapter.logger.connect("192.168.5.203", 50000);

        Underdark.configureLogging(true);
    }

    public void start() {
        if (running)
            return;

        running = true;
        transport.start();
        Log.e("TAG", "---Node-------------------------------start------");
    }

    public void stop() {
        if (!running)
            return;

        running = false;
        transport.stop();
    }

    public ArrayList<Link> getLinks() {
        return links;
    }

    public int getFramesCount() {
        return framesCount;
    }

    //发送数据
    public void broadcastFrame(byte[] frameData) {
        if (links.size() == 0) {
            if (Debug.DEBUG) {
                Log.e("TAG", "---node-------------node------broadcastFrame------------------");
            }
            return;
        }
        for (Link link : links) {
            link.sendFrame(frameData);

        }
    }

    //region TransportListener
    @Override
    public void transportNeedsActivity(Transport transport, ActivityCallback callback) {
        callback.accept(activity);
    }

    //连接
    @Override
    public void transportLinkConnected(Transport transport, Link link) {
        if (Debug.DEBUG) {
            Log.e("TAG", "---node-------------------------------------transportLinkConnected------------");
        }
        links.add(link);

    }

    //断开连接
    @Override
    public void transportLinkDisconnected(Transport transport, Link link) {
        links.remove(link);//移除link
        if (Debug.DEBUG) {
            Log.e("TAG", "----断开连接-----------------------------------------" + links.size());
        }
    }

    //接收数据，自动调用
    @Override
    public void transportLinkDidReceiveFrame(Transport transport, Link link, byte[] frameData) {
        //接收到数据后将用户数据发送给对方

    }


    /**
     * 根据nodeId获取link
     *
     * @param nodeId
     * @return
     */
    public Link getLink(long nodeId) {
        for (int i = 0; i < links.size(); i++) {
            Link link = links.get(i);
            if (link.getNodeId() == nodeId) {
                return link;
            }
        }
        return null;
    }
} // Node
