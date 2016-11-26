package com.daf_gruppe.telaffuz.dto;

/**
 * Created by numan_turkeri on 22.11.2016.
 */

public class Word {
    private String text;

    public Word(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
