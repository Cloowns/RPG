
package id.cloowns.armoryrpg;

import java.util.function.Function;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

public class ModItems {

    private static ResourceKey<Item> createKey(String name) {
        return ResourceKey.create(
            Registries.ITEM,
            ArmoryRpg.id(name)
        );
    }

    private static Item register(
        String name,
        Function<Item.Properties, Item> factory,
        Item.Properties properties
    ) {
        ResourceKey<Item> key = createKey(name);

        Item item = factory.apply(properties.setId(key));

        return Registry.register(
            BuiltInRegistries.ITEM,
            key,
            item
        );
    }

    private static Item registerArmor(
        String name,
        ArmorMaterial material,
        ArmorType type
    ) {
        return register(
            name,
            Item::new,
            new Item.Properties()
                .humanoidArmor(material, type)
                .durability(
                    type.getDurability(
                        ArmorMaterialsRpg.BASE_DURABILITY
                    )
                )
        );
    }

    // =================================
    // GUARDIAN ARMOR
    // =================================

    public static final Item GUARDIAN_HELMET =
        registerArmor("guardian_helmet", ArmorMaterialsRpg.GUARDIAN, ArmorType.HELMET);

    public static final Item GUARDIAN_CHESTPLATE =
        registerArmor("guardian_chestplate", ArmorMaterialsRpg.GUARDIAN, ArmorType.CHESTPLATE);

    public static final Item GUARDIAN_LEGGINGS =
        registerArmor("guardian_leggings", ArmorMaterialsRpg.GUARDIAN, ArmorType.LEGGINGS);

    public static final Item GUARDIAN_BOOTS =
        registerArmor("guardian_boots", ArmorMaterialsRpg.GUARDIAN, ArmorType.BOOTS);

    // =================================
    // INFERNO ARMOR
    // =================================

    public static final Item INFERNO_HELMET =
        registerArmor("inferno_helmet", ArmorMaterialsRpg.INFERNO, ArmorType.HELMET);

    public static final Item INFERNO_CHESTPLATE =
        registerArmor("inferno_chestplate", ArmorMaterialsRpg.INFERNO, ArmorType.CHESTPLATE);

    public static final Item INFERNO_LEGGINGS =
        registerArmor("inferno_leggings", ArmorMaterialsRpg.INFERNO, ArmorType.LEGGINGS);

    public static final Item INFERNO_BOOTS =
        registerArmor("inferno_boots", ArmorMaterialsRpg.INFERNO, ArmorType.BOOTS);

    // =================================
    // GLACIER ARMOR
    // =================================

    public static final Item GLACIER_HELMET =
        registerArmor("glacier_helmet", ArmorMaterialsRpg.GLACIER, ArmorType.HELMET);

    public static final Item GLACIER_CHESTPLATE =
        registerArmor("glacier_chestplate", ArmorMaterialsRpg.GLACIER, ArmorType.CHESTPLATE);

    public static final Item GLACIER_LEGGINGS =
        registerArmor("glacier_leggings", ArmorMaterialsRpg.GLACIER, ArmorType.LEGGINGS);

    public static final Item GLACIER_BOOTS =
        registerArmor("glacier_boots", ArmorMaterialsRpg.GLACIER, ArmorType.BOOTS);

    // =================================
    // TEMPEST ARMOR
    // =================================

    public static final Item TEMPEST_HELMET =
        registerArmor("tempest_helmet", ArmorMaterialsRpg.TEMPEST, ArmorType.HELMET);

    public static final Item TEMPEST_CHESTPLATE =
        registerArmor("tempest_chestplate", ArmorMaterialsRpg.TEMPEST, ArmorType.CHESTPLATE);

    public static final Item TEMPEST_LEGGINGS =
        registerArmor("tempest_leggings", ArmorMaterialsRpg.TEMPEST, ArmorType.LEGGINGS);

    public static final Item TEMPEST_BOOTS =
        registerArmor("tempest_boots", ArmorMaterialsRpg.TEMPEST, ArmorType.BOOTS);

    // =================================
    // RPG RELICS
    // =================================

    public static final Item GUARDIAN_RELIC =
        register("guardian_relic", RelicItem::new, new Item.Properties());

    public static final Item EMBER_RELIC =
        register("ember_relic", RelicItem::new, new Item.Properties());

    public static final Item FROST_RELIC =
        register("frost_relic", RelicItem::new, new Item.Properties());

    public static final Item STORM_RELIC =
        register("storm_relic", RelicItem::new, new Item.Properties());

    // =================================
    // INITIALIZE
    // =================================

    public static void initialize() {

        CreativeModeTabEvents.modifyOutputEvent(
            CreativeModeTabs.INGREDIENTS
        ).register(output -> {

            output.accept(GUARDIAN_HELMET);
            output.accept(GUARDIAN_CHESTPLATE);
            output.accept(GUARDIAN_LEGGINGS);
            output.accept(GUARDIAN_BOOTS);

            output.accept(INFERNO_HELMET);
            output.accept(INFERNO_CHESTPLATE);
            output.accept(INFERNO_LEGGINGS);
            output.accept(INFERNO_BOOTS);

            output.accept(GLACIER_HELMET);
            output.accept(GLACIER_CHESTPLATE);
            output.accept(GLACIER_LEGGINGS);
            output.accept(GLACIER_BOOTS);

            output.accept(TEMPEST_HELMET);
            output.accept(TEMPEST_CHESTPLATE);
            output.accept(TEMPEST_LEGGINGS);
            output.accept(TEMPEST_BOOTS);

            output.accept(GUARDIAN_RELIC);
            output.accept(EMBER_RELIC);
            output.accept(FROST_RELIC);
            output.accept(STORM_RELIC);
        });
    }
}