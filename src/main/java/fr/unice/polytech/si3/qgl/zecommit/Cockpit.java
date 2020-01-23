package fr.unice.polytech.si3.qgl.zecommit;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.unice.polytech.si3.qgl.zecommit.action.Action;
import fr.unice.polytech.si3.qgl.regatta.cockpit.ICockpit;
import fr.unice.polytech.si3.qgl.zecommit.goal.Regatta;

public class Cockpit implements ICockpit {
	Game game;
	CaptainMate captainMate;
	Captain captain;

	public void initGame(String json) {
		ParserInit parserInit = new ParserInit();
		try {
			this.game=parserInit.parserInitGame(json);
			this.captain= new Captain(game);
			this.captainMate= new CaptainMate(captain);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	public String nextRound(String round) {
		String res;
		ParserNext parserNext = new ParserNext();

		if(round.equals("{}"))
			return "[ ]";
		try {
			parserNext.parserNextRound(round, game);
			captain.setGame(game);
			List<Action> actions = new ArrayList<>();
			if(game.getGoal().getMode().equals("REGATTA")){
				captainMate.actions(((Regatta)game.getGoal()).getCheckpoints());
				actions = captainMate.getActionList();
			}
			Output output = new Output();
			res = output.afficheRound(actions);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			res = "[ ]";
		}
		
		return res; 

	}

	@Override
	public List<String> getLogs() {
		return new ArrayList<>();
	}
}
