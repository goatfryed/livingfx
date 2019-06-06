package de.goatfryed.livingfx.loader;

import javafx.fxml.FXMLLoader;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class LivingFXMLLoader {
    private final Set<LoadPostProcessor> postProcessors = new LinkedHashSet<>();
    private final FXMLLoader wrapped;

    public LivingFXMLLoader() {
        wrapped = new FXMLLoader();
    }

    public FXMLLoader getWrapped() {
        return wrapped;
    }

    /**
     * @throws FXMLLoadingException in case of an IOException at loading.
     */
    public <T> T load() {
        try {
            final T loaded = wrapped.load();
            onLoaded();
            return loaded;
        } catch (IOException e) {
            throw new FXMLLoadingException(e);
        }

    }

    public <T> T load(InputStream inputStream) {
        try {
            final T load = wrapped.load(inputStream);
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

    public <T> T getRoot() {
        return wrapped.getRoot();
    }

    public URL getLocation() {
        return wrapped.getLocation();
    }

    public void setLocation(@Nonnull URL location) {
        Objects.requireNonNull(location, "Invalid location value given. Location must be not null");
        wrapped.setLocation(location);
    }

    public <T> T getController() {
        return wrapped.getController();
    }
}
