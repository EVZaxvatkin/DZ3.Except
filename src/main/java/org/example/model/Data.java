package org.example.model;

import org.example.exception.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Data {

    public static int dataCount = 6;

    private String firstName;
    private String lastName;
    private String patronymic;
    private LocalDate birthDay;
    private Long phoneNumber;
    private Gender gender;

    public Data(){
    }

    public void CheckData(String[] splitedString) throws ParsingDataException {
        if (splitedString == null) {
            throw new NullPointerException("Нет данных");
        }

        StringBuilder fullErrorsMessages = new StringBuilder();
        for (String string : splitedString) {
            if (Character.isLetter(string.charAt(0))) {
                if (string.length() == 1) {
                    if (this.gender == null) {
                        try {
                            this.gender = checkGender(string);
                        } catch (GenderException e) {
                            fullErrorsMessages.append(e.getMessage());
                        }
                    } else {
                        fullErrorsMessages.append("Гендер указан больше 1 раза\n");
                    }
                } else {
                    if (this.lastName == null) {
                        try {
                            this.lastName = checkFullname(string);
                        } catch (FullnameException e) {
                            fullErrorsMessages.append(e.getMessage());
                        }
                    } else if (this.firstName == null) {
                        try {
                            this.firstName = checkFullname(string);
                        } catch (FullnameException e) {
                            fullErrorsMessages.append(e.getMessage());
                        }
                    } else if (this.patronymic == null) {
                        try {
                            this.patronymic = checkFullname(string);
                        } catch (FullnameException e) {
                            fullErrorsMessages.append(e.getMessage());
                        }
                    } else {
                        fullErrorsMessages.append("Слишком много элементов распознаны как ФИО.\n");
                    }
                }
            } else {

                if (string.matches("[0-9]{2}\\.[0-9]{2}\\.[0-9]{4}")) {
                    if (this.birthDay == null) {
                        try {
                            this.birthDay = checkBirthDate(string);
                        } catch (BirthDataException e) {
                            fullErrorsMessages.append(e.getMessage());
                        }
                    } else {
                        fullErrorsMessages.append("Дата рождения должна быть только одна, а обнаружены две: '"
                                + this.birthDay + "','" + string + "'\n");
                    }
                } else {
                    if (this.phoneNumber == null) {
                        try {
                            this.phoneNumber = checkPhoneNumber(string);
                        } catch (PhoneException e) {
                            fullErrorsMessages.append(e.getMessage());
                        }
                    } else {
                        fullErrorsMessages.append("Должен быть только один телефонный норме, а не несколько: '"
                                + this.phoneNumber + "','" + string + "'\n");
                    }
                }

            }
        }
        if (!fullErrorsMessages.isEmpty()) {
            throw new ParsingDataException(fullErrorsMessages.toString());
        }
    }

    public String getLastName() {
        return lastName;
    }

    private String checkFullname(String inputString) throws FullnameException {
        if (inputString.toLowerCase().matches("^[a-zа-яё]*$")) {
            return inputString;
        } else {
            throw new FullnameException(inputString);
        }
    }

    private long checkPhoneNumber(String inpuString) throws PhoneException {
        if (inpuString.length() == 10) {
            try {
                return Long.parseLong(inpuString);
            } catch (NumberFormatException e) {
                throw new PhoneException(inpuString);
            }
        } else {
            throw new PhoneException(inpuString);
        }
    }

    private Gender checkGender(String inputString) throws GenderException {
        try {
            return Gender.valueOf(inputString);
        } catch (IllegalArgumentException e) {
            throw new GenderException(inputString);
        }
    }

    private LocalDate checkBirthDate(String inputString) throws BirthDataException {
        try {
            return LocalDate.parse(inputString,
                    DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        } catch (DateTimeParseException e) {
            throw new BirthDataException(inputString);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<").append(lastName).append(">")
                .append("<").append(firstName).append(">")
                .append("<").append(patronymic).append(">")
                .append("<").append(birthDay.toString()).append(">")
                .append("<").append(phoneNumber).append(">")
                .append("<").append(gender).append(">");
        return sb.toString();
    }





}
