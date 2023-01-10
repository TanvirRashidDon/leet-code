package design.pattern.mapper.mapper;

public interface MapperRegistry {
    <S, T> void  addMapper(Class<S> sourceClass, Class<T> targetClass, Mapper<S, T> mapper);
    <S, T> Mapper<S, T> getMapper(Class<S> sourceClass, Class<T> targetClass);
}
