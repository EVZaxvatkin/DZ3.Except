package org.example.exception;

public class FullnameException extends Exception {

    String inputString;

    public FullnameException(String inputString){
        this.inputString=inputString;
    }

    @Override
    public String getMessage() {
        return "Неправильный формат ФИО '" + inputString + "'. ФИО могут состоять только из букв.\n";
    }
}
