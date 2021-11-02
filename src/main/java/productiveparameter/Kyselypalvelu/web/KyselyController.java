package productiveparameter.Kyselypalvelu.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import productiveparameter.Kyselypalvelu.domain.Kysely;
import productiveparameter.Kyselypalvelu.domain.KyselyRepo;
import productiveparameter.Kyselypalvelu.domain.KysymysRepo;




@Controller
public class KyselyController {

	@Autowired	
	private KyselyRepo kyselyrepo; 
	
	@Autowired	
	private KysymysRepo kysymysrepo; 
	
	
	
// 1.  hakee kaikki kyselyt
	
    @RequestMapping(value="/kyselyt")
    public String kyselyLista(Model model) {	
        model.addAttribute("kyselyt", kyselyrepo.findAll());
        return "kyselyt";
    }
    
	// REST kaikkien teemojen hakuun
    @RequestMapping(value="/kyselytrest")
    public @ResponseBody List<Kysely> kyselyListaRest() {	
        return (List<Kysely>) kyselyrepo.findAll();
    }    
    
    
	// REST hakee kyselyn id:n perusteella  <td><a th:href="@{theme/{id}(id=${theme.themeid})}"
    @RequestMapping(value="/kysely/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Kysely> findKyselyRest(@PathVariable("id") Long id) {	
    	return kyselyrepo.findById(id);
    }       
    
    
  
    

// 2.  uuden kyselyn lisääminen (tyhjä lomake)
    
	@RequestMapping(value = "/uusikysely", method = RequestMethod.GET)
	public String getUusiLomake(Model model) {
		model.addAttribute("kysely", new Kysely()); // "tyhjä" olio
		return "uusikysely";
	}


// 3. kyselyn tallennus  
	
    @RequestMapping(value = "/tallenna", method = RequestMethod.POST)
    public String tallenna(Kysely kysely){
        kyselyrepo.save(kysely);
        return "redirect:lisaakysymys";        
    }    
    
 // 4. Kyselyä klikkaamalla näyttää kyselyn kysymykset kyselyn sivulla (hakee kysymykset kyselyn id:n perusteella)

     @RequestMapping(value = "/kyselyt/{id}", method =RequestMethod.GET)
      public String haeKysely(@PathVariable("id") Long id, Model model){
    	Optional<Kysely> kysely = kyselyrepo.findById(id); 	
  		 model.addAttribute("kysely",kysely);	
          return "kysely";
    }
     
     
     // ??   näyttää kaikki kysymykset kyselysivulla http://localhost:8080/kysely
     @RequestMapping(value="/kyselyt/{id}/kysymykset")
     public String kysymysLista(Model model) {	
     	model.addAttribute("kyselyt", kyselyrepo.findAll());
         model.addAttribute("kysymys", kysymysrepo.findAll());
         return "kysely";
     }
	}