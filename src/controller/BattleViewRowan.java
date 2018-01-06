package controller;

import javafx.util.Duration;
import javafx.animation.Animation;
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
import model.RandomPokemonGenerator;
import model.Trainer;
import model.pokemon.*;

@SuppressWarnings("restriction")
public class BattleViewRowan extends BorderPane implements Observer {

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
	private ImageView leftPoke;
	private Timeline timeline;
	private Timeline ballthrow;
	private Image trainerSprite;
	private Image pokeball = new Image("file:Images/safariBall.png");
	private Observer main;
	private Button btn1;
	private Button btn2;
	private Button btn3;
	private Button btn4;
	private Button btn5;
	private Button cont;
	private int waiting = 0;

	Boolean turn;

	private Vector<String> messages;

	public BattleViewRowan(Boolean multi, Trainer player, Observer main) {
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

		if (multi) {
			// battle = new MultiplayerBattle(
			// player.getPokedex().getPokedex().get((player.getPokedex().getPokedex().size()
			// - 1)), this);
			battle = new MultiplayerBattle(RandomPokemonGenerator.next(15), this);
		} else
			battle = new SafariBattle(this, player);

		turn = battle.isYourTurn();

		setBackground();
		setLeftPokemon();
		setRightPokemon();
		setAttackButtons();
		setHealthBars();
		this.main = main;

		this.setCenter(stack);
		updateButtons(battle.isYourTurn());
		cont.setDisable(false);
		System.out.print(battle.isYourTurn());
		battle.startMessage();
		nextMessage();
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
			pokemon1Stats.setText("" + battle.getRight().title() + " " + battle.getLeft().hp + "/" + battle.getLeft().maxHP);
			pokemon1Stats.setStyle("-fx-font-size: 12px;");
			pokemon1Stats.setTranslateX(-75);
			pokemon1Stats.setTranslateY(150);
			stack.getChildren().add(pokemon1Stats);
		}

		pokemon2Stats.setText("" + battle.getRight().title() + " " + battle.getRight().hp + "/" + battle.getRight().maxHP);
		gc.setFill(Color.GRAY);
		gc.fillRect(255, 340, 120, 20);
		gc.fillRect(255, 342, 120, 16);
		pokemon2Stats.setTranslateX(75);
		pokemon2Stats.setTranslateY(150);
		pokemon2Stats.setStyle("-fx-font-size: 12px;");
		updateHealthBars();

