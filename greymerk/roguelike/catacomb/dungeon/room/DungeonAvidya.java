package greymerk.roguelike.catacomb.dungeon.room;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import greymerk.roguelike.catacomb.dungeon.IDungeon;
import greymerk.roguelike.treasure.ITreasureChest;
import greymerk.roguelike.treasure.TreasureChestEmpty;
import greymerk.roguelike.treasure.loot.Loot;
import greymerk.roguelike.treasure.loot.provider.ItemNovelty;
import greymerk.roguelike.worldgen.Cardinal;
import greymerk.roguelike.worldgen.Coord;
import greymerk.roguelike.worldgen.MetaBlock;
import greymerk.roguelike.worldgen.WorldGenPrimitive;

public class DungeonAvidya implements IDungeon {

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		
		MetaBlock redClay = new MetaBlock(Block.stainedClay.blockID, 14);
		MetaBlock whiteClay = new MetaBlock(Block.stainedClay.blockID, 0);
		MetaBlock pillarQuartz = new MetaBlock(Block.blockNetherQuartz.blockID, 2);
		
		// clear space
		WorldGenPrimitive.fillRectSolid(world, x - 8, y, z - 8, x + 8, y + 5, z + 8, 0);
				
		// roof
		WorldGenPrimitive.fillRectSolid(world, x - 6, y + 6, z - 6, x + 6, y + 6, z + 6, redClay, true, true);
		WorldGenPrimitive.fillRectSolid(world, x - 3, y + 6, z - 3, x + 3, y + 6, z + 3, Block.glowStone.blockID);
		
		WorldGenPrimitive.fillRectSolid(world, x - 7, y - 1, z - 7, x + 7, y - 1, z + 7, 0);
		
		
		// floor
		MetaBlock ying = new MetaBlock(Block.stainedClay.blockID, 15);
		MetaBlock yang = new MetaBlock(Block.stainedClay.blockID, 0);
		
		// ying
		WorldGenPrimitive.fillRectSolid(world, x - 8, y - 2, z - 8, x + 8, y - 2, z + 8, ying, true, true);
		
		// yang
		MetaBlock quartz = new MetaBlock(Block.blockNetherQuartz.blockID);
		Coord start = new Coord(x, y, z);
		start.add(Cardinal.DOWN, 2);
		start.add(Cardinal.WEST, 5);
		Coord end = new Coord(start);
		start.add(Cardinal.NORTH, 2);
		end.add(Cardinal.SOUTH, 2);
		WorldGenPrimitive.fillRectSolid(world, start, end, yang, true, true);
		
		start.add(Cardinal.EAST, 1);
		end.add(Cardinal.EAST, 1);
		start.add(Cardinal.NORTH, 2);
		end.add(Cardinal.SOUTH, 2);
		WorldGenPrimitive.fillRectSolid(world, start, end, yang, true, true);
		
		start.add(Cardinal.EAST, 1);
		end.add(Cardinal.EAST, 1);
		end.add(Cardinal.NORTH, 3);
		WorldGenPrimitive.fillRectSolid(world, start, end, yang, true, true);
		
		start.add(Cardinal.EAST, 1);
		end.add(Cardinal.EAST, 1);
		start.add(Cardinal.NORTH, 1);
		end.add(Cardinal.NORTH, 1);
		WorldGenPrimitive.fillRectSolid(world, start, end, yang, true, true);
		
		start.add(Cardinal.EAST, 1);
		end.add(Cardinal.EAST, 3);
		end.add(Cardinal.NORTH, 1);
		WorldGenPrimitive.fillRectSolid(world, start, end, yang, true, true);
		
		start.add(Cardinal.EAST, 3);
		end.add(Cardinal.EAST, 1);
		start.add(Cardinal.SOUTH, 1);
		end.add(Cardinal.NORTH, 1);
		WorldGenPrimitive.fillRectSolid(world, start, end, yang, true, true);
		
		start.add(Cardinal.WEST, 3);
		end.add(Cardinal.WEST, 2);
		end.add(Cardinal.NORTH, 1);
		WorldGenPrimitive.fillRectSolid(world, start, end, ying, true, true);
		
