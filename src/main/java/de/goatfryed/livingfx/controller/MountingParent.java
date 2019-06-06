package de.goatfryed.livingfx.controller;

import javafx.scene.layout.Pane;

import java.util.function.Consumer;

abstract public class MountingParent extends BaseParent {
    public void mountChild(LivingController controller, Pane pane) {
        mountChild(
                controller,
                c -> pane.getChildren().add(c.getRoot())
        );
    }

    /**
     * @throws IllegalStateException if the controller was already mounted
     */
    @Override
    public void mountChild(LivingController controller, Consumer<LivingController> mountAction) {
        if (getManagedChildren().add(controller)) {
            mountAction.accept(controller);
            controller.didMount();
        } else {
            throw new IllegalStateException("given controller was already managed");
        }

    }

    @Override
    public void unmountChild(LivingController controller, Consumer<LivingController> unmountAction) {
        if (getManagedChildren().remove(controller)) {
            controller.unmount(unmountAction);
        }
    }
}
