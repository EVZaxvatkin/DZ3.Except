package org.example.presentation;

import org.example.exception.ParsingDataException;
import org.example.model.Data;
import org.example.view.View;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

    public class Presentation<V extends View> {

        private Data model;
        private V view;

        public Presentation(V v) {
            view = v;
        }

        public void start() {
            boolean wokring = true;
            do {
                String input = view.getInput(
                        "Введите данные через пробел (Фамилию Имя Отчество ДатуРождения НомерТелефона Пол), или Exit для прекращения программы:");
                if (input.equals("Exit")) {
                    wokring = false;
                    break;
                } else {

                    String[] splitedInput = input.replaceAll("\\s+", " ").split(" ");

                    int inputDataCount = checkInputDataCount(splitedInput.length);
                    if (inputDataCount == -1) {
                        view.printOutput("Слишком мало данных на вводе (должно быть " + Data.dataCount
                                + " разделённых пробелом ' ': Фамилия Имя Отчество НомерТелефона ДатаРождения Пол)\n");
                    } else if (inputDataCount == 0) {
                        view.printOutput("Слишком много данных на вводе (должно быть " + Data.dataCount
                                + " разделённых пробелом ' ': Фамилия Имя Отчество НомерТелефона ДатаРождения Пол)\n");
                    } else {
                        try {
                            model = new Data();
                            model.CheckData(splitedInput);
                            writePersonData(model);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ParsingDataException e) {
                            view.printOutput(e.getMessage());
                        }
                    }
                }
            } while (wokring);
        }


        private int checkInputDataCount(int inputDataCount) {
            if (inputDataCount < Data.dataCount) {
                return -1;
            } else if (inputDataCount > Data.dataCount) {
                return 0;
            } else {
                return inputDataCount;
            }
        }


        private void writePersonData(Data data) throws IOException {
            File filepath = new File(data.getLastName());
            try (FileWriter fw = new FileWriter(filepath, true)) {
                fw.append(data.toString() + "\n");
            } catch (IOException e) {
                throw e;
            }
        }
    }

