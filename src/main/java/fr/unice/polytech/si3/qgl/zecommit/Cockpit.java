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
			this.initgame=parserInitGame(game);

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
			if(initgame.getGoal() instanceof Regatta)
				actions = captain.actions(((Regatta) initgame.getGoal()).getCheckpoints());
			Sortie sortie = new Sortie();
			res = sortie.afficheRound(actions);
			System.out.println(nextRound.toString());
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
