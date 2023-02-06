package net.officina_hide.base.control;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

/**
 * ボタン付き表示項目[Display item with button]
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2023/01/31 Ver. 1.00
 */
public class FD_DispWithButton {
	
	/** フォント */
	private Font font = new Font("Meiryo UI", 12);
	/** 表示テキスト */
	private String dispText;
	private TextField disp;

	/**
	 * コンストラクタ[Constructor]
	 * @author officina-hide.net
	 * @since 2023/01/31 Ver. 1.00
	 * @param labelName ラベル名[Label name]
	 */
	public FD_DispWithButton(String labelName) {
		this.labelName = labelName;
	}

	/** ラベル名 */
	private String labelName;

	/**
	 * 画面ノード取得[Getting screen node]
	 * @author officina-hide.net
	 * @since 2023/01/31 Ver. 1.00
	 * @return 画面ノード[Screen node]
	 */
	public Node getNode() {
		HBox box = new HBox(5);
		box.setAlignment(Pos.CENTER_LEFT);
		
		Label label = new Label(labelName);
		label.setFont(font);
		box.getChildren().add(label);
		
		disp = new TextField(dispText);
		disp.setFont(font);
		disp.setEditable(false);
		box.getChildren().add(disp);
		
		return box;
	}

	public void setDispText(String dispText) {
		this.dispText = dispText;
		if(disp != null) {
			disp.setText(dispText);
		}
	}

}
