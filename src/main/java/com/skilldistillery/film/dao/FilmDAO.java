package com.skilldistillery.film.dao;

import java.sql.SQLException;
import java.util.List;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

public interface FilmDAO {
	  public Film findFilmById(int filmId);
	  public List<Film> findFilmsByKeyword(String keyword);
	  public Actor findActorById(int actorId);
	  public List<Actor> findActorsByFilmId(int filmId);
	  public Film addFilm(Film newFilm);
	  public boolean deleteFilmById(Film film)throws SQLException, ClassNotFoundException;
	  public Film updateFilmById(Film film)throws SQLException, ClassNotFoundException;
}
