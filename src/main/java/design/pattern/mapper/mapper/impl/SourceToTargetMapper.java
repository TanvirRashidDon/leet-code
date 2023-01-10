package design.pattern.mapper.mapper.impl;

import design.pattern.mapper.mapper.Mapper;
import design.pattern.mapper.mapper.MapperRegistry;
import design.pattern.mapper.mapper.object.Source;
import design.pattern.mapper.mapper.object.Target;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SourceToTargetMapper implements Mapper<Source, Target> {

    @NonNull
    private final MapperRegistry mapperRegistry;

    @PostConstruct
    public void registerMap() {
        mapperRegistry.addMapper(Source.class, Target.class, this);
    }

    @Override
    public Target map(Source source) {
        return Target.builder().value(source.getValue()).build();
    }
}
