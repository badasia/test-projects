package src.main.websocket.server;

import src.main.websocket.model.Message;
import src.main.websocket.model.MessageDecoder;
import src.main.websocket.model.MessageEncoder;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static java.lang.String.format;

@ServerEndpoint(value = "/chat", encoders = MessageEncoder.class, decoders = MessageDecoder.class)
public class ServerEndpointSocket {

    static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void onOpen(Session session) {
        System.out.println(format("%s joined the chat room.", session.getId()));
        peers.add(session);
    }

    @OnMessage
    public void onMessage(Message message, Session session) throws IOException, EncodeException {
        String user = (String) session.getUserProperties().get("user");
        if (user == null) {
            session.getUserProperties().put("user", message.getSender());
        }
        if ("quit".equalsIgnoreCase(message.getContent())) {
            session.close();
        }

        System.out.println(format("[%s:%s] %s", session.getId(), message.getReceived(), message.getContent()));

        //broadcast the message
        for (Session peer : peers) {
            if (!session.getId().equals(peer.getId())) { // do not resend the message to its sender
                peer.getBasicRemote().sendObject(message);
            }
        }
    }

    @OnClose
    public void onClose(Session session) throws IOException, EncodeException {
        System.out.println(format("%s left the chat room.", session.getId()));
        peers.remove(session);
        //notify peers about leaving the chat room
        for (Session peer : peers) {
            Message chatMessage = new Message();
            chatMessage.setSender("Server");
            chatMessage.setContent(format("%s left the chat room.", (String) session.getUserProperties().get("user")));
            chatMessage.setReceived(new Date());
            peer.getBasicRemote().sendObject(chatMessage);
        }
    }

}