package ua.telesens.ostapenko.transportmanager.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author root
 * @since 27.01.16
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ValidationResponse {

    private Boolean status;
    private String error;

    public ValidationResponse(Boolean status) {
        this.status = status;
    }

}
