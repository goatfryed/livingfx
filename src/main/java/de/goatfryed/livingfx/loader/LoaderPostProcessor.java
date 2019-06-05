package de.goatfryed.livingfx.loader;

@FunctionalInterface
public interface LoaderPostProcessor {
    void process(LivingFXMLLoader loader);
}
