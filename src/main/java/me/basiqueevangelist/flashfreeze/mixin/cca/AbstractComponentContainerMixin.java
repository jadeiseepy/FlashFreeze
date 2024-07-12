package me.basiqueevangelist.flashfreeze.mixin.cca;

import org.ladysnake.cca.internal.base.AbstractComponentContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;

@SuppressWarnings("UnstableApiUsage")
@Pseudo
@Mixin(AbstractComponentContainer.class)
public class AbstractComponentContainerMixin {
    // TODO: fix this.

    /*
    @Unique private final Map<String, NbtCompound> unknownComponents = new HashMap<>();

    @Inject(method = "fromTag", at = @At("HEAD"), remap = false)
    private void readUnknownComponentsFromList(NbtCompound tag, CallbackInfo ci) {
        unknownComponents.clear();

        if (tag.contains("cardinal_components", NbtElement.LIST_TYPE)) {
            NbtList components = tag.getList("cardinal_components", NbtElement.COMPOUND_TYPE);
            for (int i = 0; i < components.size(); i++) {
                NbtCompound componentTag = components.getCompound(i);
                String componentId = componentTag.getString("componentId");
                Identifier parsedId = Identifier.tryParse(componentId);
                if (parsedId == null || ComponentRegistry.get(parsedId) == null) {
                    NbtCompound newComponentTag = componentTag.copy();
                    newComponentTag.remove("componentId");
                    unknownComponents.put(componentId, newComponentTag);
                }
            }
        }
    }

    @Redirect(method = "fromTag", at = @At(value = "INVOKE", target = "Ldev/onyxstudios/cca/internal/base/ComponentsInternals;logDeserializationWarnings(Ljava/util/Collection;)V"))
    private void shhhhhhhhhh(Collection<String> cause) {

    }

    @Inject(method = "fromTag", at = @At(value = "INVOKE", target = "Ldev/onyxstudios/cca/internal/base/ComponentsInternals;logDeserializationWarnings(Ljava/util/Collection;)V"), locals = LocalCapture.CAPTURE_FAILHARD, remap = false)
    private void readUnknownComponents(NbtCompound tag, CallbackInfo ci, NbtCompound componentMap) {
        for (var key : componentMap.getKeys()) {
            unknownComponents.put(key, componentMap.getCompound(key));
        }
    }

    @Inject(method = "toTag", at = @At(value = "RETURN"), remap = false)
    private void addUnknownComponents(NbtCompound tag, CallbackInfoReturnable<NbtCompound> cir) {
        if (unknownComponents.isEmpty()) return;

        if (!tag.contains("cardinal_components", NbtElement.COMPOUND_TYPE)) tag.put("cardinal_components", new NbtCompound());

        NbtCompound componentsTag = tag.getCompound("cardinal_components");
        for (Map.Entry<String, NbtCompound> entry : unknownComponents.entrySet()) {
            componentsTag.put(entry.getKey(), entry.getValue());
        }
    }
    */
}
