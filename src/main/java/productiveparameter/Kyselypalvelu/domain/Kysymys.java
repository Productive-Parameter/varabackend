package productiveparameter.Kyselypalvelu.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Kysymys {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long kysymysid;
    private String teksti;

    
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id") 
    private Kysely kysely;

    
    public Kysymys() {}
    
  
	public Kysymys(String teksti, Kysely kysely) {
		super();
		this.teksti = teksti;
		this.kysely = kysely;
	}		

	

	public void setId(Long kysymysid) {
		this.kysymysid = kysymysid;
	}
	
	public void setTeksti(String teksti) {
		this.teksti = teksti;
	}


	public void setKysely(Kysely kysely) {
		this.kysely = kysely;
	}
	
	
	
	public Long getId() {
		return kysymysid;
	}
	public String getTeksti() {
		return teksti;
	}


	public Kysely getKysely() {
		return kysely;
	}



	
	@Override
	public String toString() {
		if (this.kysely != null)
			return "Kysymys [kysymysid=" + kysymysid + ", teksti=" + teksti +  ", kysely =" + this.getKysely() + "]";		
		else
			return "Kysymys [kysymysid=" + kysymysid + ", teksti=" + teksti + "]";	
	}
}
