package de.goatfryed.livingfx.controller;

import javafx.scene.layout.Pane;

import java.util.function.Consumer;

abstract public class MountingParent extends BaseParent {

    public void mountChild(RootAware controller, Pane pane) {
        mountChild(
                controller,
                c -> pane.getChildren().add(c.getRoot())
        );
    }

    /**
     * @throws IllegalStateException if the controller was already mounted
     * TODO: should we check if not-living controllers were mounted before?
     */
    @Override
    public <T> void mountChild(T controller, Consumer<T> mountAction) {
        if (controller instanceof LivingController) {
            if (getManagedChildren().add((LivingController) controller)) {
                ((LivingController) controller).didMount();
            } else {
                throw new IllegalStateException("given controller was already managed");
            }
        }

        mountAction.accept(controller);
    }

    /**
     * TODO: Should we check if not living controllers were mounted before running the unmount action?
     */
    @Override
    public <T> void unmountChild(T controller, Consumer<T> unmountAction) {

        if (controller instanceof LivingController) {
            if (getManagedChildren().remove((LivingController) controller)) {
                ((LivingController) controller).unmount((Consumer<LivingController>) unmountAction);
            }
        } else {
            unmountAction.accept(controller);
        }
    }
}
