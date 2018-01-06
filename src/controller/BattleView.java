package controller;

import javafx.util.Duration;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import model.Trainer;
import model.pokemon.*;

public class BattleView extends BorderPane implements Observer {

	private StackPane stack;
	private Boolean isMultiplayer;
	private Battle battle;
	private Trainer player;
	private Canvas canvas;
	private GraphicsContext gc;
	private Label pokemon1Stats = new Label();
	private Label pokemon2Stats = new Label();
	private Label actionText = new Label();
	private ImageView rightPoke;
	private Timeline timeline;
	private Timeline ballthrow;
	private Image trainerSprite;
	private Image pokeball = new Image("file:Images/safariBall.png");
	private Observer main;
	private Boolean waiting = true;
	private Button attack1;
	private Button attack2;
	private Button attack3;
	private Button cont;
	private boolean captured;

	private Vector<String> messages;

	public BattleView(Boolean multi, Trainer player, Observer main) {
		messages = new Vector<String>();

		stack = new StackPane();
		canvas = new Canvas(475, 475);
		gc = canvas.getGraphicsContext2D();
		isMultiplayer = multi;
		stack.getChildren().add(canvas);

		// set the action Text configuration.
		actionText.setTranslateY(-180);
		actionText.setFont(Font.font("Comic Sans MS", 22));
		actionText.setMaxSize(400, 200);
		actionText.setWrapText(true);
		actionText.setStyle(
				"-fx-text-fill: linear-gradient(#dd4a21 25%, #dd4a21 75%, #757575 100%); -fx-font-weight: bold;");

		stack.getChildren().add(actionText);

		this.player = player;

		if (multi)
			battle = new MultiplayerBattle(player.getPokedex().getPokedex().get(0), this);
		else {
			battle = new SafariBattle(this, player);
		}
		battle.startMessage();

		setBackground();
		setLeftPokemon();
		setRightPokemon();
		setAttackButtons();
		setHealthBars();
		this.main = main;

		this.setCenter(stack);
		updateMessage();
		updateButtons();
	}
	


	/**
	 * Sets the Health Bars for the Pokemon, if it is pokemon battle. Will ignore
	 * the health Bars if it is a Safari Battle
	 */
	private void setHealthBars() {

		if (isMultiplayer) {
			gc.setFill(Color.GRAY);
			gc.fillRect(100, 340, 120, 20);
			gc.setFill(Color.GREEN);
			gc.fillRect(100, 342, 120, 16);
			pokemon1Stats.setText("" + battle.getLeft().hp + "/" + battle.getLeft().maxHP);
			pokemon1Stats.setStyle("-fx-font-size: 22px;");
			pokemon1Stats.setTranslateX(-75);
			pokemon1Stats.setTranslateY(150);
			stack.getChildren().add(pokemon1Stats);

		}

		pokemon2Stats.setText("" + battle.getRight().hp + "/" + battle.getRight().maxHP);
		gc.setFill(Color.GRAY);
		gc.fillRect(255, 340, 120, 20);
		gc.fillRect(255, 342, 120, 16);
		pokemon2Stats.setTranslateX(75);
		pokemon2Stats.setTranslateY(150);
		pokemon2Stats.setStyle("-fx-font-size: 22px;");
		updateHealthBars();

		stack.getChildren().add(pokemon2Stats);
	}

	/**
	 * Updates the Stats text for both pokemon, as well as redraw their current
	 * health Bars.
	 */
	private void updateHealthBars() {

		// updates the stats
		pokemon1Stats.setText("" + battle.getLeft().hp + "/" + battle.getLeft().maxHP);
		pokemon2Stats.setText("" + battle.getRight().hp + "/" + battle.getRight().maxHP);

		// update Health Bars for Right Pokemon
		double rightHealth = (double) battle.getRight().hp / battle.getRight().maxHP;
		int width = (int) (rightHealth * 120);
		gc.clearRect(255, 342, 120, 16);

		gc.setFill(Color.GREY);
		gc.fillRect(255, 340, 120, 20);

		if (width < (120 * 0.7) && width > (120 * 0.3))
			gc.setFill(Color.YELLOW);
		else if (width < (120 * 0.3))
			gc.setFill(Color.RED);
		else
			gc.setFill(Color.GREEN);

		gc.fillRect((255 + (120 - width)), 342, width, 16);

		// update HealthBars for Left Pokemon
		if (isMultiplayer) {
			double leftHealth = (double) battle.getLeft().hp / battle.getLeft().maxHP;
			width = (int) (leftHealth * 120);
			gc.clearRect(100, 340, 120, 20);

			gc.setFill(Color.GREY);
			gc.fillRect(100, 340, 120, 20);
			if (width < (120 * 0.7) && width > (120 * 0.3))
				gc.setFill(Color.YELLOW);
			else if (width < (120 * 0.3))
				gc.setFill(Color.RED);
			else
				gc.setFill(Color.GREEN);

			gc.fillRect(100, 342, width, 16);
		}
	}

