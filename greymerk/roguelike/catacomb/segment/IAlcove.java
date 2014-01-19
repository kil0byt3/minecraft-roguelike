package greymerk.roguelike.catacomb.segment;

import greymerk.roguelike.worldgen.Cardinal;

import java.util.Random;

import net.minecraft.src.World;

public interface IAlcove {
	
	public void generate(World world, Random rand, int x, int y, int z, Cardinal dir);
	
	public boolean isValidLocation(World world, int x, int y, int z, Cardinal dir);
	
}
