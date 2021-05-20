package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.ArtistListDao;
import database.JDBCArtistList;
import model.Album;
import model.Artist;

@WebServlet("/albums")
public class AlbumList extends HttpServlet {
	
	
	private ArtistListDao dao = new JDBCArtistList();
	
	
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			
			Long id = Long.parseLong(req.getParameter("ArtistId"));
			
			
			Artist art = this.dao.getArtist(id);
			
			String artist = art.getName();
			
			req.setAttribute("artist", artist);
			
			List<Album> albums = this.dao.getAlbums(id);
			
			req.setAttribute("albums", albums);
			req.getRequestDispatcher("/WEB-INF/albums.jsp").forward(req, resp);;
			
		}
		

	}
