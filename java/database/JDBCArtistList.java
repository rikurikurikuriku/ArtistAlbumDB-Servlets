package database;

import java.sql.Connection;    
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import model.Album;
import model.Artist;


	public class JDBCArtistList implements ArtistListDao {
		
		 private static final String URL = "jdbc:sqlite:C:\\sqlite\\Chinook.sqlite";
				 

		  public Connection connect() throws SQLException {
		        return DriverManager.getConnection(URL);
		    }
		  
	    public List<Artist> getAllArtists() {
	    	
	    	String sql = "SELECT ArtistId, Name FROM Artist ORDER BY Name ASC";
	    	
	    	 List<Artist> allArtists = new ArrayList<>();
	    	
	    	 try (Connection connection = connect();
	    	    	 PreparedStatement s = connection.prepareStatement(sql);
	    	    	 ResultSet r = s.executeQuery()){
	    	 		
	    	 while (r.next()) {
	    		 long id = r.getLong("ArtistId");
	    		 String it = r.getString("Name");
	    		 Artist x = new Artist(id, it);
	    		 allArtists.add(x);
	    	 }
	    	 
	    	
	    } catch (SQLException e) {
	    	 e.printStackTrace();
	    }
	   
	    	 return allArtists;
	
	    	
	   
	    }
	    
  public List<Album> getAlbums(long id) {
	    	
	    	String sql = "SELECT Title FROM Album WHERE ArtistId = ?;";
	    	
	    	 List<Album> albums = new ArrayList<>();
	    	
	    	 try (Connection connection = connect();
	    	    	 PreparedStatement s = connection.prepareStatement(sql);
	    	    	 ResultSet r = s.executeQuery()){
	    		 
	    		 s.setLong(1, id);
	    		  s.executeQuery();
	    	 		
	    	 while (r.next()) {
	    		 String it = r.getString("Title");
	    		 Album x = new Album(it);
	    		 albums.add(x);
	    	 }
	    	 
	    	
	    } catch (SQLException e) {
	    	 e.printStackTrace();
	    }
	   
	    	 return albums;
	
	    	
	   
	    }
	    
	    public boolean addArtist(Artist newArtist) {
	 	   
	    	String i = newArtist.getName();
	    	
	   
	   	 
	   	 try 
	   		 (Connection connection = connect();
	   		 PreparedStatement query = connection.prepareStatement("INSERT INTO Artist (Name) VALUES (?)"))
	   	 {
	   		 

	 	
			
	   	 query.setString(1, i);
	   	query.executeUpdate();
	  
	   	 
	   } catch (SQLException e) {
	   	 e.printStackTrace();
	   	  return false;
	   
	   }
	   	 return true;
	     
	      
	    }
	    public Artist getArtist(long id) {
	        
	  	  Artist res = new Artist(id, null);
	              
	          try (Connection conn = connect();
	                  PreparedStatement items= conn.prepareStatement ("SELECT * FROM Artist WHERE ArtistId = ?");
	                  ResultSet r = items.executeQuery()) {
	                  
	                      items.setLong (1, id);
	                      items.executeQuery();
	                   
	                   
	          if (r.next()) {
	           long v = r.getLong("ArtistId");
	     		 String it = r.getString("Name");
	     		res = new Artist(v, it);  
	          } else {
	          	return null;
	          }
	          } catch (SQLException e) {
	  	    	 e.printStackTrace();
	         
	          }
	          return res;
	         
	          }
	   
	}