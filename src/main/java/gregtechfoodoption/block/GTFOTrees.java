package gregtechfoodoption.block;

import gregtechfoodoption.worldgen.trees.BananaTree;

public class GTFOTrees {
    public static BananaTree BANANA_TREE;

    public static void init() {
        BANANA_TREE = new BananaTree();

        GTFOTree.TREES.forEach(GTFOTree::setupBlocks);
    }
}
