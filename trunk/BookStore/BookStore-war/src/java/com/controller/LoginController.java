/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.controller;





import com.formulaire.LoginForm;
import com.session.Session;
import ejb.entity.Client;
import ejb.entity.Libraire;
import java.util.List;
import javax.validation.Valid;
import metier.Libraire.LibraireEjbLocal;
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
    private LibraireEjbLocal LibraireEjbLocal;

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
            ModelAndView mv =new ModelAndView("login/login");
            mv.addObject("erreurLogin", "Le couple Login/Mot de passe n'est pas bon");
            return mv;
            
        }
        else if(clients.size()>1){
            ModelAndView mv =new ModelAndView("login/login");
            mv.addObject("erreurLogin", "Erreur des données le login est en double");
            return mv;
        }
        else{

           //chargement des parametres en session
            
            session.setClient(clients.get(0));
           

            ModelAndView mv = new ModelAndView("login/connexion");
            return mv;

           
        }
       
    }
    
    @RequestMapping(value="deconnexion.htm", method=RequestMethod.GET)
    public ModelAndView deconnxion(){
        session.deconnexion();
        return afficherLogin();
    }
    
    
    
    
    @RequestMapping(value="monCompte.htm", method=RequestMethod.GET)
    public ModelAndView afficherMonCompte(String erreur){
        ModelAndView mv = new ModelAndView("login/monCompte");
        mv.addObject("client", session.getClient()); 
        mv.addObject("error", erreur);
        return mv;
       
    }
    
    @RequestMapping(value="monCompte.htm", method=RequestMethod.POST)
    public ModelAndView modifierMonCompte(@Valid @ModelAttribute("client") Client clientForm, BindingResult binder){

        if(binder.hasErrors()){
            return this.afficherMonCompte("Les champs ne sont pas valides");
        }
        clientForm.setClientid(session.getClient().getClientid());
        List<Client> clients = ClientEjbLocal.loginUnique(clientForm.getClientlogin());
        if(clients.size()==0){
            ClientEjbLocal.updateClient(clientForm);
            session.setClient(clientForm);
            ModelAndView mv = new ModelAndView("login/connexion");

            mv.addObject("valide", "Modification Effectuées");
            return mv;
       }
        else{
            return afficherMonCompte("Le login existe déjà, utilisé votre adresse Email");
        }
    }
    
    
    
    
    @RequestMapping(value="inscription.htm", method=RequestMethod.GET)
    public ModelAndView afficherInscription(String erreur){
        ModelAndView mv = new ModelAndView("login/inscription");
        mv.addObject("client", new Client(-1)); 
        mv.addObject("error", erreur);
        return mv;
       
    }
    
    @RequestMapping(value="inscription.htm", method=RequestMethod.POST)
    public ModelAndView ajouterInscription(@Valid @ModelAttribute("client") Client clientForm, BindingResult binder){

        if(binder.hasErrors()){
            return this.afficherMonCompte("Les champs ne sont pas valides");
        }
        
        
        List<Client> clients = ClientEjbLocal.loginUnique(clientForm.getClientlogin());
        if(clients.size()==0){
            ClientEjbLocal.addClient(clientForm);
            System.out.print("############## client id: "+ clientForm.getClientid() + "#################");
            session.setClient(clientForm);
            ModelAndView mv = new ModelAndView("login/connexion");
            
            mv.addObject("message", "Votre inscription a bien été prise en compte. <br/> Maitenant vous pouvez:<br/>");
            return mv;
            
        }
        else{
            return afficherInscription("Le login existe déjà, utilisé votre adresse Email");
        }
        
       
    }
    
    
    
    /*
     * #####################################################################################################################
     *                                      Separation de l'administration
     * #####################################################################################################################
     */
    //TODO NicoExia ajouter l'ejb du libraire
    
    
    @RequestMapping(value="adminLogin.htm", method=RequestMethod.GET)
    public ModelAndView afficherAdminLogin(){
        ModelAndView mv = new ModelAndView("admin/login/login");
        mv.addObject("login", new LoginForm()); 
        return mv;
       
    }
    
    @RequestMapping(value="adminLogin.htm", method=RequestMethod.POST)
    public ModelAndView testerAdminLogin(@Valid @ModelAttribute("login") LoginForm loginForm, BindingResult binder){

        if(binder.hasErrors()){
            return this.afficherAdminLogin();
        }
        
        List<Libraire> libraires = LibraireEjbLocal.loginLibraire(loginForm.getLogin(), loginForm.getPass());
        if(libraires.isEmpty()){
            ModelAndView mv =new ModelAndView("admin/login/login");
            mv.addObject("erreurLogin", "Le couple Login/Mot de passe n'est pas bon");
            return mv;
            
        }
        else if(libraires.size()>1){
            ModelAndView mv =new ModelAndView("admin/login/login");
            mv.addObject("erreurLogin", "Erreur des données le login est en double");
            return mv;
        }
        else{

           //chargement des parametres en session
            Client client = new Client(-1);
            client.setClientnom(libraires.get(0).getLibrairenom());
            session.setAdmin(true);
            session.setClient(client);
            
           

            ModelAndView mv = new ModelAndView("admin/admin");
            return mv;

           
        }
       
    }

    
    public void setLibraireEjbLocal(LibraireEjbLocal LibraireEjbLocal) {
        this.LibraireEjbLocal = LibraireEjbLocal;
    }
    
    
    public void setClientEjbLocal(ClientEjbLocal ClientEjbLocal) {
        this.ClientEjbLocal = ClientEjbLocal;
    }
}
