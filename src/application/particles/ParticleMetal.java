package application.particles;

import javafx.scene.paint.Color;

public class ParticleMetal extends Particle {
	
	public ParticleMetal() {
		super();
		
		this.type = Particles.METAL;
		this.color = Color.GREY;
	}
}
