package sampledungeon;

import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureStart;

public class MapGenSampleDungeon extends MapGenStructure {

	@Override
	public String func_143025_a() {
		// 構造物名
		return "SampleDungeon";
	}

	@Override
	protected boolean canSpawnStructureAtCoords(int i, int j) {
		// ここではチャンク座標が0,0の場所に構造物を生成するものとする
		return i  == 0 && j == 0;
	}

	@Override
	protected StructureStart getStructureStart(int i, int j) {
		return new StructureSampleDungeonStart(this.worldObj, this.rand, i, j);
	}

}
