package numble.shorturl.domain.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import numble.shorturl.domain.Status;

import java.util.Objects;

@Converter
public class StatusConverter implements AttributeConverter<Status,String> {

    @Override
    public String convertToDatabaseColumn(Status status) {
        if (Objects.isNull(status)) {
            throw new NullPointerException("Enum Converting String - Status is null");
        }

        return status.toString();
    }

    @Override
    public Status convertToEntityAttribute(String dbData) {
        return Status.valueOf(dbData);
    }
}
