package com.github.hellocore;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.github.hellocore.model.Group;
import com.github.hellocore.model.User;
import com.github.hellocore.repository.GroupRepository;
import com.github.hellocore.repository.UserRepository;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.TextMessage;

@Controller
public class LineBotCustomController {

    @Autowired
    private LineMessagingClient lineMessagingClient;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private GroupRepository groupRepository;
    
	@RequestMapping("/echo")
	@ResponseStatus(value = HttpStatus.OK)
	public void echo(@RequestParam String message){
		List<User> listSender = userRepository.listUser();
		for (User user : listSender) {
			lineMessagingClient.pushMessage(new PushMessage(user.getUserId(), new TextMessage(message)));
		}
	}
	

	@RequestMapping("/groupEcho")
	@ResponseStatus(value = HttpStatus.OK)
	public void groupEcho(@RequestParam String message){
		List<Group> listGroup = groupRepository.listGroup();
		for (Group group : listGroup) {
			lineMessagingClient.pushMessage(new PushMessage(group.getGroupId(), new TextMessage(message)));
		}
	}
	
	
	@RequestMapping("/virus11.tx")
	public @ResponseBody String virus11tx(){
		return "1";
	}
	
	@RequestMapping("/virus22.tx")
	public @ResponseBody String viru22tx(){
		return "Hello World!!!";
	}

	@RequestMapping("/virus33.tx")
	public @ResponseBody String viru33tx(){
		return "A16078870033C4898424";
	}
	
}
