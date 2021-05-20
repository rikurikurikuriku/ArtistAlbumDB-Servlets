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
import model.Artist;

@WebServlet("/")
public class ArtistList extends HttpServlet {
	
	private ArtistListDao dao = new JDBCArtistList();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<Artist> artists = this.dao.getAllArtists();
		
		req.setAttribute("artists", artists);
		req.getRequestDispatcher("/WEB-INF/artistlist.jsp").forward(req, resp);;
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String t = req.getParameter("artist");
		Artist newItem = new Artist(t);
		this.dao.addArtist(newItem);
		resp.sendRedirect("/");
}
}