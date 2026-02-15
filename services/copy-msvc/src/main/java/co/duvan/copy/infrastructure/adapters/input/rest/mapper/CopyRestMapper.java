package co.duvan.copy.infrastructure.adapters.input.rest.mapper;

import co.duvan.copy.domain.model.Copy;
import co.duvan.copy.infrastructure.adapters.input.rest.model.request.CopyRequest;
import co.duvan.copy.infrastructure.adapters.input.rest.model.response.CopyResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CopyRestMapper {

    Copy toCopy(CopyRequest copyRequest);

    CopyResponse toCopyResponse(Copy copy);

    List<CopyResponse> toCopyResponseList(List<Copy> copies);

}
