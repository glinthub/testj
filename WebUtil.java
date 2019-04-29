
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

class Cookie {
    
}

public class WebUtil {

	public String getContent0(String strUrl) throws IOException {

		URL url = new URL(strUrl);

		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));

		String s = "";
		StringBuffer sb = new StringBuffer("");

		System.out.println(url.toString());
		while ((s = br.readLine()) != null) {
			sb.append(s);
			System.out.println(s);
		}
		br.close();
		return sb.toString();

	}

    public String getContent(String strUrl) throws IOException {

        URL url = new URL(strUrl);
        String curLine = "";
        String content = "";

        System.out.println("visiting " + strUrl);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        putCookies(conn);
        conn.connect();
        Map<String, java.util.List<String>> headerMap = conn.getHeaderFields();
        for (Entry<String, java.util.List<String>> entry : headerMap.entrySet()) {
            //System.out.println(entry.getKey() + ": " + entry.getValue());
            if (entry.getKey() != null && entry.getKey().equals("Set-Cookie")) {
                String strCookies = entry.getValue().toString();
                strCookies = strCookies.substring(1, strCookies.length() - 1);
                saveCookies(strCookies);
            }
        }
        
        InputStream is = conn.getInputStream();
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"));
        int lines = 0;
        while ((curLine = reader.readLine()) != null) {
            content = content + curLine;
            lines++;
            //System.out.println(curLine);
        }
        System.out.println("html page has " + lines + " lines");
        is.close();
        
        return content;
    }

	public void initProxy(String strProxy) {

        String host = "135.251.33.16";
        String port = "8080";

        String username = "username";
        String password = "password";

        host = strProxy.substring(0, strProxy.indexOf(':'));
        port = strProxy.substring(strProxy.indexOf(':')+1);

        Authenticator.setDefault(new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, new String(password).toCharArray());
			}
		});

		//System.setProperty("http.proxyType", "4");
		System.setProperty("http.proxyPort", port);
		System.setProperty("http.proxyHost", host);
		//System.setProperty("http.proxySet", "true");

        //System.setProperty("http.proxyType", "4");
        System.setProperty("https.proxyPort", port);
        System.setProperty("https.proxyHost", host);
        //System.setProperty("http.proxySet", "true");
	}

	public void saveCookies(String strCookies) {
	    System.out.println("saveCookies: " + strCookies);
	    Cookies_m = strCookies;
	}

    public void putCookies(HttpURLConnection huc) {
        System.out.println("putCookies: " + Cookies_m);
        huc.setRequestProperty("Cookie", Cookies_m);
    }

    public Cookie getCookie() {
        Cookie c = new Cookie();
        return c;
    }

    public void setCookie(Cookie c) {
        
    }
    
	public void test(String[] args) throws IOException {

		 String url = "http://java.sun.com";

	     if (args.length > 0) {
	         url = args[0];
	         if (!url.startsWith("http"))
	             url = "http://" + url;
	     }

	     if (args.length > 1)
	     {
	         initProxy(args[1]);
	     }

		 System.out.println(getContent(url));

	}

	private String Cookies_m = "";
}

