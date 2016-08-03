import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) throws IOException {
        List<String> tempArr = new ArrayList<>();
        //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String firstAddress = "128.168.0.0";
        String secondAddress = "128.168.255.255";

        int [] address1 = parseAddress(firstAddress);
        int [] address2 = parseAddress(secondAddress);

        if(address1[0] >= 192 && address1[0] <=223 )
             tempArr = fillArrayClassC(address1, address2);
        if(address1[0] >= 128 && address1[0] <= 191)
            tempArr = fillArrayClassB(address1, address2);
        if(address1[0] >= 0 && address1[0] <=127)
            tempArr = fillArrayClassA(address1, address2);

        for(String s : tempArr){
            System.out.println(s);
        }
    }



    private static int[] parseAddress(String address){
        int [] addressArray = new int[4];
        for(int index = 0; index < 4; index++){
            int nextOctet = Integer.parseInt(address.split("\\.")[index]);
            if(nextOctet > 255) {
                System.out.println("ip адрес "+ address +" неверен. Октет " + index + " должен быть меньше или равен 255");
                System.exit(1);
            }
            addressArray[index] = nextOctet;
        }
        return addressArray;
    }


    private static List<String> fillArrayClassC(int[] firstAddr, int[] secondAddr){
        List<String> diapason = new ArrayList<>();
        if(secondAddr[0] < firstAddr[0] || secondAddr[1] < firstAddr[1] || secondAddr[2] < firstAddr[2]) {
            System.out.println("Некорректный диапазон адресов");
            System.exit(1);
        }
        for(int i = firstAddr[3] + 1; i < secondAddr[3]; i++){
            diapason.add(firstAddr[0] + "." + firstAddr[1] + "." + firstAddr[2] + "." + i);
            if(i == 254) break;
        }
        return diapason;
    }

    private static List<String> fillArrayClassB(int[] firstAddr, int[] secondAddr){
        List<String> diapason = new ArrayList<>();
        if(secondAddr[0] < firstAddr[0] || secondAddr[1] < firstAddr[1]) {
            System.out.println("Некорректный диапазон адресов");
            System.exit(1);
        }
        for(int i = firstAddr[2]; i <= secondAddr[2]; i++){
            int j;
            int min;
            if(i != secondAddr[2]){
                j = firstAddr[3];
                min = 255;
            }
            else {
                j = -1;
                min = secondAddr[3];
            }
            while (j < min){
                j++;
                diapason.add(firstAddr[0] + "." + firstAddr[1] + "." + i + "." + j);
                if(i == secondAddr[2] && j == secondAddr[3])
                    break;
            }
        }
        diapason.remove(diapason.size()-1);
        return diapason;
    }

    private static List<String> fillArrayClassA(int[] firstAddr, int[] secondAddr) {
        List<String> diapason = new ArrayList<>();
        if(secondAddr[0] < firstAddr[0]) {
            System.out.println("Некорректный диапазон адресов");
            System.exit(1);
            //TODO дописать алгоритм заполнения списка адресами класса A
        }
        return diapason;
    }

}
