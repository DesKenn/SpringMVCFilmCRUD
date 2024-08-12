package com.skilldistillery.film.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.film.dao.FilmDAO;
import com.skilldistillery.film.entities.Film;

@Controller
public class FilmController {

	@Autowired
	private FilmDAO filmDAO;

	@RequestMapping("index.do")
	public String index() {
		return "WEB-INF/index.jsp";
	}

	@RequestMapping(path = "index.do", params = "id", method = RequestMethod.GET)
	public ModelAndView findFilmById(String id) {
		ModelAndView mv = new ModelAndView();
		Film film = null;
		try {
			int Id = Integer.parseInt(id);
			film = filmDAO.findFilmById(Id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (film == null) {
			mv.addObject("ERROR! There is no film with that film id.");
		} else {
			mv.addObject("info", film);
			mv.setViewName("/WEB-INF/result.jsp");
		}

		return mv;
	}

	@RequestMapping(path = "createFilm.do", method = RequestMethod.POST)
	public ModelAndView createFilm(Film film) {
		ModelAndView mv = new ModelAndView();

		if (film.getId() != 0) {
			mv.addObject("newFilm");

		} else {
			mv.addObject("ERROR, cannot add a new film.");
		}

		mv.setViewName("result.jsp");

		return mv;
	}

	@RequestMapping("deleteFilm.do")
	public ModelAndView deleteFilm(@RequestParam("filmId") int id) throws ClassNotFoundException, SQLException {
		ModelAndView mv = new ModelAndView();
		boolean filmDeleted;
		Film toBeDeleted = filmDAO.findFilmById(id);

		if (toBeDeleted != null) {
			filmDeleted = filmDAO.deleteFilmById(toBeDeleted);
			if (filmDeleted) {
				mv.addObject("filmDeleted", "Your film id: " + id + " was deleted.");
			} else {
				mv.addObject("ERROR! Film has not been deleted.");
				mv.setViewName("notDeleted");
				return mv;
			}
		} else {
			mv.addObject("ERROR! Film has not been deleted.");
		}
		mv.setViewName("deleteFilm.jsp");

		return mv;
	}

	@RequestMapping(path = "updateFilm.do", method = RequestMethod.POST)
	public ModelAndView updateFilm(@ModelAttribute("film") Film film) throws ClassNotFoundException, SQLException {
		ModelAndView mv = new ModelAndView();
		Film updatedFilm;

		if (film != null) {
			updatedFilm = filmDAO.updateFilmById(film);
			mv.addObject("film", updatedFilm);
		} else {
			mv.addObject("ERROR! Film not updated.");
		}
		mv.setViewName("result.jsp");

		return mv;
	}

	@RequestMapping("index.do")
	public ModelAndView searchFilm(@RequestParam("searchTerm") String keyword) {
		ModelAndView mv = new ModelAndView();
		List<Film> films = new ArrayList<>();

		films = filmDAO.findFilmsByKeyword(keyword);
		mv.addObject("searchResults", films);
		mv.setViewName("index.jsp");
		return mv;

	}
}

//	@RequestMapping(path = { "index.do"}, method = RequestMethod.GET )
//	public ModelAndView findById(@RequestParam("id") int id) {
//		ModelAndView mv = new ModelAndView();
//		Film f = filmDAO.findFilmById(1);
//		mv.addObject("film", f);
//		mv.setViewName("WEB-INF/index.jsp");
//		return mv;
//
//	}
////	@RequestMapping (path= "index.do", params ="id", method =RequestMethod.GET)
////	public ModelAndView findById(@RequestParam("id")int id ) {
////		 ModelAndView mv = new ModelAndView();
////		  Film f = filmDAO.findFilmById(1);
////		  mv.addObject("film", f);
////		  mv.setViewName("WEB-INF/index.jsp");
////				return mv;
////		
////	}
