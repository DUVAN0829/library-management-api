package co.duvan.loan.infrastructure.adapters.output;

import co.duvan.loan.application.ports.output.CopyClientPort;
import co.duvan.loan.application.ports.output.dto.CopyClientResponse;
import co.duvan.loan.infrastructure.adapters.output.client.CopyFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CopyClientAdapter implements CopyClientPort {

    private final CopyFeignClient copyFeignClient;

    @Override
    public CopyClientResponse findCopyById(Long copyId, String token) {
        return copyFeignClient.findById(copyId);
    }

}