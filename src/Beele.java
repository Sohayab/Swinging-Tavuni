/* Beele class extends Tavunu class */
public class Beele extends Tavunu{
   public Beele(String name, int birthYear, int pava) {
       /* validate the pava as per requirement */
       if (pava >= 0 && pava < 10) {
           pava = 10;
       }
      
       if (pava < 21 || pava > 80) {
           throw new IllegalArgumentException("invalid pava amount");
       } else {
           pava = pava;
       }
       this.name= name;
       this.pava= pava;
       this.birthYear = birthYear;
   }
  
   /* displays the Beele object in a defined format */
   @Override
   public String toString() {
       return name + " born in " + birthYear + " is a Beele with " + pava + " pava.";
   }
}
