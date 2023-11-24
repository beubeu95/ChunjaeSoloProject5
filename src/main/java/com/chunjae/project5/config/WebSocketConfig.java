package com.chunjae.project5.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@RequiredArgsConstructor
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/chat/send");         // 발행자가 보낸 메시지가 @MessageMapping("/chat/send")의 어노테이션이 붙은 곳으로 향한다. (발행자가 메시지 보내는 곳)
        registry.enableSimpleBroker("/chat/ChatDetail");  // 발행자가 "/chat/ChatDetail"의 경로로 메시지를 주면 구독자들에게 전달 (구독자가 들어가는 곳)

        // 발행자가  @MessageMapping("/chat/send") 메시지로 보내면 메시지가 "/chat/ChatDetail"로 가게 됨
        // 이 메시지는 broker에게 가게되고 메시지 브로커에서 "/chat/ChatDetail"를 구독 중인 구독자에게 이걸 보냄
    }


    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws-stomp")   //SockJS 연결 주소
                .withSockJS(); //버전 낮은 브라우저에서도 적용 가능
        // 주소 : ws://localhost:8080/ws-stomp
    }

}
