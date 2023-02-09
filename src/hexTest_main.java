import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

public class hexTest_main {
    
    // hex값 받아서 --> byte코드 변환
    public static void main(String[] args) throws UnsupportedEncodingException {
        // 1. 패킷에서 넘어온 이름에 관한 hex값이라고 가정
         String hexValue = "ec9e84eab2bdebafbc";
         String name = "임경민";
         // 2. 해당 메소드에 hex값을 넘겨서 byte 배열로 만듬
         byte[] byteArr = hexStringToByteArray(hexValue);

         //byte[] test = hexStringToByteArray("[B@234bef66");
        // TEST
         String result_hex = byteArrayToHexString(byteArr);
         String result_name = byteArrayToName(byteArr);
         byte[] result_byteArr = NameToByteArray(name);

         System.out.println("원본 : "+ hexValue);
         System.out.println("16진수 -> 바이트배열 : "+ byteArr);
         System.out.println("바이트배열 -> 16진수 : " + result_hex);
         System.out.println("바이트배열 -> 문자열 : " + result_name);
         System.out.println("문자열 -> 바이트배열 : " + result_byteArr);

    }

    public static byte[] hexStringToByteArray(String s){    //ec9e84eab2bdebafbc
        // 3. hex값의 길이 지정
        int len = s.length();   // hex코드의 길이, 모든 요소에 접근하기 위해서 필요
        
        // 길이의 절반인 이유는, byte단위 저장이라서. 16진수 한자리 = 4bit, 8bit = 1byte
        // 4. byte 배열 결과값을 받을 배열 data 생성
        // 4-1. 길이가 절반인 이유는. byte단위 저장이기 때문! 16진수 한자리 = 4bit . 1byte = 8bit
        byte[] data = new byte[len /2];

        // 5. 반복문으로 요소들 접근. 1byte 기준으로 움직여야 하기 때문에 증감은 2씩.
        for(int i = 0; i < len; i+=2){

        // 6. byte배열이 담길 data에 값 채우기
        // 6-1. isDigit는 숫자인지 아닌지를 boolean값으로 return / digit는 들어온 문자를 int형으로 return? (라이브러리에서 봄)
        // 6-2. charAt(index)로 해당 16진수 요소의 위치를 가져오고 16진수로 변환
        // 6-3. i번째 16진수(4bit)와 i+1번째 16진수(4bit)연산
        // 6-4. 그냥 더 하는 게 아니라. ex) EC는 1110과 1100이 된다. 1110과 1100을 바로 더하는 게 아니라
        // 6-5. 1110을 시프트연산으로 4bit를 밀면 빈 자리는 0으로 채워지고 1110 0000이 되고, 이 상태에서 더하게 되면 11101100이 된다 (EC)
        // 6-6. 이 결과를 하나씩 data 배열에 채워나간다
            data[i/2] = (byte) (
                    (Character.digit(s.charAt(i),16) <<4)  + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    public static String byteArrayToHexString(byte[] bytes){
        StringBuilder sb = new StringBuilder();

        for (byte b : bytes){
            sb.append(String.format("%02X", b&0xff));
        }
        return sb.toString().toLowerCase();
    }

    public static String byteArrayToName(byte[] bytes) throws UnsupportedEncodingException {

        return new String(bytes, "UTF-8");
    }

    public static byte[] NameToByteArray(String s) throws UnsupportedEncodingException {

        byte[] byteArray = s.getBytes("UTF-8");

        return byteArray;
    }
}
