/* Crode class extends Tavunu class */
public class Crode extends Tavunu{
   public Crode(String name, int birthYear, int pava) {
       /* validate the pava as per requirement */
       if (pava >= 0 && pava < 10) {
           pava = 10;
       }
      
       if (pava <= 80) {
           throw new IllegalArgumentException("invalid pava amount");
       } else {
           pava = pava;
       }
       this.name= name;
       this.pava= pava;
       
       if(birthYear%2 == 0)
       {
    	   throw new IllegalArgumentException("invalid year");
       }
       this.birthYear = birthYear;
   }
  
   @Override
   public void setBirthYear(int birthYear) {   //sets the birth year as passed
	   if(birthYear%2 == 0)
       {
    	   throw new IllegalArgumentException("invalid year");
       }
       this.birthYear = birthYear;
   }
  
  
   /* displays the Crode object in a defined format */
   @Override
   public String toString() {
       return name + " born in " + birthYear + " is a Crode with " + pava + " pava.";
   }
}
