package id.cloowns.armoryrpg;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.resources.Identifier;

public class ArmoryRpg implements ModInitializer {

    public static final String MOD_ID = "armory_rpg";

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }

    @Override
    public void onInitialize() {

        PayloadTypeRegistry.serverboundPlay().register(
            ActivateSkillPayload.TYPE,
            ActivateSkillPayload.CODEC
        );

        ModItems.initialize();
        SkillHandler.initialize();
    }
}