	/**
	 * Sets the three attack buttons according to the pokemon's attacks.
	 */
	private void setAttackButtons() {
		// set Pokemon1 attacks
		GridPane attacks1 = new GridPane();
		attacks1.setVgap(5);

		attack1 = new Button(battle.avaliableOptions()[0]);
		attack1.setOnAction(event -> {
			if (waiting)
				return;

			battle.action(0);
			updateHealthBars();

			if (battle.getLeft() instanceof Human)
				timeline.play();

			if (battle.isOver()) {
				stack.getChildren().remove(rightPoke);
			}
			updateMessage();

		});

		attack2 = new Button(battle.avaliableOptions()[1]);

		attack2.setOnAction(event -> {
			if (waiting)
				return;
			battle.action(1);
			updateHealthBars();

			if (!isMultiplayer)
				timeline.play();

			if (battle.isOver()) {
				stack.getChildren().remove(rightPoke);

			}
			updateMessage();
		});

		attack3 = new Button(battle.avaliableOptions()[2]);
		attack3.setOnAction(event -> {
			if (waiting)
				return;
			battle.action(2);

			if (!isMultiplayer) {
				if(battle.isOver()){
					FadeTransition ft = new FadeTransition(Duration.millis(100), stack);
					ft.setFromValue(1);
					ft.setToValue(0);
					ft.play();
					ft.setOnFinished((ActionEvent event1)->{
						stack.getChildren().remove(rightPoke);
						
						if(battle.isOver() && ((SafariBattle)battle).captured){
							ballthrow = new Timeline(new KeyFrame(Duration.millis(100), new CaptureAnimation()));
							ballthrow.setCycleCount(21);
							ballthrow.play();
						}
						else{
							setRightPokemon();
						}
						fadeInAnimation();
					});
				}

			}
			updateMessage();
		});
		cont = new Button("Continue");
		cont.setOnAction(event -> {
			updateMessage();
			updateButtons();
			});

		// set Styles for buttons.
		attack1.setStyle(
				"-fx-background-color: linear-gradient(#ff5400, #be1d00); -fx-background-radius: 30; -fx-background-insets: 0; -fx-text-fill: white;");
		attack2.setStyle(
				"-fx-background-color: linear-gradient(#ff5400, #be1d00); -fx-background-radius: 30; -fx-background-insets: 0; -fx-text-fill: white;");
		attack3.setStyle(
				"-fx-background-color: linear-gradient(#ff5400, #be1d00); -fx-background-radius: 30; -fx-background-insets: 0; -fx-text-fill: white;");
		cont.setStyle(
				"-fx-background-color: linear-gradient(#ff5400, #be1d00); -fx-background-radius: 30; -fx-background-insets: 0; -fx-text-fill: white;");

		if (isMultiplayer) {
			Button attack4 = new Button(battle.avaliableOptions()[0]);
			attack4.setStyle(
					"-fx-background-color: linear-gradient(#ff5400, #be1d00); -fx-background-radius: 30; -fx-background-insets: 0; -fx-text-fill: white;");
			attacks1.add(attack4, 0, 3);
		}

		attacks1.add(attack1, 0, 0);
		attacks1.add(attack2, 0, 1);
		attacks1.add(attack3, 0, 2);
		attacks1.add(cont, 0, 4);
		attacks1.setTranslateX(15);
		attacks1.setTranslateY(320);

		stack.getChildren().addAll(attacks1);

	}

