
package id.cloowns.armoryrpg;

import com.mojang.blaze3d.platform.InputConstants;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;

public class ArmoryRpgClient implements ClientModInitializer {

    private static final KeyMapping.Category CATEGORY =
        KeyMapping.Category.register(
            Identifier.fromNamespaceAndPath(
                ArmoryRpg.MOD_ID,
                "skills"
            )
        );

    private static final KeyMapping ACTIVATE_SKILL =
        KeyMappingHelper.registerKeyMapping(
            new KeyMapping(
                "key.armory_rpg.activate_skill",
                InputConstants.Type.KEYSYM,
                InputConstants.KEY_H,
                CATEGORY
            )
        );

    @Override
    public void onInitializeClient() {

        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            while (ACTIVATE_SKILL.consumeClick()) {

                if (client.player == null) {
                    continue;
                }

                ClientPlayNetworking.send(
                    new ActivateSkillPayload(1)
                );
            }
        });
    }
}
