package fr.unice.polytech.si3.qgl.zecommit;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.unice.polytech.si3.qgl.regatta.cockpit.ICockpit;
import fr.unice.polytech.si3.qgl.zecommit.action.Action;
import fr.unice.polytech.si3.qgl.zecommit.crew.Captain;
import fr.unice.polytech.si3.qgl.zecommit.crew.CaptainMate;
import fr.unice.polytech.si3.qgl.zecommit.parser.Output;
import fr.unice.polytech.si3.qgl.zecommit.parser.ParserInit;
import fr.unice.polytech.si3.qgl.zecommit.parser.ParserNext;

import java.util.ArrayList;
import java.util.List;

public class Cockpit implements ICockpit {
	Game game;
	CaptainMate captainMate;
	Captain captain;

	public void initGame(String json) {
		ParserInit parserInit = new ParserInit();
		try {
			this.game=parserInit.parserInitGame(json);
			this.captainMate= new CaptainMate(game);
		} catch (JsonProcessingException e) {
			Logs.add("Erreur Parseur InitGame");
		}
	}

	public String nextRound(String round) {
		String res;
		Logs.add("\n - - - \n");
		ParserNext parserNext = new ParserNext();

		if(round.equals("{}")) {
			return "[ ]";
		}
		try {
			parserNext.parserNextRound(round, game);
			List<Action> actions = new ArrayList<>();
			if(game.getGoal().isRegatta()){
				actions = captainMate.actions(game);
			}
			Output output = new Output();
			res = output.afficheRound(actions);
		} catch (JsonProcessingException e) {
			Logs.add("Erreur Parseur nextRound");
			res = "[ ]";
		}
		
		return res; 

	}

	@Override
	public List<String> getLogs() {
		return Logs.sortie();
	}
}
