import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) throws IOException {
        //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String firstAddress = "192.168.0.10";
        String secondAddress = "192.168.0.255";

        int [] address1 = parseAddress(firstAddress);
        int [] address2 = parseAddress(secondAddress);
        List<String> tempArr = fillArray(address1, address2);

        for(String s : tempArr){
            System.out.println(s);
        }
    }

    private static int[] parseAddress(String address){
        int [] addressArray = new int[4];
        for(int index = 0; index < 4; index++){
            int nextOctet = Integer.parseInt(address.split("\\.")[index]);
            if(nextOctet > 255) {
                System.out.println("ip адрес неверен. Октет " + index + " должен быть меньше 255");
                System.exit(1);
            }
            addressArray[index] = nextOctet;
        }
        return addressArray;
    }


    private static List<String> fillArray(int[] firstAddr, int[] secondAddr){
        List<String> diapason = new ArrayList<>();

        for(int i = firstAddr[3] + 1; i < secondAddr[3]; i++){
            diapason.add(firstAddr[0] + "." + firstAddr[1] + "." + firstAddr[2] + "." + i);
            if(i == 254) break;
        }
        return diapason;
    }

}
