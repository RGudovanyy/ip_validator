import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

public class MainTest {

    @Test
    public void checkCorrectDiapason(){
        int[] firstAddress = {192,168,0,1};
        int[] secondAddress = {192,168,0,5};
        String testingAddress;
        int index = 2;

        List<String>resultList = Main.fillDiapasonArray(firstAddress,secondAddress);

        for(String address : resultList) {
            testingAddress = 192 + "." + 168 + "." +0 + "." + index;
            Assert.assertEquals(testingAddress, address);
            index++;
        }
    }

    // Внимание! Этот тестовый метод требует для работы больше 10-ти секунд
    @Ignore
    @Test
    public void checkCorrectCountWithFirstOctet(){
        int[] firstAddress = {192,168,0,1};
        int[] secondAddress = {193,168,0,1};

        List<String>resultList = Main.fillDiapasonArray(firstAddress,secondAddress);

        Assert.assertEquals(16777215, resultList.size());
        Assert.assertEquals("193.168.0.0", resultList.get(resultList.size()-1));
    }

    @Test
    public void checkCorrectCountWithSecondOctet(){
        int[] firstAddress = {192,0,0,1};
        int[] secondAddress = {192,1,0,1};

        List<String>resultList = Main.fillDiapasonArray(firstAddress,secondAddress);

        Assert.assertEquals(65535, resultList.size());
        Assert.assertEquals("192.1.0.0", resultList.get(resultList.size()-1));
    }

    @Test
    public void checkCorrectCountWithThirdOctet(){
        int[] firstAddress = {192,168,0,1};
        int[] secondAddress = {192,168,1,1};

        List<String>resultList = Main.fillDiapasonArray(firstAddress,secondAddress);

        Assert.assertEquals(255, resultList.size());
        Assert.assertEquals("192.168.1.0", resultList.get(resultList.size()-1));
    }


    @Test
    public void checkCorrectCountWithFourthOctet(){
        int[] firstAddress = {192,168,0,1};
        int[] secondAddress = {192,168,0,255};

        List<String>resultList = Main.fillDiapasonArray(firstAddress,secondAddress);

        Assert.assertEquals(253, resultList.size());
        Assert.assertEquals("192.168.0.254", resultList.get(resultList.size()-1));
    }

    @Test
    public void checkCorrectParsing(){
        String rawAddress = "192.168.0.1";
        int[] testAddress = {192,168,0,1};

        int[] ipAddress = Main.parseAddress(rawAddress);

        Assert.assertEquals(testAddress[0],ipAddress[0]);
        Assert.assertEquals(testAddress[1], ipAddress[1]);
        Assert.assertEquals(testAddress[2], ipAddress[2]);
        Assert.assertEquals(testAddress[3], ipAddress[3]);
    }

    @Test
    public void checkOctetOutOfBound(){
        String rawAddress = "192.168.0.1000";
        int[] ipAddress = Main.parseAddress(rawAddress);
        Assert.assertNull(ipAddress);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void checkNullCount(){
        List<String>resultList = Main.fillDiapasonArray(null,null);
        Assert.fail(resultList.get(0));

    }






}