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
		if (rng.nextInt(101) > 25) {
			if (x-1 >= 0) {
				if (grid[x-1][y] == Particles.LEAVES || grid[x-1][y] == Particles.WOOD)
					grid[x-1][y] = Particles.FIRE;
				else if (grid[x-1][y] == Particles.SAND)
					grid[x-1][y] = Particles.GLASS;
			}
			if (x+1 < grid.length) {
				if (grid[x+1][y] == Particles.LEAVES || grid[x+1][y] == Particles.WOOD)
					grid[x+1][y] = Particles.FIRE;
				else if (grid[x+1][y] == Particles.SAND)
					grid[x+1][y] = Particles.GLASS;
			}
			if (y-1 >= 0) {
				if (grid[x][y-1] == Particles.LEAVES || grid[x][y-1] == Particles.WOOD)
					grid[x][y-1] = Particles.FIRE;
				else if (grid[x][y-1] == Particles.SAND)
					grid[x][y-1] = Particles.GLASS;
			}
			if (y+1 < grid[x].length) {
				if (grid[x][y+1] == Particles.LEAVES || grid[x][y+1] == Particles.WOOD)
					grid[x][y+1] = Particles.FIRE;
				else if (grid[x][y+1] == Particles.SAND)
					grid[x][y+1] = Particles.GLASS;
			}
				
		}
		
		if (rng.nextInt(101) > 90);
			grid[x][y] = Particles.NONE;
	}
}
