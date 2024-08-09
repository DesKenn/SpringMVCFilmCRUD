package com.skilldistillery.film.dao;

import java.util.List;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

public interface FilmDAO {
	public Film findFilmById(int filmId);
	  public List<Film> findFilmsByKeyword(String keyword);
	  public Actor findActorById(int actorId);
	  public List<Actor> findActorsByFilmId(int filmId);
	  public void addFilm(Film newFilm);
	  public boolean deleteFilmById(Film filmToDelete);
}
