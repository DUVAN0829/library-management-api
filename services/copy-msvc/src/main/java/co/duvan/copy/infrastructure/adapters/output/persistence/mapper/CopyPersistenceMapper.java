package co.duvan.copy.infrastructure.adapters.output.persistence.mapper;

import co.duvan.copy.domain.model.Copy;
import co.duvan.copy.infrastructure.adapters.output.persistence.model.CopyEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CopyPersistenceMapper {

    Copy toCopy(CopyEntity copyEntity);

    CopyEntity toCopyEntity(Copy copy);

    List<Copy> toCopyList(List<CopyEntity> copyEntityList);

}
