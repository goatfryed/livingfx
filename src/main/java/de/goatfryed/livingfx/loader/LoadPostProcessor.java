package de.goatfryed.livingfx.loader;

@FunctionalInterface
public interface LoadPostProcessor {
    void process(LivingFXMLLoader loader);
}
