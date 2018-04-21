package com.study.and.studyand.domain;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.study.and.studyand.R;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.chat2.Chat;
import org.jivesoftware.smack.chat2.ChatManager;
import org.jivesoftware.smack.chat2.IncomingChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jxmpp.jid.EntityBareJid;
import org.jxmpp.jid.impl.JidCreate;

import java.net.InetAddress;


public class ChatRoomActivity extends AppCompatActivity {

    AbstractXMPPConnection connection = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Thread connTh = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    XMPPTCPConnectionConfiguration.Builder configBuilder = XMPPTCPConnectionConfiguration.builder();
                    configBuilder.setUsernameAndPassword("yoonki", "5710");
                    configBuilder.setResource("Smack");

                    configBuilder.setHostAddress(InetAddress.getByName("10.113.169.42"));
                    configBuilder.setPort(5222);
                    configBuilder.setXmppDomain("dev-yangs02.ncl.nhnsystem.com");
                    configBuilder.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);

                    connection = new XMPPTCPConnection(configBuilder.build());

            // Connect to the server
                    connection.connect();
            // Log into the server
                    connection.login();

                    ChatManager chatManager = ChatManager.getInstanceFor(connection);
                    chatManager.addIncomingListener(new IncomingChatMessageListener() {
                        @Override
                        public void newIncomingMessage(EntityBareJid entityBareJid, Message message, Chat chat) {

                        }
                    });

                    EntityBareJid jid = JidCreate.entityBareFrom("admin@dev-yangs02.ncl.nhnsystem.com");
                    Chat chat = chatManager.chatWith(jid);
                    chat.send("HELLOE");

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        connTh.start();


//            Thread.sleep(1000000);

        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<100; i++) {
                    System.out.println("CHECK");
//                    System.out.print(connection.isConnected());
                    try {
                        Thread.sleep(500);

                    } catch (Exception e) {

                    }

                }
            }
        });

        th.start();




        // roaster 를 통해서 메시지를 보낼 수 있는지를 체크한다? 메시지는 그냥 보낼 수 있다.
        // 메시지를 받을 때 메시지를 받을 게 있는지 체크한다.
        // presence는 항상 available로 한다. connection 자체로 연결을 관리.
        // roaster는 따로 사용할 필요는 없다.
        //
        setContentView(R.layout.activity_chat_room);

    }
}
