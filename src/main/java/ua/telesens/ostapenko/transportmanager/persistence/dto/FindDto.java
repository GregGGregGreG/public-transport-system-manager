package ua.telesens.ostapenko.transportmanager.persistence.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import ua.telesens.ostapenko.transportmanager.persistence.model.Station;

/**
 * @author root
 * @since 29.01.16
 */
@ToString
@Getter
@Setter
public class FindDto {

    @NotEmpty
    @Length(max = Station.MAX_LEN_NAME)
    private String start;

    @NotEmpty
    @Length(max = Station.MAX_LEN_NAME)
    private String end;

}
