
package id.cloowns.armoryrpg;

import java.util.Map;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

public class ArmorMaterialsRpg {

    public static final int BASE_DURABILITY = 35;

    private static ArmorMaterial createMaterial(
        String name,
        int helmet,
        int chestplate,
        int leggings,
        int boots
    ) {
        ResourceKey<EquipmentAsset> assetKey =
            ResourceKey.create(
                EquipmentAssets.ROOT_ID,
                ArmoryRpg.id(name)
            );

        TagKey<Item> repairTag =
            TagKey.create(
                BuiltInRegistries.ITEM.key(),
                ArmoryRpg.id("repairs_" + name)
            );

        return new ArmorMaterial(
            BASE_DURABILITY,
            Map.of(
                ArmorType.HELMET, helmet,
                ArmorType.CHESTPLATE, chestplate,
                ArmorType.LEGGINGS, leggings,
                ArmorType.BOOTS, boots
            ),
            15,
            SoundEvents.ARMOR_EQUIP_DIAMOND,
            2.0F,
            0.1F,
            repairTag,
            assetKey
        );
    }

    public static final ArmorMaterial GUARDIAN =
        createMaterial("guardian", 4, 9, 7, 4);

    public static final ArmorMaterial INFERNO =
        createMaterial("inferno", 3, 8, 6, 3);

    public static final ArmorMaterial GLACIER =
        createMaterial("glacier", 4, 8, 6, 3);

    public static final ArmorMaterial TEMPEST =
        createMaterial("tempest", 3, 7, 5, 3);
}
