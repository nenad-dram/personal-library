package com.endyary.perlibserver.misc;

/**
 * Enumeration of Book languages
 *
 * @author Nenad Dramicanin
 */
public enum Language {
    SERBIAN_LATIN("Serbian (Latin)"),
    SERBIAN_CYRILLIC("Serbian (Cyrillic)"),
    ENGLISH("English");

    private final String value;

    Language(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
