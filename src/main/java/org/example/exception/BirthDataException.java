package org.example.exception;

public class BirthDataException extends Exception {

    String inputString;

    public BirthDataException(String inputString) {
        this.inputString = inputString;
    }

    @Override
    public String getMessage() {
        return "Ошибка при вводе даты '" + inputString + "', требуем формат 'дд.мм.гггг'.\n";
    }
}
