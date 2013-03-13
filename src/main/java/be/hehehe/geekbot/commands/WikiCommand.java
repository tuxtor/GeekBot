package be.hehehe.geekbot.commands;

import java.util.List;

import javax.inject.Inject;

import be.hehehe.geekbot.annotations.BotCommand;
import be.hehehe.geekbot.annotations.Help;
import be.hehehe.geekbot.annotations.Trigger;
import be.hehehe.geekbot.annotations.TriggerType;
import be.hehehe.geekbot.bot.TriggerEvent;
import be.hehehe.geekbot.commands.GoogleCommand.Lang;
import be.hehehe.geekbot.commands.GoogleCommand.Mode;

/**
 * Wikipedia search
 * 
 */
@BotCommand
public class WikiCommand {

	@Inject
	GoogleCommand googleCommand;

	@Trigger(value = "!wiki", type = TriggerType.STARTSWITH)
	@Help("Wikipedia search.")
	public List<String> getWikiResults(TriggerEvent event) {
		return googleCommand.google("wikipedia " + event.getMessage(),
				Lang.ENGLISH, Mode.WEB);
	}

	@Trigger(value = "!wikipedia", type = TriggerType.STARTSWITH)
	@Help("Wikipedia search.")
	public List<String> getWikiResults2(TriggerEvent event) {
		return getWikiResults(event);
	}

	@Trigger(value = "!wikies", type = TriggerType.STARTSWITH)
	@Help("Wikipedia search (Spanish).")
	public List<String> getWikiResultses(TriggerEvent event) {
		return googleCommand.google("wikipedia " + event.getMessage(),
				Lang.SPANISH, Mode.WEB);
	}

	@Trigger(value = "!wikipediaes", type = TriggerType.STARTSWITH)
	@Help("Wikipedia search (Spanish).")
	public List<String> getWikiResults2es(TriggerEvent event) {
		return getWikiResults2es(event);
	}
}
