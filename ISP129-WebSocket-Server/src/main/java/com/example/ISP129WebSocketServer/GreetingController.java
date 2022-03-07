package com.example.ISP129WebSocketServer;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {
//    指定要接收訊息的地址，類似@RequestMapping
//    所以當訊息發送到/hello，greeting()就會被呼叫
    @MessageMapping("/hello")
//    the return value is broadcast to all subscribers of /topic/greetings
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(2000); // simulated delay
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

}