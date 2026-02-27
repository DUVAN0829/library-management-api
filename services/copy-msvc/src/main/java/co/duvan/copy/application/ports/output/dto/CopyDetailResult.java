package co.duvan.copy.application.ports.output.dto;

import co.duvan.copy.domain.model.Copy;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CopyDetailResult {

    private Copy copy;
    private BookClientResponse book;

}
