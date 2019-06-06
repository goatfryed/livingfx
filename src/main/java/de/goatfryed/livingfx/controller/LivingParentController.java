package de.goatfryed.livingfx.controller;

import javax.annotation.Nonnull;
import java.util.Set;
import java.util.function.Consumer;

public interface LivingParentController extends LivingController {

    @Override
    default void didMount() {
        getManagedChildren().forEach(LivingController::didMount);
    }

    @Override
    default void willUnmount() {
        getManagedChildren().forEach(LivingController::willUnmount);
    }

    @Override
    default void didUnmount() {
        getManagedChildren().forEach(LivingController::didUnmount);
    }

    void mountChild(LivingController controller, Consumer<LivingController> mountAction);

    void unmountChild(LivingController controller, Consumer<LivingController> unmountAction);

    @Nonnull
    Set<LivingController> getManagedChildren();
}
