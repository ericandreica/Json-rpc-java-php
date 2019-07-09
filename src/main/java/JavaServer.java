import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import repo.Repo;
import repo.RepoClass;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

@WebServlet("/JavaServer")
public class JavaServer extends HttpServlet {
    private Repo repo = new RepoClass("D:\\java-hes\\src\\myfile.txt");
    private JsonParser parser = new JsonParser();
    private String pathServ;
    private int port;

    private void out(Object m, PrintWriter out) {
        String response = "{";
        if (m instanceof String)
            if (((String) m).startsWith("ERR:")) response += "\"error\":\"" + m + "\"}";
            else response += "\"result\":[{" + m + "}]}";
        else response += "\"result\":[{" + m + "}]}";
        out.println(response);
        out.close();
    }

    private  String listAll() {
        return repo.listAll();
    }

    private  String getByName(String name) {
        return repo.searchByName(name);
    }

    private  String getByContentString(String content) {
        return repo.searchByContentString(content);
    }

    private  String getByContentBytes(String bytes) {
        return repo.searchByContentBytes(bytes);
    }

    private  String getByHash(String hash) {
        return repo.searchByHash(hash);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Hashtable<String, String> uri = Uri.uriFields(request.getRequestURL().toString());
        port = Integer.parseInt(uri.get("port"));
        pathServ = uri.get("path");
        PrintWriter out = response.getWriter();
        response.setContentType("text/plain");
        JsonElement params;
        String method;
        try {
            BufferedReader reader = request.getReader();
            StringBuilder body = new StringBuilder();
            for (; ; ) {
                String linie = reader.readLine();
                if (linie == null) break;
                body.append(linie);
            }
            reader.close();
            JsonObject req = parser.parse(body.toString()).getAsJsonObject();
            params = req.get("params");
            method = req.get("method").getAsString();
        } catch (Exception e) {
            out("ERR: " + e, out);
            return;
        }
        switch (method) {
            case "listAll":
                out(listAll(), out);
                break;
            case "getByName":
                out(getByName(((JsonObject) params).get("nume").getAsString()), out);
                break;
            case "getByContentString":
                out(getByContentString(((JsonObject) params).get("content").getAsString()), out);
                break;
            case "getByContentBytes":
                out(getByContentBytes(((JsonObject) params).get("content").getAsString()), out);
                break;
            case "getByHash":
                out(getByHash(((JsonObject) params).get("myhash").getAsString()), out);
                break;
            default:
                out("Error: " + method + " bad input ", out);
                break;
        }
    }
}