	private void updateButtons() {
		attack1.setDisable(waiting);
		attack2.setDisable(waiting);
		attack3.setDisable(waiting);
		cont.setDisable(!waiting);

	}

	private void updateMessage() {
		if (!messages.isEmpty()) {
			actionText.setText((String) messages.get(0));
			if(messages.get(0).equals(battle.getRight().getName() + " captured!")){
				System.out.println("Gets here");
				player.getPokedex().addPokemon(battle.getRight());
			}
			messages.remove(0);
		} else if (messages.isEmpty() && battle.isOver()) {
			actionText.setText("");
			battle.deleteObserver(this);
			battle.addObserver(main);
			battle.message("END");
		} else if (messages.isEmpty()) {
			waiting = false;
			actionText.setText("");
		}
	}

	/** Draws the Background of the battle. */
	private void setBackground() {
		Image backgroundSprites = new Image("file:Images/pokemon_x.png");
		gc.drawImage(backgroundSprites, 0, 0);
	}

	/**
	 * Flips the image to face the opposite direction.
	 * 
	 * @param ImageView
	 *            image.
	 */
	public void flipImage(ImageView image) {
		image.setTranslateZ(image.getBoundsInLocal().getWidth() / 2.0);
		image.setRotationAxis(Rotate.Y_AXIS);
		image.setRotate(180);
	}

	public void setLeftPokemon() {

		// if it is a Battle View than use the spritesheet animation.
		if (!isMultiplayer) {
			trainerSprite = new Image("file:Images/trainerSprtite.png", false);
			timeline = new Timeline(new KeyFrame(Duration.millis(100), new TrainerAnimation()));
			timeline.setCycleCount(21);
			timeline.play();
		} else {
			ImageView leftPoke = new ImageView(new Image(battle.getLeft().getImage()));

			flipImage(leftPoke);
			leftPoke.setTranslateX(-180);
			stack.getChildren().add(leftPoke);
		}
	}

	public void setRightPokemon() {
		rightPoke = new ImageView(new Image(battle.getRight().getImage()));
		rightPoke.setTranslateX(180);
		stack.getChildren().add(rightPoke);
	}

	/**
	 * Creates the fade in animation that is used to go from area to area.
	 */
	public void fadeInAnimation() {
		FadeTransition ft = new FadeTransition(Duration.millis(1000), stack);
		ft.setFromValue(0);
		ft.setToValue(1);
		ft.play();		
	}
	/**
	 * Private Animation Handler that will perform a 21 cycle animation for the
	 * Trainer Sprite Animation.
	 * 
	 * @author Alek Tutchton
	 *
	 */
	private class TrainerAnimation implements EventHandler<ActionEvent> {
		double sx, sy, sw, sh, dx, dy, dw, dh;

		public TrainerAnimation() {
			sx = 0;
			sy = 0;
			sw = 80;
			sh = 80;
			dx = 10;
			dy = 220;
			dw = 80;
			dh = 80;
		}

		@Override
		public void handle(ActionEvent event) {
			setBackground();
			updateHealthBars();
			gc.drawImage(trainerSprite, sx, sy, sw, sh, dx, dy, dw, dh);
			sy += 81;

			if (sy > 1620)
				sy = 0;
		}

	}

	/**
	 * Animation for the pokeball capture animations
	 */
	private class CaptureAnimation implements EventHandler<ActionEvent> {

		double sx, sy, sw, sh, dx, dy, dw, dh;

		public CaptureAnimation() {
			sx = 128;
			sy = 0;
			sw = 16;
			sh = 27;
			dx = 380;
			dy = 220;
			dw = 16;
			dh = 16;
		}

		@Override
		public void handle(ActionEvent event) {	
		setBackground();
		gc.drawImage(pokeball, sx, sy, sw, sh, dx, dy, dw, dh);
		gc.drawImage(trainerSprite, 0, 0, 80, 80, 10, 220, 80, 80);
	    		    
	    sx += 16;
		}
	}


	@Override
	public void update(Observable o, Object arg) {
		messages.add((String) arg);
		waiting = true;
		if(attack1 != null)
			updateButtons();
	}
}