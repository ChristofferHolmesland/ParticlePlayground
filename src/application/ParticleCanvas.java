package application;

import application.particles.Particle;
import application.particles.Particle.Particles;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class ParticleCanvas extends Canvas {

	private Particles selectedParticle = Particles.NONE;
	private Particles[][] particles;
	
	private int particleSize = 4;
	
	/**
	 * Initializes particle array, sets size and with of canvas and particle array.
	 * Draws the particles to the canvas. Adds mouse binds.
	 * Adds a timer to update the canvas several every 0.1 seconds.
	 * @param arg0 Canvas width.
	 * @param arg1 Canvas height.
	 */
	public ParticleCanvas(double arg0, double arg1) {
		super(arg0, arg1);
		
		int width = (int) arg0/particleSize;
		int height = (int) arg1/particleSize;
		
		particles = new Particles[width][height];
		for (int x = 0; x < particles.length; x++) {
			for (int y = 0; y < particles[x].length; y++) {
				particles[x][y] = Particles.NONE;
			}
		}
		
		setOnMouseClicked((e) -> mouseClick(e));
		setOnMouseDragged((e) -> mouseClick(e));
		
		displayParticles();
	
		Timeline timer = new Timeline(new KeyFrame(Duration.seconds(0.1), new EventHandler<ActionEvent>() {
		    
			@Override
		    public void handle(ActionEvent event) {			
				for (int x = 0; x < particles.length; x++) {
					for (int y = particles[x].length-1; y >= 0; y--) {
						Particles type = particles[x][y];
						Particle.getParticle(type).update(x, y, particles);
					}
				}
				displayParticles();
		    }
		}));
		
		timer.setCycleCount(Timeline.INDEFINITE);
		timer.play();
	}
	
	/**
	 * Renders the particles to the canvas.
	 */
	public void displayParticles() {
		GraphicsContext gc = getGraphicsContext2D();
		
		for (int x = 0; x < particles.length; x++) {
			for (int y = 0; y < particles[x].length; y++) {
				Particle p = Particle.getParticle(particles[x][y]);
				gc.setFill(p.color);
				gc.fillRect(x*particleSize, y*particleSize, particleSize, particleSize);
			}
		}
	}
	
	/**
	 * Adds a particle at the click location.
	 * @param e Mouse event, used for click position.
	 */
	private void mouseClick(MouseEvent e) {
		// Project screen coordinates to world coordinates:
		int x = (int) (e.getX() / particleSize);
		int y = (int) (e.getY() / particleSize);
		
		if (x < 0 || x >= particles.length || y < 0 || y >= particles[0].length)
			return;
		
		particles[x][y] = selectedParticle;
		displayParticles();
	}
	
	/**
	 * Changes what particle will be put where the user clicks.
	 * @param type New particle type to use.
	 */
	public void setSelectedParticle(Particles type) {
		selectedParticle = type;
	}
}
