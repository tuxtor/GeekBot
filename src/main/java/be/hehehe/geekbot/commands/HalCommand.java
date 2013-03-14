package be.hehehe.geekbot.commands;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import be.hehehe.geekbot.annotations.BotCommand;
import be.hehehe.geekbot.annotations.Help;
import be.hehehe.geekbot.annotations.RandomAction;
import be.hehehe.geekbot.annotations.Trigger;
import be.hehehe.geekbot.annotations.TriggerType;
import be.hehehe.geekbot.bot.TriggerEvent;
import be.hehehe.geekbot.persistence.dao.HalDAO;

/**
 * Stores all lines spoken on the channel in order to create a Markov brain. 
 * The bot will also give one of those sentences back when addressed or randomly in a conversation.
 */
@BotCommand
public class HalCommand {

	@Inject
	HalDAO dao;

	@Trigger(type = TriggerType.EVERYTHING)
	public List<String> storeEveryLines(TriggerEvent event) {
		String message = event.getMessage();
		List<String> result = new ArrayList<String>();
		if (!event.hasURL()) {
			if (!event.isNickInMessage() && message.length() > 9
					&& !message.contains("<") && !message.contains(">")
					&& !event.isStartsWithTrigger()) {
				dao.save(message);
			}
		}

		return result;
	}

	@RandomAction(2)
	@Trigger(type = TriggerType.BOTNAME)
	public String getRandomLine(TriggerEvent event) {		
		String randomLine;
		if(event.getMessage()!=null&&!event.getMessage().isEmpty()){
			randomLine=dao.getSentence(event.getMessage());
		}else{
			randomLine=dao.getSentence();
		}
		return randomLine;
	}

	@Trigger(value = "!hal", type = TriggerType.STARTSWITH)
	@Help("Returns a markov simple sentence matching given arguments.")
	public String getRandQuote(TriggerEvent event) {
		String r = "";
		String keywords = event.getMessage();
		if (StringUtils.isNotBlank(keywords)) {		
			r = dao.getSentence(keywords);
		}else{
			r = dao.getSentence();
		}
		return r;
	}

}
