package co.com.johan.biblio.gestion_biblioteca.books.dtos.request;

public record RegisterBookR(String title, String author,String releaseYear,String genre,Long totalAmount,Long branchId) {
    
}
