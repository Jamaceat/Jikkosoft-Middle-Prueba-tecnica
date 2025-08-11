package co.com.johan.biblio.gestion_biblioteca.loans.dtos;

public enum LoanStateEnum {
    TAKEN("Prestado"),
    RETURNED("Devuelto"),
    LATE("Atrasado");

    private String state;

    private LoanStateEnum(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
