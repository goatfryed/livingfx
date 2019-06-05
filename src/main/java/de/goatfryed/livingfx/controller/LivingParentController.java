package de.goatfryed.livingfx.controller;

import javafx.scene.layout.Pane;

import java.util.Set;

public interface LivingParentController extends LivingController {

    @Override
    default void componentDidUpdate() {
        getManagedChilds().forEach(LivingController::componentDidUpdate);
    }

    @Override
    default void componentDidMount() {
        getManagedChilds().forEach(LivingController::componentDidMount);
    }

    @Override
    default void componentWillUnmount() {
        getManagedChilds().forEach(LivingController::componentWillUnmount);
    }

    @Override
    default void componentDidUnmount() {
        getManagedChilds().forEach(LivingController::componentDidUnmount);
    }

    void mountChild(LivingController controller, Pane parent);

    void unmountChild(LivingController controller);

    Set<LivingController> getManagedChilds();
}
