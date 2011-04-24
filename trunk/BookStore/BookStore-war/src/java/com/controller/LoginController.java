/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.controller;





import com.formulaire.LoginForm;
import com.session.Session;
import ejb.entity.Client;
import java.util.List;
import javax.validation.Valid;
import metier.client.ClientEjbLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Wikola
 */
@Controller
@Scope("prototype")
@RequestMapping(value="/login/*")
public class LoginController {

    
    @Autowired
    Session session;
    
    
    private ClientEjbLocal ClientEjbLocal;

    @RequestMapping(value="login.htm", method=RequestMethod.GET)
    public ModelAndView afficherLogin(){
        ModelAndView mv = new ModelAndView("login/login");
        mv.addObject("login", new LoginForm()); 
        return mv;
       
    }
    
    
    
    @RequestMapping(value="login.htm", method=RequestMethod.POST)
    public ModelAndView testerLogin(@Valid @ModelAttribute("login") LoginForm loginForm, BindingResult binder){

        if(binder.hasErrors()){
            return this.afficherLogin();
        }

        List<Client> clients = ClientEjbLocal.login(loginForm.getLogin(), loginForm.getPass());
        if(clients.isEmpty()){
            ModelAndView mv =new ModelAndView("login");
            mv.addObject("erreurLogin", "Le couple Login/Mot de passe n'est pas bon");
            return mv;
            
        }
        else if(clients.size()>1){
            ModelAndView mv =new ModelAndView("login");
            mv.addObject("erreurLogin", "Erreur des données le login est en double");
            return mv;
        }
        else{

           //chargement des parametres en session
            
            session.setClient(clients.get(0));
           

            ModelAndView mv = new ModelAndView("index");
            return mv;

           
        }
       
    }
    
    @RequestMapping(value="deconnexion.htm", method=RequestMethod.GET)
    public ModelAndView deconnxion(){
        session.deconnexion();
        return afficherLogin();
    }

    public void setClientEjbLocal(ClientEjbLocal ClientEjbLocal) {
        this.ClientEjbLocal = ClientEjbLocal;
    }



   

   



   
}
