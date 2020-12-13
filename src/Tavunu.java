
/**
 * A tavunu is an imaginary Earth-dwelling being.
 *
 * A tavunu looks a bit like a Patagonian Mara and lives in a non-gendered but
 * hierarchical society. Most interactions among tavuni are negotiated with
 * pava -- items of status used for bargaining.
 *
 * @author Mithat Konar
 * @author Suhaib Abugdera
 */
public abstract class Tavunu {
    /* See readme.md for what to do. */
    protected String name;
    protected int pava;
    protected int birthYear;
    
    public Tavunu()
    {
        name = "";
        pava =0;
        birthYear = Integer.MIN_VALUE;
    }
    
    /**
     * 
     * @param name
     * @param birthYear
     * @param pava 
     * constructors
     */
    public Tavunu(String name, int birthYear, int pava)
    {
        this.name= name;
        this.pava= pava;
        this.birthYear = birthYear;
    }
    /**
     * 
     * @return the name 
     */
    public String getName()
    {
        return name;
    }
    /**
     * 
     * @return the pava.
     */
    public int getPava()
    {
        return pava;
    }
    /**
     * 
     * @param int the pava to spend;
     * spend pava if the amount is positive.
     * @return 
     */
    public boolean spendPava(int pava)
    {
        if (pava >= 0 && pava <= this.pava)
        {
            this.pava -= pava;
            return true;
        }
        return false;
    }
    /**
     * 
     * @param int the amount to add;receive pava if amount is positive.
     * @return 
     */
    public boolean receivePava(int amount)
    {
        if (amount < 0)
        {
            return false;
        }
        pava += amount;
        return true;
    }
    /**
     * 
     * @param name to set
     * @return 
     */
    public boolean setName(String name)
    {
        if(name.startsWith("T") || name.startsWith("D"))
        //if(name.charAt(0) == 'T' || name.charAt(0) == 'D')
        {
            this.name= name;
            return true;
        }
        return false;
    }
    /**
     * 
     * @return the birthYear
     */
    public int getBirthYear()
    {
        return birthYear;
    }
    /**
     * 
     * @param birthYear to set 
     */
    public void setBirthYear(int birthYear)
    {
        this.birthYear = birthYear;
    }
    //toString to get a string representation of object.
    public String toString()
    {
        String stringRepresentation = name + " born in "+ String.valueOf(birthYear)+
                " has "+ String.valueOf(pava)+ " pava.";
        return stringRepresentation;
    }
}
