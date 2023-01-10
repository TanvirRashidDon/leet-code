package design.pattern.mapper.mapper;

public interface Mapper<S, T> {
    T map(S source);
}
