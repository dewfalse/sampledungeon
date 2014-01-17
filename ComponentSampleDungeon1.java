package sampledungeon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

public class ComponentSampleDungeon1 extends StructureComponent {

	// 構成パーツリストを記憶するためのリスト
	public List<StructureComponent> structureComponents = new ArrayList();

	public ComponentSampleDungeon1() {}

	public ComponentSampleDungeon1(int par1, Random par2Random, int par3, int par4) {
		// 東西南北の方向をランダムに決める
		this.coordBaseMode = par2Random.nextInt(4);
		switch(this.coordBaseMode) {
		case 0:
		case 1:
		case 2:
		case 3:
		default:
			// 占有範囲を設定(このサンプルではどの方角を向いてても同じ)
			// (x,y,z) = (par3, 64, par4)の地点から4x10x4ブロックが占有範囲
			this.boundingBox = new StructureBoundingBox(par3, 64, par4, par3 + 4, 74, par4 + 4);
			break;
		}
	}
	
	@Override
	public void buildComponent(StructureComponent par1StructureComponent, List par2List, Random par3Random) {
		// 次のパーツはComponentSampleDungeon2を斜めに接続
		StructureComponent structureComponent = new ComponentSampleDungeon2(0, par3Random, this.boundingBox.maxX + 1, this.boundingBox.maxZ + 1, 0);
		((ComponentSampleDungeon1)par1StructureComponent).structureComponents.add(structureComponent);
		par2List.add(structureComponent);
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
		// 建設予定範囲内に液体があった場合は建設中止
		if(this.isLiquidInStructureBoundingBox(world, structureboundingbox)) {
			return false;
		}
		
		// 占有範囲(structureboundingbox)内の指定範囲を指定ブロック＆メタデータで埋める
		// 占有範囲内の指定範囲は占有範囲原点を基準として(0,0,0)-(4,10,4)の範囲
		this.fillWithMetadataBlocks(world, structureboundingbox, 0, 0, 0, 4, 10, 4, Block.stone.blockID, 0, 0, 0, false);

		// 占有範囲(structureboundingbox)内の指定範囲を空気ブロックで埋める
		// 占有範囲内の指定範囲は占有範囲原点を基準として(1,1,1)-(3,9,3)の範囲
		// 要するに中をくりぬいてるってことです
		this.fillWithAir(world, structureboundingbox, 1, 1, 1, 3, 9, 3);
		
		// 占有範囲(structureboundingbox)内の指定範囲を置き換える
		// 占有範囲内の指定範囲は占有範囲原点を基準として(0,1,2), (0,2,2), (0,3,2)の位置を空気ブロックに置き換えている
		// 入り口っぽく壁に穴を空けている。coordBaseModeでランダムな方向になってることを確認できるようにするためです
		this.placeBlockAtCurrentPosition(world, 0, 0, 0, 1, 2, structureboundingbox);
		this.placeBlockAtCurrentPosition(world, 0, 0, 0, 2, 2, structureboundingbox);
		this.placeBlockAtCurrentPosition(world, 0, 0, 0, 3, 2, structureboundingbox);
		
		return true;
	}

}
