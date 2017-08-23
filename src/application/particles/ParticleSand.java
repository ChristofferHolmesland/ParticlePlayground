package application.particles;

import javafx.scene.paint.Color;

public class ParticleSand extends Particle {

	public ParticleSand() {
		super();
		
		this.type = Particles.METAL;
		this.color = Color.YELLOW;
	}
	
	@Override
	// Falls if either nothing or water is under it.
	public void update(int x, int y, Particles[][] grid) {
		// Bottom off screen
		if (y == grid[x].length-1)
			return;
		
		if (grid[x][y+1] != Particles.NONE && grid[x][y+1] != Particles.WATER)
			return;
		
		Particles oldP = grid[x][y+1];
		grid[x][y+1] = grid[x][y];
		grid[x][y] = oldP;
	}
}
