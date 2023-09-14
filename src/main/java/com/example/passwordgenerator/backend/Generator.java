package com.example.passwordgenerator.backend;

public class Generator {

    private String numbers = "1234567890";
    private String lettersUP = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String lettersLOW = "abcdefghijklmnopqrstuvwxyz";
    private String symbols = "?!<>-*[]{}/%&$_";
    public Generator() {
    }

    public String numeric(int lunghezzapsw) {
        String character = numbers;
        int characterLength = character.length();
        String password = "";
        for (int i = 0; i < lunghezzapsw; i++) {
            int randomIndexCharInNumeri = (int) (Math.random() * characterLength);
            password += character.charAt(randomIndexCharInNumeri);
        }
        return password;

    }

    public String text(int lunghezzapsw) {
        String character = lettersUP + lettersLOW;
        int characterLength = character.length();
        String password = "";
        for (int i = 0; i < lunghezzapsw; i++) {
            int randomIndexCharInLettere = (int) (Math.random() * characterLength);
            password += character.charAt(randomIndexCharInLettere);
        }
        return password;
    }

    public String alphanumeric(int lunghezzapsw) {
        String character = numbers + lettersUP + lettersLOW;
        int characterLength = character.length();
        String password = "";
        for (int i = 0; i < lunghezzapsw; i++) {
            int randomIndexCharInNumLett = (int) (Math.random() * characterLength);
            password += character.charAt(randomIndexCharInNumLett);
        }
        return password;
    }

    public String all(int lunghezzapsw){
        String character = numbers + lettersUP + lettersLOW + symbols;
        int characterLength = character.length();
        String password = "";
        for (int i = 0; i < lunghezzapsw; i++) {
            int randomIndexCharInCompleta = (int)(Math.random()*characterLength);
            password += character.charAt(randomIndexCharInCompleta);
        }
        return password;

    }
}

