
public class CrodeExalted extends Crode {

	public CrodeExalted(String name, int birthYear, int pava) {
		super(name, 1, pava);
		if (birthYear % 2 != 0) {
			throw new IllegalArgumentException("invalid year");
		}
		this.birthYear = birthYear;

	}
	
	   @Override
	   public void setBirthYear(int birthYear) {   //sets the birth year as passed
		   if(birthYear%2 != 0)
	       {
	    	   throw new IllegalArgumentException("invalid year");
	       }
	       this.birthYear = birthYear;
	   }
	
	 @Override
	   public String toString() {
	       return name + " born in " + birthYear + " is an Exalted Crode with " + pava + " pava.";
	   }

}
