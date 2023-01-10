package design.pattern.mapper.mapper.impl;

import design.pattern.mapper.mapper.Mapper;
import design.pattern.mapper.mapper.MapperRegistry;
import design.pattern.mapper.mapper.excepton.MapperNotFoundException;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MapperRegistryImpl implements MapperRegistry {

    private final Map<MapperKey<?, ?>, Mapper<?, ?>> mapperMap;

    public MapperRegistryImpl() {
        mapperMap = new HashMap<>();
    }

    @Override
    public <S, T> void addMapper(Class<S> sourceClass, Class<T> targetClass, Mapper<S, T> mapper) {
        mapperMap.put(MapperKey.of(sourceClass, targetClass), mapper);
    }

    @Override
    public <S, T> Mapper<S, T> getMapper(Class<S> sourceClass, Class<T> targetClass) {
        MapperKey<S, T> mapperKey = MapperKey.of(sourceClass, targetClass);
        if (!mapperMap.containsKey(mapperKey))
            throw new MapperNotFoundException(sourceClass, targetClass);

        return (Mapper<S, T>) mapperMap.get(mapperKey);
    }

    @EqualsAndHashCode
    private static class MapperKey<S, T> {
        private final Class<S> sourceClass;
        private final Class<T> targetClass;

        private MapperKey(Class<S> sourceClass, Class<T> targetClass) {
            this.sourceClass = sourceClass;
            this.targetClass = targetClass;
        }

        public static <S, T> MapperKey<S, T> of(Class<S> sourceClass,Class<T> targetClass) {
            return new MapperKey<>(sourceClass, targetClass);
        }
    }
}
