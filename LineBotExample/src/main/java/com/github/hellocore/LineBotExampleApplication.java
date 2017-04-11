package com.github.hellocore;

import java.util.HashSet;
import java.util.Set;

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
		
	private static Set<String> senderIDSet = new HashSet<>();

    public static Set<String> getSenderIDSet() {
		return senderIDSet;
	}

	@EventMapping
    public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) {    	
    	senderIDSet.add(event.getSource().getSenderId());
        return new TextMessage("You say:"+event.getMessage().getText());
    }

    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        System.out.println("event: " + event);
    }
    
}
