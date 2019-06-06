package de.goatfryed.livingfx.controller;

import javafx.scene.Node;

public interface RootAware {

    void setRoot(Node root);

    <T extends Node> T getRoot();
}
