package fr.unice.polytech.si3.qgl.zecommit;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.unice.polytech.si3.qgl.regatta.cockpit.ICockpit;
import fr.unice.polytech.si3.qgl.zecommit.action.Action;
import fr.unice.polytech.si3.qgl.zecommit.crew.Captain;
import fr.unice.polytech.si3.qgl.zecommit.crew.CaptainMate;
import fr.unice.polytech.si3.qgl.zecommit.parser.*;


import java.util.ArrayList;
import java.util.List;

public class Cockpit implements ICockpit {
	Game game;
	CaptainMate captainMate;
	Captain captain;

	/**
	 * Construit Game Captain et CaptainMate
	 * @param jsonInitGame
	 */
	public void initGame(String jsonInitGame) {
		try {
			game = new Game();
			InitGame initGame = Parser.parseInitGame(jsonInitGame);
			setGameInfo(initGame);
			initCaptain();
		} catch (JsonProcessingException e) {
			Logs.add("Erreur Parseur InitGame");
			Logs.add(e.getMessage());
		}
	}

	public String nextRound(String jsonNextRound) {
		String res;
		try {
			updateGame(Parser.parseNextRound(jsonNextRound));

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

		res = "";
		return res;
	}


	@Override
	public List<String> getLogs() {
		return Logs.sortie();
	}


	/**
	 * Création et initialisation de l'objet Game
	 * @param initGame
	 */
	public void setGameInfo(InitGame initGame) {
		//game.setGoal(initGame.getGoal());
		//game.setShip(initGame.getShip());
		//game.setSailors(initGame.getSailors());
		//game.setShipCount(initGame.getShipCount());
	}

	/**
	 * Création du CaptainMate et du Captain
	 */
	public void initCaptain() {
		this.captainMate= new CaptainMate(game);
		this.captain= new Captain(game,captainMate);
	}

	/**
	 * Met à jour l'objet Game
	 * @param nextRound
	 */
	public void updateGame(NextRound nextRound) {
		game.setShip(nextRound.getShip());
		game.setVisibleEntities(nextRound.getVisibleEntities());
		game.setWind(nextRound.getWind());
		captain.setGame(game);
	}

}
