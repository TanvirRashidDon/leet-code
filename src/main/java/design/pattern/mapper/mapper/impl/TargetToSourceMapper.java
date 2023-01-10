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
public class TargetToSourceMapper implements Mapper<Target, Source> {

    @NonNull
    private final MapperRegistry mapperRegistry;

    @PostConstruct
    public void registerMap() {
        mapperRegistry.addMapper(Target.class, Source.class, this);
    }

    @Override
    public Source map(Target target) {
        return Source.builder().value((int)target.getValue()).build();
    }
}
