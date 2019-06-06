package de.goatfryed.livingfx.loader;

import java.net.URL;
import java.util.LinkedHashSet;
import java.util.Set;

public class LivingFXMLLoaderFactory {

    private final Set<LoaderPostProcessor> postProcessors = new LinkedHashSet<>();

    public LivingFXMLLoaderFactory()
    {
        addPostProcessor(p -> p.addPostProcessor(new RootAwareProcessor()));
    }

    public Set<LoaderPostProcessor> getPostProcessors() {
        return postProcessors;
    }

    public boolean addPostProcessor(LoaderPostProcessor processor) {
        return postProcessors.add(processor);
    }


    public LivingFXMLLoader createLoader(URL location) {
        LivingFXMLLoader loader = createLoader();
        loader.setLocation(location);
        return loader;
    }

    private LivingFXMLLoader createLoader() {
        final LivingFXMLLoader loader = new LivingFXMLLoader();
        postProcessors.forEach(p -> p.process(loader));
        return loader;
    }
}
