package de.goatfryed.livingfx.loader;

import javafx.fxml.FXMLLoader;
import javafx.util.BuilderFactory;
import javafx.util.Callback;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.Set;

public class LivingFXMLLoader extends FXMLLoader {
    private final Set<LoadPostProcessor> postProcessors = new HashSet<>();

    public LivingFXMLLoader() {
    }

    public LivingFXMLLoader(URL location) {
        super(location);
    }

    public LivingFXMLLoader(URL location, ResourceBundle resources) {
        super(location, resources);
    }

    public LivingFXMLLoader(URL location, ResourceBundle resources, BuilderFactory builderFactory) {
        super(location, resources, builderFactory);
    }

    public LivingFXMLLoader(URL location, ResourceBundle resources, BuilderFactory builderFactory, Callback<Class<?>, Object> controllerFactory) {
        super(location, resources, builderFactory, controllerFactory);
    }

    public LivingFXMLLoader(Charset charset) {
        super(charset);
    }

    public LivingFXMLLoader(URL location, ResourceBundle resources, BuilderFactory builderFactory, Callback<Class<?>, Object> controllerFactory, Charset charset) {
        super(location, resources, builderFactory, controllerFactory, charset);
    }

    public LivingFXMLLoader(URL location, ResourceBundle resources, BuilderFactory builderFactory, Callback<Class<?>, Object> controllerFactory, Charset charset, LinkedList<FXMLLoader> loaders) {
        super(location, resources, builderFactory, controllerFactory, charset, loaders);
    }

    /**
     * @throws FXMLLoadingException in case of an IOException at loading.
     */
    @Override
    public <T> T load() {
        try {
            final T loaded = super.load();
            onLoaded();
            return loaded;
        } catch (IOException e) {
            throw new FXMLLoadingException(e);
        }

    }

    @Override
    public <T> T load(InputStream inputStream) {
        try {
            final T load = super.load(inputStream);
            onLoaded();
            return load;
        } catch (IOException e) {
            throw new FXMLLoadingException(e);
        }
    }

    protected void onLoaded() {
        postProcessors.forEach(p -> p.process(this));
    }

    public Set<LoadPostProcessor> getPostProcessors() {
        return postProcessors;
    }

    public boolean addPostProcessor(LoadPostProcessor processor) {
        return postProcessors.add(processor);
    }
}
