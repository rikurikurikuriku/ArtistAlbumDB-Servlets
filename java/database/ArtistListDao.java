package database;

import java.util.List;

import model.Album;
import model.Artist;

public interface ArtistListDao {

	 public List<Artist> getAllArtists();

	 public List<Album> getAlbums(long id);
	 
	 public boolean addArtist(Artist newArtist);

	   public Artist getArtist(long id);
	

}
