package net.officina_hide.project.tools;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * ガントチャート表示[Gantt chart display]<br>
 * <p></p>
 * @author officina-hide.net
 * @version 1.00 テストバージョン[Test]
 * @since 2022/11/19 Ver. 1.00
 */
public class WorkBench extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		ScrollPane  scPane = new ScrollPane();
		Pane pane = new Pane();
		pane.setPadding(new Insets(10, 10, 10, 10));
		scPane.setContent(pane);
		scPane.setFitToHeight(true);
		
		Rectangle mainBox = new Rectangle(300, 200);
		mainBox.setX(10);
		mainBox.setY(10);
		mainBox.setStrokeWidth(2);
		mainBox.setStroke(Color.DARKGRAY);
		mainBox.setFill(Color.WHITE);
		pane.getChildren().add(mainBox);
		
		Scene scene = new Scene(scPane);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
