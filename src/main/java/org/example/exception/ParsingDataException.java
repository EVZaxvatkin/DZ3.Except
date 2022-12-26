package org.example.exception;

public class ParsingDataException extends Exception{
    String messege;

    public ParsingDataException(String messege){
        this.messege=messege;
    }

    @Override
    public String getMessage() {
        return this.messege;
    }
}
