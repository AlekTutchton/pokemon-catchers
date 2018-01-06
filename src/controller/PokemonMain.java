package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;
import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PopupControl;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import model.Arena;
import model.Map;
import model.Persistence;
import model.Trainer;



public class PokemonMain extends Application implements Observer {
    Font fontLarge = Font.font("Droid Sans", FontWeight.BOLD, 15);
    Font fontSmall = Font.font("Droid Sans", FontWeight.BOLD, 10);
	Stage istage;

	private Map map;
	private Arena arena;
	private Arena caveA;
	private Arena snowA;
	private Arena fireA;
	private Arena waterA;
	
	private Trainer trainer;
	private BorderPane all;
	private String pv = "";
	private String pd = "";
	private static String[] songDatabase = new String[6];
	private static int songCount = 0;
	private MediaPlayer mediaPlayer;

	private GraphicsContext gc;

	private Canvas canvas;

	private TextArea items;

	int xi;
	int yi;
	int xf;
	int yf;
	
	private Stage battleStage;

	private Image grass;
	private Image water;
	private Image dirt;
	private Image other;
	private Image border;
	private Image borderC;
	private Image ball;
	private Image rock;
	private Image cave;
	private Image logo;
	private Image back;
	private Image pika;
	private Image mapSel;
	private Image caveD;
	private Image light;
	private Image portal;
	private Image item;
	private Image arenaB;
	private Image arenaW;
	private Image arenaP;

	private Image caveW;
	private Image sparkles;
	
	private Image telePort;
	private Image snow;
	private Image snowB;
	private Image ice;
	private Image fire;
	private Image waterW;
	private Image sand;
	private Image path;

	private Image spritesheet;
	private Timeline timeline;
	private boolean isMoving = false;
	private boolean scMovingY = false;
	private boolean scMovingX = false;
	private boolean isContinue = false;
	
	private boolean isMap = false;
	private boolean isCave = false;

	private boolean isSnow = false;
	private boolean isFire = false;
	private boolean isWater = false;
	
	private boolean isIce = false;
	
	private boolean gameOver = false;
	
	private boolean firstA = true;
	private boolean firstM = true;

	private boolean firstS = true;
	private boolean firstW = true;
	private boolean firstF = true;

	private boolean multi = false;
	
	private boolean isWin = false;

	private Button newGame;
	private Button con;
	private Button multiplayerGame;
	private Button info;
	private Button map1;
	private Button map2;

	private BorderPane inventory;
	private String i;

	private double sx, sy, sw, sh, dx, dy, dw, dh, tic;
	private double faceX, faceY, posX, posY;

	private int maxSteps = 1000000;

	public static void main(String[] args) {
		launch();

	}

	@Override
	public void start(Stage stage) throws Exception {
		sx = 18;
		sy = 56;
		sw = 18;
		sh = 30;
		dx = 0;
		dy = 0;
		dw = 15;
		dh = 20;
		tic = 0;
		faceX = 40;
		faceY = sy;
		map = new Map();
		map.createMap1();
		map.setTeleport(18, 2);
		
		arena = new Arena();
		arena.createArena();
		
		caveA = new Arena();
		caveA.createCave();
		
		snowA = new Arena();
		snowA.createSnow();
		
		fireA = new Arena();
		fireA.createFire();
		
		waterA = new Arena();
		waterA.createWater();

		trainer = new Trainer();
		trainer.setPos(2, 3);
		map.setTrainer(trainer.getX(), trainer.getY());
		all = new BorderPane();
		
		makeSongDataBase();

		grass = new Image("file:Images/grass9.png");
		water = new Image("file:Images/water1.jpg");
		dirt = new Image("file:Images/dirt1.jpg");
		other = new Image("file:Images/grass2a.jpg");
		border = new Image("file:Images/border6.png");
		ball = new Image("file:Images/Pokeball1.png");
		rock = new Image("file:Images/rock1.png");
		cave = new Image("file:Images/cave2.png");
		item = new Image("file:Images/grassI.png");
		logo = new Image("file:Images/logo.png");
		back = new Image("file:Images/introBack1.png");
		pika = new Image("file:Images/pikachu2.gif");
		mapSel = new Image("file:Images/pokemon2.png");
		caveW = new Image("file:Images/caveWater.jpg");
		caveD = new Image("file:Images/caveDirt1.png");
		light = new Image("file:Images/caveLight2.png");
		borderC = new Image("file:Images/rock2.png");

		portal = new Image("file:Images/portal2.png");

		arenaB = new Image("file:Images/stars.jpg");
		arenaW = new Image("file:Images/white.jpg");
		arenaP = new Image("file:Images/red.png");
		
		telePort = new Image("file:Images/telePortal3.png");
		snow = new Image("file:Images/Snow.jpg");
		snowB = new Image("file:Images/snowB.png");
		ice = new Image("file:Images/ice1.jpg");
		fire = new Image("file:Images/lava2.jpg");
		waterW = new Image("file:Images/water1.png");
		sand = new Image("file:Images/sand1.png");
		path = new Image("file:Images/path.jpeg");
		
		sparkles = new Image("file:Images/sparkles6.png");

		spritesheet = new Image("file:Images/sprite2.png");

		introScreen();
	
		Scene scene = new Scene(all, 600, 600);

		stage.setScene(scene);
		stage.show();
		stage.setOnCloseRequest(new WritePersistentObject());

	}
	
	/**
	 * Handler that is called when the user tries to exit the game.
	 * This gives the user the option to save their game.  If chosen to save
	 * this handler sends the current trainer to Persistence.writePersistedTrainer();
	 * 
	 */
	private class WritePersistentObject implements EventHandler<WindowEvent> {

	    @Override
	    public void handle(WindowEvent event) {
	      Alert alert = new Alert(AlertType.CONFIRMATION);
	      alert.setTitle("Shut Down Option");
	      alert.setHeaderText("SAVE?");
	      Optional<ButtonType> result = alert.showAndWait();

	      if (result.get() == ButtonType.OK) {
	        Persistence.writePersistedTrainer(trainer);
	       
	        
	      }
	      
	    }
	  }

	/**
	 * Sets up the intro screen which includes buttons that can choose to 
	 * either continue from a game saved using persistence, or to start a new game.
	 */
	private void introScreen() {
		canvas = new Canvas(600, 400);
		all.setTop(canvas);
		newGame = new Button("New Game");
		con = new Button("Continue");
		info = new Button("Instructions");
		ImageView p = new ImageView(pika);
		p.setFitWidth(50);
		p.setFitHeight(50);
		multiplayerGame = new Button("Multiplayer");
		multiplayerGame.setOnMouseClicked(event -> {
		BattleViewRowan mpBattle = new BattleViewRowan(true, trainer, this);

		Scene battleScene = new Scene(mpBattle, 475, 475);
		battleStage = new Stage();
		battleStage.initModality(Modality.APPLICATION_MODAL);
		battleStage.setScene(battleScene);
		battleStage.show();

		});
		newGame.setMinWidth(100);
		con.setMinWidth(150);
		con.setMinHeight(60);

		gc = canvas.getGraphicsContext2D();
		gc.drawImage(back, 0, 0, 600, 450);
		gc.drawImage(logo, 40, 40, 500, 200);

		all.setStyle("-fx-background-color: #7FAC71");
		TilePane tileButtons = new TilePane(Orientation.HORIZONTAL);
		tileButtons.setHgap(50);
		tileButtons.setAlignment(Pos.TOP_CENTER);
		tileButtons.getChildren().addAll(newGame, con, multiplayerGame);
		newGame.setGraphic(p);
		all.setCenter(tileButtons);
		all.setBottom(info);
		
	
		EventHandler<ActionEvent> handler = new ButtonHandler();
		
		playMusic(null);

		newGame.setOnAction(handler);
		con.setOnAction(handler);
		info.setOnAction(handler);

	}
	
	
	/**
	 * Handles whether the user chose to begin a new game or to continue a saved game.
	 * If a new game is chosen, the user is sent to a screen where they can choose which map 
	 * they want to use.
	 * If "Continue" is chosen, the last game saved using persistence is generated.
	 *  
	 *
	 */
	private class ButtonHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			if (event.getSource() == newGame) {
				Canvas c = new Canvas(600, 200);
				GraphicsContext g;
				g = c.getGraphicsContext2D();
				all.setCenter(null);
				all.setStyle("-fx-background-color: #B1DDF9");
				all.setBottom(c);
				g.drawImage(mapSel, 0, -150);
				gc.clearRect(0, 0, 600, 600);
				gc.drawImage(logo, 40, 40, 500, 200);

				map1 = new Button("Map 1");
				map2 = new Button("Map 2");

				mapHandler handler = new mapHandler();

				map1.setMinWidth(100);
				map2.setMinWidth(100);

				map1.setOnAction(handler);
				map2.setOnAction(handler);

				TilePane tileButtons = new TilePane(Orientation.HORIZONTAL);
				tileButtons.setHgap(50);
				tileButtons.setAlignment(Pos.TOP_CENTER);
				tileButtons.getChildren().addAll(map1, map2);

				all.setCenter(tileButtons);

			}
			if (event.getSource() == con) {
				map.unsetTrainer(trainer.getX(), trainer.getY());
				trainer = Persistence.readPersistedTrainer();
				
				isMap = true;
				String loc = trainer.getRoom();
				if(loc.equals("water")) {
//					System.out.println("location: " + loc);
					
					setLocation("water");
					waterA.setTrainer(trainer.getX(), trainer.getY());
				}
				else if(loc.equals("fire")) {
//					System.out.println("location: " + loc);
					setLocation("fire");
					fireA.setTrainer(trainer.getX(), trainer.getY());
				}
				else if(loc.equals("cave")) {
//					System.out.println("location: " + loc);
					setLocation("cave");
					caveA.setTrainer(trainer.getX(), trainer.getY());
				}
				else if(loc.equals("snow")) {
//					System.out.println("location: " + loc);
					setLocation("snow");
					snowA.setTrainer(trainer.getX(), trainer.getY());
					
				}
				else if(loc.equals("map")) {
//					System.out.println("location: " + loc);
					setLocation("map");
					map.setTrainer(trainer.getX(), trainer.getY());
				}
				else {
					setLocation("arena");
//					System.out.println("location: " + loc);
					arena.setTrainer(trainer.getX(), trainer.getY());
				}
				
				createDisplay();
			}
			if(event.getSource() == info) {
				Stage pStage = new Stage();
				pStage.initModality(Modality.APPLICATION_MODAL);

				BorderPane pdex = new BorderPane();
				Scene inv;

				TextArea dex = new TextArea();
				pdex.setPadding(new Insets(10, 10, 10, 10));
//				String i = "";
//				i = getInv();
				FileReader fr = null;
				try {
					fr = new FileReader("instructions.txt");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				BufferedReader br = new BufferedReader(fr);
				String sCurrentLine;
				String cat = " ";
				try {
					while ((sCurrentLine = br.readLine()) != null) {
						cat += sCurrentLine + "\n";
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dex.setText(cat);
				dex.setEditable(false);
				pdex.setCenter(dex);
				pdex.setStyle("-fx-background-color: #E0FFFF");

				inv = new Scene(pdex, 370, 450);
//				EventHandler<KeyEvent> handler = new inventoryHandler();
//				pdex.setOnKeyPressed(handler);
				pStage.setScene(inv);
				pStage.show();
			}

		}
	}
	
	/**
	 * Sets the boolean for the given location to true and the boolean for the other 
	 * locations to false.
	 * @param loc
	 */
	public void setLocation(String loc) {
		if(loc.equals("snow")) {
			isSnow = true;
			isMap = false;
			isCave = false;
			isFire = false;
			isWater = false;
		}
		if(loc.equals("map")) {
			isSnow = false;
			isMap = true;
			isCave = false;
			isFire = false;
			isWater = false;
		}
		if(loc.equals("cave")) {
			isSnow = false;
			isMap = false;
			isCave = true;
			isFire = false;
			isWater = false;
		}
		if(loc.equals("fire")) {
			isSnow = false;
			isMap = false;
			isCave = false;
			isFire = true;
			isWater = false;
		}
		if(loc.equals("water")) {
			isSnow = false;
			isMap = false;
			isCave = false;
			isFire = false;
			isWater = true;
		}
		if(loc.equals("arena")) {
			isSnow = false;
			isMap = false;
			isCave = false;
			isFire = false;
			isWater = false;
		}
	}

	/**
	 * 
	 * This handles the map chosen by the user.  If "Map 1" is chosen, the game begins
	 * in the large single player map.  If "Map 2" is chosen, the game begins in the 
	 * multiplayer arena.
	 *
	 */
	private class mapHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			if (event.getSource() == map1) {
				isMap = true;
			}
			if (event.getSource() == map2) {
				isMap = false;
				arena.setTrainer(15, 14);
				trainer.setPos(15, 14);
			}
			FadeTransition ft = new FadeTransition(Duration.millis(100), all);
			ft.setFromValue(1);
			ft.setToValue(0);
			ft.play();
			ft.setOnFinished((ActionEvent event1)->{
				fadeInAnimation();
			});
			createDisplay();
		}

	}

	/**
	 * Creates a string of all of the trainer's inventory.
	 * 
	 */
	private String getInv() {
		String i = "              Inventory\n";
		i += "------------------------------\n";
		i += "PokeBalls: " + trainer.getBalls() + "\n";
		i += "Bait: " + trainer.getBait() + "\n";
		i += "Rocks: " + trainer.getRocks() + "\n";
		i += "Potions: " + trainer.getPotion() + "\n";
		i += "Zinc: " + trainer.getZinc() + "\n";
		i += "------------------------------\n";

		return i;
	}

	/**
	 * Creates a text area which uses getInv() to show the inventory to the user.
	 */
	private void showInventory() {
		istage = new Stage();
		istage.initModality(Modality.APPLICATION_MODAL);

		inventory = new BorderPane();
		Scene inv;

		items = new TextArea();
		inventory.setPadding(new Insets(20, 20, 20, 20));
		String i = "";
		i = getInv();
		items.setText(i);
		items.setEditable(false);
		inventory.setCenter(items);
		inventory.setStyle("-fx-background-color: #E0FFFF");

		inv = new Scene(inventory, 240, 240);
//		EventHandler<KeyEvent> handler = new inventoryHandler();
//		inventory.setOnKeyPressed(handler);
		istage.setScene(inv);
		istage.show();

	}
	
	/**
	 * Presents the trainer's captured Pokemon to the user.
	 */
	private void displayPokedex() {
		
		Stage pStage = new Stage();
		pStage.initModality(Modality.APPLICATION_MODAL);

		BorderPane pdex = new BorderPane();
		Scene inv;

		TextArea dex = new TextArea();
		pdex.setPadding(new Insets(20, 20, 20, 20));
//		String i = "";
//		i = getInv();
		dex.setText(pd);
		dex.setEditable(false);
		pdex.setCenter(dex);
		pdex.setStyle("-fx-background-color: #E0FFFF");

		inv = new Scene(pdex, 240, 240);
//		EventHandler<KeyEvent> handler = new inventoryHandler();
//		pdex.setOnKeyPressed(handler);
		pStage.setScene(inv);
		pStage.show();

	}

