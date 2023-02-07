import java.io.*;
import java.net.ConnectException;
import java.net.Socket;

public class Client_main {

    public static void main(String[] args) {

        try{
            String SERVER_IP = "211.189.132.233";

            System.out.println("서버에 연결 중입니다. 서버 IP : " +  SERVER_IP);

            Socket socket = new Socket(SERVER_IP, 4444);

            OutputStream out = socket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(out);

            dos.writeUTF("test 메세지");
            System.out.println("데이터를 전송합니다");

            dos.close();
            socket.close();

        } catch (ConnectException ce){
            ce.printStackTrace();
        } catch (IOException ie){
            ie.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
