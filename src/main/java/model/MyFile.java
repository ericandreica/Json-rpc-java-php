package model;

public class MyFile {

    private String name;
    private String content;
    private String hash;

    public MyFile(String name, String content, String hash) {
        this.name = name;
        this.content = content;
        this.hash = hash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHash() {
        return this.hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    @Override
    public String toString() {
        return "\"MyFile\":{" +
                "\"name\":\"" + name + '\"' +
                ", \"content\":\"" + content + '\"' +
                ", \"hash\":\"" + hash + '\"' +
                '}';
    }
}
