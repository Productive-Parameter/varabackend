package productiveparameter.Kyselypalvelu.web;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import productiveparameter.Kyselypalvelu.domain.Kysely;
import productiveparameter.Kyselypalvelu.domain.KyselyRepo;
import productiveparameter.Kyselypalvelu.domain.Kysymys;
import productiveparameter.Kyselypalvelu.domain.KysymysRepo;



@Controller
public class KysymysController {
	
	@Autowired	
	private KyselyRepo kyselyrepo; 
	
	@Autowired	
	private KysymysRepo kysymysrepo; 
	
    
	// 2.  REST --> hakee kaikki kysymykset osoitteeseen http://localhost:8080/words
    @RequestMapping(value="/kysymykset", method = RequestMethod.GET)
    public @ResponseBody List<Kysymys> kysymyslistaRest() {	
        return (List<Kysymys>) kysymysrepo.findAll();
    } 


	// 3. REST --> hakee kysymykset id:n avulla
    @RequestMapping(value="/kysymykset/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Kysymys> findKysymysRest(@PathVariable("kysymysid") Long Id) {	
    	return kysymysrepo.findById(Id);
    }  
  

      
     // 4.  Uuden kysymyksen lisääminen 
     @RequestMapping(value = "/lisaakysymys")
     public String lisaaKysymys(Model model){
     	model.addAttribute("kysymys", new Kysymys());
     	model.addAttribute("kyselyt", kyselyrepo.findAll());
       return "lisaakysymys";
     }  
     

     
     // 5. Tallenna kysymys
     @RequestMapping(value = "/tallennakysymys", method = RequestMethod.POST)
     public String tallennaKysymys(Kysymys kysymys){        
         kysymysrepo.save(kysymys);
         return "redirect:lisaakysymys";
     } 
     
     //--------------------------------------------------------------
     //6. 
     @RequestMapping(value = "/kysely/{id}/kysymykset", method =RequestMethod.GET)
     public String kyselyKysymykset(@PathVariable("id") Long Id, Model model){
     	Optional<Kysely> kysely = kyselyrepo.findById(Id); 	
   		model.addAttribute("kysely", kysely);	
   		List<Kysymys> kyselynKysymykset = kysymysrepo.findByKysely(kysely);
        System.out.println("moimoimoi");
   		System.out.println(kyselynKysymykset);
   		model.addAttribute("kysymykset", kyselynKysymykset);
   		return "kysely";
     }
     
     //-----------------------------------------------------
    
}