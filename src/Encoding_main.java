import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class Encoding_main {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String stringToConver = "임경민";

        convertStringToHex(stringToConver);
    }

    public static void convertStringToHex(String str) throws UnsupportedEncodingException {
        byte[] getBytesFromString  = str.getBytes("UTF-8");   // getBytes()는 유니코드문자열 -->

        System.out.println("RESULT : "+ getBytesFromString);


        BigInteger bigInteger = new BigInteger(1, getBytesFromString);
        System.out.println("변환결과 : " + bigInteger);

        String convertResult = String.format("%x", bigInteger);
        System.out.println("변환결과 : " + convertResult);



    }
}
