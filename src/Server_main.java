import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server_main {
    public static void main(String[] args) {
        // 1. Server프로그램에서 사용하는소캣, Port를 통해 연결 요청이 오기를 대기
        ServerSocket serverSocket = null;

        try {
            // 2. 특정 Port를 열어놓은 상태에서 대기, 접속 요청이 온다면 새로운 소켓으로 연결처리
            serverSocket = new ServerSocket(4444);
        }catch (IOException e){
            System.out.println("해당 포트가 열려있습니다.");
        }

        while(true){

            try {
                System.out.println(getTime() + "연결 요청을 기다리는 중입니다 ");

                // 3. accept()메소드 호출. Client로부터 요청이 올 때까지  Blocking 상태
                // 4. Connection 요청이 들어오면 새로운 Socket을 생성하여, Client와 한쌍을 이루고 연결됨
                Socket socket = serverSocket.accept();
                System.out.println(getTime() + socket.getInetAddress());

                // 5. getInputSteam()은 inputStream 객체를 반환하기 때문에 InputStream type의 변수에 대입
                // 6. DataOutputeStream 주석과 동일
                InputStream in = socket.getInputStream();
                DataInputStream dis = new DataInputStream(in);
                // 7. readUTF()로 받은 메세지를 UTF-8 인코딩을 사용해서 읽어들임
                String recHex = dis.readUTF();  // 받은 hex값

                // hex --> byte[] --> String 수행
                String hexToName = hexChangeString(recHex);

                System.out.println("[Server 출력]받은 메세지 : " + hexToName );
                System.out.println("연결을 종료합니다. ");

                dis.close();
                socket.close();
                System.out.println("연결을 종료되었습니다. ");

            } catch (IOException e) {
                e.printStackTrace();
                     }
                  }
            }

    public static String hexChangeString(String s) throws UnsupportedEncodingException {    //ec9e84eab2bdebafbc
        int len = s.length();

        byte[] data = new byte[len /2];

        for(int i = 0; i < len; i+=2){
            data[i/2] = (byte) (
                    (Character.digit(s.charAt(i),16) <<4)  + Character.digit(s.charAt(i+1), 16));
        }
        return new String(data, "EUC-KR");
    }

    // 현재 시간을 문자열로 반환하는 함수
    private static String getTime() {
        SimpleDateFormat f = new SimpleDateFormat("[hh.mm.ss]");
        return f.format(new Date());
    }
}