package productiveparameter.Kyselypalvelu.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;


public interface KysymysRepo extends CrudRepository<Kysymys, Long> {

	   List<Kysymys> findByTeksti(String teksti);
	    List<Kysymys> findByKysely(Optional<Kysely> kysely);
	    
	    
	 
	  
  
    
}
