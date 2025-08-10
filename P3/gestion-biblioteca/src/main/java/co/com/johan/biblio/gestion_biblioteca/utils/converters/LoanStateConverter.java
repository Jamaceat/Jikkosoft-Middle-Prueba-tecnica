package co.com.johan.biblio.gestion_biblioteca.utils.converters;

import co.com.johan.biblio.gestion_biblioteca.loans.dtos.LoanStateEnum;
import jakarta.persistence.AttributeConverter;

public class LoanStateConverter implements AttributeConverter<LoanStateEnum, String> {

    @Override
    public String convertToDatabaseColumn(LoanStateEnum attribute) {

        if (attribute == null) {
            return null;
        }
        return attribute.getState();

    }

    @Override
    public LoanStateEnum convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        for (LoanStateEnum state : LoanStateEnum.values()) {
            if (state.getState().equals(dbData)) {
                return state;
            }
        }
        // Lanza una excepción si el valor de la BD no corresponde a ningún enum
        throw new IllegalArgumentException("Valor desconocido en la base de datos: " + dbData);
    }
}
