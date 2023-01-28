package net.officina_hide.base.model;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

/**
 * チェックボックスクラス[Checkbox class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2022/10/21 Ver 1.00
 */
public class FD_Check {
	
	/** ラベル名 */
	private String labelName;
	/** チェックボックス */
	private CheckBox check;
	/** フォント */
	private Font font = new Font("Meiryo UI", 12);
	
	/**
	 * コンストラクタ[Constructor]
	 * @author officina-hide.net
	 * @since 2022/10/21 Ver. 1.00
	 * @param labelName ラベル
	 */
	public FD_Check(String labelName) {
		this.labelName = labelName;
	}

	/**
	 * 画面ノード取得[Getting screen node]
	 * @author officina-hide.net
	 * @since 2022/10/22 Ver. 1.00
	 * @return 画面ノード[Screen node]
	 */
	public Node getNode() {
		HBox box = new HBox(5);
		box.setAlignment(Pos.CENTER_LEFT);
		check = new CheckBox(labelName);
		check.setFont(font);
		
		box.getChildren().addAll(check);
		return box;
	}

	/**
	 * チェックボックスセット[Setting of check box]
	 * @author officina-hide.net
	 * @since 2022/10/31 Ver. 1.00
	 * @param chk true - チェック、false - チェック外す
	 */
	public void setCheck(boolean chk) {
		check.setSelected(chk);
	}
}
