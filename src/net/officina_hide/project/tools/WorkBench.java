package net.officina_hide.project.tools;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import net.officina_hide.project.model.FD_WorkSheet;

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
	/** 作業 : 高さ/件 */
	private double lineHeight;
	/** テスト用データテーブル */
	private List<FD_WorkSheet> wlist = new ArrayList<>();
	
	@Override
	public void start(Stage stage) throws Exception {
		//作業票リスト生成
		wlist = getWorkSheetList();
		System.out.println(wlist.size());
		
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
		
		//システム画面のサイズ取得
		dispWidth = Screen.getPrimary().getBounds().getWidth();
		dispHeight = Screen.getPrimary().getBounds().getHeight();
		System.out.println("画面サイズ : "+dispWidth+"×"+dispHeight);
		//表示画面サイス計算（システム画面の80%を初期値にする）
		iniWidth = Math.abs(dispWidth * 0.8);
		iniHeight = Math.abs(dispHeight * 0.8);
		//タイトルの幅（表示画面サイズの20%を初期値とする。)
		titleWidth = Math.abs(iniWidth * 0.2);
		//1行高さ
		lineHeight = 28;
		
		//タイトル枠
		Rectangle titleBox = new Rectangle(titleWidth, lineHeight * wlist.size());
		titleBox.setX(10);
		titleBox.setY(10);
		titleBox.setStrokeWidth(2);
		titleBox.setStroke(Color.DARKGRAY);
		titleBox.setFill(Color.WHITE);
		paneR.getChildren().addAll(titleBox);
		//バーチャート枠
		Rectangle barBox = new Rectangle(iniWidth * 0.8, lineHeight * wlist.size() + 1);
		barBox.setStrokeWidth(2);
		barBox.setStroke(Color.DARKGRAY);
		barBox.setFill(Color.WHITE);
		paneL.getChildren().addAll(barBox);
		
		Scene scene = new Scene(sp, iniWidth, iniHeight);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * 作業票リスト取得[Getting Work Sheet List]<br>
	 * @author officina-hide.net
	 * @since 2022/11/29 Ver. 1.00
	 * @return 作業票リスト[Work sheet list]
	 */
	private List<FD_WorkSheet> getWorkSheetList() {
		List<FD_WorkSheet> list = new ArrayList<>();
		list.add(new FD_WorkSheet());
		list.add(new FD_WorkSheet());
		return list;
	}

	public static void main(String[] args) {
		launch(args);
	}

}