		start.add(Cardinal.EAST, 1);
		end.add(Cardinal.EAST, 1);
		start.add(Cardinal.SOUTH, 7);
		end.add(Cardinal.SOUTH, 7);
		WorldGenPrimitive.fillRectSolid(world, start, end, yang, true, true);
		
		
		
		for(Cardinal dir : Cardinal.directions){
			for (Cardinal orth : Cardinal.getOrthogonal(dir)){

				// upper trim
				start = new Coord(x, y, z);
				start.add(dir, 8);
				start.add(Cardinal.UP, 4);
				end = new Coord(start);
				end.add(orth, 8);
				WorldGenPrimitive.fillRectSolid(world, start, end, whiteClay, true, true);
				start.add(Cardinal.DOWN, 5);
				end.add(Cardinal.DOWN, 5);
				WorldGenPrimitive.fillRectSolid(world, start, end, new MetaBlock(Block.stoneBrick.blockID), true, true);
				
				start = new Coord(x, y, z);
				start.add(dir, 7);
				start.add(Cardinal.UP, 5);
				end = new Coord(start);
				end.add(orth, 7);
				WorldGenPrimitive.fillRectSolid(world, start, end, whiteClay, true, true);
				
				// ceiling details
				start = new Coord(x, y, z);
				start.add(dir, 4);
				start.add(Cardinal.UP, 5);
				end = new Coord(start);
				end.add(orth, 2);
				WorldGenPrimitive.fillRectSolid(world, start, end, quartz, true, true);
				
				Coord cursor = new Coord(end);
				cursor.add(dir, 1);
				WorldGenPrimitive.setBlock(world, cursor, quartz, true, true);
				cursor = new Coord(end);
				cursor.add(Cardinal.reverse(dir), 1);
				cursor.add(orth, 1);
				WorldGenPrimitive.setBlock(world, cursor, quartz, true, true);
				pillarTop(world, cursor);
				
				// pillars
				start = new Coord(x, y, z);
				start.add(Cardinal.DOWN, 1);
				start.add(dir, 8);
				start.add(orth, 2);
				end = new Coord(start);
				end.add(Cardinal.UP, 4);
				WorldGenPrimitive.fillRectSolid(world, start, end, pillarQuartz, true, true);
				start.add(orth, 4);
				end.add(orth, 4);
				WorldGenPrimitive.fillRectSolid(world, start, end, pillarQuartz, true, true);
				
				// pillar tops
				cursor = new Coord(x, y, z);
				cursor.add(dir, 8);
				cursor.add(orth, 2);
				cursor.add(Cardinal.UP, 3);
				Coord cursor2 = new Coord(cursor);
				pillarTop(world, cursor);
				cursor2.add(orth, 4);
				pillarTop(world, cursor2);
				cursor2.add(Cardinal.reverse(dir), 1);
				cursor2.add(Cardinal.UP, 1);
				WorldGenPrimitive.setBlock(world, cursor2, quartz, true, true);
				cursor2.add(Cardinal.reverse(dir), 1);
				cursor2.add(Cardinal.UP, 1);
				WorldGenPrimitive.setBlock(world, cursor2, whiteClay, true, true);
				cursor.add(Cardinal.reverse(dir), 1);
				cursor.add(Cardinal.UP, 1);
				pillarTop(world, cursor);
				cursor.add(Cardinal.reverse(dir), 1);
				cursor.add(Cardinal.UP, 1);
				pillarTop(world, cursor);		
				
				// outer wall shell
				start = new Coord(x, y, z);
				start.add(dir, 9);
				end = new Coord(start);
				end.add(orth, 9);
				end.add(Cardinal.UP, 3);
				WorldGenPrimitive.fillRectSolid(world, start, end, whiteClay, false, true);
				
				// floor outer step circle
				cursor = new Coord(x, y, z);
				cursor.add(dir, 7);
				cursor.add(Cardinal.DOWN, 1);
				MetaBlock step = new MetaBlock(Block.stairsStoneBrick.blockID);
				step.setMeta(WorldGenPrimitive.blockOrientation(Cardinal.reverse(dir), false));
				WorldGenPrimitive.setBlock(world, cursor, step, true, true);
				
				cursor.add(orth, 1);
				WorldGenPrimitive.setBlock(world, cursor, step, true, true);
				
				cursor.add(orth, 1);
				WorldGenPrimitive.setBlock(world, cursor, step, true, true);
				
				step.setMeta(WorldGenPrimitive.blockOrientation(Cardinal.reverse(orth), false));
				cursor.add(orth, 1);
				WorldGenPrimitive.setBlock(world, cursor, step, true, true);
				
				cursor.add(Cardinal.reverse(dir), 1);
				WorldGenPrimitive.setBlock(world, cursor, step, true, true);
				
				step.setMeta(WorldGenPrimitive.blockOrientation(Cardinal.reverse(dir), false));
				cursor.add(orth, 1);
				WorldGenPrimitive.setBlock(world, cursor, step, true, true);
				
				step.setMeta(WorldGenPrimitive.blockOrientation(Cardinal.reverse(orth), false));
				cursor.add(orth, 1);
				WorldGenPrimitive.setBlock(world, cursor, step, true, true);
				
				cursor.add(Cardinal.reverse(dir), 1);
				WorldGenPrimitive.setBlock(world, cursor, step, true, true);
				
				// perimeter decor
				cursor = new Coord(x, y, z);
				cursor.add(Cardinal.DOWN, 1);
				cursor.add(dir, 8);
				cursor.add(orth, 3);
				WorldGenPrimitive.setBlock(world, cursor, Block.grass.blockID);
				MetaBlock leaves = new MetaBlock(Block.leaves.blockID, 7);
				WorldGenPrimitive.setBlock(world, cursor.getX(), cursor.getY() + 1, cursor.getZ(), leaves);
				cursor.add(orth, 1);
				WorldGenPrimitive.setBlock(world, cursor, Block.grass.blockID);
				WorldGenPrimitive.setBlock(world, cursor.getX(), cursor.getY() + 1, cursor.getZ(), leaves);
				cursor.add(orth, 1);
				WorldGenPrimitive.setBlock(world, cursor, Block.grass.blockID);
				WorldGenPrimitive.setBlock(world, cursor.getX(), cursor.getY() + 1, cursor.getZ(), leaves);
				cursor.add(Cardinal.reverse(dir), 1);
				WorldGenPrimitive.setBlock(world, cursor, Block.cobblestone.blockID);
				cursor.add(Cardinal.reverse(orth), 1);
				WorldGenPrimitive.setBlock(world, cursor, Block.glowStone.blockID);
				cursor.add(orth, 2);
				WorldGenPrimitive.setBlock(world, cursor, 0);
				cursor.add(Cardinal.DOWN, 1);
				WorldGenPrimitive.setBlock(world, cursor, Block.glowStone.blockID);
				cursor.add(Cardinal.UP, 1);
				cursor.add(Cardinal.reverse(dir), 1);
				WorldGenPrimitive.setBlock(world, cursor, Block.cobblestone.blockID);
				cursor.add(dir, 1);
				cursor.add(orth, 1);
				WorldGenPrimitive.setBlock(world, cursor, Block.cobblestone.blockID);
				cursor.add(dir, 1);
				WorldGenPrimitive.setBlock(world, cursor, Block.cobblestone.blockID);
				cursor.add(orth, 1);
				WorldGenPrimitive.setBlock(world, cursor, Block.cobblestone.blockID);
				cursor.add(Cardinal.UP, 1);
				WorldGenPrimitive.setBlock(world, cursor, Block.cobblestone.blockID);
				cursor.add(Cardinal.UP, 3);
				WorldGenPrimitive.setBlock(world, cursor, Block.waterMoving.blockID);
			}
		}
		
		ITreasureChest chest = new TreasureChestEmpty();
		chest.generate(world, rand, x, y - 1, z);
		int middle = chest.getInventorySize() / 2;
		chest.setInventorySlot(ItemNovelty.getItem(ItemNovelty.AVIDYA), middle);
		
		return true;
	}

	@Override
	public int getSize() {
		return 10;
	}
	
	private static void pillarTop(World world, Coord cursor){
		MetaBlock step = new MetaBlock(Block.stairsNetherQuartz.blockID);
		for(Cardinal dir : Cardinal.directions){
			step.setMeta(WorldGenPrimitive.blockOrientation(dir, true));
			cursor.add(dir, 1);
			WorldGenPrimitive.setBlock(world, cursor, step, true, false);
			cursor.add(Cardinal.reverse(dir), 1);
		}
	}

}