import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Yupoo extends PhotoAlbum {
    List<Album> getAlbumList(String strAlbumListUrl) throws IOException {
        String htmlText = getContent(strAlbumListUrl);
        List<Album> albums = new ArrayList<Album>();
        String strUrl = "album1";
//        String lines[] = htmlText.split("\n");
//        System.out.println(lines.length + " lines");
        //Pattern p = Pattern.compile("set-details.*?href=\"([^\"]*)\".*");
        String strPattern = "set-details.*?href=\"([^\"]*)\"";
        Pattern p = Pattern.compile(strPattern);
        Matcher m;
//        for (String line : lines) {
            m = p.matcher(htmlText);
            while (m.find()) {
                strUrl = m.group(1);
                Album album = new Album();
                album.url = strUrl;
                //System.out.println("album matched: "+ strUrl);
                albums.add(album);
            }
//        }
        return albums;
    }
    
    List<Photo> getAlbumPhotoList(Album album) throws IOException {
        List<Photo> photos = new ArrayList<Photo>();

        String albumKey = "";
        String strPattern = "albums/([0-9]+)";
        Pattern p = Pattern.compile(strPattern);
        Matcher m;
        m = p.matcher(album.url);
        if (m.find())
            albumKey = m.group(1);
        else
            throw new IOException("Album key get fail.");

        String albumPageUrlBase = "http://www.yupoo.com/ajax/album/card_view/?username=kingdomofdreams&album_id=" + albumKey + "&page=";
        int page = 1;
        while (true) {
            String albumPageUrl = albumPageUrlBase + page;
            String htmlText = getContent(albumPageUrl);
            boolean photoFound = false;
            strPattern = "large-box\" href=\"[^\"]+?([0-9]+)/\"";
            p = Pattern.compile(strPattern);
            m = p.matcher(htmlText);
            while (m.find()) {
                String strPhotoId = m.group(1);
                String strPhotoUrl = "http://www.yupoo.com/photos/kingdomofdreams/" + strPhotoId + "/zoom/original/";
                Photo photo = new Photo();
                photo.url = strPhotoUrl;
                photos.add(photo);
                photoFound = true;
            }
            if (!photoFound)
                break;
            page++;
        }
        return photos;
    }
    
    void getPhotoOrig(Photo photo) throws IOException {
        //System.out.println(photo.url);//TODO
        String htmlText = getContent(photo.url);
        String strPattern = "(http://photo.yupoo.com/[^\"]+)\".*?alt=\"([^\"]+)\"";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(htmlText);
        if (m.find()) {
            String strPhotoOrigUrl = m.group(1);
            String strPhotoDesc = m.group(2);
            System.out.println(strPhotoDesc + ": " + strPhotoOrigUrl);
        }
    }
    
    public void test() throws IOException {
        String strCookies = "BCSI-CS-8d528b0c9a8789e9=2; BCSI-CS-bb84aa71ca590268=2; sid=6aivripm1fnm7d4l07sn545nb0; __utma=229552404.1981898646.1493783315.1493887459.1493944305.4; __utmc=229552404; __utmz=229552404.1493783315.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); BCSI-CS-7d045cd770fac493=2";
        saveCookies(strCookies);
        Photo p = new Photo();
        p.url = "http://www.yupoo.com/photos/kingdomofdreams/90820693/zoom/original/";
        getPhotoOrig(p);
    }
}
