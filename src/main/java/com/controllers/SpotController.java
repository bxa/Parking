package com.controllers;

import com.dao.SpotDao;
import com.models.Spot;
import com.services.SpotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin/spots")
public class SpotController extends Spot {
	private  static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private SpotService spotSrv;

	@RequestMapping("/all")
	public ModelAndView showAll(){
		ModelAndView model = new ModelAndView("/admin/spots/spots");
		return model;
	}
	/**
	 * show all users
	 */
	@RequestMapping("/json")
	@Transactional
	public @ResponseBody
	List<Spot> getJson() {
		return  this.spotSrv.get("");
	}



	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@Transactional
	public @ResponseBody
	AjaxResponse addSpot(@Valid @ModelAttribute("spot") Spot spot, BindingResult result) {
		AjaxResponse aj = new AjaxResponse();
		if (result.hasErrors()) {
			aj.setStatus(false);
			aj.setMsg("Something is wrong with binding data to class");
		}
		if(this.spotSrv.add(spot)) {
			aj.setStatus(true);
			aj.setMsg("You have successfully added the Parking lot");
		} else {
			aj.setStatus(false);
			aj.setMsg(" Something is wrong to inserting to database");
		}
		return aj;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	@Transactional
	public @ResponseBody
	List<Spot> getSpotsOfOneLot(@PathVariable("id")  int id) {
		return this.spotSrv.getLotSpot(id);
	}

	@RequestMapping(value = "/spot/{id}")
	public	ModelAndView showSpotsOfOneLot(@PathVariable("id")  int id) {
		ModelAndView model = new ModelAndView("/admin/spots/spots");
		return model;
	}



}
