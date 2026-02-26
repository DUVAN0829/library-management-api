package co.duvan.loan.application.ports.output;

import co.duvan.loan.application.ports.output.dto.CopyClientResponse;

public interface CopyClientPort {

    CopyClientResponse findCopyById(Long copyId, String token);

}
