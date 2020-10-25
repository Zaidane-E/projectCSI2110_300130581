/**
 * PQPair class where the ranking of each employee
 * is stored and associated with a student;
 * (employer_ranking, s).
 * @author Zaidane El Haouari
 * Student number: 300130581
 */
public class PQPair implements Comparable<PQPair>
{
    //Data *******************************************************************

    /**
     * Stores value of employer.
     */
    protected int employer;

    /**
     * Stores value of student.
     */
    protected int student;

    //Class constructors *****************************************************

    PQPair(int employer, int student)
    {
        this.employer = employer;
        this.student = student;
    }

    //Class methods **********************************************************

    /**
     * Getter method for employer.
     * @return Employer value
     */
    public int getEmployer() {
        return employer;
    }

    /**
     * Getter method for student.
     * @return Student value
     */
    public int getStudent() {
        return student;
    }

    @Override
    public int compareTo(PQPair other)
    {
        return Integer.compare(this.getEmployer(), other.employer);
    }

    /**
     * toString method for PrioQueue class.
     * @return string representing the pair (employer, student)
     */
    @Override
    public String toString() {
        return "(" + employer + "," + student + ")";
    }
}
