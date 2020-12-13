/* Avo class extends Tavunu class */
public class Avo extends Tavunu{
   
    public Avo(String name, int birthYear, int pava) {
       /* validate the pava as per requirement */
       if (pava >= 0 && pava < 10) {
           pava = 10;
       } else if (pava > 20) {
           throw new IllegalArgumentException("invalid pava amount");
       } else {
           pava = pava;
       }
       this.name= name;
       this.pava= pava;
       this.birthYear = birthYear;
   }
   
 /* displays the Avo object in a defined format */
   @Override
   public String toString() {
       return name + " born in " + birthYear + " is an Avo with " + pava + " pava.";
   }
}
