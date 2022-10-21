package net.officina_hide.base.model;

import javafx.scene.control.CheckBox;
import javafx.scene.text.Font;

/**
 * チェックボックスクラス[Checkbox class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2022/10/21 Ver 1.00
 */
public class FDCheck {
	
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
	public FDCheck(String labelName) {
		this.labelName = labelName;
	}
}
