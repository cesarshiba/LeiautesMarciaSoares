package br.com.cesarshiba.leiautesmarciasoares;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class FrmPrincipal implements Initializable{

    @FXML
    public BorderPane root;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (!MainClass.loadSplash) {
			carregaSplash();
		}
	}

	private void carregaSplash() {
		MainClass.loadSplash=true;
		
		try {
			AnchorPane pane = FXMLLoader.load(getClass().getResource(MainClass.caminho() +"/frmLogo.fxml"));
			root.getChildren().setAll(pane);
			Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
			double x = bounds.getMinX() + (bounds.getWidth() - pane.getWidth()) * 0.28;
			double y = bounds.getMinY() + (bounds.getHeight() - pane.getHeight()) * 0.3;
			pane.setLayoutX(x);
			pane.setLayoutY(y);
			
			FadeTransition fadeIn = new FadeTransition(Duration.seconds(2),pane);
			fadeIn.setFromValue(0);
			fadeIn.setToValue(1);
			fadeIn.setCycleCount(1);
			FadeTransition fadeOut = new FadeTransition(Duration.seconds(2),pane);
			fadeOut.setFromValue(1);
			fadeOut.setToValue(0);
			fadeOut.setCycleCount(1);
			fadeIn.play();
			fadeIn.setOnFinished((e) -> {
				fadeOut.play();
			});
			
			fadeOut.setOnFinished((e) ->{
				BorderPane parentPane;
				try {
					parentPane = FXMLLoader.load(getClass().getResource(MainClass.caminho() +"/frmPrincipal.fxml"));
					root.getChildren().setAll(parentPane);
					root.setLeft(Menu());
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private VBox Menu() {
		VBox vbox = new VBox();
		vbox.setPrefWidth(95);
		vbox.setStyle("-fx-background-color:#332a7c");
		for(int i=1;i<6;i++) {
			vbox.getChildren().add(Item(String.valueOf(i)));
		}
		return vbox;
	}
	
	private HBox Item(String nome) {
		Image imagem = new Image(MainClass.caminho() +"/"+ nome +".png");
		ImageView imageView = new ImageView(imagem);
		Button btn = new Button();
		btn.setGraphic(imageView);
		btn.setPrefSize(95, 50);
		btn.setStyle("-fx-background-color:#35145d");
		btn.setOnAction(value -> {
			switch (Integer.valueOf(nome)) {
			case 1: {
				try {
					FXMLLoader fxmlClientes = new FXMLLoader(getClass().getResource(MainClass.caminho() +"/frmClientes.fxml"));
					Parent rootClientes = (Parent) fxmlClientes.load();
					Scene sceneClientes = new Scene(rootClientes);
					sceneClientes.setFill(Color.TRANSPARENT);
					Stage stageClientes = new Stage();
					stageClientes.setTitle("Selecionar Cliente");
					stageClientes.setScene(sceneClientes);
					stageClientes.setResizable(false);
					stageClientes.initStyle(StageStyle.TRANSPARENT);
					stageClientes.initModality(Modality.APPLICATION_MODAL);
					stageClientes.show();
				} catch (IOException e1) {
					e1.printStackTrace();
					System.out.println(e1);
				}
				break;
			}
			case 5: {
				System.exit(0);
			}
			default:
				System.out.println("apertou botão:"+ nome);
			}
		});
		Pane paneIndicator = new Pane();
		paneIndicator.setPrefSize(5, 50);
		paneIndicator.setStyle("-fx-background-color:#000000");
		menuDecorator(btn, paneIndicator);
		HBox hbox = new HBox(paneIndicator,btn);
		return hbox;
	}
	
	private void menuDecorator(Button btn, Pane pane) {
		btn.setOnMouseEntered(value -> {
			btn.setStyle("-fx-background-color:#ffffff");
			pane.setStyle("-fx-background-color:#ffff00");
		});
		btn.setOnMouseExited(value -> {
			btn.setStyle("-fx-background-color:#35145d");
			pane.setStyle("-fx-background-color:#000000");
		});
	}
}
