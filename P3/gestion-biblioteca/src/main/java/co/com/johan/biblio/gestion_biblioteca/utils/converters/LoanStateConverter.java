package co.com.johan.biblio.gestion_biblioteca.utils.converters;

import java.util.stream.Stream;

import co.com.johan.biblio.gestion_biblioteca.loans.dtos.LoanStateEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class LoanStateConverter implements AttributeConverter<LoanStateEnum, String> {


    @Override
    public String convertToDatabaseColumn(LoanStateEnum loanStateEnum) {
        if (loanStateEnum == null) {
            return null;
        }
        return loanStateEnum.getState(); 
    }


    @Override
    public LoanStateEnum convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        return Stream.of(LoanStateEnum.values())
              .filter(c -> c.getState().equals(dbData))
              .findFirst()
              .orElseThrow(IllegalArgumentException::new);
    }
}
