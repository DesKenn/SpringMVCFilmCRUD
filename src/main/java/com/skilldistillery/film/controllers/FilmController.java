package com.skilldistillery.film.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
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
			mv.setViewName("WEB-INF/index.jsp");
		} else {
			mv.addObject("info", film);
			mv.setViewName("/WEB-INF/index.jsp");
		}

		return mv;
	}
	@RequestMapping(path = "index.do", method = RequestMethod.GET)
	private ModelAndView addFilm( Film film) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/WEB-INF/index.jsp");
		
		return mv;
		
	}
	@RequestMapping(path = "index.do", method = RequestMethod.POST)
	public ModelAndView createFilm(Film film, Errors error) {
		ModelAndView mv = new ModelAndView();
		
		if(error.getErrorCount()>0) {
			mv.setViewName("/WEB-INF/index.jsp");
		}
		
		else {
			Film newFilm = filmDAO.addFilm(film);
			mv.addObject("newfilm", newFilm);
			mv.setViewName("WEB-INF/index.jsp");
			
		}
		return mv;

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

}
