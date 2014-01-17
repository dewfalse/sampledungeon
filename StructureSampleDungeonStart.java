package sampledungeon;

import java.util.List;
import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;

public class StructureSampleDungeonStart extends StructureStart {

	public StructureSampleDungeonStart() {}
	
	public StructureSampleDungeonStart(World par1World, Random par2Random, int par3, int par4) {
		super(par3, par4);
		
		// 構造物の構成パーツを決定する
		// 基点はComponentSampleDungeon1
		ComponentSampleDungeon1 componentSampleDungeon1 = new ComponentSampleDungeon1(0, par2Random, (par3 << 4) + 2, (par4 << 4) + 2);
		this.components.add(componentSampleDungeon1);
		
		// 次のパーツを得る
		componentSampleDungeon1.buildComponent(componentSampleDungeon1, components, par2Random);
		
		// 次のパーツが決定していないパーツは一時的にstructureComponentsに保持されるので、空になるまで次のパーツの決定を続ける
		List<StructureComponent> list = componentSampleDungeon1.structureComponents;
		while(!list.isEmpty()) {
            int k = par2Random.nextInt(list.size());
            StructureComponent structurecomponent = list.remove(k);
            structurecomponent.buildComponent(componentSampleDungeon1, this.components, par2Random);
		}

		// 構造物全体の占有範囲を更新する
		this.updateBoundingBox();
	}
}
