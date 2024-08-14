package br.com.alura.model;

public enum Tipo {
    SOLO("solo"),
    DUPLA("dupla"),
    BANDA("banda");

    private String tipoArtista;

    Tipo(String tipoArtista){
        this.tipoArtista = tipoArtista;
    }

    public static Tipo fromString(String text) {
        for (Tipo categoria : Tipo.values()) {
            if (categoria.tipoArtista.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhum tipo encontrado para a string fornecida: " + text);
    }
}
