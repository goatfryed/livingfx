package de.goatfryed.livingfx.loader;

import java.util.LinkedHashSet;
import java.util.Set;

public class LivingFXMLLoaderFactory {

    private final Set<LoaderPostProcessor> postProcessors = new LinkedHashSet<>();

    public LivingFXMLLoaderFactory()
    {

    }

    public Set<LoaderPostProcessor> getPostProcessors() {
        return postProcessors;
    }

    public boolean addPostProcessor(LoaderPostProcessor processor) {
        return postProcessors.add(processor);
    }
}
