package com.skilldistillery.film.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

@Component
public class FilmDAOJdbcImpl implements FilmDAO {

	private static final String URL = "jdbc:mysql://localhost:3306/sdvid";
	private static final String user = "student";
	private static final String pass = "student";

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;
		Connection conn;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			String sqltext = "SELECT film.title, film.release_year, film.rating, film.description, language.name FROM film JOIN language ON film.language_id = language.id WHERE film.id = ?";

			PreparedStatement stmt = conn.prepareStatement(sqltext);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				film = new Film();
				film.setTitle(rs.getString(1));
				film.setReleaseYear(rs.getInt(2));
				film.setRating(rs.getString(3));
				film.setDescription(rs.getString(4));
				film.setActors(findActorsByFilmId(filmId));
				film.setLanguage(rs.getString(5));
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;
	}

	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;
		Connection conn;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			String sqltext = "SELECT * FROM actor WHERE actor.id = ?";

			PreparedStatement stmt = conn.prepareStatement(sqltext);
			stmt.setInt(1, actorId);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				actor = new Actor();
				actor.setId(rs.getInt(1));
				actor.setFirstName(rs.getString(2));
				actor.setLastName(rs.getString(3));
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();
		Connection conn;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			String sqltext = "SELECT actor.id, actor.first_name, actor.last_name FROM actor JOIN film_actor ON actor.id = film_actor.actor_id JOIN film ON film.id = film_actor.film_id WHERE film.id = ?";

			PreparedStatement stmt = conn.prepareStatement(sqltext);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				Actor actor = new Actor(id, firstName, lastName);
				actors.add(actor);
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actors;
	}

	@Override
	public List<Film> findFilmsByKeyword(String keyword) {
		List<Film> films = new ArrayList<>();
		Connection conn;
		keyword = "%" + keyword + "%";
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			String sqltext = "SELECT film.id, film.title, film.release_year, film.rating, film.description, language.name FROM film JOIN language ON film.language_id = language.id WHERE film.title LIKE ? OR film.description LIKE ?";

			PreparedStatement stmt = conn.prepareStatement(sqltext);
			stmt.setString(1, keyword);
			stmt.setString(2, keyword);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Film film = new Film();
				film.setId(rs.getInt(1));
				film.setTitle(rs.getString(2));
				film.setReleaseYear(rs.getInt(3));
				film.setRating(rs.getString(4));
				film.setDescription(rs.getString(5));
				film.setLanguage(rs.getString(6));
				film.setActors(findActorsByFilmId(film.getId()));
				films.add(film);
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Unable to load MySQL driver");
			e.printStackTrace();
		}
	}

	@Override
	public Film addFilm(Film newFilm) {

		try {
			String user = "student";
			String pass = "student";

			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sqltext;
			sqltext = "INSERT INTO film (film.title, film.language_id) " + " VALUES (?, ?)"; /////
			conn.setAutoCommit(false); // Start transaction
			PreparedStatement stmt = conn.prepareStatement(sqltext, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, newFilm.getTitle());
			stmt.setInt(2, 1);
			int uc = stmt.executeUpdate();
			conn.commit();
			System.out.println(stmt);
			ResultSet keys = stmt.getGeneratedKeys();

			while (keys.next()) {
				newFilm.setId(keys.getInt(1));

				System.out.println("New film ID: " + keys.getInt(1));

			}
			keys.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newFilm;
	}

	@Override
	public boolean deleteFilmById(Film film) throws SQLException, ClassNotFoundException {

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false); // START TRANSACTION
			String sql = "DELETE FROM film WHERE film.id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, film.getId());
			int updateCount = stmt.executeUpdate();
			sql = "DELETE FROM film WHERE id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, film.getId());
			updateCount = stmt.executeUpdate();
			conn.commit(); // COMMIT TRANSACTION
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			return false;
		}

		return true;
	}

	@Override
	public Film updateFilmById(Film film) throws SQLException, ClassNotFoundException {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false); // START TRANSACTION
			String sql = "UPDATE film SET film.title, language_id = ? " + " WHERE id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(2, film.getLanguageId());
			stmt.setString(1, film.getTitle());
			int updateCount = stmt.executeUpdate();
			if (updateCount == 1) {
				// Replace actor's film list
				sql = "DELETE title, language_id FROM film WHERE actor_id = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(2, film.getLanguageId());
				stmt.setString(1, film.getTitle());
				updateCount = stmt.executeUpdate();
				sql = "INSERT INTO film (title, language_id) VALUES (?,?)";
				stmt = conn.prepareStatement(sql);
				for (Film films : film.getFilms()) {
					stmt.setInt(2, film.getLanguageId());
					stmt.setString(1, film.getTitle());
					updateCount = stmt.executeUpdate();
				}
				conn.commit(); // COMMIT TRANSACTION
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} // ROLLBACK TRANSACTION ON ERROR
				catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
				return film;
			}
		}
		return film;
	}
}
