package de.goatfryed.livingfx.controller;

public interface RootAware {

    void setRoot(Object root);

    <T> T getRoot();
}
