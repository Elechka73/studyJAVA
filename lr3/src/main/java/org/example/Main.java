package org.example;

public class Main {
    public static void main(String[] args) {
        String[][] Array = {
                {"2", "у", "6", "8"},
                {"1", "7", "5", "е"},
                {"9", "3", "2", "."},
                {"0", "7", "1", "1"}
        };
            try {
                if (check(Array)) System.out.println("Результат " + sum);
            } catch (MyArraySizeException | MyArrayDataException e) {
                e.printStackTrace();
            }
//            catch (MyVowelsException ee){
//                ee.printStackTrace();
//            }
    }
    static int sum = 0;
    static int v = 0;
    public static boolean check(String[][] array) throws MyArraySizeException, MyArrayDataException, MyVowelsException {
        boolean tmp = true;
            for (int i = 0; i < array.length; i++) {
                if (array[i].length != 4) throw new MyArraySizeException("Ваш массив не 4x4");
                else {
                    for (int j = 0; j < array[i].length; j++) {
                        if(array[j].length != 4) throw new MyArraySizeException("Ваш массив не 4x4");
                        try {
                            if (!(array[i][j].matches("(?ui:[аеёиоуыэюя])")))
                            v = Integer.valueOf(array[i][j]);
                            if(array[i][j].length() > 1) throw new MyArrayDataException(i, j, array[i][j]);
                        } catch (NumberFormatException e) {
                            throw new MyArrayDataException(i,j, array[i][j]);
                        }
                    }
                }
            }
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    sum += Integer.valueOf(array[i][j]);
                }
                catch (NumberFormatException e) {
                    try {
                        if ((array[i][j].matches("(?ui:[аеёиоуыэюя])"))) throw new MyVowelsException(i, j, array[i][j]);
                    }
                    catch (MyVowelsException ee) {
                        ee.printStackTrace();
                        tmp = false;
                    }

                }
            }
        }

        return  tmp;
    }
}