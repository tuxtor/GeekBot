package be.hehehe.geekbot.persistence.dao;

import javax.ejb.Stateless;
import javax.inject.Inject;

import be.hehehe.geekbot.utils.HalBrain;


@Stateless
public class HalDAO {


	@Inject
	HalBrain hal;

	public void save(String sentence) {
		hal.save(sentence);
	}
	
	public String getSentence() {
		return limitString(hal.getSentence()); 
	}

	public String getSentence(String... words){
		return limitString(hal.getSentence(words[0]));
	}
	
	public String getSentence(String word){
		return limitString(hal.getSentence(word));
	}
	
	public String limitString(String sentence){
		if(sentence.length()>300){
			return sentence.substring(0, 300);
		}else{
			return sentence;
		}
	}
}
