package com.github.hellocore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.TextMessage;

@Controller
public class LineBotCustomController {

    @Autowired
    private LineMessagingClient lineMessagingClient;
    
	@RequestMapping("/echo")
	public void echo(@RequestParam String message){
		String[] listSender = (String[]) LineBotExampleApplication.getSenderIDSet().toArray();
		for (String string : listSender) {
			lineMessagingClient.pushMessage(new PushMessage(string, new TextMessage(message)));
		}
	}
}
