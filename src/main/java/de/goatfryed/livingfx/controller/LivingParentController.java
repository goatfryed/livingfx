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


    <T> void mountChild(T controller, Consumer<T> mountAction);

    <T> void unmountChild(T controller, Consumer<T> unmountAction);

    @Nonnull
    Set<LivingController> getManagedChildren();
}
