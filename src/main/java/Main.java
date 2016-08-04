import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args){
        List<String> resultList;
        String firstAddress = "";
        String secondAddress = "";

        // получаем от пользователя границы диапазона IP адресов
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            firstAddress = reader.readLine();
            secondAddress = reader.readLine();
        } catch (IOException e) {
            System.out.println("Произошла ошибка ввода/вывода " + e.getMessage());
        }

        // преобразуем каждый адрес в массив int, где каждый элемент - октет адреса
        int [] address1 = parseAddress(firstAddress);
        int [] address2 = parseAddress(secondAddress);
        //Long t0 = System.currentTimeMillis();

        //заполняем список адресов для заданного диапазона
        resultList = fillDiapasonArray(address1,address2);
        System.out.println();
        for(String s : resultList)
            System.out.println(s);

        //Long t1 = System.currentTimeMillis();
        //System.out.println("Time: " + (t1 - t0)/1000d + " sec.");
    }

    /**
     * Метод преобразует строку с ip адресом в массив int, где каждый элемент является октетом.
     * Если какой либо элемент превосходит максимально допустимый  - 255, метод возвращает null вместо массива
     * @param address строка содержащая ip адрес
     * @return массив int, состоящий из октетов ip адреса
     * @throws NumberFormatException если введенный адрес содержит посторонние элементы
     */

    public static int[] parseAddress(String address){
        int [] addressArray = new int[4];
        for(int index = 0; index < 4; index++){
            int nextOctet = 0;
            try{
                nextOctet = Integer.parseInt(address.split("\\.")[index]);
            }catch (NumberFormatException e){
                System.out.println("Введен некорректный IP адрес. Адрес должен состоять только из цифр и разделяющих точек");
                System.exit(1);
            }
            if(nextOctet > 255) {
                System.out.println("ip адрес "+ address +" неверен. Октет " + index + " должен быть меньше или равен 255");
                return null;
            }
            addressArray[index] = nextOctet;
        }
        return addressArray;
    }

    /**
     * Служебный метод, который проверяет, что верхняя граница диапазона IP адресов меньше нижней
     * Если обнаружится, что верхняя граница больше нижней - метод вызовет System.exit(1) и напишет в консоль об ошибке
     * @param firstAddress верхняя граница диапазона адресов
     * @param secondAddress нижняя граница диапазона адресов
     */
    private static void validateAdresses(int[] firstAddress, int[] secondAddress){
        if(firstAddress[0] > secondAddress[0]) {
            System.out.println("Первый октет первого адреса больше первого октета второго адреса");
            System.exit(1);
        }
        else {
            if(firstAddress[1] > secondAddress[1]) {
                System.out.println("Второй октет первого адреса больше второго октета второго адреса");
                System.exit(1);
            }
            else {
                if(firstAddress[2] > secondAddress[2]) {
                    System.out.println("Третий октет первого адреса больше третьего октета второго адреса");
                    System.exit(1);
                }
                else {
                    if(firstAddress[3] > secondAddress[3]){
                        System.out.println("Четвертый октет первого адреса больше четвертого октета второго адреса");
                        System.exit(1);
                    }
                }
            }
        }
    }

    /**
     * Метод проходит между верхней и нижней границей диапазона IP адресов, и каждый допустимый адрес добавляет
     * в список.
     * Расчет производится следующим образом: если младший октет в десятичном представлении принимает значение 255, то
     * старший октет увеличивается на 1 бит, младший приводится к 0. Значение первого октета не может принять значение
     * больше 255.
     *
     * @param firstAddress верхняя граница диапазона
     * @param secondAddress нижняя граница диапазона
     * @return список допустимых адресов
     * @throws NullPointerException в случае если какая-либо из границ равна null
     */
    public static List<String> fillDiapasonArray(int[] firstAddress, int[] secondAddress){
        List<String> diapason = new ArrayList<>();
        // проверяем корректность границ диапазона - начальный адрес не должен быть больше конечного
        try{
            validateAdresses(firstAddress,secondAddress);
        }catch (NullPointerException e){
            return diapason;
        }
        int fourth = firstAddress[3];
        int third = firstAddress[2];
        int second = firstAddress[1];
        int first = firstAddress[0];
        while (true){
            if(first == secondAddress[0] & second == secondAddress[1] & third == secondAddress[2] & fourth == secondAddress[3])
                break;
            else {
                fourth++;
                if (fourth > 255) {
                    fourth = 0;
                    third += 1;
                    if (third > 255) {
                        third = 0;
                        second += 1;
                        if (second > 255) {
                            second = 0;
                            first += 1;
                            if(first > 255){
                                break;
                            }
                        }
                    }
                }

                diapason.add(first + "." + second + "." + third + "." + fourth);
            }
        }
        //последний адрес является границей диапазона
        diapason.remove(diapason.size()-1);
    return diapason;
    }

}
