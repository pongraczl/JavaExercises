package model.langElements.objectOriented;

import model.langElements.imperative.Block;

public class InitializerBlock {
    protected Block block;
    protected Modifiers modifiers = new Modifiers();

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public Modifiers getModifiers() {
        return modifiers;
    }
}
