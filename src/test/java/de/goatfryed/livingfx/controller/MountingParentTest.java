package de.goatfryed.livingfx.controller;

import de.goatfryed.livingfx.loader.LivingFXMLLoader;
import de.goatfryed.livingfx.loader.LivingFXMLLoaderFactory;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import org.junit.Assert;
import org.junit.Test;

public class MountingParentTest {

    @Test
    public void testLifeCycle() {

        final LivingFXMLLoader parentLoader = new LivingFXMLLoaderFactory().createLoader(getClass().getResource("/fxml/parent.fxml"));
        final LivingFXMLLoader childLoader = new LivingFXMLLoaderFactory().createLoader(getClass().getResource("/fxml/child.fxml"));
        final LivingController anotherChild = new LivingController() {
            @Override
            public void setRoot(Node root) {

            }

            @Override
            public <T extends Node> T getRoot() {
                return null;
            }
        };

        final boolean[] parentMounted = {false};
        final boolean[] parentUnmounted = {false};

        final boolean[] childMounted = {false};
        final boolean[] childUnmounted = {false};

        parentLoader.getWrapped().setController(
            new TestParent() {
                @Override
                public void onComponentDidMount() {
                    parentMounted[0] = true;

                    childLoader.load();
                    Assert.assertEquals(0, getManagedChildren().size());
                    this.mountChild(childLoader.getController(), childMount);
                    this.mountChild(anotherChild, c -> {});
                    Assert.assertEquals(2, getManagedChildren().size());
                }

                @Override
                public void onComponentWillUnmount() {
                    Assert.assertEquals(2, getManagedChildren().size());

                    unmountChild(childLoader.getController(), null);
                }

                @Override
                public void onComponentDidUnmount() {
                    Assert.assertEquals(1, getManagedChildren().size());
                    parentUnmounted[0] = true;

                }
            }
        );


        childLoader.getWrapped().setController(
                new LivingController() {
                    Node root;

                    @Override
                    public <T extends Node> T getRoot() {
                        return (T) root;
                    }

                    @Override
                    public void setRoot(Node root) {
                       this.root = root;
                    }

                    @Override
                    public void didMount() {
                        childMounted[0] = true;
                    }

                    @Override
                    public void didUnmount() {
                        childUnmounted[0] = true;
                    }
                }
        );

        parentLoader.load();
        final TestParent parent = parentLoader.getController();
        parent.didMount();

        Assert.assertTrue(parentMounted[0]);
        Assert.assertTrue(childMounted[0]);

        parent.unmount(null);

        Assert.assertTrue(parentUnmounted[0]);
        Assert.assertTrue(childUnmounted[0]);
        Assert.assertEquals(1, parent.getManagedChildren().size());

    }

    public class TestParent extends MountingParent {
        public Pane childMount;
    }
}