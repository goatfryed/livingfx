package de.goatfryed.livingfx.controller;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.LinkedHashSet;
import java.util.Set;

public abstract class BaseParent<RootType extends Node> implements LivingParentController {

    Set<LivingController> managedChilds = new LinkedHashSet<>();

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
        getManagedChilds().forEach(LivingController::didMount);
    }

    public void onComponentDidMount() {

    }

    @Override
    final public void willUnmount() {
        onComponentWillUnmount();
        getManagedChilds().forEach(LivingController::willUnmount);
    }

    public void onComponentWillUnmount() {
    }

    @Override
    final public void didUnmount() {
        getManagedChilds().forEach(LivingController::didUnmount);
        onComponentDidUnmount();
    }

    public void onComponentDidUnmount() {

    }

    @Override
    public Set<LivingController> getManagedChilds() {
        return managedChilds;
    }
}
