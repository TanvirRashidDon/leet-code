package design.pattern.mapper.mapper;

import design.pattern.mapper.mapper.object.Source;
import design.pattern.mapper.mapper.object.Target;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ApiObjectMapper {
    @NonNull
    private final MapperRegistry mapperRegistry;

    public Target mapSource(Source source) {
        Mapper<Source, Target> mapper = mapperRegistry.getMapper(Source.class, Target.class);
        return mapper.map(source);
    }

    public Source mapTarget(Target target) {
        Mapper<Target, Source> mapper = mapperRegistry.getMapper(Target.class, Source.class);
        return mapper.map(target);
    }
}
