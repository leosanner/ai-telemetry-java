package com.telai.api.agent.shared.domain;

import java.util.Arrays;

public enum Language {

    PORTUGUESE("portuguese", "pt-br"),
    ENGLISH("english", "en");

    private String language;
    private String abbreviation;

    Language(String language, String abbreviation) {
        this.language = language;
        this.abbreviation = abbreviation;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", language, abbreviation);
    }

    public static Language fromString(String language) {
        for (Language l : Language.values()) {
            if (l.language.equals(language)) {
                return l;
            }
        }

        return ENGLISH;
    }
}
