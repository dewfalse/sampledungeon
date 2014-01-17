package sampledungeon;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

public class ComponentSampleDungeon2 extends StructureComponent {

	// 構成ブロック（羊毛）の色
	int color = 0;

	public ComponentSampleDungeon2() {}

	public ComponentSampleDungeon2(int par1, Random par2Random, int par3, int par4, int par5) {

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
		this.color = par5;
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
	public void buildComponent(StructureComponent par1StructureComponent, List par2List, Random par3Random) {
		// 構成ブロックである羊毛の色を変えながらパーツをつなげる
		if(this.color < 15) {
			StructureComponent structureComponent1 = new ComponentSampleDungeon2(0, par3Random, this.boundingBox.maxX + 1, this.boundingBox.maxZ + 1, color + 1);
			((ComponentSampleDungeon1)par1StructureComponent).structureComponents.add(structureComponent1);
			par2List.add(structureComponent1);
		}
		else {
			// 羊毛の色を変えながらパーツをつなげて、最後の色まで到達したら終端のパーツをつなげる
			// 若干ランダムっぽさを演出するためパーツ3とパーツ4をランダムに選択
			if(par3Random.nextBoolean()) {
				StructureComponent structureComponent = new ComponentSampleDungeon3(0, par3Random, this.boundingBox.minX, this.boundingBox.maxZ + 1);
				((ComponentSampleDungeon1)par1StructureComponent).structureComponents.add(structureComponent);
				par2List.add(structureComponent);
			}
			else {
				StructureComponent structureComponent = new ComponentSampleDungeon4(0, par3Random, this.boundingBox.maxX + 1, this.boundingBox.minZ);
				((ComponentSampleDungeon1)par1StructureComponent).structureComponents.add(structureComponent);
				par2List.add(structureComponent);
			}
		}
	}

	@Override
	public boolean addComponentParts(World world, Random random, StructureBoundingBox structureboundingbox) {

		if(this.isLiquidInStructureBoundingBox(world, structureboundingbox)) {
			return false;
		}
		
		// 指定の色の羊毛ブロックで範囲を埋める
		this.fillWithMetadataBlocks(world, structureboundingbox, 0, 0, 0, 4, 10, 4, Block.cloth.blockID, this.color, 0, 0, false);

		this.fillWithAir(world, structureboundingbox, 1, 1, 1, 3, 9, 3);

		this.placeBlockAtCurrentPosition(world, 0, 0, 0, 1, 2, structureboundingbox);
		this.placeBlockAtCurrentPosition(world, 0, 0, 0, 2, 2, structureboundingbox);
		this.placeBlockAtCurrentPosition(world, 0, 0, 0, 3, 2, structureboundingbox);
		
		return true;
	}

}
