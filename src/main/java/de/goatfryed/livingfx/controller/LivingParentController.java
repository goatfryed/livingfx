package de.goatfryed.livingfx.controller;

import java.util.Set;

public interface LivingParentController extends LivingController {

    @Override
    default void didMount() {
        getManagedChilds().forEach(LivingController::didMount);
    }

    @Override
    default void willUnmount() {
        getManagedChilds().forEach(LivingController::willUnmount);
    }

    @Override
    default void didUnmount() {
        getManagedChilds().forEach(LivingController::didUnmount);
    }

    void mountChild(LivingController controller, ControllerMountAction mountAction);

    void unmountChild(LivingController controller, ControllerMountAction unmountAction);

    Set<LivingController> getManagedChilds();
}
