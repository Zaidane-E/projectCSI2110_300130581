/**
 * Employer class that stores a string
 * representing the name of each employer.
 * @author Zaidane El Haouari
 * Student number: 300130581
 */
public class Employer
{
    //Data ************************************************

    /**
     * Name of the employer.
     */
    String employer;

    //Class constructors **********************************

    /**
     * Constructor for Emplyer class.
     */
    Employer(String employer)
    {
        this.employer = employer;
    }

    //Class methods ***************************************

    /**
     * Getter method for Employer class
     * @return Employer name
     */
    public String getEmployer() {
        return employer;
    }

    /**
     * Method toString for the Employer class.
     * @return name of the employer.
     */
    @Override
    public String toString() {
        return employer;
    }
}
