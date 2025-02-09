package com.pablodev.imageprocessing.services.image.tranformers;

import com.pablodev.imageprocessing.dto.transformations.Transformations;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

@Component
public class TransformerFactory {

    public List<Transformer> buildTransformerList(Transformations transformations) {
        List<Transformer> transformers = new ArrayList<>();
        addTransformerIfNotNull(transformations.getResize(), ResizeTransformer::new, transformers);
        addTransformerIfNotNull(transformations.getCrop(), CropTransformer::new, transformers);
        addTransformerIfNotNull(transformations.getRotate(), RotateTransformer::new, transformers);
        addTransformerIfNotNull(transformations.getFilters(), FiltersTransformer::new, transformers);
        addTransformerIfTrue(transformations.getMirror(), MirrorTransformer::new, transformers);
        addTransformerIfTrue(transformations.getFlip(), FlipTransformer::new, transformers);
        addTransformerIfNotNull(transformations.getWatermark(), WatermarkTransformer::new, transformers);
        return transformers;
    }

    private <T> void addTransformerIfNotNull(T value, Function<T, Transformer> constructor, List<Transformer> transformers) {
        Optional.ofNullable(value)
                .ifPresent(v -> transformers.add(constructor.apply(v)));
    }

    private void addTransformerIfTrue(Boolean condition, Supplier<Transformer> supplier, List<Transformer> transformers) {
        Optional.ofNullable(condition)
                .filter(Boolean::booleanValue)
                .ifPresent(c -> transformers.add(supplier.get()));
    }

}
