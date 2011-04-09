/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Wikola
 */
@Controller
@Scope("prototype")
@RequestMapping(value="/*")
public class accueilController {

    @RequestMapping(value="index.htm", method=RequestMethod.GET)
    public ModelAndView afficherAccueil(){

        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

}
