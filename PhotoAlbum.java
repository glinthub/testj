import java.io.IOException;
import java.util.List;

class Album {
    String url;
    String name;
    int number;
    int pageSize;
};

class Photo {
    String url;
    String origImgUrl;
    String desc;
}

public abstract class PhotoAlbum extends WebUtil {
    abstract List<Album> getAlbumList(String strAlbumListUrl) throws IOException ;
    abstract List<Photo> getAlbumPhotoList(Album album) throws IOException ;
    abstract void getPhotoOrig(Photo photo) throws IOException;
    public void getAll(String strUrl) throws IOException {

        List<Album> albumList = getAlbumList(strUrl);
        for (Album album : albumList) {
            //System.out.println(album.url);
            List<Photo> photoList = getAlbumPhotoList(album);
            for (Photo photo : photoList) {
                getPhotoOrig(photo);
            }
            break; //just test 1 album
        }
    }
}
