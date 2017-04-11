package com.github.hellocore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

@SpringBootApplication
@LineMessageHandler
public class LineBotExampleApplication {

    @Autowired
    private LineMessagingClient lineMessagingClient;

	public static void main(String[] args) {
		SpringApplication.run(LineBotExampleApplication.class, args);
	}
			
	
	@RequestMapping("/echo")
	public void echo(@RequestParam String message){
		if(senderId != null){
			lineMessagingClient.pushMessage(new PushMessage(senderId, new TextMessage(message)));
		}
	}
	
	private static String senderId;

    @EventMapping
    public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
    	senderId = event.getSource().getUserId();
        return new TextMessage("You say:"+event.getMessage().getText());
    }

    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        System.out.println("event: " + event);
    }
}
