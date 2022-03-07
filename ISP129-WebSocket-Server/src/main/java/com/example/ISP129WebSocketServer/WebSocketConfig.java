package com.example.ISP129WebSocketServer;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
// 宣告啟用STOMP協定來傳輸訊息
// Stomp協定是socket的子協議，因為如果直接使用websocket協議開發程式比較繁瑣
// STOMP被用來實現雙工非同步通訊能力
// 訊息是基於訊息代理(Message broker)
// 此時，可以在@Controller類中使用@MessageMapping
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
//    配製訊息代理(Message broker)
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
//        如果訊息的傳送的地址 prefixed with /topic
//        訊息會傳送到這個 broker
        config.enableSimpleBroker("/topic");
//        /app/hello is the endpoint that the GreetingController.greeting() method is mapped to handle
        config.setApplicationDestinationPrefixes("/app");

    }

//    註冊了一個節點,用來映射指定URL,方法內註冊一個STOMP的endpoint,並且指定使用SockJS協定
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/gs-guide-websocket") //新增STOMP協議的端點。這個HTTP URL是供WebSocket或SockJS客戶端訪問的地址
                .setAllowedOriginPatterns("*")
                .withSockJS(); //指定端點使用SockJS協議
//        SockJS是websocket協議的實現，增加了對瀏覽器不支援websocket的時候的相容支援 SockJS的支援的傳輸的協議有3類: WebSocket, HTTP Streaming, and HTTP Long Polling。預設使用websocket，如果瀏覽器不支援websocket，則使用後兩種的方式。 SockJS使用"Get /info"從服務端獲取基本資訊。然後客戶端會決定使用哪種傳輸方式。如果瀏覽器使用websocket，則使用websocket。如果不能，則使用Http Streaming，如果還不行，則最後使用 HTTP Long Polling
    }
}
