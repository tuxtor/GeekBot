package be.hehehe.geekbot.utils;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.jibble.jmegahal.JMegaHal;

import be.hehehe.geekbot.persistence.dao.ConnerieDAO;
import be.hehehe.geekbot.persistence.model.Connerie;

@Singleton
public class HalBrain {

	@Inject
	ConnerieDAO connerieDao;
	
	@Inject
	Logger log;
	
	
	JMegaHal hal;
	@PostConstruct
	public void init() {
		hal = new JMegaHal();
		List<Connerie> phrases = connerieDao.findAll();
		log.debug("Starting braing construction from database");
		for(Connerie data:phrases){
			hal.add(data.getValue());
		}
		log.debug("Brain constructed . . . ");
	}

	
	public void save(String sentence) {
		hal.add(sentence);
	}
	
	public String getSentence() {
		return hal.getSentence();
	}

	public String getSentence(String... words){
		return hal.getSentence(words[1]);
	}
	
	public String getSentence(String word){
		return hal.getSentence(word);
	}
	
}
