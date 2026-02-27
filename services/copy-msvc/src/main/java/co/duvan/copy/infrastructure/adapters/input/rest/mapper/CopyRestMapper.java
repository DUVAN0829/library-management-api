package co.duvan.copy.infrastructure.adapters.input.rest.mapper;

import co.duvan.copy.application.ports.output.dto.CopyDetailResult;
import co.duvan.copy.domain.enums.Status;
import co.duvan.copy.domain.model.Copy;
import co.duvan.copy.infrastructure.adapters.input.rest.model.request.CopyRequest;
import co.duvan.copy.infrastructure.adapters.input.rest.model.response.CopyDetailResponse;
import co.duvan.copy.infrastructure.adapters.input.rest.model.response.CopyResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CopyRestMapper {

    Copy toCopy(CopyRequest copyRequest);

    CopyResponse toCopyResponse(Copy copy);

    List<CopyResponse> toCopyResponseList(List<Copy> copies);

    @Mapping(source = "copy.copyId", target = "copyId")
    @Mapping(source = "copy.bookId", target = "bookId")
    @Mapping(source = "copy.code", target = "code")
    @Mapping(source = "copy.status", target = "status")
    CopyDetailResponse toCopyDetailResponse(CopyDetailResult copyDetailResult);

}