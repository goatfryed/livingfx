package de.goatfryed.livingfx.loader;

import de.goatfryed.livingfx.controller.RootAware;

public class RootAwareProcessor implements LoadPostProcessor {

    @Override
    public void process(LivingFXMLLoader loader) {
        final Object controller = loader.getController();
        if (controller instanceof RootAware) {
            ((RootAware) controller).setRoot(loader.getRoot());
        }
    }
}
