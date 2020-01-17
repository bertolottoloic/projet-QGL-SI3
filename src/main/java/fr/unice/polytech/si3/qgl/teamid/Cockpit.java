package fr.unice.polytech.si3.qgl.teamid;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.unice.polytech.si3.qgl.regatta.cockpit.ICockpit;

import static fr.unice.polytech.si3.qgl.teamid.Parser.parserInitGame;

public class Cockpit implements ICockpit {

	public void initGame(String game) {
		try {
			parserInitGame(game);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println("Init game input: " + game);
	}

	public String nextRound(String round) {
		System.out.println("Next round input: " + round);
		return "[]";
	}

	@Override
	public List<String> getLogs() {
		return new ArrayList<>();
	}
}
