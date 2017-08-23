package application.particles;

import java.util.Random;

import javafx.scene.paint.Color;

public class ParticleWater extends Particle {

	private Random rng = new Random();
	
	public ParticleWater() {
		super();
		
		this.type = Particles.METAL;
		this.color = Color.BLUE;
	}
	
	@Override
	// Action priority:
	// Go down
	// Go down-left or down-right
	// Go left or right
	public void update(int x, int y, Particles[][] grid) {		
		// Bottom off screen
		if (y == grid[x].length-1)
			return;
		
		int newX = -1;
		int newY = -1;
		
		// Try going down
		if (grid[x][y+1] == Particles.NONE) {
			newX = x;
			newY = y+1;
		} else {
			// If we are at the left edge:
			if (x == 0) {
				// Try going right and down
				if (grid[x+1][y+1] == Particles.NONE && grid[x+1][y] == Particles.NONE) {
					newX = x+1;
					newY = y+1;
				// Try going right
				} else if (grid[x+1][y] == Particles.NONE) {
					newX = x+1;
					newY = y;
				}
			// If we are at the right edge
			} else if (x == grid.length-1) {
				// Try going left and down
				if (grid[x-1][y+1] == Particles.NONE && grid[x-1][y] == Particles.NONE) {
					newX = x-1;
					newY = y+1;
				// Try going left
				} else if (grid[x-1][y] == Particles.NONE) {
					newX = x-1;
					newY = y;
				 }
			// If we are not at an edge
			} else {
				// Choose a random direction
				boolean left = rng.nextBoolean();
				int offset = 1;
				int iOffset = -1;
				if (left) {
					offset = -1;
					iOffset = 1;
				}
				
				// Try going in random direction and down
				if (grid[x+offset][y+1] == Particles.NONE && grid[x+offset][y] == Particles.NONE) {
					newX = x+offset;
					newY = y+1;
				// Try going in random direction
				} else if (grid[x+offset][y] == Particles.NONE) {
					newX = x+offset;
					newY = y;
				// Try going in inverse random direction and down
				} else if (grid[x+iOffset][y+1] == Particles.NONE && grid[x+iOffset][y] == Particles.NONE) {
					newX = x+iOffset;
					newY = y+1;
				// Try going in inverse random direction
				} else if (grid[x+iOffset][y] == Particles.NONE) {
					newX = x+iOffset;
					newY = y;
				}
				
			}
		}
		
		if (newX == -1 || newY == -1)
			return;
		
		grid[newX][newY] = grid[x][y];
		grid[x][y] = Particles.NONE;
	}
}
