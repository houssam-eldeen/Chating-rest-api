package tesing;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;

import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;



import java.lang.reflect.Type;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeoutException;

import static java.util.Arrays.asList;
import static java.util.concurrent.TimeUnit.SECONDS;


public class WebsocketTesting
{

    static final String WEBSOCKET_URI = "ws://localhost:8081/stomp";
    static final String WEBSOCKET_TOPIC = "/topic";

     BlockingQueue<String> blockingQueue;
     WebSocketStompClient stompClient;
    
    public static void main(String[] args) {
        
        WebsocketTesting wsocket = new WebsocketTesting();
        wsocket.go1();
    }
    
    public  void go1()
    {
        
        blockingQueue = new LinkedBlockingDeque<>();
        stompClient = new WebSocketStompClient(new SockJsClient(
                asList(new WebSocketTransport(new StandardWebSocketClient()))));
        
        
        StompSession session = null;
        try {
            
            session = stompClient
                .connect(WEBSOCKET_URI, new StompSessionHandlerAdapter() {})
                .get(1, SECONDS);
            
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TimeoutException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    session.subscribe(WEBSOCKET_TOPIC, new DefaultStompFrameHandler());

    String message = "MESSAGE TEST";
    session.send(WEBSOCKET_TOPIC, message.getBytes());
    
    
        
        

    }
    

    class DefaultStompFrameHandler implements StompFrameHandler {
        @Override
        public Type getPayloadType(StompHeaders stompHeaders) {
            return byte[].class;
        }

        @Override
        public void handleFrame(StompHeaders stompHeaders, Object o) {
            blockingQueue.offer(new String((byte[]) o));
        }
    }
    
    

}
