package net.officina_hide.project.tools;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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

	/** システム画面サイズ : 幅 */
	private double dispWidth;
	/** システム画面サイズ : 高さ */
	private double dispHeight;
	/** 表示画面サイズ : 幅 */
	private double iniWidth;
	/** 表示画面サイズ : 高さ */
	private double iniHeight;
	/** タイトル : 幅 */
	private double titleWidth;
	
	
	@Override
	public void start(Stage stage) throws Exception {
		SplitPane sp = new SplitPane();
		
		ScrollPane  scPaneR = new ScrollPane();
		Pane paneR = new Pane();
		scPaneR.setContent(paneR);
		scPaneR.pannableProperty().set(true);

		ScrollPane  scPaneL = new ScrollPane();
		Pane paneL = new Pane();
		scPaneL.setContent(paneL);
		
		sp.getItems().addAll(scPaneR, scPaneL);
		sp.setDividerPositions(0.2, 0.8);
		
//		ScrollPane  scPane = new ScrollPane();
//		Pane pane = new Pane();
//		pane.setPadding(new Insets(10, 10, 10, 10));
//		
		//システム画面のサイズ取得
		dispWidth = Screen.getPrimary().getBounds().getWidth();
		dispHeight = Screen.getPrimary().getBounds().getHeight();
		System.out.println("画面サイズ : "+dispWidth+"×"+dispHeight);
		//表示画面サイス計算（システム画面の80%を初期値にする）
		iniWidth = Math.abs(dispWidth * 0.8);
		iniHeight = Math.abs(dispHeight * 0.8);
		//タイトルの幅（表示画面サイズの20%を初期値とする。)
		titleWidth = Math.abs(iniWidth * 0.2);
		
		//タイトル枠
		VBox rbox = new VBox(5);
		rbox.setPrefWidth(titleWidth);
		Label label = new Label("test");
		
		Rectangle titleBox = new Rectangle(titleWidth, iniHeight - 20);
		titleBox.setX(10);
		titleBox.setY(10);
		titleBox.setStrokeWidth(2);
		titleBox.setStroke(Color.DARKGRAY);
		titleBox.setFill(Color.WHITE);

//		rbox.getChildren().addAll(label, titleBox);
		
		paneR.getChildren().addAll(titleBox);
		
//		paneR.getChildren().addAll(rbox);
		
//		
//		Rectangle mainBox = new Rectangle(iniWidth, iniHeight);
//		mainBox.setX(10);
//		mainBox.setY(10);
//		mainBox.setStrokeWidth(2);
//		mainBox.setStroke(Color.DARKGRAY);
//		mainBox.setFill(Color.WHITE);
//		pane.getChildren().add(mainBox);
		
		Scene scene = new Scene(sp, iniWidth, iniHeight);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
