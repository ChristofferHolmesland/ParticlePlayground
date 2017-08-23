package application.particles;

import java.util.Random;

import javafx.scene.paint.Color;

public class ParticleFire extends Particle {

	private Random rng = new Random();
	
	public ParticleFire() {
		super();
		
		this.type = Particles.FIRE;
		this.color = Color.ORANGERED;
	}
	
	@Override
	public void update(int x, int y, Particles[][] grid) {
		if (rng.nextInt(15) > 5) {
			if (grid[x-1][y] == Particles.LEAVES || grid[x-1][y] == Particles.WOOD)
				grid[x-1][y] = Particles.FIRE;
			if (grid[x+1][y] == Particles.LEAVES || grid[x+1][y] == Particles.WOOD)
				grid[x+1][y] = Particles.FIRE;
			if (grid[x][y-1] == Particles.LEAVES || grid[x][y-1] == Particles.WOOD)
				grid[x][y-1] = Particles.FIRE;
			if (grid[x][y+1] == Particles.LEAVES || grid[x][y+1] == Particles.WOOD)
				grid[x][y+1] = Particles.FIRE;
				
		}
		
		if (rng.nextInt(100) > 80);
			grid[x][y] = Particles.NONE;
	}
}
