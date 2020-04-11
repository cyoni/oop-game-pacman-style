package game;

import java.util.ArrayList;
import java.util.List;

import FileFormat.CSVReader;
import GameObjects.Player;
import Robot.Play;

public class GameManager extends Thread {

	GameBoard gameboard;
	Player player;
	CSVReader CsvReader;
	Play play1;
	ArrayList<String> board_data;
	
	
	public GameManager(GameBoard gameboard) {
		this.gameboard = gameboard;
		this.player = gameboard.getPlayer();
				
	}

	public void startGame() {
	/*	// 1) Create a "play" from a file (attached to Ex4)
		String file_name = "data/Ex4_OOP_example9.csv";
		play1 = new Play(file_name);
		
		// 2) Set your ID's - of all the group members
		play1.setIDs(123);
		
		// 3)Get the GPS coordinates of the "arena"
		String map_data = play1.getBoundingBox();
		System.out.println("Bounding Box info: "+map_data);
		
		// 4) get the game-board data
		board_data = play1.getBoard();
		for(int i=0;i<board_data.size();i++) {
			System.out.println(board_data.get(i));
		}
		System.out.println();
		System.out.println("Init Player Location should be set using the bounding box info");
		
		// 5) Set the "player" init location - should be a valid location
		play1.setInitLocation(32.1040,35.2061);
		
		// 6) Start the "server"
		play1.start(); // default max time is 100 seconds (1000*100 ms).
		
		// 7) "Play" as long as there are "fruits" and time
	//	for(int i=0;i<10;i++) {
		

		start();
		// 9) print the data & save to the course DB
		String info = play1.getStatistics();
		System.out.println(info);*/
	
	}
	
	public void run() {
		while(play1.isRuning()) {
			try {
				sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	// 7.1) this is the main command to the player (on the server side)
		play1.rotate(player.getDegree()); 
		
	
		
	// 7.2) get the current score of the game
		String info = play1.getStatistics();
		
		
	//	System.out.println(info);
	// 7.3) get the game-board current state
		
		board_data = play1.getBoard();
		List<String> data = new ArrayList<>();
		for(int a=0;a<board_data.size();a++) {
			//System.out.println(board_data.get(a));
			data.add(board_data.get(a));
		}
		
		gameboard.updateGameObjects(data);
		
	
	//	System.out.println();
	}
		
	}
	
	
}
