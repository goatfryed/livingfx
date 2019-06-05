package de.goatfryed.livingfx.controller;

import javafx.scene.layout.Pane;

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
    public void mountChild(LivingController controller, ControllerMountAction mountAction) {
        if (getManagedChilds().add(controller)) {
            mountAction.apply(controller);
            controller.didMount();
        } else {
            throw new IllegalStateException("given controller was already managed");
        }

    }

    @Override
    public void unmountChild(LivingController controller, ControllerMountAction unmountAction) {
        if (getManagedChilds().remove(controller)) {
            controller.willUnmount();
            unmountAction.apply(controller);
            controller.didUnmount();
        }
    }
}
