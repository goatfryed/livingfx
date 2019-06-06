package de.goatfryed.livingfx.controller;

import javafx.scene.Node;

import java.util.LinkedHashSet;
import java.util.Set;

public abstract class BaseParent<RootType extends Node> implements LivingParentController {

    Set<LivingController> managedChildren = new LinkedHashSet<>();

    RootType root;

    @Override
    public void setRoot(Node root) {
        this.root = (RootType) root;
    }

    @Override
    public <T extends Node> T getRoot() {
        return (T) root;
    }

    @Override
    final public void didMount() {
        onComponentDidMount();
        getManagedChildren().forEach(LivingController::didMount);
    }

    public void onComponentDidMount() {

    }

    @Override
    final public void willUnmount() {
        getManagedChildren().forEach(LivingController::willUnmount);
        onComponentWillUnmount();
    }

    public void onComponentWillUnmount() {
    }

    @Override
    final public void didUnmount() {
        onComponentDidUnmount();
        getManagedChildren().forEach(LivingController::didUnmount);
    }

    public void onComponentDidUnmount() {

    }

    @Override
    public Set<LivingController> getManagedChildren() {
        return managedChildren;
    }
}
