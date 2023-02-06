package net.officina_hide.base.control;

import javafx.scene.control.Button;

/**
 * ボタンクラス[Button class]
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2023/02/02 Ver. 1.00
 */
public class FD_Button extends Button {

	private String className = null;

	/**
	 * コンストラクタ[Constructor]
	 * @author officina-hide.net
	 * @since 2023/02/02 Ver. 1.00
	 * @param buttonName ボタン表示名
	 */
	public FD_Button(String buttonName) {
		setText(buttonName);
		this.setOnAction(event->{
			execute();
		});
	}

	/**
	 * 実行処理[Execute process]
	 */
	private void execute() {
		System.out.println("test");
	}

}
