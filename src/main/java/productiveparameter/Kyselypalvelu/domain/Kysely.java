package productiveparameter.Kyselypalvelu.domain;



import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

//Kyselylomakkeen kokonaisuus (entity)


@Entity
public class Kysely {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String nimi;

//Yhdellä teemalla on monia sanoja (wordmap)
//Sanalla on yksi teema (tätä pitää kehittää sillä sanalla voi olla monta teemaa mutta ensin tehdään yksinkertaisemmin	
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "kysely")
	
	private List<Kysymys> kysymykset;
	

	public Kysely() {}
	
	public Kysely (String nimi, List<Kysymys> kysymykset) {
		super();
		this.nimi = nimi;
		this.kysymykset = kysymykset;
	
	}
		
	
	
	public Long getId() {
		return id;
	}
	public String getNimi() {
		return nimi;
	}
	
	
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setNimi(String nimi) {
		this.nimi = nimi;
	}
	

	public List<Kysymys> getKysymys() {
		return kysymykset;
	}

	public void setKysymykset(List<Kysymys> kysymykset) {
		this.kysymykset = kysymykset;
	}

//	}

	@Override
	public String toString() {
		                                    
		return "Kysely [id=" +id + ", nimi=" + nimi   + "]";
		
	}
}