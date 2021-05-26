package com.robertx22.mine_and_slash.potion_effects.bases;

public interface IOneOfATypePotion {

    public enum Type {
        DIVINE_BUFF,
        HUNTING_BUFF,
        NATURE_CURSE,
        FIRE_BUFF,
        STORM_BUFF,
        HUNTING_POTION
    }

    public Type getOneOfATypeType();
}
