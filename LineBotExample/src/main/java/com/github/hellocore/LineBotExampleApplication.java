package com.github.hellocore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

@SpringBootApplication
@LineMessageHandler
public class LineBotExampleApplication {


	public static void main(String[] args) {
		SpringApplication.run(LineBotExampleApplication.class, args);
	}
	
	private static String senderId;

    @EventMapping
    public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
    	setSenderId(event.getSource().getUserId());
        return new TextMessage("You say:"+event.getMessage().getText());
    }

    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        System.out.println("event: " + event);
    }

	public static String getSenderId() {
		return senderId;
	}

	public static void setSenderId(String senderId) {
		LineBotExampleApplication.senderId = senderId;
	}
}
