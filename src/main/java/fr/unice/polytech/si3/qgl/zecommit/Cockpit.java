package fr.unice.polytech.si3.qgl.zecommit;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.unice.polytech.si3.qgl.zecommit.action.Action;
import fr.unice.polytech.si3.qgl.regatta.cockpit.ICockpit;
import fr.unice.polytech.si3.qgl.zecommit.crew.Captain;
import fr.unice.polytech.si3.qgl.zecommit.crew.CaptainMate;
import fr.unice.polytech.si3.qgl.zecommit.parser.Output;
import fr.unice.polytech.si3.qgl.zecommit.parser.ParInit;
import fr.unice.polytech.si3.qgl.zecommit.parser.ParserInit;
import fr.unice.polytech.si3.qgl.zecommit.parser.ParserNext;

public class Cockpit implements ICockpit {
	Game game;
	CaptainMate captainMate;
	Captain captain;

	public void initGame(String json) {
		ParInit parserInit = new ParInit();
		try {
			this.game=parserInit.parse(json);
			this.captainMate= new CaptainMate();
			this.captain= new Captain(game,captainMate);
		} catch (JsonProcessingException e) {
			Logs.add("Erreur Parseur InitGame");
		}
	}

	public String nextRound(String round) {
		String res;
		ParserNext parserNext = new ParserNext();

		if(round.equals("{}")) {
			return "[ ]";
		}
		try {
			parserNext.parserNextRound(round, game);
			captain.refreshGame(game);
			List<Action> actions = new ArrayList<>();
			if(game.getGoal().isRegatta()){
				captain.actions();
				actions = captainMate.getActionList();
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
