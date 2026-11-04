
package id.cloowns.armoryrpg;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class SkillHandler {

    private static final Map<UUID, Map<Integer, Long>> COOLDOWNS =
        new HashMap<>();

    public static void initialize() {

        ServerPlayNetworking.registerGlobalReceiver(
            ActivateSkillPayload.TYPE,
            (payload, context) -> {
                activateSkill(context.player(), payload.skillId());
            }
        );
    }

    private static void activateSkill(ServerPlayer player, int skillId) {

        ServerLevel level = player.serverLevel();

        long currentTime = level.getGameTime();

        int cooldown = getCooldown(skillId);

        if (cooldown == 0) {
            return;
        }

        Map<Integer, Long> playerCooldowns =
            COOLDOWNS.computeIfAbsent(
                player.getUUID(),
                uuid -> new HashMap<>()
            );

        long nextUse = playerCooldowns.getOrDefault(skillId, 0L);

        if (currentTime < nextUse) {
            return;
        }

        switch (skillId) {

            case 1 -> {
                // Guardian: temporary protection
                player.addEffect(
                    new MobEffectInstance(
                        MobEffects.RESISTANCE, 100, 1
                    )
                );

                level.sendParticles(
                    ParticleTypes.ENCHANT,
                    player.getX(), player.getY() + 1, player.getZ(),
                    35, 0.8, 0.8, 0.8, 0.5
                );
            }

            case 2 -> {
                // Ember: temporary strength
                player.addEffect(
                    new MobEffectInstance(
                        MobEffects.STRENGTH, 160, 1
                    )
                );

                level.sendParticles(
                    ParticleTypes.FLAME,
                    player.getX(), player.getY() + 1, player.getZ(),
                    30, 0.7, 0.8, 0.7, 0.05
                );
            }

            case 3 -> {
                // Frost: temporary slow falling
                player.addEffect(
                    new MobEffectInstance(
                        MobEffects.SLOW_FALLING, 160, 0
                    )
                );

                level.sendParticles(
                    ParticleTypes.SNOWFLAKE,
                    player.getX(), player.getY() + 1, player.getZ(),
                    35, 0.8, 0.8, 0.8, 0.1
                );
            }

            case 4 -> {
                // Storm: speed and jump boost
                player.addEffect(
                    new MobEffectInstance(
                        MobEffects.SPEED, 160, 1
                    )
                );

                player.addEffect(
                    new MobEffectInstance(
                        MobEffects.JUMP_BOOST, 160, 1
                    )
                );

                level.sendParticles(
                    ParticleTypes.ELECTRIC_SPARK,
                    player.getX(), player.getY() + 1, player.getZ(),
                    35, 0.8, 0.8, 0.8, 0.1
                );
            }

            default -> {
                return;
            }
        }

        playerCooldowns.put(skillId, currentTime + cooldown);

        player.sendSystemMessage(
            Component.literal("RPG Skill Activated!")
        );
    }

    private static int getCooldown(int skillId) {

        return switch (skillId) {
            case 1 -> 20 * 30;
            case 2 -> 20 * 25;
            case 3 -> 20 * 20;
            case 4 -> 20 * 15;
            default -> 0;
        };
    }
}
