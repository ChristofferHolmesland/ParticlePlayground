package application.particles;

import java.util.Hashtable;

import javafx.scene.paint.Color;

public class Particle {
	public static enum Particles {
		NONE,
		METAL,
		SAND,
		WATER,
		WOOD,
		DIRT,
		LEAVES
	}
	
	public Particles type;
	public Color color;
	
	/**
	 * Gets called once every frame. All logic should be here.
	 * @param x Current x-position of particle.
	 * @param y Current y-position of particle.
	 * @param grid Array containing particle.
	 */
	public void update(int x, int y, Particles[][] grid) {
		
	}
	
	private static Hashtable<Particles, Particle> particles = new Hashtable<Particles, Particle>(); static {
		particles.put(Particles.NONE, new ParticleNone());
		particles.put(Particles.METAL, new ParticleMetal());
		particles.put(Particles.SAND, new ParticleSand());
		particles.put(Particles.WATER, new ParticleWater());
		particles.put(Particles.WOOD, new ParticleWood());
		particles.put(Particles.DIRT, new ParticleDirt());
		particles.put(Particles.LEAVES, new ParticleLeaves());
	}
	public static Particle getParticle(Particles type) {
		return particles.get(type);
	}
}
