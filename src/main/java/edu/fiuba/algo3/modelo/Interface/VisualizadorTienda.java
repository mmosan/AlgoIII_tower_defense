package edu.fiuba.algo3.modelo.Interface;

import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.vista.ConstanteImagenes;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.FileNotFoundException;

public class VisualizadorTienda {
    public static VBox crearPanelTienda() {
        VBox vbox = new VBox();
        vbox.setStyle("-fx-background-color: #336699;");

        Text text = new Text("Defensas disponibles:");
        text.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        vbox.getChildren().addAll(text, crearIconosDefensas());
        return vbox;
    }

    private static FlowPane crearIconosDefensas() {
        FlowPane flowPane = new FlowPane();
        flowPane.setHgap(1000); // para que se apilen verticalmente
        flowPane.setVgap(30);
        flowPane.setPadding(new Insets(70, 0, 0, 65));
        Jugador.getInstance().verificarConstruccionesPosibles().forEach(defensa -> {
            ImageView nuevaDefensa = null;
            try {
                nuevaDefensa = ConstanteImagenes.getImagen(defensa);
                nuevaDefensa.setFitHeight(200);
                nuevaDefensa.setFitWidth(150);
                flowPane.getChildren().add(nuevaDefensa);

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        return flowPane;
    }
}