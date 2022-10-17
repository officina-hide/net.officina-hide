package net.officina_hide.base.model;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.HBox;

/**
 * パスワード用テキスト領域[Text area for password]
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2022/10/13 Ver. 1.00
 */
public class PasswordText {
	/** ラベル名 */
	private String labelName;
	/** パスワード入力項目 */
	private PasswordField password;

	/**
	 * コンストラクタ[Constructor]
	 * @author officina-hide.net
	 * @since 2022/10/13 Ver. 1.00
	 * @param labelName ラベル名
	 */
	public PasswordText(String labelName) {
		this.labelName = labelName;
	}

	/**
	 * 画面ノード取得[Getting screen node]
	 * @author officina-hide.net
	 * @since 2022/10/13 Ver. 1.00
	 * @return 画面ノード
	 */
	public Node getNode() {
		HBox box = new HBox(5);
		box.setAlignment(Pos.CENTER_LEFT);
		Label label = new Label(labelName);
		password = new PasswordField();
		box.getChildren().addAll(label, password);
		return box;
	}

	/**
	 * パスワード文字列取得[Getting password strings]
	 * @author officina-hide.net
	 * @since 2022/10/14 Ver. 1.00
	 * @return パスワード文字列[Password strings]
	 */
	public String getText() {
		return password.getText();
	}

	/**
	 * パスワード文字列セット[Setting password strings]
	 * @author officina-hide.net
	 * @since 2022/10/17 Ver. 1.00
	 */
	public void setText(String data) {
		if(password == null) {
			password = new PasswordField();
		}
		password.setText(data);
	}


}
