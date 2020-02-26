package fr.unice.polytech.si3.qgl.zecommit;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.unice.polytech.si3.qgl.regatta.cockpit.ICockpit;
import fr.unice.polytech.si3.qgl.zecommit.action.Action;
import fr.unice.polytech.si3.qgl.zecommit.boat.Deck;
import fr.unice.polytech.si3.qgl.zecommit.boat.Ship;
import fr.unice.polytech.si3.qgl.zecommit.crew.Captain;
import fr.unice.polytech.si3.qgl.zecommit.crew.CaptainMate;
import fr.unice.polytech.si3.qgl.zecommit.parser.InitGame;
import fr.unice.polytech.si3.qgl.zecommit.parser.NextRound;
import fr.unice.polytech.si3.qgl.zecommit.parser.Output;
import fr.unice.polytech.si3.qgl.zecommit.parser.Parser;

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
			System.out.println(Logs.sortie());//TODO à supprimer
		}
	}

	public String nextRound(String jsonNextRound) {
		String res;
		try {
			updateGame(Parser.parseNextRound(jsonNextRound));
			List<Action> actions = new ArrayList<>();
			if(game.getGoal().isRegatta()){
				actions = captainMate.actions(game);
			}

			Output output = new Output();
			res = output.afficheRound(actions);

		} catch (JsonProcessingException e) {
			Logs.add("Erreur Parseur nextRound");
			System.out.println(Logs.sortie()); //TODO à supprimer

			res = "[ ]";
		}

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
		game.setGoal(initGame.getGoal());
		game.setShip(initGame.getShip());
		game.setSailors(initGame.getSailors());
		game.setShipCount(initGame.getShipCount());
	}

	/**
	 * Création du CaptainMate et du Captain
	 */
	public void initCaptain() {
		this.captain = new Captain(game);
		this.captainMate = new CaptainMate(captain);
	}

	/**
	 * Met à jour l'objet Game
	 * @param nextRound
	 */
	public void updateGame(NextRound nextRound) {
		Deck deck = game.getShip().getDeck();
		Ship newShip = nextRound.getShip();
		deck.updateSails(newShip.getEntities());
		newShip.setDeck(deck);
		game.setShip(newShip);
		captain.setShip(newShip);
		game.setVisibleEntities(nextRound.getVisibleEntities());
		game.setWind(nextRound.getWind());
	}

}
