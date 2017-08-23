package application;
	
import application.particles.Particle;
import application.particles.Particle.Particles;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			
			ParticleCanvas pc = new ParticleCanvas(200, 400);
			root.setCenter(pc);
			
			Label lblParticle = new Label("Particle type: ");
			ComboBox<Particle.Particles> particleOptions = new ComboBox<Particle.Particles>();
			for (Particle.Particles p : Particle.Particles.values()) {
				particleOptions.getItems().add(p);
			}
			particleOptions.setValue(Particle.Particles.NONE);
			particleOptions.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Particles>() {
				@Override
				public void changed(ObservableValue<? extends Particles> arg0, Particles old, Particles newP) {
					pc.setSelectedParticle(newP);
				}
			});
			
			
			root.setBottom(new HBox(lblParticle, particleOptions));
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
