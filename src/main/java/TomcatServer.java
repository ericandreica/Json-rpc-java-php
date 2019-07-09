import java.io.*;
import org.apache.catalina.*;
import org.apache.catalina.core.*;
import org.apache.catalina.webresources.*;
import org.apache.catalina.startup.*;

public class TomcatServer {
    public static void main(String[] args) throws Exception {
        Tomcat server = new Tomcat();
        server.setPort(8080);
        StandardContext rootCtx = (StandardContext)server.addWebapp("",
            (new File(System.getProperty("java.io.tmpdir"))).getAbsolutePath());
        WebResourceRoot resources = new StandardRoot(rootCtx);
        resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes",
            (new File(".")).getAbsolutePath(), "/"));
        rootCtx.setResources(resources);
        server.start();
        System.out.println("Start server Tomcat embedded");
        server.getServer().await();
    }
}