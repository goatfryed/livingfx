package de.goatfryed.livingfx.controller;

import javafx.scene.Node;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

import static org.junit.Assert.*;

public class LivingParentControllerTest {

    @Test
    public void testNothing() {

        final boolean[] didMount = {false};
        final boolean[] willUnmountCalled = {false};
        final boolean[] didUnmount = {false};

        LivingController child = new LivingController() {
            @Override
            public void setRoot(Node root) {

            }

            @Override
            public <T extends Node> T getRoot() {
                return null;
            }

            @Override
            public void didMount() {
                didMount[0] = true;
            }

            @Override
            public void willUnmount() {
                willUnmountCalled[0] = true;
            }

            @Override
            public void didUnmount() {
                didUnmount[0] = true;
            }
        };

        LivingParentController sut = new LivingParentController() {
            @Override
            public void setRoot(Node root) {

            }

            @Override
            public <T extends Node> T getRoot() {
                return null;
            }

            @Override
            public void mountChild(LivingController controller, Consumer<LivingController> mountAction) {
                Assert.fail();
            }

            @Override
            public void unmountChild(LivingController controller, Consumer<LivingController> unmountAction) {
                Assert.fail();
            }

            @Override
            @Nonnull
            public Set<LivingController> getManagedChildren() {
                final HashSet<LivingController> children = new HashSet<>();
                children.add(child);
                return children;
            }
        };

        sut.didMount();
        sut.unmount(null);

        Assert.assertTrue(didMount[0]);
        Assert.assertTrue(willUnmountCalled[0]);
        Assert.assertTrue(didUnmount[0]);
    }
}