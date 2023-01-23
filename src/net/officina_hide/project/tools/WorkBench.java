package net.officina_hide.project.tools;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import net.officina_hide.project.model.FD_Project;
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
	/** 月幅 */
	private double monthWidth;
	/** タイトルフォント */
	private Font titleFont = new Font("Meiryo UI", 16);
	private Font Font12 = new Font("Meiryo UI", 12);
	
	@Override
	public void start(Stage stage) throws Exception {
		//プロジェクト情報生成（仮 : 2022/12/07)
		FD_Project project = new FD_Project();
		project.setStartDate("2023/4/1");
		project.setEndDate("2026/3/31");
		int mcnt = project.getLengthOfMonth();
		//作業票リスト生成
		wlist = getWorkSheetList();
		
		SplitPane sp = new SplitPane();
		sp.setOrientation(Orientation.HORIZONTAL);		//横並びスプリット
		
		ScrollPane  scPaneR = new ScrollPane();
		Pane paneR = new Pane();
		scPaneR.setContent(paneR);
		scPaneR.pannableProperty().set(true);

		Pane paneLU = new Pane();		//バーチャート上部
		paneLU.setPrefHeight(100);
		Pane paneLD = new Pane();		//バーチャート下部
		paneLD.setPrefHeight(300);
		ScrollPane  scPaneL = new ScrollPane();
		scPaneL.setContent(paneLD);
		VBox ld = new VBox(paneLU, scPaneL);
		
		sp.getItems().addAll(scPaneR, ld);
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
		//1ヶ月の幅計算
		monthWidth = Math.abs((iniWidth - titleWidth) / mcnt);
		
		//プロジェクト枠
		paneLU.getChildren().addAll(createProjectBox());
		
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
		barBox.setX(0);
		barBox.setY(10);
		barBox.setStrokeWidth(2);
		barBox.setStroke(Color.DARKGRAY);
		barBox.setFill(Color.WHITE);
		paneLD.getChildren().add(barBox);
		//タイトル下線
		Line titleBottomLine = new Line(0, (lineHeight * 2) + 10, iniWidth * 0.8, (lineHeight * 2) + 10);
		titleBottomLine.setStrokeWidth(2);
		titleBottomLine.setStroke(Color.DARKGRAY);
		Line yearBottomLine = new Line(0, lineHeight + 10, iniWidth * 0.8, lineHeight + 10);
		yearBottomLine.setStrokeWidth(2);
		yearBottomLine.setStroke(Color.DARKGRAY);
		paneLD.getChildren().addAll(yearBottomLine, titleBottomLine);
		//年
		int yearCount = project.getLengthOfYear();
		double cx = 0;
		double startX = 0;
		for(int ix = 1; ix <= yearCount; ix++) {
			int monthCnt = 12;
			if(ix == 1) {
				monthCnt = 12 - project.getStartDate().get(Calendar.MONTH);
			}
			cx = cx + monthWidth * monthCnt;
			if(ix == yearCount) {
				monthCnt = project.getEndDate().get(Calendar.MONTH) + 1;
				cx = iniWidth * 0.8;
			} else {
				//年境界線の線引き
				Line yearLine = new Line(cx, 10, cx, lineHeight * 2 + 10);
				yearLine.setStroke(Color.DARKGRAY);
				yearLine.setStrokeWidth(1);
				paneLD.getChildren().add(yearLine);
			}
			//年表示
			Text yearText = new Text(Integer.toString(project.getStartDate().get(Calendar.YEAR) + ix - 1)+"年");
			yearText.setFont(titleFont);
			yearText.setX(cx - (monthWidth * monthCnt / 2) - (yearText.getLayoutBounds().getWidth() / 2));
			yearText.setY(18 + (yearText.getLayoutBounds().getHeight() / 2));
			paneLD.getChildren().add(yearText);
			//月表示
			for(int jx = 1; jx < monthCnt; jx++) {
				double x = startX + monthWidth * (jx);
				Line monthLine = new Line(x, lineHeight + 10, x, lineHeight * 2 + 10);
				monthLine.setStroke(Color.DARKGRAY);
				monthLine.setStrokeWidth(1);
				paneLD.getChildren().add(monthLine);
			}
			startX = cx;
		}
		
		Scene scene = new Scene(sp, iniWidth, iniHeight);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * プロジェクト名表示<br>
	 * @author officina-hide.net
	 * @since 2023/01/19 Ver. 1.00
	 * @return プロジェクト名枠
	 */
	private Collection<Node> createProjectBox() {
		Collection<Node> nodes = new ArrayList<Node>();
		Text projectLabelText = new Text("プロジェクト");
		projectLabelText.setFont(titleFont);
		projectLabelText.setStroke(Color.DARKGRAY);
		projectLabelText.setX(10);
		projectLabelText.setY(31);
		nodes.add(projectLabelText);
		Rectangle box = new Rectangle(200, lineHeight, Color.WHITE);
		box.setX(110);
		box.setY(10);
		box.setStrokeWidth(2);
		box.setStroke(Color.DARKGRAY);
		nodes.add(box);
		return nodes;
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
