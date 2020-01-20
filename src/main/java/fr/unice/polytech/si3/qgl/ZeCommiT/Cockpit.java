package fr.unice.polytech.si3.qgl.ZeCommiT;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.JsonProcessingException;
import fr.unice.polytech.si3.qgl.ZeCommiT.action.Action;
import fr.unice.polytech.si3.qgl.ZeCommiT.action.Oar;
import fr.unice.polytech.si3.qgl.regatta.cockpit.ICockpit;

import static fr.unice.polytech.si3.qgl.ZeCommiT.Parser.*;

public class Cockpit implements ICockpit {
	public void initGame(String game) {
		try {
			InitGame initGame=parserInitGame(game);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	public String nextRound(String round) {
		try {
			NextRound nextRound = parserNextRound(round);
			System.out.println(nextRound.toString());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		Sortie sortie = new Sortie();
		List<Action> actions = new ArrayList<>();
		for(int i = 0; i < nbSailors; i++) {
			actions.add(i, new Oar(i));
		}

		return sortie.afficheRound(actions);
	}

	@Override
	public List<String> getLogs() {
		return new ArrayList<>();
	}
}
