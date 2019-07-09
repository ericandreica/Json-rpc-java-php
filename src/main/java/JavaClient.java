import java.io.*;
import java.net.*;
public class JavaClient {
    private String urlCall(String urlServ, String body) {
        try {
            URL url = new URL(urlServ);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", "" + body.length());
            Writer writer = new OutputStreamWriter(conn.getOutputStream());
            writer.write(body);
            writer.close();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            while(true){
                String linie = reader.readLine();
                if (linie == null) break;
                response.append(linie);
            }
            reader.close();
            return response.toString();
        } catch (Exception e) {
            return "{\"error\":\""+e+"\"}";
        }
    }
    private  JavaClient(String urlServ) {
        System.out.println("Client Java JSON-RPC " + urlServ);
        String n1 = "{\"method\":\"listAll\",\"params\":{}}";
        System.out.println(" =>"+n1+"\n<= "+urlCall(urlServ, n1));
        String n2 = "{\"method\":\"getByName\",\"params\":{\"nume\":\"file3\"}}";
        System.out.println(" =>"+n2+"\n<= "+urlCall(urlServ, n2));
        String n3 = "{\"method\":\"getByContentString\",\"params\":{\"content\":\"ana\"}}";
        System.out.println(" =>"+n3+"\n<= "+urlCall(urlServ, n3));
        String n4 = "{\"method\":\"getByContentBytes\",\"params\":{\"content\":\"0x61\"}}";
        System.out.println(" =>"+n4+"\n<= "+urlCall(urlServ, n4));
        String n5 = "{\"method\":\"getByHash\",\"params\":{\"myhash\":\"0dbab6d0c841278d33be207f14eeab8b\"}}";
        System.out.println(" =>"+n5+"\n<= "+urlCall(urlServ, n5));
        String n6 = "{\"method\":\"aiurea\",\"params\":{\"a\":66,\"b\":75}}";
        System.out.println(" =>"+n6+"\n<= "+urlCall(urlServ, n6));
    }
    public static void main(String[] args){
        if (args.length > 0)
            new JavaClient(args[0]);
        else
            new JavaClient("http://localhost:8080/JavaServer");
    }
}
