package repo;

import model.MyFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RepoClass implements Repo {

    private ArrayList<MyFile> db = new ArrayList<>();

    private void init(String file) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                String[] atr = line.split(",");
                db.add(new MyFile(atr[0], atr[1], atr[2]));
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public RepoClass(String file) {
        init(file);
    }

    @Override
    public String listAll() {
        StringBuilder sb = new StringBuilder();
        String prefix = "";
        for (MyFile myFile : db) {
            sb.append(prefix);
            prefix = ",\n";
            sb.append(myFile);
        }
        return sb.toString();
    }

    @Override
    public String searchByName(String name) {
        StringBuilder sb = new StringBuilder();
        for (MyFile myFile : db) {
            if (myFile.getName().equals(name))
                sb.append(myFile).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String searchByContentString(String content) {
        StringBuilder sb = new StringBuilder();
        for (MyFile myFile : db) {
            if (myFile.getContent().contains(content))
                sb.append(myFile).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String searchByContentBytes(String bytes) {
        System.out.println(bytes);
        StringBuilder content = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        String[] getbytes = bytes.replaceAll("0x", "").split(" ");
        for (String string : getbytes) {
            int x = Integer.parseInt(string, 16);
            char c = (char) x;
            System.out.println(c);
            content.append(c);
        }
        System.out.println(content.toString());
        for (MyFile myFile : db) {
            if (myFile.getContent().contains(content.toString()))
                sb.append(myFile).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String searchByHash(String hash) {
        StringBuilder sb = new StringBuilder();
        for (MyFile myFile : db) {
            if (myFile.getHash().equals(hash))
                sb.append(myFile).append("\n");
        }
        return sb.toString();
    }
}
