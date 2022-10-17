package net.officina_hide.base.model;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

/**
 * １行テキストクラス[Single text class]
 * FIXME ラベルと項目の縦位置をそろえる。 2022/10/11
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2022/10/11
 */
public class SingleText {

	/** ラベル名 */
	private String labelName;
	/** 入力項目 */
	private TextField text;
	/** フォント */
	private Font font = new Font("Meiryo UI", 12);
	
	/**
	 * コンストラクタ[Constructor]
	 * @param labelName ラベル名
	 */
	public SingleText(String labelName) {
		this.labelName = labelName;
	}

	/**
	 * 画面ノード取得[Getting screen node]
	 * @author officina-hide.net
	 * @since 2022/10/11
	 * @return 画面ノード
	 */
	public Node getNode() {
		HBox box = new HBox(5);
		box.setAlignment(Pos.CENTER_LEFT);
		Label label = new Label(labelName);
		label.setFont(font);
		text = new TextField();
		text.setFont(font);
		
		box.getChildren().addAll(label, text);
		
		return box;
	}

	/**
	 * 入力情報取得[Get input information]
	 * @author officina-hide.net
	 * @since 2022/10/13 Ver. 1.00
	 * @return 入力情報
	 */
	public String getText() {
		return text.getText();
	}

	/**
	 * テキスト領域セット[Setting text field]
	 * @author officina-hide.net
	 * @since 2022/10/17 Ver. 1.00
	 * @param data テキスト
	 */
	public void setText(String data) {
		if(text == null) {
			text = new TextField();
		}
		text.setText(data);
	}

}
