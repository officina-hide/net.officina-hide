package net.officina_hide.base.control;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javafx.scene.control.Button;
import net.officina_hide.base.model.FD_EnvData;

/**
 * ボタンクラス[Button class]
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2023/02/02 Ver. 1.00
 */
public class FD_Button extends Button {

	/** クラス名 */
	private String className = null;
	/** 環境情報 */
	private FD_EnvData env;

	/**
	 * コンストラクタ[Constructor]
	 * @author officina-hide.net
	 * @since 2023/02/02 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param buttonName ボタン表示名[Button displayed name]
	 */
	public FD_Button(FD_EnvData env, String buttonName) {
		setText(buttonName);
		this.env = env;
		this.setOnAction(event->{
			execute();
		});
	}

	/**
	 * 実行処理[Execute process]
	 */
	private void execute() {
		try {
			Class<?> clazz = Class.forName(className);
			Object obj = clazz.getDeclaredConstructor().newInstance();
			Method execute = clazz.getMethod("onClick", FD_EnvData.class);
			execute.invoke(obj, env);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
	}

	public void setClassName(String className) {
		this.className = className;
	}

}
