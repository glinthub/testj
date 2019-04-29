import java.io.IOException;


public class Main {
    public static void main(String s[]) throws IOException {
        System.out.println("main()");
//        String args[] = {"http://www.baidu.com", "135.251.33.16:8080"};
//        String args[] = {"http://yupoo.com", "155.132.90.26:8000"};
//        HttpUtils.test(args);
        Yupoo yupoo = new Yupoo();
        yupoo.initProxy("155.132.90.26:8000");
        String strCookies = "BCSI-CS-8d528b0c9a8789e9=2; BCSI-CS-bb84aa71ca590268=2; sid=6aivripm1fnm7d4l07sn545nb0; __utma=229552404.1981898646.1493783315.1493887459.1493944305.4; __utmc=229552404; __utmz=229552404.1493783315.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); BCSI-CS-7d045cd770fac493=2";
        yupoo.saveCookies(strCookies);
        yupoo.getAll("http://www.yupoo.com/photos/kingdomofdreams/albums/");
        //yupoo.test();
//        Test t = new Test();
//        t.test(args);
    }
}
