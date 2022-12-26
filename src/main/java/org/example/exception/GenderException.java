package org.example.exception;

public class GenderException extends  Exception{
    String inputString;

    public GenderException(String inputString) {
        this.inputString = inputString;
    }

    @Override
    public String getMessage() {
        return "Неправильно указан пол (используйте только латинские буквы f или m), а не '" + inputString + "'\n";
    }
}
