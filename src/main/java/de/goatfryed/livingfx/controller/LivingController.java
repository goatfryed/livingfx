package de.goatfryed.livingfx.controller;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public interface LivingController extends RootAware {

    default void didMount() {

    }

    default void willUnmount() {

    }

    default void didUnmount() {

    }

    default void unmount(@Nullable Consumer<LivingController> unmountAction) {
        willUnmount();
        if (unmountAction != null) {
            unmountAction.accept(this);
        }
        didUnmount();
    }
}