		stack.getChildren().add(pokemon2Stats);
	}

	/**
	 * Updates the Stats text for both pokemon, as well as redraw their current
	 * health Bars.
	 */
	private void updateHealthBars() {
		// updates the stats
		pokemon1Stats.setText(battle.getLeft().title() + " " + battle.getLeft().hp + "/" + battle.getLeft().maxHP);
		pokemon2Stats.setText(battle.getRight().title() + " " + battle.getRight().hp + "/" + battle.getRight().maxHP);

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

		btn1 = new Button(battle.avaliableOptions()[0]);
		btn1.setOnAction(event -> {
			if (waiting > 0)
				return;
			if (battle.action(0))
				waiting = 2;

			updateHealthBars();

			if (battle.getLeft() instanceof Human)
				timeline.play();

			if (battle.isOver()) {
				stack.getChildren().remove(rightPoke);
			}
			updateButtons(waiting <= 0);

		});

		btn2 = new Button(battle.avaliableOptions()[1]);

		btn2.setOnAction(event -> {
			if (waiting > 0)
				return;
			if (battle.action(1))
				waiting = 2;

			if (!isMultiplayer)
				timeline.play();

			if (battle.isOver()) {
				stack.getChildren().remove(rightPoke);

			}
			updateButtons(waiting <= 0);
		});

		btn3 = new Button(battle.avaliableOptions()[2]);
		btn3.setOnAction(event -> {
			if (waiting > 0)
				return;
			if (battle.action(2))
				waiting = 2;
			if (!isMultiplayer) {
				timeline.play();
				ballthrow = new Timeline(new KeyFrame(Duration.millis(100), new CaptureAnimation()));
				ballthrow.setCycleCount(Animation.INDEFINITE);
				ballthrow.play();
			}
			updateButtons(waiting <= 0);
		});
		btn4 = new Button(battle.avaliableOptions()[3]);
		btn4.setOnAction(event -> {
			if (waiting > 0)
				return;
			if (battle.action(3))
				waiting = 2;

			if (battle.getLeft() instanceof Human)
				timeline.play();

			if (battle.isOver()) {
				stack.getChildren().remove(rightPoke);
			}
			updateButtons(waiting <= 0);

		});
		btn5 = new Button("Zinc (" + player.getZinc() + ")");
		btn5.setOnAction(event -> {
			if (player.getZinc() < 0)
				return;
			
			battle.getLeft().setTempDefense(20);
			btn5.setText("Zinc (" + player.getZinc() + ")");
		});
		cont = new Button("Continue");
		cont.setOnAction(event -> {
			updateHealthBars();
			updateButtons(waiting <= 0);
			nextMessage();

		});

		String btnStyle = "-fx-background-color: linear-gradient(#ff5400, #be1d00); -fx-background-radius: 30; -fx-background-insets: 0; -fx-text-fill: white;";
		// set Styles for buttons.
		for (Button btn : new Button[] { btn1, btn2, btn3, btn4, btn5, cont }) {
			btn.setStyle(btnStyle);
		}
		if (!battle.avaliableOptions()[0].equals(""))
			attacks1.add(btn1, 0, 0);
		if (!battle.avaliableOptions()[1].equals(""))
			attacks1.add(btn2, 0, 1);
		if (!battle.avaliableOptions()[2].equals(""))
			attacks1.add(btn3, 0, 2);
		// if(!battle.avaliableOptions()[3].equals(""))
		attacks1.add(btn4, 0, 3);
		attacks1.add(btn5, 0, 4);

		attacks1.add(cont, 0, 5);
		attacks1.setTranslateX(15);
		attacks1.setTranslateY(32);

		stack.getChildren().addAll(attacks1);

	}

	private void updateButtons(Boolean yourTurn) {
		btn1.setDisable(!yourTurn);
		btn2.setDisable(!yourTurn);
		btn3.setDisable(!yourTurn);
		btn4.setDisable(!yourTurn);
		btn5.setDisable(!yourTurn);

	}

	/** display the next message if we aren't currently displaying one */

	/** Called whenever continue is pressed */
	private void nextMessage() {
		if (!messages.isEmpty()) {
			actionText.setText((String) messages.get(0));
			messages.remove(0);
		} else if (messages.isEmpty() && battle.isOver()) {
			end();
		} else if (messages.isEmpty()) {
			actionText.setText("");
		}
	}

	private void end() {
		battle.getLeft().resetTemp();
		battle.getRight().resetTemp();
		actionText.setText("");
		battle.deleteObserver(this);
		battle.addObserver(main);
		gc.clearRect(0, 0, 500, 500);
		if (SafariBattle.class.isInstance(battle) && ((SafariBattle) battle).captured) {
			player.getPokedex().addPokemon(battle.getRight());
		}
		battle.message("END");
		stack.getChildren().remove(canvas);

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
			leftPoke = new ImageView(new Image(battle.getLeft().getImage()));
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
	 * TODO:Not showing up for some reason right now, might not add it into final
	 * iteration.
	 */
	private class CaptureAnimation implements EventHandler<ActionEvent> {

		double sx, sy, sw, sh, dx, dy, dw, dh, count;

		public CaptureAnimation() {
			sx = 0;
			sy = 0;
			sw = 16;
			sh = 27;
			dx = 50;
			dy = 220;
			dw = 16;
			dh = 16;
			count = 0;
		}

		@Override
		public void handle(ActionEvent event) {
			setBackground();

			gc.drawImage(pokeball, sx, sy, sw, sh, dx, dy, dw, dh);
			gc.drawImage(trainerSprite, 0, 0, 80, 80, 10, 220, 80, 80);

			count++;
			if (dx <= 390) {
				dx += 20;
				sx += 16;
				if (sx == 128) {
					sx = 0;
				}
				if (dx == 390)
					rightPoke.setVisible(false);
			} else {

				if (dx == 410 && count < 30)
					sx = 144;

				if (!rightPoke.isVisible()) {
					sx += 16;
				}
				if (!battle.isOver()) {
					rightPoke.setVisible(true);
				}
			}
		}

	}

	/**
	 * TODO: Ask Niven about what is going on with the actiontext.
	 */
	@Override
	public void update(Observable o, Object arg) {
		waiting--;

		messages.add((String) arg);
		//if (waiting == 1) {
		//	nextMessage();
		//}

	}
}
