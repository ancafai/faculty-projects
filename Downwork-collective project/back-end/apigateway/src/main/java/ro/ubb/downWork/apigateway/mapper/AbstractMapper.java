package ro.ubb.downWork.apigateway.mapper;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by CristianCosmin on 25.10.2017.
 */
public abstract class AbstractMapper<Model, Dto> {

    public abstract Model toInternal(Dto dto);

    public abstract Dto toExternal(Model model);

    public Set<Model> toInternals(Set<Dto> dtos) {
        Set<Model> models = dtos.stream().map(dto -> toInternal(dto)).collect(Collectors.toSet());
        return models;
    }

    public Set<Dto> toExternals(Set<Model> models) {
        //        Set<Dto> dtos = models.stream().map(model -> toExternal(model)).collect(Collectors.toSet());
        Set<Dto> dtos = new LinkedHashSet<>();
        for(Model model: models){
            dtos.add(toExternal(model));
        }
        return dtos;
    }
}


