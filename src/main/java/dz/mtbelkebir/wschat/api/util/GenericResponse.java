package dz.mtbelkebir.wschat.api.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GenericResponse<T> {
    private boolean success;
    private T data;
    private String message;
}
