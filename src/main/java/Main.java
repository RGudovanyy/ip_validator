import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) throws IOException {
        List<String> tempArr = new ArrayList<>();
        //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String firstAddress = "192.168.0.0";
        String secondAddress = "192.169.0.255";

        int [] address1 = parseAddress(firstAddress);
        int [] address2 = parseAddress(secondAddress);
        Long t0 = System.currentTimeMillis();

        tempArr = fillDiapasonArray(address1,address2);

        System.out.println(tempArr.size());

        for(String s : tempArr)
            System.out.println(s);

        Long t1 = System.currentTimeMillis();
        System.out.println("Time: " + (t1 - t0)/1000d + " sec.");
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

    private static List<String> fillDiapasonArray(int[] firstAddr, int[] secondAddr){
        List<String> diapason = new ArrayList<>();
        int fourth = firstAddr[3];
        int third = firstAddr[2];
        int second = firstAddr[1];
        int first = firstAddr[0];
        while (true){
            if(first == secondAddr[0] & second == secondAddr[1] & third == secondAddr[2] & fourth == secondAddr[3])
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