//	private class inventoryHandler implements EventHandler<KeyEvent> {
//
//		@Override
//		public void handle(KeyEvent event) {
//			if (event.getCode() == KeyCode.I) {
//				// istage.getScene().getWindow().hide();
//				istage.hide();
//			}
//		}
//
//	}

	
	/**
	 * Depending on which room the trainer is currently in, this will
	 * generate the appropriate GUI.
	 */
	private void createDisplay() {
		if (!gameOver) {
			all.setTop(null);
			all.setCenter(null);
			canvas = new Canvas(600, 600);

			if (isMap) {
				pv = map.playerView(trainer.getX(), trainer.getY());
				createGUI(trainer.getX(), trainer.getY());

			} 
			else if (isCave) {
				pv = caveA.playerView(trainer.getX(), trainer.getY());
				createGUIA(trainer.getX(), trainer.getY());
			}
			else if(isSnow) {
				pv = snowA.playerView(trainer.getX(), trainer.getY());
				createGUIA(trainer.getX(), trainer.getY());
			}
			else if(isFire) {
				pv = fireA.playerView(trainer.getX(), trainer.getY());
				createGUIA(trainer.getX(), trainer.getY());
			}
			else if(isWater) {
				pv = fireA.playerView(trainer.getX(), trainer.getY());
				createGUIA(trainer.getX(), trainer.getY());
			}
			else {
					pv = arena.playerView(trainer.getX(), trainer.getY());
					createGUIA(trainer.getX(), trainer.getY());

				}

			all.setCenter(canvas);
			EventHandler<KeyEvent> handler = new keyEventHandler();
			canvas.setOnKeyPressed(handler);
			canvas.setFocusTraversable(true);
		}

	}

	
	/**
	 * Generates the GUI given the trainer's x and y position.
	 * @param x
	 * @param y
	 */
	private void createGUI(int x, int y) {
		gc = canvas.getGraphicsContext2D();
		scMovingY = true;
		scMovingX = true;
				

		int w = 20;
		int h = 20;

		int vx = 0;
		int vy = 0;

		xf = x + 15;
		yf = y + 15;

		xi = x - 15;
		yi = y - 15;

		if (xf > 100) {
			xi -= (xf - 100);
			xf = 100;
			scMovingX = false;
		}
		if (yf > 100) {
			yi -= (yf - 100);
			yf = 100;
			scMovingY = false;
		}
		if (xi <= 0) {
			xf += (xi * -1);
			xi = 0;
			scMovingX = false;
		}
		if (yi <= 0) {
			yf += (yi * -1);
			yi = 0;
			scMovingY = false;
		}

		for (int r = yi; r < yf; r++) {
			for (int c = xi; c < xf; c++) {
				if (map.getRoom(c, r) == 'T') {
					if (map.isGrass(c, r)) {
						gc.drawImage(other, vy, vx, w, h);
						gc.drawImage(grass, vy, vx, w, h);
					}
					else if (map.isItem(c, r)) {
						gc.drawImage(other, vy, vx, w, h);
						gc.drawImage(item, vy, vx, w, h);
					}
					else if (map.isPortal(c, r)) {
						gc.drawImage(other, vy, vx, w, h);
						gc.drawImage(portal, vy, vx, w, h);
					} 
					else if(map.isTeleport(c, r)) {
						gc.drawImage(other, vy, vx, w, h);
						gc.drawImage(telePort, vy, vx, w, h);
					}
					else if (map.isPath(c, r)) {
						gc.drawImage(dirt, vy, vx, w, h);
					}
					else if (map.isOther(c, r)) {
						gc.drawImage(other, vy, vx, w, h);
					}
					else if (map.isWater(c, r)) {
						gc.drawImage(water, vy, vx, w, h);
					}

					else if (map.isCave(c, r)) {
						gc.drawImage(other, vy, vx, w, h);
						gc.drawImage(cave, vy, vx, w, h);
					}
					else {
						gc.drawImage(other, vy, vx, w, h);

					}
					gc.drawImage(spritesheet, faceX, faceY, 18, 27, vy + 4, vx - 5, dw, dh);
				} else if (map.isItem(c, r) && map.isGrass(c, r)) {
					gc.drawImage(other, vy, vx, w, h);
					gc.drawImage(item, vy, vx, w, h);
				} else if (map.isRock(c, r)) {
					if (map.isGrass(c, r)) {
						gc.drawImage(grass, vy, vx, w, h);
					}
					if (map.isOther(c, r)) {
						gc.drawImage(other, vy, vx, w, h);
					}
					if (map.isWater(c, r)) {
						gc.drawImage(water, vy, vx, w, h);
					}
					if (map.isPath(c, r)) {
						gc.drawImage(dirt, vy, vx, w, h);
					}
					gc.drawImage(rock, vy, vx, w, h);
				} else if(map.isTeleport(c, r)) {
					gc.drawImage(other, vy, vx, w, h);
					gc.drawImage(telePort, vy, vx, w, h);
				} else if (map.isCave(c, r)) {
					gc.drawImage(other, vy, vx, w, h);
					gc.drawImage(cave, vy, vx, w, h);
				} else if (map.isPortal(c, r)) {
					gc.drawImage(other, vy, vx, w, h);
					gc.drawImage(portal, vy, vx, w, h);
				} else if (map.getRoom(c, r) == 'B') {
					if (map.isGrass(c, r)) {
						gc.drawImage(other, vy, vx, w, h);
						gc.drawImage(grass, vy, vx, w, h);
					}
					if (map.isOther(c, r)) {
						gc.drawImage(other, vy, vx, w, h);
					}
					if (map.isWater(c, r)) {
						gc.drawImage(water, vy, vx, w, h);
					}
					if (map.isPath(c, r)) {
						gc.drawImage(dirt, vy, vx, w, h);
					}
					gc.drawImage(ball, vy, vx, w, h);
				} else if (map.getRoom(c, r) == 'G') {
					gc.drawImage(other, vy, vx, w, h);
					gc.drawImage(grass, vy, vx, w, h);
				} else if (map.getRoom(c, r) == 'W') {
					gc.drawImage(water, vy, vx, w, h);
				} else if (map.getRoom(c, r) == 'P') {
					gc.drawImage(dirt, vy, vx, w, h);
				} else if (map.getRoom(c, r) == 'X') {
					gc.drawImage(other, vy, vx, w, h);
					gc.drawImage(border, vy, vx, w, h);
				}

				else if (map.getRoom(c, r) == 'O') {
					gc.drawImage(other, vy, vx, w, h);

				}
				vy += 20;

			}
			vx += 20;
			vy = 0;
		}

	}

	/**
	 * Generates the GUI given the trainer's x and y position if the 
	 * trainer is currently not in the main map.
	 * @param x
	 * @param y
	 */
	private void createGUIA(int x, int y) {
		isIce = false;
		gc = canvas.getGraphicsContext2D();
		scMovingY = true;
		scMovingX = true;

		int w = 20;
		int h = 20;

		int vx = 0;
		int vy = 0;

		xf = x + 15;
		yf = y + 15;

		xi = x - 15;
		yi = y - 15;

		if (xf > 29) {
			xi -= (xf - 29);
			xf = 29;
			scMovingX = false;
		}
		if (yf > 29) {
			yi -= (yf - 29);
			yf = 29;
			scMovingY = false;
		}
		if (xi <= 0) {
			xf += (xi * -1);
			xi = 0;
			scMovingX = false;
		}
		if (yi <= 0) {
			yf += (yi * -1);
			yi = 0;
			scMovingY = false;
		}

		for (int r = yi; r < yf; r++) {
			for (int c = xi; c < xf; c++) {
				if(isCave) {
					trainer.inCave();
					if (caveA.getRoom(c, r) == 'T') {
						if (caveA.isGrass(c, r)) {
							gc.drawImage(caveD, vy, vx, w, h);
						}
						if (caveA.isOther(c, r)) {
							gc.drawImage(caveD, vy, vx, w, h);
						}
						if (caveA.isWater(c, r)) {
							gc.drawImage(caveW, vy, vx, w, h);
						}
						if (map.isCave(c, r)) {
							gc.drawImage(caveD, vy, vx, w, h);
							gc.drawImage(light, vy, vx, w, h);
						}
						if (caveA.isPath(c, r)) {
							gc.drawImage(dirt, vy, vx, w, h);
						} else {
							gc.drawImage(caveD, vy, vx, w, h);

						}
						gc.drawImage(spritesheet, faceX, faceY, 18, 27, vy + 4, vx - 5, dw, dh);
					} else if(caveA.isItem(c, r)) {
						gc.drawImage(caveD, vy, vx, w, h);
						gc.drawImage(sparkles, vy, vx, w, h);
					}
					else if (map.isCave(c, r)) {
						gc.drawImage(caveD, vy, vx, w, h);
						gc.drawImage(light, vy, vx, w, h);
					} else if (caveA.getRoom(c, r) == 'G') {
						gc.drawImage(caveD, vy, vx, w, h);
						gc.drawImage(grass, vy, vx, w, h);
					} else if (caveA.getRoom(c, r) == 'W') {
						gc.drawImage(caveW, vy, vx, w, h);
					} else if (caveA.getRoom(c, r) == 'P') {
						gc.drawImage(dirt, vy, vx, w, h);
					} else if (caveA.getRoom(c, r) == 'X') {
						gc.drawImage(caveD, vy, vx, w, h);
						gc.drawImage(borderC, vy, vx, w, h);
					}

					else if (caveA.getRoom(c, r) == 'O') {
						gc.drawImage(caveD, vy, vx, w, h);

					}
				}
				else if(isSnow) {
					trainer.inSnow();
					if (snowA.getRoom(c, r) == 'T') {
						if (snowA.isGrass(c, r)) {
							gc.drawImage(snow, vy, vx, w, h);
							gc.drawImage(grass, vy, vx, w, h);
						}
						else if (snowA.isWater(c, r)) {
							
							gc.drawImage(ice, vy, vx, w, h);
						}
						else if(snowA.isTeleport(c, r)) {
							gc.drawImage(snow, vy, vx, w, h);
							gc.drawImage(telePort, vy, vx, w, h);
						}
						else if(snowA.isItem(c, r)) {
							gc.drawImage(snow, vy, vx, w, h);
							gc.drawImage(item, vy, vx, w, h);
						}
						else if (snowA.isOther(c, r)) {
							gc.drawImage(snow, vy, vx, w, h);
						}

						else if (snowA.isPath(c, r)) {
							gc.drawImage(dirt, vy, vx, w, h);
						} else {
							gc.drawImage(snow, vy, vx, w, h);

						}
						gc.drawImage(spritesheet, faceX, faceY, 18, 27, vy + 4, vx - 5, dw, dh);
					} 
					else if(snowA.isTeleport(c, r)) {
						gc.drawImage(snow, vy, vx, w, h);
						gc.drawImage(telePort, vy, vx, w, h);
					}
					else if(snowA.isItem(c, r)) {
						gc.drawImage(snow, vy, vx, w, h);
						gc.drawImage(item, vy, vx, w, h);
					}
					else if (snowA.getRoom(c, r) == 'G') {
						gc.drawImage(snow, vy, vx, w, h);
						gc.drawImage(grass, vy, vx, w, h);
					} 
					else if (snowA.getRoom(c, r) == 'W') {
						gc.drawImage(ice, vy, vx, w, h);
					} 
					else if (snowA.getRoom(c, r) == 'P') {
						gc.drawImage(dirt, vy, vx, w, h);
					} 
					else if (snowA.getRoom(c, r) == 'X') {
						gc.drawImage(snow, vy, vx, w, h);
						gc.drawImage(snowB, vy, vx, w, h);
					}
					else if (snowA.getRoom(c, r) == 'O') {
						gc.drawImage(snow, vy, vx, w, h);

					}
				}
				else if(isFire){
					trainer.inFire();
					if (fireA.getRoom(c, r) == 'T') {
						if(fireA.isTeleport(c, r)) {
							gc.drawImage(caveD, vy, vx, w, h);
							gc.drawImage(telePort, vy, vx, w, h);
						}
						else if (fireA.isOther(c, r)) {
							gc.drawImage(caveD, vy, vx, w, h);
						}
						else if (fireA.isWater(c, r)) {
							gc.drawImage(fire, vy, vx, w, h);
						}
						else if (fireA.isPath(c, r)) {
							gc.drawImage(dirt, vy, vx, w, h);
						} 
						else {
							gc.drawImage(caveD, vy, vx, w, h);

						}
						gc.drawImage(spritesheet, faceX, faceY, 18, 27, vy + 4, vx - 5, dw, dh);
					} 
					else if(fireA.isTeleport(c, r)) {
						gc.drawImage(caveD, vy, vx, w, h);
						gc.drawImage(telePort, vy, vx, w, h);
					}
					else if(fireA.isItem(c, r)) {
						gc.drawImage(caveD, vy, vx, w, h);
						gc.drawImage(sparkles, vy, vx, w, h);
					}
					else if (fireA.getRoom(c, r) == 'G') {
						gc.drawImage(caveD, vy, vx, w, h);
						gc.drawImage(grass, vy, vx, w, h);
					} 
					else if (fireA.getRoom(c, r) == 'W') {
						gc.drawImage(fire, vy, vx, w, h);
					} 
					else if (fireA.getRoom(c, r) == 'P') {
						gc.drawImage(dirt, vy, vx, w, h);
					} 
					else if (fireA.getRoom(c, r) == 'X') {
						gc.drawImage(fire, vy, vx, w, h);
						gc.drawImage(fire, vy, vx, w, h);
					}

					else if (fireA.getRoom(c, r) == 'O') {
						gc.drawImage(caveD, vy, vx, w, h);

					}
				}
				else if(isWater) {
					trainer.inWater();
					if (waterA.getRoom(c, r) == 'T') {
						if (waterA.isGrass(c, r)) {
							gc.drawImage(sand, vy, vx, w, h);
							gc.drawImage(grass, vy, vx, w, h);
						}
						else if(waterA.isTeleport(c, r)) {
							gc.drawImage(sand, vy, vx, w, h);
							gc.drawImage(telePort, vy, vx, w, h);
						}
						else if(waterA.isItem(c, r)) {
							gc.drawImage(sand, vy, vx, w, h);
							gc.drawImage(item, vy, vx, w, h);
						}
					    else if (waterA.isPath(c, r)) {
							gc.drawImage(path, vy, vx, w, h);
						}
					    else if (waterA.isWater(c, r)) {
							gc.drawImage(sand, vy, vx, w, h);
						}

						else if (waterA.isOther(c, r)) {
							gc.drawImage(waterW, vy, vx, w, h);
						}

						gc.drawImage(spritesheet, faceX, faceY, 18, 27, vy + 4, vx - 5, dw, dh);
					} 
					else if(waterA.isTeleport(c, r)) {
						gc.drawImage(sand, vy, vx, w, h);
						gc.drawImage(telePort, vy, vx, w, h);
					}
					else if(waterA.isItem(c, r)) {
						gc.drawImage(sand, vy, vx, w, h);
						gc.drawImage(item, vy, vx, w, h);
					}
					else if (waterA.getRoom(c, r) == 'G') {
						gc.drawImage(sand, vy, vx, w, h);
						gc.drawImage(grass, vy, vx, w, h);
					} else if (waterA.getRoom(c, r) == 'W') {
						gc.drawImage(sand, vy, vx, w, h);
					} else if (waterA.getRoom(c, r) == 'P') {
						gc.drawImage(path, vy, vx, w, h);
					} else if (waterA.getRoom(c, r) == 'X') {
						gc.drawImage(waterW, vy, vx, w, h);
					}

					else if (waterA.getRoom(c, r) == 'O') {
						gc.drawImage(waterW, vy, vx, w, h);

					}
					else {
						gc.drawImage(waterW, vy, vx, w, h);
					}
				}
				else {
					trainer.inArena();

					if (arena.getRoom(c, r) == 'T') {
						if (arena.isGrass(c, r)) {
							gc.drawImage(arenaP, vy, vx, w, h);
						} else if (arena.isOther(c, r)) {
							gc.drawImage(arenaW, vy, vx, w, h);
						} else if (arena.isPortal(c, r)) {
							gc.drawImage(arenaB, vy, vx, w, h);
							gc.drawImage(portal, vy, vx, w, h);
						} else {
							gc.drawImage(arenaW, vy, vx, w, h);
						}
						gc.drawImage(spritesheet, faceX, faceY, 18, 27, vy + 4, vx - 5, dw, dh);
					} else if (arena.getRoom(c, r) == 'V') {
						gc.drawImage(arenaB, vy, vx, w, h);
						gc.drawImage(portal, vy, vx, w, h);
					} else if (arena.getRoom(c, r) == 'G') {
						gc.drawImage(arenaW, vy, vx, w, h);
						gc.drawImage(arenaP, vy, vx, w, h);
					} else if (arena.getRoom(c, r) == 'X') {
						gc.drawImage(arenaW, vy, vx, w, h);
						gc.drawImage(arenaB, vy, vx, w, h);
					} else if (arena.getRoom(c, r) == 'O') {
						gc.drawImage(arenaW, vy, vx, w, h);
	
					}
				}
				vy += 20;

			}
			vx += 20;
			vy = 0;
		}

	}
	
	/**
	 * Shows the trainer's items to the user in form of a text area.
	 */
	private void displayItems() {
		BorderPane b = new BorderPane();
		TextArea t = new TextArea();
		t.setMaxWidth(100);
		t.setMaxHeight(40);

		Stage iStage = new Stage(StageStyle.UNDECORATED);
		iStage.initModality(Modality.APPLICATION_MODAL);
		PauseTransition delay = new PauseTransition(Duration.seconds(.5));
		delay.setOnFinished(event -> iStage.close());
		delay.play();

		Scene iScene = new Scene(b, 100, 55);
		
		b.setCenter(t);
//		b.setStyle("-fx-background-color: #E0FFFF");
		b.setPadding(new Insets(2,2,2,2));

		t.setFont(fontLarge);
		t.setText("+1" + i);
		t.setStyle("-fx-font-alignment: center");
		t.setStyle("-fx-background-radius: 10; -fx-border-radius: 10;-fx-border-width:2;-fx-border-color: #00ff00;");
		t.setEditable(false);
		
		
		iStage.setScene(iScene);
		iStage.show();
	}
	
	/**
	 * Shows the trainer's step count to the user in the form of a textArea.
	 */
	private void displaySteps() {
		BorderPane b = new BorderPane();
		TextArea t = new TextArea();
		String i = Integer.toString(trainer.getSteps());
		
		t.setMaxWidth(40);
		t.setMaxHeight(25);

		Stage iStage = new Stage(StageStyle.UNDECORATED);
		iStage.initModality(Modality.APPLICATION_MODAL);
		PauseTransition delay = new PauseTransition(Duration.seconds(.75));
		delay.setOnFinished(event -> iStage.close());
		delay.play();

		Scene iScene = new Scene(b, 62, 55);
		
		b.setCenter(t);
		b.setPadding(new Insets(2,2,2,2));

		t.setFont(fontLarge);

		t.setText(i);
		t.setStyle("-fx-font-alignment: center");
		t.setStyle("-fx-background-radius: 10; -fx-border-radius: 10;-fx-border-width:2;-fx-border-color: #00BFFF;");
		t.setEditable(false);
		
		
		iStage.setScene(iScene);
		iStage.show();
	}

	/**
	 * 
	 * Handles when the user presses a key. If the user presses an arrow key, this will animate the 
	 * trainer to move in that direction if possible, pick up items, and confront Pokemon. 
	 * If the user presses the "i" key, they will show their inventory.  
	 * If the user presses the "s" key, they will show their step count.
	 * If the user presses the "p" key, they will show their pokedex.
	 *
	 */
	private class keyEventHandler implements EventHandler<KeyEvent>, Observer {
		Stage battleStage;

		@Override
		public void handle(KeyEvent keyEvent) {
			if (isMoving == true) {
				isContinue = true;
			} else {

				if (keyEvent.getCode() == KeyCode.UP) {
					isContinue = false;
					if (isMap) {
						if (map.isCave(trainer.getX(), trainer.getY() - 1)) {
							setLocation("cave");
							map.unsetTrainer(trainer.getX(), trainer.getY());
							caveA.setTrainer(trainer.getX(), trainer.getY());
							
							FadeTransition ft = new FadeTransition(Duration.millis(3), all);
							ft.setFromValue(1);
							ft.setToValue(0);
							ft.play();
							ft.setOnFinished((ActionEvent event)->{
								fadeInAnimation();
							});

							createDisplay();
						}
						if (map.getRoom(trainer.getX(), trainer.getY() - 1) == 'X') {
							return;
						}
						if (map.getRoom(trainer.getX(), trainer.getY() - 1) == 'W') {
							return;
						}
						if(map.isTeleport(trainer.getX(), trainer.getY() - 1)) {
							isMap = false;
							isCave = false;
							getArena();
							
							FadeTransition ft = new FadeTransition(Duration.millis(3), all);
							ft.setFromValue(1);
							ft.setToValue(0);
							ft.play();
							ft.setOnFinished((ActionEvent event)->{
								fadeInAnimation();
							});
							if(isSnow) {
								if (firstS) {
									map.unsetTrainer(trainer.getX(), trainer.getY());
	
									trainer.setPos(14,13);
									firstA = true;
									firstM = true;
									firstS = false;
								}
							}
							if(isFire) {
								if(firstF) {
									map.unsetTrainer(trainer.getX(), trainer.getY());

									trainer.setPos(14, 28);
									firstA = true;
									firstM = true;
									firstF = false;
								}
							}
							if(isWater) {
								if(firstW) {
									map.unsetTrainer(trainer.getX(), trainer.getY());

									trainer.setPos(4, 22);
									firstA = true;
									firstM = true;
									firstW = false;
								}
							}
							createDisplay();
						}

						else if (map.isItem(trainer.getX(), (trainer.getY() - 1))) {
							i = map.getItem(trainer.getX(), (trainer.getY() - 1));
							displayItems();

							if (map.isPotion(trainer.getX(), (trainer.getY() - 1))) {
								trainer.addPotion();
								map.potionUsed(trainer.getX(), (trainer.getY() - 1));
							}
							if (map.isBait(trainer.getX(), (trainer.getY() - 1))) {
								trainer.addBait();
								map.baitUsed(trainer.getX(), (trainer.getY() - 1));
							}
							if (map.isZinc(trainer.getX(), (trainer.getY() - 1))) {
								trainer.addZinc();
								map.zincUsed(trainer.getX(), (trainer.getY() - 1));
							}
							if (map.isBall(trainer.getX(), (trainer.getY() - 1))) {
								trainer.addBall();
								map.ballUsed(trainer.getX(), (trainer.getY() - 1));
							}
							if (map.isRock(trainer.getX(), (trainer.getY() - 1))) {
								trainer.addRock();
								map.rockUsed(trainer.getX(), (trainer.getY() - 1));
							}
						}

						else if (map.getRoom(trainer.getX(), trainer.getY() - 1) == 'G') {
							if (isPokemon()) {
								BattleView safariBattle = new BattleView(false, trainer, this);
								playMusic("safari");
								Scene battleScene = new Scene(safariBattle, 475, 475);
								
								battleStage = new Stage();
								battleStage.setScene(battleScene);
								battleStage.initModality(Modality.APPLICATION_MODAL);

								battleStage.show();
								
				
							}
						}
						map.unsetTrainer(trainer.getX(), trainer.getY());
					} 
					else if(isSnow) {
						
						if (snowA.getRoom(trainer.getX(), trainer.getY() - 1) == 'X') {
							return;
						}
						if (snowA.isItem(trainer.getX(), (trainer.getY() - 1))) {
							i = snowA.getItem(trainer.getX(), (trainer.getY() - 1));
							displayItems();

							if (snowA.isPotion(trainer.getX(), (trainer.getY() - 1))) {
								trainer.addPotion();
								snowA.potionUsed(trainer.getX(), (trainer.getY() - 1));
							}
							if (snowA.isBait(trainer.getX(), (trainer.getY() - 1))) {
								trainer.addBait();
								snowA.baitUsed(trainer.getX(), (trainer.getY() - 1));
							}
							if (snowA.isZinc(trainer.getX(), (trainer.getY() - 1))) {
								trainer.addZinc();
								snowA.zincUsed(trainer.getX(), (trainer.getY() - 1));
							}
							if (snowA.isBall(trainer.getX(), (trainer.getY() - 1))) {
								trainer.addBall();
								snowA.ballUsed(trainer.getX(), (trainer.getY() - 1));
							}
							if (snowA.isRock(trainer.getX(), (trainer.getY() - 1))) {
								trainer.addRock();
								snowA.rockUsed(trainer.getX(), (trainer.getY() - 1));
							}
						}
						else if (snowA.getRoom(trainer.getX(), trainer.getY() - 1) == 'G') {
							if (isPokemon()) {
								BattleView safariBattle = new BattleView(false, trainer, this);
								playMusic("safari");
								Scene battleScene = new Scene(safariBattle, 475, 475);

								battleStage = new Stage();
								battleStage.initModality(Modality.APPLICATION_MODAL);
								battleStage.setScene(battleScene);
								battleStage.show();							
				
							}
						}
						
						if(snowA.isTeleport(trainer.getX(), trainer.getY() - 1)) {
							trainer.inMap();
							setLocation("map");
							snowA.unsetTrainer(trainer.getX(), trainer.getY());
	
							FadeTransition ft = new FadeTransition(Duration.millis(3), all);
							ft.setFromValue(1);
							ft.setToValue(0);
							ft.play();
							ft.setOnFinished((ActionEvent event)->{
								fadeInAnimation();
							});
							if (firstM) {
								snowA.unsetTrainer(trainer.getX(), trainer.getY());
								int[] c = new int[2];
								c = map.getTeleport();
								trainer.setPos(c[0], c[1]);
								firstM = false;
								firstS = true;
							}
	
							createDisplay();
						}
						
						snowA.unsetTrainer(trainer.getX(), trainer.getY());
					}
					else if(isFire) {
						if (fireA.getRoom(trainer.getX(), trainer.getY() - 1) == 'W') {
							return;
						}
												
						if (fireA.getRoom(trainer.getX(), trainer.getY() - 1) == 'X') {
							return;
						}
						if (fireA.isItem(trainer.getX(), (trainer.getY() - 1))) {
							i = fireA.getItem(trainer.getX(), (trainer.getY() - 1));
							displayItems();
							
							if (fireA.isPotion(trainer.getX(), (trainer.getY() - 1))) {
								trainer.addPotion();
								fireA.potionUsed(trainer.getX(), (trainer.getY() - 1));
							}
							if (fireA.isBait(trainer.getX(), (trainer.getY() - 1))) {
								trainer.addBait();
								fireA.baitUsed(trainer.getX(), (trainer.getY() - 1));
							}
							if (fireA.isZinc(trainer.getX(), (trainer.getY() - 1))) {
								trainer.addZinc();
								fireA.zincUsed(trainer.getX(), (trainer.getY() - 1));
							}
							if (fireA.isBall(trainer.getX(), (trainer.getY() - 1))) {
								trainer.addBall();
								fireA.ballUsed(trainer.getX(), (trainer.getY() - 1));
							}
							if (fireA.isRock(trainer.getX(), (trainer.getY() - 1))) {
								trainer.addRock();
								fireA.rockUsed(trainer.getX(), (trainer.getY() - 1));
							}
						}
						fireA.unsetTrainer(trainer.getX(), trainer.getY());
					}
					else if(isWater) {
						if (waterA.getRoom(trainer.getX(), trainer.getY() - 1) == 'O') {
							return;
						}
												
						if (waterA.getRoom(trainer.getX(), trainer.getY() - 1) == 'X') {
							return;
						}
						if (waterA.isItem(trainer.getX(), (trainer.getY() - 1))) {
							i = waterA.getItem(trainer.getX(), (trainer.getY() - 1));
							displayItems();

							if (waterA.isPotion(trainer.getX(), (trainer.getY() - 1))) {
								trainer.addPotion();
								waterA.potionUsed(trainer.getX(), (trainer.getY() - 1));
							}
							if (waterA.isBait(trainer.getX(), (trainer.getY() - 1))) {
								trainer.addBait();
								waterA.baitUsed(trainer.getX(), (trainer.getY() - 1));
							}
							if (waterA.isZinc(trainer.getX(), (trainer.getY() - 1))) {
								trainer.addZinc();
								waterA.zincUsed(trainer.getX(), (trainer.getY() - 1));
							}
							if (waterA.isBall(trainer.getX(), (trainer.getY() - 1))) {
								trainer.addBall();
								waterA.ballUsed(trainer.getX(), (trainer.getY() - 1));
							}
							if (waterA.isRock(trainer.getX(), (trainer.getY() - 1))) {
								trainer.addRock();
								waterA.rockUsed(trainer.getX(), (trainer.getY() - 1));
							}
						}
						else if (waterA.getRoom(trainer.getX(), trainer.getY() - 1) == 'G') {
							if (isPokemon()) {
								BattleView safariBattle = new BattleView(false, trainer, this);
								playMusic("safari");
								Scene battleScene = new Scene(safariBattle, 475, 475);

								battleStage = new Stage();
								battleStage.initModality(Modality.APPLICATION_MODAL);
								battleStage.setScene(battleScene);
								battleStage.show();
				
							}
						}
						if(waterA.isTeleport(trainer.getX(), trainer.getY() - 1)) {
							trainer.inMap();
							setLocation("map");
							waterA.unsetTrainer(trainer.getX(), trainer.getY());
	
							FadeTransition ft = new FadeTransition(Duration.millis(3), all);
							ft.setFromValue(1);
							ft.setToValue(0);
							ft.play();
							ft.setOnFinished((ActionEvent event)->{
								fadeInAnimation();
							});
							if (firstM) {
								waterA.unsetTrainer(trainer.getX(), trainer.getY());
								int[] c = new int[2];
								c = map.getTeleport();
								trainer.setPos(c[0], c[1]);
								firstM = false;
								firstW = true;
							}
	
							createDisplay();
						}
						
						waterA.unsetTrainer(trainer.getX(), trainer.getY());

					}
					else if(isCave){
						if (caveA.isCave(trainer.getX(), trainer.getY() - 1)) {
							trainer.inMap();
							setLocation("map");
//							isCave = false;
//							isSnow = false;
//							isMap = true;
							
							caveA.unsetTrainer(trainer.getX(), trainer.getY());
							map.setTrainer(trainer.getX(), trainer.getY());
							
							FadeTransition ft = new FadeTransition(Duration.millis(3), all);
							ft.setFromValue(1);
							ft.setToValue(0);
							ft.play();
							ft.setOnFinished((ActionEvent event)->{
								fadeInAnimation();
							});

							createDisplay();
						}
						if (caveA.getRoom(trainer.getX(), trainer.getY() - 1) == 'X') {
							return;
						}
						if (caveA.getRoom(trainer.getX(), trainer.getY() - 1) == 'W') {
							return;
						}
						if (caveA.isItem(trainer.getX(), (trainer.getY() - 1))) {
							i = caveA.getItem(trainer.getX(), (trainer.getY() - 1));
							displayItems();
							
							if (caveA.isPotion(trainer.getX(), (trainer.getY() - 1))) {
								trainer.addPotion();
								caveA.potionUsed(trainer.getX(), (trainer.getY() - 1));
							}
							if (caveA.isBait(trainer.getX(), (trainer.getY() - 1))) {
								trainer.addBait();
								caveA.baitUsed(trainer.getX(), (trainer.getY() - 1));
							}
							if (caveA.isZinc(trainer.getX(), (trainer.getY() - 1))) {
								trainer.addZinc();
								caveA.zincUsed(trainer.getX(), (trainer.getY() - 1));
							}
							if (caveA.isBall(trainer.getX(), (trainer.getY() - 1))) {
								trainer.addBall();
								caveA.ballUsed(trainer.getX(), (trainer.getY() - 1));
							}
							if (caveA.isRock(trainer.getX(), (trainer.getY() - 1))) {
								trainer.addRock();
								caveA.rockUsed(trainer.getX(), (trainer.getY() - 1));
							}
						}

						else if (caveA.getRoom(trainer.getX(), trainer.getY() - 1) == 'G') {
							if (isPokemon()) {
								BattleView safariBattle = new BattleView(false, trainer, this);
								playMusic("safari");
								Scene battleScene = new Scene(safariBattle, 475, 475);

								battleStage = new Stage();
								battleStage.initModality(Modality.APPLICATION_MODAL);
								battleStage.setScene(battleScene);
								battleStage.show();
							}
						}
						caveA.unsetTrainer(trainer.getX(), trainer.getY());
					}
					else {
						if (arena.getRoom(trainer.getX(), trainer.getY() - 1) == 'X') {
							return;
						}
						if (arena.getRoom(trainer.getX(), trainer.getY() - 1) == 'W') {
							return;
						}
						arena.unsetTrainer(trainer.getX(), trainer.getY());
					}

					timeline = new Timeline(new KeyFrame(Duration.millis(80), new UpAnimate()));
					timeline.setCycleCount(Animation.INDEFINITE);
					isMoving = true;
					timeline.play();
				}

				if (keyEvent.getCode() == KeyCode.DOWN) {
					isContinue = false;
					if (trainer.getY() == 100) {
						return;
					}
					if (isMap) {
						if (map.getRoom(trainer.getX(), trainer.getY() + 1) == 'X') {
							return;
						}
						if (map.getRoom(trainer.getX(), trainer.getY() + 1) == 'W') {
							return;
						}

						if(map.isTeleport(trainer.getX(), trainer.getY() + 1)) {
							isMap = false;
							isCave = false;
							getArena();
							
							FadeTransition ft = new FadeTransition(Duration.millis(3), all);
							ft.setFromValue(1);
							ft.setToValue(0);
							ft.play();
							ft.setOnFinished((ActionEvent event)->{
								fadeInAnimation();
							});
							if(isSnow) {
								if (firstS) {
									map.unsetTrainer(trainer.getX(), trainer.getY());
	
									trainer.setPos(14,12);
									firstA = true;
									firstM = true;
									firstS = false;
								}
							}
							if(isFire) {
								if(firstF) {
									map.unsetTrainer(trainer.getX(), trainer.getY());

									trainer.setPos(14, 26);
									firstA = true;
									firstM = true;
									firstF = false;

								}
								
								if(fireA.isTeleport(trainer.getX(), trainer.getY() + 1)) {
									isMap = true;
									isCave = false;
									isFire = false;
									int [] c = new int[2];
									c = map.getTeleport();
									arena.unsetTrainer(trainer.getX(), trainer.getY());

									FadeTransition f = new FadeTransition(Duration.millis(3), all);
									f.setFromValue(1);
									f.setToValue(0);
									f.play();
									f.setOnFinished((ActionEvent event)->{
										fadeInAnimation();
									});
									if (firstM) {
										fireA.unsetTrainer(trainer.getX(), trainer.getY());
										trainer.setPos(c[0],c[1]);
										firstM = false;
										firstF = true;
									}

									createDisplay();
								}
							}
							if(isWater) {
								if(firstW) {
									map.unsetTrainer(trainer.getX(), trainer.getY());

									trainer.setPos(4, 20);
									firstA = true;
									firstM = true;
									firstW = false;
								}
							}
						}
						else if (map.isItem(trainer.getX(), (trainer.getY() + 1))) {
							i = map.getItem(trainer.getX(), (trainer.getY() + 1));
							displayItems();
							if (map.isPotion(trainer.getX(), (trainer.getY() + 1))) {
								trainer.addPotion();
								map.potionUsed(trainer.getX(), (trainer.getY() + 1));
							}
							if (map.isBait(trainer.getX(), (trainer.getY() + 1))) {
								trainer.addBait();
								map.baitUsed(trainer.getX(), (trainer.getY() + 1));
							}
							if (map.isZinc(trainer.getX(), (trainer.getY() + 1))) {
								trainer.addZinc();
								map.zincUsed(trainer.getX(), (trainer.getY() + 1));
							}
							if (map.isBall(trainer.getX(), (trainer.getY() + 1))) {
								trainer.addBall();
								map.ballUsed(trainer.getX(), (trainer.getY() + 1));
							}
							if (map.isRock(trainer.getX(), (trainer.getY() + 1))) {
								trainer.addRock();
								map.rockUsed(trainer.getX(), (trainer.getY() + 1));
							}

						}

						else if (map.getRoom(trainer.getX(), trainer.getY() + 1) == 'G') {
							if (isPokemon()) {
								BattleView safariBattle = new BattleView(false, trainer, this);
								playMusic("safari");
								Scene battleScene = new Scene(safariBattle, 475, 475);

								battleStage = new Stage();
								battleStage.initModality(Modality.APPLICATION_MODAL);
								battleStage.setScene(battleScene);
								battleStage.show();
							}
						}
						map.unsetTrainer(trainer.getX(), trainer.getY());
					} 
					else if(isSnow) {
						
						if (snowA.getRoom(trainer.getX(), trainer.getY() + 1) == 'X') {
							return;
						}
						if (snowA.isItem(trainer.getX(), (trainer.getY() + 1))) {
							i = snowA.getItem(trainer.getX(), (trainer.getY() + 1));
							displayItems();
							if (snowA.isPotion(trainer.getX(), (trainer.getY() + 1))) {
								trainer.addPotion();
								snowA.potionUsed(trainer.getX(), (trainer.getY() + 1));
							}
							if (snowA.isBait(trainer.getX(), (trainer.getY() + 1))) {
								trainer.addBait();
								snowA.baitUsed(trainer.getX(), (trainer.getY() + 1));
							}
							if (snowA.isZinc(trainer.getX(), (trainer.getY() + 1))) {
								trainer.addZinc();
								snowA.zincUsed(trainer.getX(), (trainer.getY() + 1));
							}
							if (snowA.isBall(trainer.getX(), (trainer.getY() + 1))) {
								trainer.addBall();
								snowA.ballUsed(trainer.getX(), (trainer.getY() + 1));
							}
							if (snowA.isRock(trainer.getX(), (trainer.getY() + 1))) {
								trainer.addRock();
								snowA.rockUsed(trainer.getX(), (trainer.getY() + 1));
							}

						}
						else if (snowA.getRoom(trainer.getX(), trainer.getY() + 1) == 'G') {
							if (isPokemon()) {
								BattleView safariBattle = new BattleView(false, trainer, this);
								playMusic("safari");
								Scene battleScene = new Scene(safariBattle, 475, 475);

								battleStage = new Stage();
								battleStage.initModality(Modality.APPLICATION_MODAL);
								battleStage.setScene(battleScene);
								battleStage.show();
							}
						}
						if(snowA.isTeleport(trainer.getX(), trainer.getY() + 1)) {
							trainer.inMap();
							setLocation("map");
							snowA.unsetTrainer(trainer.getX(), trainer.getY());
	
							FadeTransition ft = new FadeTransition(Duration.millis(3), all);
							ft.setFromValue(1);
							ft.setToValue(0);
							ft.play();
							ft.setOnFinished((ActionEvent event)->{
								fadeInAnimation();
							});
							if (firstM) {
								snowA.unsetTrainer(trainer.getX(), trainer.getY());
								int[] c = new int[2];
								c = map.getTeleport();
								trainer.setPos(c[0], c[1]);
								firstM = false;
								firstS = true;
							}
	
							createDisplay();
						}
						
						snowA.unsetTrainer(trainer.getX(), trainer.getY());
					}
					else if(isFire) {
						if (fireA.getRoom(trainer.getX(), trainer.getY() + 1) == 'W') {
							return;
						}
												
						if (fireA.getRoom(trainer.getX(), trainer.getY() + 1) == 'X') {
							return;
						}
						
						if(fireA.isTeleport(trainer.getX(), trainer.getY() + 1)) {
							trainer.inMap();
							setLocation("map");
							fireA.unsetTrainer(trainer.getX(), trainer.getY());
	
							FadeTransition ft = new FadeTransition(Duration.millis(3), all);
							ft.setFromValue(1);
							ft.setToValue(0);
							ft.play();
							ft.setOnFinished((ActionEvent event)->{
								fadeInAnimation();
							});
							if (firstM) {
								fireA.unsetTrainer(trainer.getX(), trainer.getY());
								int[] c = new int[2];
								c = map.getTeleport();
								trainer.setPos(c[0], c[1]);
								firstM = false;
								firstF = true;
							}

	
							createDisplay();
						}
						if (fireA.isItem(trainer.getX(), (trainer.getY() + 1))) {
							i = fireA.getItem(trainer.getX(), (trainer.getY() + 1));
							displayItems();
							
							if (fireA.isPotion(trainer.getX(), (trainer.getY() + 1))) {
								trainer.addPotion();
								fireA.potionUsed(trainer.getX(), (trainer.getY() + 1));
							}
							if (fireA.isBait(trainer.getX(), (trainer.getY() + 1))) {
								trainer.addBait();
								fireA.baitUsed(trainer.getX(), (trainer.getY() + 1));
							}
							if (fireA.isZinc(trainer.getX(), (trainer.getY() + 1))) {
								trainer.addZinc();
								fireA.zincUsed(trainer.getX(), (trainer.getY() + 1));
							}
							if (fireA.isBall(trainer.getX(), (trainer.getY() + 1))) {
								trainer.addBall();
								fireA.ballUsed(trainer.getX(), (trainer.getY() + 1));
							}
							if (fireA.isRock(trainer.getX(), (trainer.getY() + 1))) {
								trainer.addRock();
								fireA.rockUsed(trainer.getX(), (trainer.getY() + 1));
							}
						}
						
						fireA.unsetTrainer(trainer.getX(), trainer.getY());
					}
					else if(isWater) {
						if (waterA.getRoom(trainer.getX(), trainer.getY() + 1) == 'O') {
							return;
						}
												
						if (waterA.getRoom(trainer.getX(), trainer.getY() + 1) == 'X') {
							return;
						}
						if (waterA.isItem(trainer.getX(), (trainer.getY() + 1))) {
							i = waterA.getItem(trainer.getX(), (trainer.getY() + 1));
							displayItems();
							if (waterA.isPotion(trainer.getX(), (trainer.getY() + 1))) {
								trainer.addPotion();
								waterA.potionUsed(trainer.getX(), (trainer.getY() + 1));
							}
							if (waterA.isBait(trainer.getX(), (trainer.getY() + 1))) {
								trainer.addBait();
								waterA.baitUsed(trainer.getX(), (trainer.getY() + 1));
							}
							if (waterA.isZinc(trainer.getX(), (trainer.getY() + 1))) {
								trainer.addZinc();
								waterA.zincUsed(trainer.getX(), (trainer.getY() + 1));
							}
							if (waterA.isBall(trainer.getX(), (trainer.getY() + 1))) {
								trainer.addBall();
								waterA.ballUsed(trainer.getX(), (trainer.getY() + 1));
							}
							if (waterA.isRock(trainer.getX(), (trainer.getY() + 1))) {
								trainer.addRock();
								waterA.rockUsed(trainer.getX(), (trainer.getY() + 1));
							}

						}
						else if (waterA.getRoom(trainer.getX(), trainer.getY() + 1) == 'G') {
							if (isPokemon()) {
								BattleView safariBattle = new BattleView(false, trainer, this);
								playMusic("safari");
								Scene battleScene = new Scene(safariBattle, 475, 475);

								battleStage = new Stage();
								battleStage.initModality(Modality.APPLICATION_MODAL);
								battleStage.setScene(battleScene);
								battleStage.show();
							}
						}
						waterA.unsetTrainer(trainer.getX(), trainer.getY());

					}
					else if(isCave){
						if (caveA.getRoom(trainer.getX(), trainer.getY() + 1) == 'X') {
							return;
						}
						if (caveA.getRoom(trainer.getX(), trainer.getY() + 1) == 'W') {
							return;
						}
						if (caveA.isItem(trainer.getX(), (trainer.getY() + 1))) {
							i = caveA.getItem(trainer.getX(), (trainer.getY() + 1));
							displayItems();
							if (caveA.isPotion(trainer.getX(), (trainer.getY() + 1))) {
								trainer.addPotion();
								caveA.potionUsed(trainer.getX(), (trainer.getY() + 1));
							}
							if (caveA.isBait(trainer.getX(), (trainer.getY() + 1))) {
								trainer.addBait();
								caveA.baitUsed(trainer.getX(), (trainer.getY() + 1));
							}
							if (caveA.isZinc(trainer.getX(), (trainer.getY() + 1))) {
								trainer.addZinc();
								caveA.zincUsed(trainer.getX(), (trainer.getY() + 1));
							}
							if (caveA.isBall(trainer.getX(), (trainer.getY() + 1))) {
								trainer.addBall();
								caveA.ballUsed(trainer.getX(), (trainer.getY() + 1));
							}
							if (caveA.isRock(trainer.getX(), (trainer.getY() + 1))) {
								trainer.addRock();
								caveA.rockUsed(trainer.getX(), (trainer.getY() + 1));
							}
						}

						else if (caveA.getRoom(trainer.getX(), trainer.getY() + 1) == 'G') {
							if (isPokemon()) {
								BattleView safariBattle = new BattleView(false, trainer, this);
								playMusic("safari");
								Scene battleScene = new Scene(safariBattle, 475, 475);

								battleStage = new Stage();
								battleStage.initModality(Modality.APPLICATION_MODAL);
								battleStage.setScene(battleScene);
								battleStage.show();
							}
						}
						caveA.unsetTrainer(trainer.getX(), trainer.getY());
					}
					
					else {
						if (arena.getRoom(trainer.getX(), trainer.getY() + 1) == 'X') {
							return;
						}
						if (arena.getRoom(trainer.getX(), trainer.getY() + 1) == 'W') {
							return;
						}
						arena.unsetTrainer(trainer.getX(), trainer.getY());
					} 

					timeline = new Timeline(new KeyFrame(Duration.millis(80), new DownAnimate()));
					timeline.setCycleCount(Animation.INDEFINITE);
					isMoving = true;
					timeline.play();

				}

				if (keyEvent.getCode() == KeyCode.RIGHT) {
					isContinue = false;
					if (isMap) {
						if (map.getRoom(trainer.getX() + 1, trainer.getY()) == 'X') {
							return;
						}
						if (map.getRoom(trainer.getX() + 1, trainer.getY()) == 'W') {
							return;
						}

						if (map.isPortal((trainer.getX() + 1), trainer.getY())) {
							isMap = false;
							isCave = false;
							isSnow = false;
							isFire = false;
							isWater = false;
							
							FadeTransition ft = new FadeTransition(Duration.millis(3), all);
							ft.setFromValue(1);
							ft.setToValue(0);
							ft.play();
							ft.setOnFinished((ActionEvent event)->{
								fadeInAnimation();
							});
							if (firstA) {
								map.unsetTrainer(trainer.getX(), trainer.getY());

								trainer.setPos(0, 14);
								firstA = false;
								firstM = true;
								firstS = true;
							}
						}
						if(map.isTeleport(trainer.getX() + 1, trainer.getY())) {
							isMap = false;
							isCave = false;
							getArena();
							
							FadeTransition ft = new FadeTransition(Duration.millis(3), all);
							ft.setFromValue(1);
							ft.setToValue(0);
							ft.play();
							ft.setOnFinished((ActionEvent event)->{
								fadeInAnimation();
							});
							if(isSnow) {
								if (firstS) {
									map.unsetTrainer(trainer.getX(), trainer.getY());
	
									trainer.setPos(14,13);
									firstA = true;
									firstM = true;
									firstS = false;
								}
							}
							if(isFire) {
								if(firstF) {
									map.unsetTrainer(trainer.getX(), trainer.getY());

									trainer.setPos(14, 28);
									firstA = true;
									firstM = true;
									firstF = false;
								}
							}
							if(isWater) {
								if(firstW) {
									map.unsetTrainer(trainer.getX(), trainer.getY());

									trainer.setPos(3, 21);
									firstA = true;
									firstM = true;
									firstW = false;
								}
							}
						}

						else if (map.isItem((trainer.getX() + 1), trainer.getY())) {
							i = map.getItem(trainer.getX() + 1, (trainer.getY()));
							displayItems();
							if (map.isPotion((trainer.getX() + 1), trainer.getY())) {
								trainer.addPotion();
								map.potionUsed((trainer.getX() + 1), trainer.getY());
							}
							if (map.isBait((trainer.getX() + 1), trainer.getY())) {
								trainer.addBait();
								map.baitUsed((trainer.getX() + 1), trainer.getY());
							}
							if (map.isZinc((trainer.getX() + 1), trainer.getY())) {
								trainer.addZinc();
								map.zincUsed((trainer.getX() + 1), trainer.getY());
							}
							if (map.isBall((trainer.getX() + 1), trainer.getY())) {
								trainer.addBall();
								map.ballUsed((trainer.getX() + 1), trainer.getY());
							}
							if (map.isRock((trainer.getX() + 1), trainer.getY())) {
								trainer.addRock();
								map.rockUsed((trainer.getX() + 1), trainer.getY());
							}
						}
						else if (map.getRoom(trainer.getX() + 1, trainer.getY()) == 'G') {
							if (isPokemon()) {
								BattleView safariBattle = new BattleView(false, trainer, this);
								playMusic("safari");
								Scene battleScene = new Scene(safariBattle, 475, 475);

								battleStage = new Stage();
								battleStage.initModality(Modality.APPLICATION_MODAL);
								battleStage.setScene(battleScene);
								battleStage.show();
							}
						}
						map.unsetTrainer(trainer.getX(), trainer.getY());
					} 
					else if(isSnow) {
						
						if (snowA.getRoom(trainer.getX() + 1, trainer.getY()) == 'X') {
							return;
						}
						if (snowA.isItem((trainer.getX() + 1), trainer.getY())) {
							i = snowA.getItem(trainer.getX() + 1, (trainer.getY()));
							displayItems();
							if (snowA.isPotion((trainer.getX() + 1), trainer.getY())) {
								trainer.addPotion();
								snowA.potionUsed((trainer.getX() + 1), trainer.getY());
							}
							if (snowA.isBait((trainer.getX() + 1), trainer.getY())) {
								trainer.addBait();
								snowA.baitUsed((trainer.getX() + 1), trainer.getY());
							}
							if (snowA.isZinc((trainer.getX() + 1), trainer.getY())) {
								trainer.addZinc();
								snowA.zincUsed((trainer.getX() + 1), trainer.getY());
							}
							if (snowA.isBall((trainer.getX() + 1), trainer.getY())) {
								trainer.addBall();
								snowA.ballUsed((trainer.getX() + 1), trainer.getY());
							}
							if (snowA.isRock((trainer.getX() + 1), trainer.getY())) {
								trainer.addRock();
								snowA.rockUsed((trainer.getX() + 1), trainer.getY());
							}
						}
						else if (snowA.getRoom(trainer.getX() + 1, trainer.getY()) == 'G') {
							if (isPokemon()) {
								BattleView safariBattle = new BattleView(false, trainer, this);
								playMusic("safari");
								Scene battleScene = new Scene(safariBattle, 475, 475);

								battleStage = new Stage();
								battleStage.initModality(Modality.APPLICATION_MODAL);
								battleStage.setScene(battleScene);
								battleStage.show();
							}
						}
						if(snowA.isTeleport(trainer.getX() + 1, trainer.getY())) {
							trainer.inMap();
							setLocation("map");
							snowA.unsetTrainer(trainer.getX(), trainer.getY());
	
							FadeTransition ft = new FadeTransition(Duration.millis(3), all);
							ft.setFromValue(1);
							ft.setToValue(0);
							ft.play();
							ft.setOnFinished((ActionEvent event)->{
								fadeInAnimation();
							});
							if (firstM) {
								snowA.unsetTrainer(trainer.getX(), trainer.getY());
								int[] c = new int[2];
								c = map.getTeleport();
								trainer.setPos(c[0], c[1]);
								firstM = false;
								firstS = true;
							}
	
							createDisplay();
						}
						
						snowA.unsetTrainer(trainer.getX(), trainer.getY());
					}
					else if(isFire) {
						if (fireA.getRoom(trainer.getX() + 1, trainer.getY()) == 'W') {
							return;
						}
												
						if (fireA.getRoom(trainer.getX() + 1, trainer.getY()) == 'X') {
							return;
						}
						if(fireA.isTeleport(trainer.getX() + 1, trainer.getY())) {
							trainer.inMap();
							setLocation("map");
							fireA.unsetTrainer(trainer.getX(), trainer.getY());
	
							FadeTransition ft = new FadeTransition(Duration.millis(3), all);
							ft.setFromValue(1);
							ft.setToValue(0);
							ft.play();
							ft.setOnFinished((ActionEvent event)->{
								fadeInAnimation();
							});
							if (firstM) {
								fireA.unsetTrainer(trainer.getX(), trainer.getY());
								int[] c = new int[2];
								c = map.getTeleport();
								trainer.setPos(c[0], c[1]);
								firstM = false;
								firstF = true;
							}

	
							createDisplay();
						}
						if (fireA.isItem(trainer.getX() + 1, (trainer.getY()))) {
							i = fireA.getItem(trainer.getX() + 1, (trainer.getY()));
							displayItems();
							
							if (fireA.isPotion(trainer.getX() + 1, (trainer.getY()))) {
								trainer.addPotion();
								fireA.potionUsed(trainer.getX() + 1, (trainer.getY()));
							}
							if (fireA.isBait(trainer.getX() + 1, (trainer.getY()))) {
								trainer.addBait();
								fireA.baitUsed(trainer.getX() + 1, (trainer.getY()));
							}
							if (fireA.isZinc(trainer.getX() + 1, (trainer.getY()))) {
								trainer.addZinc();
								fireA.zincUsed(trainer.getX() + 1, (trainer.getY()));
							}
							if (fireA.isBall(trainer.getX() + 1, (trainer.getY()))) {
								trainer.addBall();
								fireA.ballUsed(trainer.getX() + 1, (trainer.getY()));
							}
							if (fireA.isRock(trainer.getX() + 1, (trainer.getY()))) {
								trainer.addRock();
								fireA.rockUsed(trainer.getX() + 1, (trainer.getY()));
							}
						}
						
						fireA.unsetTrainer(trainer.getX(), trainer.getY());
					}
					else if(isWater) {
						if (waterA.getRoom(trainer.getX() + 1, trainer.getY()) == 'O') {
							return;
						}
												
						if (waterA.getRoom(trainer.getX() + 1, trainer.getY()) == 'X') {
							return;
						}
						if (waterA.isItem((trainer.getX() + 1), trainer.getY())) {
							i = waterA.getItem(trainer.getX() + 1, (trainer.getY()));
							displayItems();
							if (waterA.isPotion((trainer.getX() + 1), trainer.getY())) {
								trainer.addPotion();
								waterA.potionUsed((trainer.getX() + 1), trainer.getY());
							}
							if (waterA.isBait((trainer.getX() + 1), trainer.getY())) {
								trainer.addBait();
								waterA.baitUsed((trainer.getX() + 1), trainer.getY());
							}
							if (waterA.isZinc((trainer.getX() + 1), trainer.getY())) {
								trainer.addZinc();
								waterA.zincUsed((trainer.getX() + 1), trainer.getY());
							}
							if (waterA.isBall((trainer.getX() + 1), trainer.getY())) {
								trainer.addBall();
								waterA.ballUsed((trainer.getX() + 1), trainer.getY());
							}
							if (waterA.isRock((trainer.getX() + 1), trainer.getY())) {
								trainer.addRock();
								waterA.rockUsed((trainer.getX() + 1), trainer.getY());
							}
						}
						else if (waterA.getRoom(trainer.getX() + 1, trainer.getY()) == 'G') {
							if (isPokemon()) {
								BattleView safariBattle = new BattleView(false, trainer, this);
								playMusic("safari");
								Scene battleScene = new Scene(safariBattle, 475, 475);

								battleStage = new Stage();
								battleStage.initModality(Modality.APPLICATION_MODAL);
								battleStage.setScene(battleScene);
								battleStage.show();
							}
						}
						if(waterA.isTeleport(trainer.getX() + 1, trainer.getY())) {
							trainer.inMap();
							setLocation("map");
							waterA.unsetTrainer(trainer.getX(), trainer.getY());
	
							FadeTransition ft = new FadeTransition(Duration.millis(3), all);
							ft.setFromValue(1);
							ft.setToValue(0);
							ft.play();
							ft.setOnFinished((ActionEvent event)->{
								fadeInAnimation();
							});
							if (firstM) {
								waterA.unsetTrainer(trainer.getX(), trainer.getY());
								int[] c = new int[2];
								c = map.getTeleport();
								trainer.setPos(c[0], c[1]);
								firstM = false;
								firstW = true;
							}
	
							createDisplay();
						}
						
						waterA.unsetTrainer(trainer.getX(), trainer.getY());

					}
					else if(isCave){
						if (caveA.getRoom(trainer.getX() + 1, trainer.getY()) == 'X') {
							return;
						}
						if (caveA.getRoom(trainer.getX() + 1, trainer.getY()) == 'W') {
							return;
						}
						if (caveA.isItem((trainer.getX() + 1), trainer.getY())) {
							i = caveA.getItem(trainer.getX() + 1, (trainer.getY()));
							displayItems();
							if (caveA.isPotion((trainer.getX() + 1), trainer.getY())) {
								trainer.addPotion();
								caveA.potionUsed((trainer.getX() + 1), trainer.getY());
							}
							if (caveA.isBait((trainer.getX() + 1), trainer.getY())) {
								trainer.addBait();
								caveA.baitUsed((trainer.getX() + 1), trainer.getY());
							}
							if (caveA.isZinc((trainer.getX() + 1), trainer.getY())) {
								trainer.addZinc();
								caveA.zincUsed((trainer.getX() + 1), trainer.getY());
							}
							if (caveA.isBall((trainer.getX() + 1), trainer.getY())) {
								trainer.addBall();
								caveA.ballUsed((trainer.getX() + 1), trainer.getY());
							}
							if (caveA.isRock((trainer.getX() + 1), trainer.getY())) {
								trainer.addRock();
								caveA.rockUsed((trainer.getX() + 1), trainer.getY());
							}
						}

						else if (caveA.getRoom(trainer.getX() + 1, trainer.getY()) == 'G') {
							if (isPokemon()) {
								BattleView safariBattle = new BattleView(false, trainer, this);
								playMusic("safari");
								Scene battleScene = new Scene(safariBattle, 475, 475);

								battleStage = new Stage();
								battleStage.initModality(Modality.APPLICATION_MODAL);
								battleStage.setScene(battleScene);
								battleStage.show();
							}
						}
						caveA.unsetTrainer(trainer.getX(), trainer.getY());
					}
					else {
						if (arena.getRoom(trainer.getX() + 1, trainer.getY()) == 'X') {
							return;
						}
						if (arena.getRoom(trainer.getX() + 1, trainer.getY()) == 'W') {
							return;
						}
						arena.unsetTrainer(trainer.getX(), trainer.getY());
					} 

					timeline = new Timeline(new KeyFrame(Duration.millis(80), new RightAnimate()));
					timeline.setCycleCount(Animation.INDEFINITE);
					isMoving = true;
					timeline.play();
				}

				if (keyEvent.getCode() == KeyCode.LEFT) {
					isContinue = false;
					if (isMap) {
						if (map.getRoom(trainer.getX() - 1, trainer.getY()) == 'X') {
							return;
						}
						if (map.getRoom(trainer.getX() - 1, trainer.getY()) == 'W') {
							return;
						}

						if(map.isTeleport(trainer.getX() - 1, trainer.getY())) {
							isMap = false;
							isCave = false;
							getArena();
							
							FadeTransition ft = new FadeTransition(Duration.millis(3), all);
							ft.setFromValue(1);
							ft.setToValue(0);
							ft.play();
							ft.setOnFinished((ActionEvent event)->{
								fadeInAnimation();
							});
							if(isSnow) {
								if (firstS) {
									map.unsetTrainer(trainer.getX(), trainer.getY());
	
									trainer.setPos(14,13);
									firstA = true;
									firstM = true;
									firstS = false;
								}
							}
							if(isFire) {
								if(firstF) {
									map.unsetTrainer(trainer.getX(), trainer.getY());

									trainer.setPos(14, 28);
									firstA = true;
									firstM = true;
									firstF = false;
								}
							}
							if(isWater) {
								if(firstW) {
									map.unsetTrainer(trainer.getX(), trainer.getY());

									trainer.setPos(5, 21);
									firstA = true;
									firstM = true;
									firstW = false;
								}
							}
						}
						
						else if (map.isItem((trainer.getX() - 1), trainer.getY())) {
							i = map.getItem(trainer.getX() - 1, (trainer.getY()));
							displayItems();
							if (map.isPotion((trainer.getX() - 1), trainer.getY())) {
								trainer.addPotion();
								map.potionUsed((trainer.getX() - 1), trainer.getY());
							}
							if (map.isBait((trainer.getX() - 1), trainer.getY())) {
								trainer.addBait();
								map.baitUsed((trainer.getX() - 1), trainer.getY());
							}
							if (map.isZinc((trainer.getX() - 1), trainer.getY())) {
								trainer.addZinc();
								map.zincUsed((trainer.getX() - 1), trainer.getY());
							}
							if (map.isBall((trainer.getX() - 1), trainer.getY())) {
								trainer.addBall();
								map.ballUsed((trainer.getX() - 1), trainer.getY());
							}
							if (map.isRock((trainer.getX() - 1), trainer.getY())) {
								trainer.addRock();
								map.rockUsed((trainer.getX() - 1), trainer.getY());
							}
						}

						else if (map.getRoom(trainer.getX() - 1, trainer.getY()) == 'G') {
							if (isPokemon()) {
								BattleView safariBattle = new BattleView(false, trainer, this);
								playMusic("safari");
								Scene battleScene = new Scene(safariBattle, 475, 475);

								battleStage = new Stage();
								battleStage.initModality(Modality.APPLICATION_MODAL);
								battleStage.setScene(battleScene);
								battleStage.show();

							}
						}
						map.unsetTrainer(trainer.getX(), trainer.getY());
					} 
					
					else if(isSnow) {
						
						if (snowA.getRoom(trainer.getX() - 1, trainer.getY()) == 'X') {
							return;
						}
						if (snowA.isItem((trainer.getX() - 1), trainer.getY())) {
							i = snowA.getItem(trainer.getX() - 1, (trainer.getY()));
							displayItems();
							if (snowA.isPotion((trainer.getX() - 1), trainer.getY())) {
								trainer.addPotion();
								snowA.potionUsed((trainer.getX() - 1), trainer.getY());
							}
							if (snowA.isBait((trainer.getX() - 1), trainer.getY())) {
								trainer.addBait();
								snowA.baitUsed((trainer.getX() - 1), trainer.getY());
							}
							if (snowA.isZinc((trainer.getX() - 1), trainer.getY())) {
								trainer.addZinc();
								snowA.zincUsed((trainer.getX() - 1), trainer.getY());
							}
							if (snowA.isBall((trainer.getX() - 1), trainer.getY())) {
								trainer.addBall();
								snowA.ballUsed((trainer.getX() - 1), trainer.getY());
							}
							if (snowA.isRock((trainer.getX() - 1), trainer.getY())) {
								trainer.addRock();
								snowA.rockUsed((trainer.getX() - 1), trainer.getY());
							}
						}
						else if (snowA.getRoom(trainer.getX() - 1, trainer.getY()) == 'G') {
							if (isPokemon()) {
								BattleView safariBattle = new BattleView(false, trainer, this);
								playMusic("safari");
								Scene battleScene = new Scene(safariBattle, 475, 475);

								battleStage = new Stage();
								battleStage.initModality(Modality.APPLICATION_MODAL);
								battleStage.setScene(battleScene);
								battleStage.show();

							}
						}
						
						if(snowA.isTeleport(trainer.getX() - 1, trainer.getY())) {
							trainer.inMap();
							setLocation("map");
							snowA.unsetTrainer(trainer.getX(), trainer.getY());
	
							FadeTransition ft = new FadeTransition(Duration.millis(3), all);
							ft.setFromValue(1);
							ft.setToValue(0);
							ft.play();
							ft.setOnFinished((ActionEvent event)->{
								fadeInAnimation();
							});
							if (firstM) {
								snowA.unsetTrainer(trainer.getX(), trainer.getY());
								int[] c = new int[2];
								c = map.getTeleport();
								trainer.setPos(c[0], c[1]);
								firstM = false;
								firstS = true;
							}
	
							createDisplay();
						}
						
						snowA.unsetTrainer(trainer.getX(), trainer.getY());
					}
					else if(isFire) {
						if (fireA.getRoom(trainer.getX() - 1, trainer.getY()) == 'W') {
							return;
						}
												
						if (fireA.getRoom(trainer.getX() - 1, trainer.getY()) == 'X') {
							return;
						}
						if(fireA.isTeleport(trainer.getX() - 1, trainer.getY())) {
							trainer.inMap();
							setLocation("map");
							fireA.unsetTrainer(trainer.getX(), trainer.getY());
	
							FadeTransition ft = new FadeTransition(Duration.millis(3), all);
							ft.setFromValue(1);
							ft.setToValue(0);
							ft.play();
							ft.setOnFinished((ActionEvent event)->{
								fadeInAnimation();
							});
							if (firstM) {
								fireA.unsetTrainer(trainer.getX(), trainer.getY());
								int[] c = new int[2];
								c = map.getTeleport();
								trainer.setPos(c[0], c[1]);
								firstM = false;
								firstF = true;
							}

	
							createDisplay();
						}
						if (fireA.isItem(trainer.getX() - 1, (trainer.getY()))) {
							i = fireA.getItem(trainer.getX() - 1, (trainer.getY()));
							displayItems();
							
							if (fireA.isPotion(trainer.getX() - 1, (trainer.getY()))) {
								trainer.addPotion();
								fireA.potionUsed(trainer.getX() - 1, (trainer.getY()));
							}
							if (fireA.isBait(trainer.getX() - 1, (trainer.getY()))) {
								trainer.addBait();
								fireA.baitUsed(trainer.getX() - 1, (trainer.getY()));
							}
							if (fireA.isZinc(trainer.getX() - 1, (trainer.getY()))) {
								trainer.addZinc();
								fireA.zincUsed(trainer.getX() - 1, (trainer.getY()));
							}
							if (fireA.isBall(trainer.getX() - 1, (trainer.getY()))) {
								trainer.addBall();
								fireA.ballUsed(trainer.getX() - 1, (trainer.getY()));
							}
							if (fireA.isRock(trainer.getX() - 1, (trainer.getY()))) {
								trainer.addRock();
								fireA.rockUsed(trainer.getX() - 1, (trainer.getY()));
							}
						}
						
						fireA.unsetTrainer(trainer.getX(), trainer.getY());
					}
					else if(isWater) {
						if (waterA.getRoom(trainer.getX() - 1, trainer.getY()) == 'O') {
							return;
						}
												
						if (waterA.getRoom(trainer.getX() - 1, trainer.getY()) == 'X') {
							return;
						}
						if (waterA.isItem((trainer.getX() - 1), trainer.getY())) {
							i = waterA.getItem(trainer.getX() - 1, (trainer.getY()));
							displayItems();
							if (waterA.isPotion((trainer.getX() - 1), trainer.getY())) {
								trainer.addPotion();
								waterA.potionUsed((trainer.getX() - 1), trainer.getY());
							}
							if (waterA.isBait((trainer.getX() - 1), trainer.getY())) {
								trainer.addBait();
								waterA.baitUsed((trainer.getX() - 1), trainer.getY());
							}
							if (waterA.isZinc((trainer.getX() - 1), trainer.getY())) {
								trainer.addZinc();
								waterA.zincUsed((trainer.getX() - 1), trainer.getY());
							}
							if (waterA.isBall((trainer.getX() - 1), trainer.getY())) {
								trainer.addBall();
								waterA.ballUsed((trainer.getX() - 1), trainer.getY());
							}
							if (waterA.isRock((trainer.getX() - 1), trainer.getY())) {
								trainer.addRock();
								waterA.rockUsed((trainer.getX() - 1), trainer.getY());
							}
						}
						else if (waterA.getRoom(trainer.getX() - 1, trainer.getY()) == 'G') {
							if (isPokemon()) {
								BattleView safariBattle = new BattleView(false, trainer, this);
								playMusic("safari");
								Scene battleScene = new Scene(safariBattle, 475, 475);

								battleStage = new Stage();
								battleStage.initModality(Modality.APPLICATION_MODAL);
								battleStage.setScene(battleScene);
								battleStage.show();

							}
						}
						waterA.unsetTrainer(trainer.getX(), trainer.getY());

					}
					else if(isCave) {
						if (caveA.getRoom(trainer.getX() - 1, trainer.getY()) == 'X') {
							return;
						}
						if (caveA.getRoom(trainer.getX() - 1, trainer.getY()) == 'W') {
							return;
						}
						if (caveA.getRoom((trainer.getX() - 1), trainer.getY()) == 'B') {
							trainer.addBall();
							caveA.ballUsed((trainer.getX() - 1), trainer.getY());
						}
						if (caveA.isRock((trainer.getX() - 1), trainer.getY())) {
							trainer.addRock();
							map.rockUsed((trainer.getX() - 1), trainer.getY());
						}
						if (caveA.isItem((trainer.getX() - 1), trainer.getY())) {
							i = caveA.getItem(trainer.getX() - 1, (trainer.getY()));
							displayItems();
							if (caveA.isPotion((trainer.getX() - 1), trainer.getY())) {
								trainer.addPotion();
								caveA.potionUsed((trainer.getX() - 1), trainer.getY());
							}
							if (caveA.isBait((trainer.getX() - 1), trainer.getY())) {
								trainer.addBait();
								caveA.baitUsed((trainer.getX() - 1), trainer.getY());
							}
							if (caveA.isZinc((trainer.getX() - 1), trainer.getY())) {
								trainer.addZinc();
								caveA.zincUsed((trainer.getX() - 1), trainer.getY());
							}
							if (caveA.isBall((trainer.getX() - 1), trainer.getY())) {
								trainer.addBall();
								caveA.ballUsed((trainer.getX() - 1), trainer.getY());
							}
							if (caveA.isRock((trainer.getX() - 1), trainer.getY())) {
								trainer.addRock();
								caveA.rockUsed((trainer.getX() - 1), trainer.getY());
							}
						}

						else if (caveA.getRoom(trainer.getX() - 1, trainer.getY()) == 'G') {
							if (isPokemon()) {
								BattleView safariBattle = new BattleView(false, trainer, this);
								playMusic("safari");
								Scene battleScene = new Scene(safariBattle, 475, 475);

								battleStage = new Stage();
								battleStage.initModality(Modality.APPLICATION_MODAL);
								battleStage.setScene(battleScene);
								battleStage.show();

							}
						}
						caveA.unsetTrainer(trainer.getX(), trainer.getY());
					}
					else
					{
						if (arena.getRoom(trainer.getX() - 1, trainer.getY()) == 'X') {
							return;
						}
						if (arena.getRoom(trainer.getX() - 1, trainer.getY()) == 'W') {
							return;
						}
						if (arena.isPortal((trainer.getX() - 1), trainer.getY())) {
							trainer.inMap();
							setLocation("map");
							arena.unsetTrainer(trainer.getX(), trainer.getY());

							FadeTransition ft = new FadeTransition(Duration.millis(3), all);
							ft.setFromValue(1);
							ft.setToValue(0);
							ft.play();
							ft.setOnFinished((ActionEvent event)->{
								fadeInAnimation();
							});
							if (firstM) {
								arena.unsetTrainer(trainer.getX(), trainer.getY());
								trainer.setPos(99, 96);
								firstM = false;
								firstA = true;
							}

							createDisplay();
						}
						if(!isMap) {
							arena.unsetTrainer(trainer.getX(), trainer.getY());
						}
					} 

					timeline = new Timeline(new KeyFrame(Duration.millis(80), new LeftAnimate()));
					timeline.setCycleCount(Animation.INDEFINITE);
					isMoving = true;
					timeline.play();

					if (map.getRoom(trainer.getX(), trainer.getY()) == 'G') {

					}

				}

				if (keyEvent.getCode() == KeyCode.I) {
					showInventory();
				}

				if (keyEvent.getCode() == KeyCode.S) {
					displaySteps();

				}

				if (keyEvent.getCode() == KeyCode.P) {
//					System.out.println("	Pokedex");
//					System.out.println("---------------------");
					pd = "";
					pd += "	      Pokedex\n";
					pd += "------------------------------\n";
					for (int i = 0; i < trainer.getPokedex().size(); i++) {
						pd += (i+1) + ":  " + trainer.getPokedex().getPokedex().get(i).getName() + "\n";
//						System.out.println(i + ": " + trainer.getPokedex().getPokedex().get(i).getName());
					}
					pd += "------------------------------\n";
//					System.out.println("---------------------");
					
					displayPokedex();
						
					}

			}

		}


		/**
		 * Observable method that closes the battle stage when it is sent "END" and stops the current song from playing.
		 */
		@Override
		public void update(Observable o, Object arg) {
			String message = (String) arg;
			if(message.equals("END")) {
				battleStage.close();
				mediaPlayer.stop();
				System.gc();

				playMusic(null);
			}

		}

	}
	
	
	/**
	 * Creates the fade in animation that is used to go from area to area.
	 */
	public void fadeInAnimation() {
		FadeTransition ft = new FadeTransition(Duration.millis(3000), all);
		ft.setFromValue(0);
		ft.setToValue(1);
		ft.play();
		
	}


	/**
	 * 
	 * Handler for the down animation of the trainer.  
	 *
	 */
	// Handler for animating the trainer walking down
	public class DownAnimate implements EventHandler<ActionEvent> {

		// The sx on the spritesheet for walking to the right is 0;
		public DownAnimate() {
			sx = 18;
			sy = 56;
			tic = 0;
			if (scMovingY) {
				dy = 15 * 20;
			} else {
				if (trainer.getY() > 50) {
					dy = ((trainer.getY() - 85) * 20 + 300);
				} else {
					dy = (300 - (15 - trainer.getY()) * 20);
				}
			}
			if (scMovingX) {
				dx = 15 * 20;
			} else {
				if (trainer.getX() > 50) {
					dx = (300 + ((trainer.getX() - 85) * 20));
				} else {
					dx = (300 - ((15 - trainer.getX()) * 20));
				}
			}
		}

		@Override
		// This handle method gets called every 100 ms to draw the ship on a new
		// location
		public void handle(ActionEvent event) {

			tic++;
			createDisplay();

			// TODO 2: Draw the walker, update the proper variables, stop animaiton
			gc.drawImage(spritesheet, sx, sy, sw, sh, dx + 4, dy, dw, dh);
			// dy should increase a total of 20 over 6 tics.

			dy += (double) 20 / 3;
			if (scMovingX) {
				dx = 15 * 20;
			} else {
				if (trainer.getX() > 50) {
					dx = (300 + ((trainer.getX() - 85) * 20));
				}
			}
			sx += 22;

			if (tic > 3) {
				faceX = 18;
				if (isContinue) {
					faceY = 56;
					faceX = 18;
				} else {
					faceY = 56;
					faceX = 40;
				}

				dy = 20;
				sy = 56;
				timeline.stop();
				isMoving = false;
				trainer.moveDown();
				trainer.addStep();

				if (isMap) {
					map.setTrainer(trainer.getX(), trainer.getY());
				}
				else if(isCave) {
					caveA.setTrainer(trainer.getX(), trainer.getY());
				}
				else if(isSnow) {
					snowA.setTrainer(trainer.getX(), trainer.getY());
				} 
				else if(isFire) {
					fireA.setTrainer(trainer.getX(), trainer.getY());
				}
				else if(isWater) {
					waterA.setTrainer(trainer.getX(), trainer.getY());

				}
				else {
					arena.setTrainer(trainer.getX(), trainer.getY());
				} 

				createDisplay();
				return;
			}

			if (trainer.getSteps() > maxSteps) {
				gameOver = true;
				all.setCenter(null);
				all.setBottom(null);
				all.setStyle("-fx-background-color: #000000");

			}

		}
	}

	/**
	 * Handler for the left animation of the trainer.
	 *
	 *
	 */
	// Handler for animating the trainer walking left
	public class LeftAnimate implements EventHandler<ActionEvent> {

		// The sx on the spritesheet for walking to the right is about 30;
		public LeftAnimate() {

			sx = 18;
			sy = 91;
			tic = 0;
			if (scMovingY) {
				dy = 15 * 20;
			} else {
				if (trainer.getY() > 50) {
					dy = (300 + ((trainer.getY() - 85) * 20));
				} else {
					dy = (300 - (15 - trainer.getY()) * 20);
				}

			}
			if (scMovingX) {
				dx = 15 * 20;
			} else {
				if (trainer.getX() > 50) {
					dx = (300 + ((trainer.getX() - 85) * 20));
				} else {
					dx = (300 - ((15 - trainer.getX()) * 20));
				}
			}
		}

		@Override
		// This handle method gets called every 100 ms to draw the ship on a new
		// location
		public void handle(ActionEvent event) {

			tic++;
			createDisplay();

			// TODO 2: Draw the walker, update the proper variables, stop animaiton
			gc.drawImage(spritesheet, sx, sy, sw, sh, dx, dy - 5, dw, dh);
			// dx should decrease a total of 20 over 6 tics.
			dx -= (double) 20 / 3;
			sx += 22;
			if (tic > 3) {
				if (isContinue) {
					faceY = 91;
					faceX = 18;
				} else {
					faceY = 91;
					faceX = 40;
				}

				timeline.stop();
				isMoving = false;

				trainer.moveLeft();
				trainer.addStep();

				if (isMap) {
					map.setTrainer(trainer.getX(), trainer.getY());
				}
				else if(isCave) {
					caveA.setTrainer(trainer.getX(), trainer.getY());
				}
				else if(isSnow) {
					snowA.setTrainer(trainer.getX(), trainer.getY());
				} 
				else if(isFire) {
					fireA.setTrainer(trainer.getX(), trainer.getY());
				}
				else if(isWater) {
					waterA.setTrainer(trainer.getX(), trainer.getY());

				}
				else {
					arena.setTrainer(trainer.getX(), trainer.getY());
				} 

				createDisplay();
				return;
			}
			if (trainer.getSteps() > maxSteps) {
				gameOver = true;
				all.setCenter(null);
				all.setBottom(null);
				all.setStyle("-fx-background-color: #000000");

			}
		}
	}

	
	/**
	 * Handler for the up animation of the trainer.
	 * @author austinc
	 *
	 */
	public class UpAnimate implements EventHandler<ActionEvent> {

		// The sx on the spritesheet for walking to the right is about 50;
		public UpAnimate() {
			sx = 17;
			sy = 162;
			tic = 0;

			if (scMovingY) {
				dy = 15 * 20;
			} else {
				if (trainer.getY() > 50) {
					dy = (300 + ((trainer.getY() - 85) * 20));
				} else {
					dy = (300 - (15 - trainer.getY()) * 20);
				}
			}
			if (scMovingX) {
				dx = 15 * 20;
			} else {
				if (trainer.getX() > 50) {
					dx = (300 + ((trainer.getX() - 85) * 20));
				} else {
					dx = (300 - ((15 - trainer.getX()) * 20));
				}
			}
		}

		@Override
		public void handle(ActionEvent event) {

			tic++;
			createDisplay();

			gc.drawImage(spritesheet, sx, sy, sw, sh, dx + 4, dy - 5, dw, dh);
			// dy should decrease a total of 20 over 6 tics.

			dy -= (double) 20 / 3;

			sx += 22;

			if (tic > 3) {
				faceX = sx + 9;
				if (isContinue) {
					faceY = 162;
					faceX = 18;
				} else {
					faceY = 162;
					faceX = 40;

				}

				timeline.stop();
				isMoving = false;

				trainer.moveUp();
				trainer.addStep();

				if (isMap) {
					map.setTrainer(trainer.getX(), trainer.getY());
				}
				else if(isCave) {
					caveA.setTrainer(trainer.getX(), trainer.getY());
				}
				else if(isSnow) {
					snowA.setTrainer(trainer.getX(), trainer.getY());
				} 
				else if(isFire) {
					fireA.setTrainer(trainer.getX(), trainer.getY());
				}
				else if(isWater) {
					waterA.setTrainer(trainer.getX(), trainer.getY());

				}
				else {
					arena.setTrainer(trainer.getX(), trainer.getY());
				} 


				createDisplay();
				return;
			}
			if (trainer.getSteps() > maxSteps) {
				gameOver = true;
				all.setCenter(null);
				all.setBottom(null);
				all.setStyle("-fx-background-color: #000000");

			}
		}
	}

	/**
	 * Handler for the right animation of the trainer.
	 *
	 */
	public class RightAnimate implements EventHandler<ActionEvent> {

		// The sx on the spritesheet for walking to the right is about 80;
		public RightAnimate() {
			sx = 18;
			sy = 127;
			tic = 0;

			if (scMovingY) {
				dy = 15 * 20;
				posY = dy;
			} else {
				if (trainer.getY() > 50) {
					dy = (300 + ((trainer.getY() - 85) * 20));
				} else {
					dy = (300 - (15 - trainer.getY()) * 20);
				}
				posY = dy;
			}
			if (scMovingX) {
				dx = 15 * 20;
				posX = dx;
			} else {
				if (trainer.getX() > 50) {
					dx = (300 + ((trainer.getX() - 85) * 20));
				} else {
					dx = (300 - ((15 - trainer.getX()) * 20));
				}
				posX = dx;
			}
		}

		@Override
		// This handle method gets called every 100 ms to draw the ship on a new
		// location
		public void handle(ActionEvent event) {
			tic++;
			createDisplay();
			gc.drawImage(spritesheet, sx, sy, sw, sh, dx + 4, dy - 5, dw, dh);
			// dx should increase a total of 20 over 6 tics.
			dx += (double) 20 / 3;
			sx += 22;

			if (tic > 3) {
				if (isContinue) {
					faceY = 127;
					faceX = 18;
				} else {
					faceY = 127;
					faceX = 40;
				}

				timeline.stop();
				isMoving = false;

				trainer.moveRight();
				trainer.addStep();

				if (isMap) {
					map.setTrainer(trainer.getX(), trainer.getY());
				}
				else if(isCave) {
					caveA.setTrainer(trainer.getX(), trainer.getY());
				}
				else if(isSnow) {
					snowA.setTrainer(trainer.getX(), trainer.getY());
				} 
				else if(isFire) {
					fireA.setTrainer(trainer.getX(), trainer.getY());
				}
				else if(isWater) {
					waterA.setTrainer(trainer.getX(), trainer.getY());

				}
				else {
					arena.setTrainer(trainer.getX(), trainer.getY());
				} 
//				else {
//					caveA.setTrainer(trainer.getX(), trainer.getY());
//				}
				createDisplay();
				// return;
			}
			if (trainer.getSteps() > maxSteps) {
				if(trainer.getPokedex().size() == 0) {
					gameOver = true;
				}
				else {
					isWin = true;
				}
				all.setCenter(null);
				all.setBottom(null);
				all.setStyle("-fx-background-color: #000000");

			}
		}
	}
	
	/**
	 * Randomly chooses an arena to send the trainer to when they enter a portal.
	 */
	public void getArena() {
		Random r = new Random();
		int a = r.nextInt(3);
//		System.out.println(a);

		if(a == 0) {
			isSnow = true;
			isFire = false;
			isWater = false;
		}
		else if(a == 1) {
			isSnow = false;
			isFire = true;
			isWater = false;
		}
		else if(a == 2) {
			isSnow = false;
			isFire = false;
			isWater = true;
		}
	}


	/**
	 * Randomly decides whether a trainer will find a pokemon in a given grass location.
	 * 
	 */
	public boolean isPokemon() {
		Random r = new Random();
		int p = r.nextInt(100);
		if (p < 11) {
			return true;
		}
		return false;
	}
	
	/**
	 * Stores the sound files into an array.
	 */
	private void makeSongDataBase() {
		songDatabase[0] = "src/soundfiles/01 Opening.mp3";
		songDatabase[1] = "src/soundfiles/Road.mp3";
		songDatabase[2] = "src/soundfiles/TownTheme.mp3";
		songDatabase[3] = "src/soundfiles/09 Pewter City's Theme.mp3";
		songDatabase[4] = "src/soundfiles/42 The Final Road.mp3";
		songDatabase[5] = "src/soundfiles/107 Battle (VS Wild Pokemon).mp3";
		
		
				
	}
	
	/**
	 * Plays the given sound file.
	 * @param type
	 */
	private void playMusic(String type){
		if(type == null){
			File file = new File(songDatabase[songCount % 5]);
			songCount++;
			URI uri = file.toURI();
			Media media = new Media(uri.toString());
			mediaPlayer = new MediaPlayer(media);
			mediaPlayer.setAutoPlay(true);
			mediaPlayer.setOnEndOfMedia(new Runnable() {
				public void run() {
					mediaPlayer.stop();
					playMusic(null);
				}
			});
	}
	else if(type == "safari") {
		mediaPlayer.stop();
		File file = new File(songDatabase[5]);
		URI uri = file.toURI();
		Media media = new Media(uri.toString());
		mediaPlayer = new MediaPlayer(media);
		
	}
		mediaPlayer.play();	
}

	@Override
	public void update(Observable o, Object arg) {
		String message = (String) arg;
		if (message.equals("END")){
			battleStage.close();
		}
		
	}

}
