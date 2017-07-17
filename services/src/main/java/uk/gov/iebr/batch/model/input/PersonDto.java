package uk.gov.iebr.batch.model.input;

import java.io.Serializable;
import java.util.Objects;
public class PersonDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String firstName;
	private String lastName;
	private int harmScore;
	private int removabilityScore;
	private int kowScore;
	//private String bands;
	
	/*public String getBands() {
		return bands;
	}

	public void setBands(String bands) {
		this.bands = bands;
	}*/

	private HarmCategory harmCategory = HarmCategory.NA;
	private RemovabilityCategory removabilityCategory = RemovabilityCategory.NA;
	private KOWCategory kowCategory = KOWCategory.NA;

	private BANDS bands = BANDS.NA;
	
	
	public BANDS getBands() {
		return bands;
	}

	public void setBands(BANDS bands) {
		this.bands = bands;
	}

	public KOWCategory getKowCategory() {
		return kowCategory;
	}

	public void setKowCategory(KOWCategory kowCategory) {
		this.kowCategory = kowCategory;
	}

	public RemovabilityCategory getRemovabilityCategory() {
		return removabilityCategory;
	}

	public void setRemovabilityCategory(RemovabilityCategory removabilityCategory) {
		this.removabilityCategory = removabilityCategory;
	}

	public HarmCategory getHarmCategory() {
		return harmCategory;
	}

	public void setHarmCategory(HarmCategory harmCategory) {
		this.harmCategory = harmCategory;
	}

	public enum HarmCategory {
	        NA, LOW, MEDIUM, HIGH
	    };
	    
	    
	public enum RemovabilityCategory {
	        NA, GREEN, AMBER, RED
	    };
	    
		public enum KOWCategory {
	        NA, MINIMAL_INFORMATION, CONTACTABLE, KNOWN
	    };
	    
		public enum BANDS {
	        NA, OP1, OP2, OP3, OP4, OP5, OP6, OP7, OP8, OP9,
	         OP28, OP29, OP30, OP31, OP32, OP33, OP34, OP35, OP36,
	         OP55, OP56, OP57, OP58, OP59, OP60, OP61, OP62, OP63,
	    };
	   
	    
	public int getKowScore() {
		return kowScore;
	}

	public void setKowScore(int kowScore) {
		this.kowScore = kowScore;
	}

	public int getRemovabilityScore() {
		return removabilityScore;
	}

	public void setRemovabilityScore(int removabilityScore) {
		this.removabilityScore = removabilityScore;
	}

	public int getHarmScore() {
		return harmScore;
	}

	public void setHarmScore(int harmScore) {
		this.harmScore = harmScore;
	}

	public PersonDto() {
	}

	public PersonDto(long id, String firstName, String lastName,int harmScore,int removabilityScore, int kowScore) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.harmScore=harmScore;
		this.removabilityScore= removabilityScore;
		this.kowScore=kowScore;
		
	}	
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.firstName);
        hash = 67 * hash + Objects.hashCode(this.lastName);
        hash = 67 * hash + Objects.hashCode(this.harmScore);
      
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PersonDto other = (PersonDto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.harmScore, other.harmScore)) {
            return false;
        }
      
        return true;
    }

	@Override
	public String toString() {
		return String.format("Person[id=%d , firstName='%s', lastName='%s' ,harmScore='%d' , removabilityScore='%d', kowScore='%d' ,harmCategory= '%s' ,kowCategory= '%s',"
				+ "removabilityCategory = '%s', band = '%s']", id, firstName, lastName, harmScore, removabilityScore, kowScore, harmCategory, removabilityCategory, kowCategory , bands );
	}
/*
	public BANDS getBands() {
		return bands;
	}

	public void setBands(BANDS bands) {
		this.bands = bands;
	}*/
}
