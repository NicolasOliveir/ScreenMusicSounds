package br.com.nicolas.soundMusic.models;

public enum Tipo {
    SOLO("solo"),
    DUPLA("dupla"),
    BANDA("banda");

    private final String tipoArtista;

    Tipo(String tipo) {
        this.tipoArtista = tipo;
    }

    public static Tipo fromString(String text) {
        for (Tipo categoria : Tipo.values()) {
            if (categoria.tipoArtista.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhum tipo compativel com: " + text);
    }
}
