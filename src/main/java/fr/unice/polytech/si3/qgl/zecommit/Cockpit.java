package fr.unice.polytech.si3.qgl.zecommit;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.unice.polytech.si3.qgl.zecommit.action.Action;
import fr.unice.polytech.si3.qgl.regatta.cockpit.ICockpit;
import fr.unice.polytech.si3.qgl.zecommit.goal.Regatta;

import static fr.unice.polytech.si3.qgl.zecommit.Parser.*;

public class Cockpit implements ICockpit {
	InitGame initgame;

	public void initGame(String game) {
		try {
			Parser parse = new Parser();
			this.initgame=parse.parserInitGame(game);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	public String nextRound(String round) {
		String res;
		if(round.equals("{}"))
			return "[ ]";
		try {
			NextRound nextRound = parserNextRound(round);
			Captain captain = new Captain(nextRound, initgame.getSailors().size());
			List<Action> actions = new ArrayList<Action>();
			if(initgame.getGoal().getMode().equals("Regatta")){
				actions = captain.actions(((Regatta) initgame.getGoal()).getCheckpoints());
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
