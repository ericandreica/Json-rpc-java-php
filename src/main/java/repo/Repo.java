package repo;

public interface Repo {
    String listAll();
    String searchByName(String name);
    String searchByContentString(String content);
    String searchByContentBytes(String bytes);
    String searchByHash(String hash);
}
