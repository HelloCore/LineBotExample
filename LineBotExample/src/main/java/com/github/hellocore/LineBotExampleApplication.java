package com.github.hellocore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.hellocore.model.Group;
import com.github.hellocore.model.User;
import com.github.hellocore.repository.GroupRepository;
import com.github.hellocore.repository.UserRepository;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.JoinEvent;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

@SpringBootApplication
@LineMessageHandler
public class LineBotExampleApplication {


	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private GroupRepository groupRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(LineBotExampleApplication.class, args);
		
	}

	@EventMapping
    public void handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
		
		userRepository.saveUser(new User(event.getSource().getUserId()));
		System.out.println("USER:"+ event.getSource().getUserId());
//        return new TextMessage("You say:"+event.getMessage().getText());
    }

    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        System.out.println("event: " + event);
    }
    
    @EventMapping
    public void handlerJoinGroup(JoinEvent event) {
    	groupRepository.saveGroup(new Group(event.getSource().getSenderId()));
    	System.out.println("GROUP:"+event.getSource().getSenderId());
    }
    
}
