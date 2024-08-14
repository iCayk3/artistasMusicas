package br.com.alura.model;

public enum Genero {
    SAMBA("samba"),
    PAGODE("pagode"),
    FORRO("forro"),
    JAZZ("jazz"),
    ROCK("rock"),
    BLUES("blues"),
    GOSPEL("gospel"),
    ELETRONICA("eletronica"),
    FUNK("funk");

    private String generoMusical;

    Genero(String generoMusical){
        this.generoMusical = generoMusical;
    }

    public static Genero fromString(String text) {
        for (Genero genero : Genero.values()) {
            if (genero.generoMusical.equalsIgnoreCase(text)) {
                return genero;
            }
        }
        throw new IllegalArgumentException("Nenhum tipo encontrado para a string fornecida: " + text);
    }
}
