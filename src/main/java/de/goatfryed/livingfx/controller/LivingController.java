package de.goatfryed.livingfx.controller;

public interface LivingController extends RootAware {

    default boolean shouldComponentUpdate() {
        return true;
    }

    default void componentDidUpdate() {
    }

    default void componentDidMount() {

    }

    default void componentWillUnmount() {

    }

    default void componentDidUnmount() {

    }
}
