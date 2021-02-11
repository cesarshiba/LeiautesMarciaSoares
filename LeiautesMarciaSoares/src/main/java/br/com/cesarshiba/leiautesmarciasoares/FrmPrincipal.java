package br.com.cesarshiba.leiautesmarciasoares;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class FrmPrincipal implements Initializable{

    @FXML
    public MenuBar mnuBarra;
    
    @FXML
    public AnchorPane root;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (!MainClass.loadSplash) {
			carregaSplash();
		}
	}

    @FXML
    public void mnuBackupOnAction(ActionEvent event) {

    }

    @FXML
    public void mnuClientesOnAction(ActionEvent event) throws IOException {
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
    }

    @FXML
    public void mnuSairOnAction(ActionEvent event) {
    	System.exit(0);
    }

    @FXML
    public void mnuSobreOnAction(ActionEvent event) {

    }

	private void carregaSplash() {
		MainClass.loadSplash=true;
		
		try {
			AnchorPane pane = FXMLLoader.load(getClass().getResource(MainClass.caminho() +"/frmLogo.fxml"));
			root.getChildren().setAll(pane);
			pane.setLayoutX(350);
			pane.setLayoutY(190);
			
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
				AnchorPane parentPane;
				try {
					parentPane = FXMLLoader.load(getClass().getResource(MainClass.caminho() +"/frmPrincipal.fxml"));
					root.getChildren().setAll(parentPane);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
