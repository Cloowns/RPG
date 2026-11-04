
package id.cloowns.armoryrpg;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record ActivateSkillPayload(int skillId)
        implements CustomPacketPayload {

    public static final Type<ActivateSkillPayload> TYPE =
        new Type<>(ArmoryRpg.id("activate_skill"));

    public static final StreamCodec<RegistryFriendlyByteBuf, ActivateSkillPayload> CODEC =
        StreamCodec.of(
            (buf, payload) -> buf.writeVarInt(payload.skillId()),
            buf -> new ActivateSkillPayload(buf.readVarInt())
        );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}