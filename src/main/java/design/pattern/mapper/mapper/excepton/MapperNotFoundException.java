package design.pattern.mapper.mapper.excepton;

public class MapperNotFoundException extends RuntimeException{
    public MapperNotFoundException() {
        super("Mapper not found");
    }

    public <S, T> MapperNotFoundException(Class<S> sourceClass, Class<T> targetClass) {
        super("Mapper not found for type: " + sourceClass.getSimpleName() + " to: " + targetClass.getSimpleName());
    }
}
