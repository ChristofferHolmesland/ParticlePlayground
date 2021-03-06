package application.particles;

import java.util.Random;

import javafx.scene.paint.Color;

public class ParticleWood extends Particle {

	Random rng = new Random();
	
	public ParticleWood() {
		super();
		
		this.type = Particles.WOOD;
		this.color = Color.SADDLEBROWN;
	}
	
	@Override
	// Generates a tree of size 5 wood + leaves if a part of the wood is standing on dirt.
	public void update(int x, int y, Particles[][] grid) {
		if (y > 0) {
			boolean ok = false;
			boolean leaves = false;
			for (int yOffset = 1; yOffset < grid[0].length-y; yOffset++) {
				if (yOffset > 5) {
					ok = false;
					leaves = false;
					break;
				}
				
				if (grid[x][y+yOffset] == Particles.WOOD)
					continue;
				
				if (grid[x][y+yOffset] == Particles.DIRT) {
					ok = true;
					
					if (yOffset == 5)
						leaves = true;
				}
				
				break;
			}
			
			if (ok && !leaves) {
				if (rng.nextInt(10) == 9) {
					if (y-1 >= 0) {
						if (grid[x][y-1] == Particles.NONE || grid[x][y-1] == Particles.LEAVES) {
							grid[x][y-1] = Particles.WOOD;
						}
					}
				}
			}
			
			if (leaves && ok) {
				if (rng.nextInt(10) > 7) {
					if (y-1 >= 0) {
						if (grid[x][y-1] == Particles.LEAVES) {
							setLeaves(x, y-2, grid);
							setLeaves(x-1, y-1, grid);
							setLeaves(x+1, y-1, grid);
							setLeaves(x-2, y, grid);
							setLeaves(x+2, y, grid);
							setLeaves(x-1, y+1, grid);
							setLeaves(x+1, y+1, grid);
						} else {
							if (grid[x][y-1] != Particles.WOOD) {
								setLeaves(x, y-1, grid);
								setLeaves(x-1, y, grid);
								setLeaves(x+1, y, grid);	
							}
						}
					}
				}
			}
		}
	}
	
	private void setLeaves(int x, int y, Particles[][] grid) {
		if (x < 0 || x >= grid.length || y < 0 || y >= grid[x].length)
			return;
		
		if (grid[x][y] == Particles.FIRE || grid[x][y] == Particles.WOOD)
			return;
		
		grid[x][y] = Particles.LEAVES;
	}

}
