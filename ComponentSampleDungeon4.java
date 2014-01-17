package sampledungeon;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

public class ComponentSampleDungeon4 extends StructureComponent {

	public ComponentSampleDungeon4() {}

	public ComponentSampleDungeon4(int par1, Random par2Random, int par3, int par4) {

		this.coordBaseMode = par2Random.nextInt(4);
		switch(this.coordBaseMode) {
		case 0:
		case 1:
		case 2:
		case 3:
		default:
			this.boundingBox = new StructureBoundingBox(par3, 64, par4, par3 + 4, 74, par4 + 4);
			break;
		}
	}

	@Override
	protected void func_143012_a(NBTTagCompound nbttagcompound) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void func_143011_b(NBTTagCompound nbttagcompound) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean addComponentParts(World world, Random random, StructureBoundingBox structureboundingbox) {

		if(this.isLiquidInStructureBoundingBox(world, structureboundingbox)) {
			return false;
		}
		
		// ダイヤブロックで範囲を埋める
		this.fillWithMetadataBlocks(world, structureboundingbox, 0, 0, 0, 4, 10, 4, Block.blockDiamond.blockID, 0, 0, 0, false);

		this.fillWithAir(world, structureboundingbox, 1, 1, 1, 3, 9, 3);

		this.placeBlockAtCurrentPosition(world, 0, 0, 0, 1, 2, structureboundingbox);
		this.placeBlockAtCurrentPosition(world, 0, 0, 0, 2, 2, structureboundingbox);
		this.placeBlockAtCurrentPosition(world, 0, 0, 0, 3, 2, structureboundingbox);
		
		return true;
	}

}
