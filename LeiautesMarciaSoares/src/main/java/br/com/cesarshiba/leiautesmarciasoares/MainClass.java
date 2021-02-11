package br.com.cesarshiba.leiautesmarciasoares;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainClass extends Application {

	public static boolean loadSplash = false;
	
	/*
	 * chave para gerar .jar em produ��o
	 * devido localiza��o dos formul�rios e 
	 * arquivos auxiliares em pastas diferentes
	 */
	private static boolean PRODUCAO = false;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			Parent parentRoot = FXMLLoader.load(getClass().getResource(caminho() +"/frmPrincipal.fxml"));
			primaryStage.setTitle("Dra. M�rcia Soares");
			primaryStage.getIcons().add(new Image("logo marcia.png"));
			primaryStage.setResizable(true);
			primaryStage.setMaximized(true);
			primaryStage.setIconified(false);
			primaryStage.initStyle(StageStyle.TRANSPARENT);
			primaryStage.setScene(new Scene(parentRoot));
			primaryStage.show();
			Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
			double x = bounds.getMinX() + (bounds.getWidth() - primaryStage.getWidth()) * 0.5;
			double y = bounds.getMinY() + (bounds.getHeight() - primaryStage.getHeight()) * 0.5;
			primaryStage.setX(x);
			primaryStage.setY(y);			
		} catch (Exception e) {
			System.out.println("Erro:"+ e);
			System.exit(0);
		}
	}
	
	public static String caminho() {
		if(PRODUCAO) {
			return "/resources";
		}
		return "";
	}

}
