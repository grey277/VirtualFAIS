package com.grey.virtualfais.models;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Objects;

import android.support.annotation.NonNull;

@Entity(tableName = "languages")
public class Language {

    public Language(String language) {
        this.language = language;
    }

    public long getLanguageId() {
        return languageId;
    }

    public void setLanguageId( long languageId) {
        this.languageId = languageId;
    }

    @PrimaryKey(autoGenerate = true)
    private long languageId;

    private String language;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Language language1 = (Language) o;
        return Objects.equals(languageId, language1.languageId) &&
                Objects.equals(language, language1.language);
    }

    @Override
    public int hashCode() {

        return Objects.hash(languageId, language);
    }
}
