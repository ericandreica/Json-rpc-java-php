import java.util.Hashtable;

class Uri {
    static Hashtable<String, String> uriFields(String uri) {
        int i1, i2, i3, i4, i5, i6, i6f, i6t, i7, i8;
        final String[] kPort = {"ftp://", "mailto:", "http://", "https://", "rmi://"};
        final String[] vPort = {"21", "25", "80", "443", "1099"};
        final String[] kRes = {"protocol", "user", "host", "port", "path", "file", "type", "query", "fragment"};
        Hashtable<String, String> port = new Hashtable<>();
        Hashtable<String, String> res = new Hashtable<>();
        for (i1 = 0; i1 < kPort.length; i1++)
            port.put(kPort[i1], vPort[i1]);
        for (i1 = 0; i1 < kRes.length; i1++)
            res.put(kRes[i1], "");
        if (uri == null || uri.length() < 3)
            return res;
        i8 = uri.indexOf("#");
        if (i8 > 0) {
            res.put("fragment", uri.substring(i8 + 1));
            uri = uri.substring(0, i8);
        }
        i7 = uri.indexOf("?");
        if (i7 > 0) {
            res.put("query", uri.substring(i7 + 1));
            uri = uri.substring(0, i7);
        }
        i1 = uri.indexOf(":");
        i2 = uri.indexOf("://");
        if (i2 > 0)
            res.put("protocol", uri.substring(0, i2 + 3));
        else if (i1 > 0)
            res.put("protocol", uri.substring(0, i1 + 1));
        uri = uri.substring(res.get("protocol").length());
        i3 = uri.indexOf("@");
        if (i3 > 0) {
            res.put("user", uri.substring(0, i3));
            uri = uri.substring(res.get("user").length() + 1);
        }
        i4 = uri.indexOf(":/");
        if (i4 == 1) {
            i5 = uri.indexOf(":", 3);
            i6 = uri.indexOf("/", 3);
        } else {
            i5 = uri.indexOf(":");
            i6 = uri.indexOf("/");
        }
        if (i6 > 0) {
            res.put("path", uri.substring(i6 + 1));
            uri = uri.substring(0, i6);
        }
        if (port.containsKey(res.get("protocol")))
            res.put("port", port.get(res.get("protocol")));
        if (i5 > 0) {
            res.put("port", uri.substring(i5 + 1));
            uri = uri.substring(0, i5);
        }
        res.put("host", uri);
        uri = res.get("path");
        i6f = uri.lastIndexOf("/") + 1;
        uri = uri.substring(i6f);
        res.put("file", uri);
        i6t = uri.lastIndexOf(".") + 1;
        if (i6t > 0)
            res.put("type", uri.substring(i6t));
        return res;
    }
}