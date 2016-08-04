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

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            firstAddress = reader.readLine();
            secondAddress = reader.readLine();
        } catch (IOException e) {
            System.out.println("Произошла ошибка ввода/вывода " + e.getMessage());
        }

        int [] address1 = parseAddress(firstAddress);
        int [] address2 = parseAddress(secondAddress);
        //Long t0 = System.currentTimeMillis();

        validateAdresses(address1,address2);

        resultList = fillDiapasonArray(address1,address2);

        for(String s : resultList)
            System.out.println(s);

        //Long t1 = System.currentTimeMillis();
        //System.out.println("Time: " + (t1 - t0)/1000d + " sec.");
    }

    public static int[] parseAddress(String address){
        int [] addressArray = new int[4];
        for(int index = 0; index < 4; index++){
            int nextOctet = Integer.parseInt(address.split("\\.")[index]);
            if(nextOctet > 255) {
                System.out.println("ip адрес "+ address +" неверен. Октет " + index + " должен быть меньше или равен 255");
                return null;
            }
            addressArray[index] = nextOctet;
        }
        return addressArray;
    }

    private static void validateAdresses(int[] firstAddress, int[] secondAddress){
        if(firstAddress[0] > secondAddress[0]) {
            System.out.println("Первый октет второго адреса больше первого октета первого адреса");
            System.exit(1);
        }
        else {
            if(firstAddress[1] > secondAddress[1]) {
                System.out.println("Второй октет второго адреса больше второго октета первого адреса");
                System.exit(1);
            }
            else {
                if(firstAddress[2] > secondAddress[2]) {
                    System.out.println("Третий октет второго адреса больше третьего октета первого адреса");
                    System.exit(1);
                }
                else {
                    if(firstAddress[3] > secondAddress[3]){
                        System.out.println("Четвертый октет второго адреса больше четвертого октета первого адреса");
                        System.exit(1);
                    }
                }
            }
        }
    }

    public static List<String> fillDiapasonArray(int[] firstAddress, int[] secondAddress){
        List<String> diapason = new ArrayList<>();


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
                        }
                    }
                }

                diapason.add(first + "." + second + "." + third + "." + fourth);
            }
        }
        diapason.remove(diapason.size()-1);
    return diapason;
    }

}
