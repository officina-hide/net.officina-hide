package net.officina_hide.base.model;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

/**
 * ステータスバー[Status bar]
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2022/10/31 Ver. 1.00
 */
public class StatusBar {

	/** ステータステキスト */
	private TextField statusText;
	/** フォント */
	private Font font = new Font("Meiryo UI", 12);
	
	/**
	 * コンストラクタ[Constructor]
	 * @author officina-hide.net
	 * @since 2022/10/31 Ver. 1.00
	 */
	public StatusBar() {
	}

	/**
	 * 画面ノード取得[Getting screen node]
	 * @author officina-hide.net
	 * @since 2022/10/31 Ver. 1.00
	 * @return 画面ノード
	 */
	public Node getNode() {
		HBox box = new HBox(5);
		box.setAlignment(Pos.CENTER_LEFT);
		statusText = new TextField();
		statusText.setFont(font);
		statusText.setEditable(false);
		box.getChildren().add(statusText);
		return box;
	}
}
