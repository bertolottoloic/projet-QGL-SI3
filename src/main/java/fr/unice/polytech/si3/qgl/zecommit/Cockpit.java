package fr.unice.polytech.si3.qgl.zecommit;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.unice.polytech.si3.qgl.zecommit.action.Action;
import fr.unice.polytech.si3.qgl.regatta.cockpit.ICockpit;
import fr.unice.polytech.si3.qgl.zecommit.goal.Regatta;
import fr.unice.polytech.si3.qgl.zecommit.other.Checkpoint;

public class Cockpit implements ICockpit {
	InitGame initgame;
	CaptainMate captainMate;
	Captain captain;

	public void initGame(String game) {
		ParserInit parserInit = new ParserInit();
		try {
			this.initgame=parserInit.parserInitGame(game);
			this.captain= new Captain(initgame);
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
			NextRound nextRound = parserNext.parserNextRound(round);

			List<Action> actions = new ArrayList<Action>();
			if(initgame.getGoal().getMode().equals("REGATTA")){
				captainMate.actions(((Regatta)initgame.getGoal()).getCheckpoints());
				actions = captainMate.getActionList();
				captain.setNextRound(nextRound);
			}
			Sortie sortie = new Sortie();
			res = sortie.afficheRound(actions);
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
