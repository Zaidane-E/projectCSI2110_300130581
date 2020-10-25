/**
 * Pair calss that represents a pair of
 * a student ranking and an employer ranking;
 * (employer_ranking, student_ranking).
 * @author Zaidane El Haouari
 * Student number: 300130581
 */
public class Pair
{
    //Data ********************************************************

    /**
     * Stores the student ranking.
     */
    private int studentRanking;

    /**
     * Stores the employer ranking.
     */
    private int employerRanking;

    //Class constructors ******************************************

    /**
     * Constructor for Pair class.
     */
    Pair(int employerRanking, int studentRanking)
    {
        this.studentRanking = studentRanking;
        this.employerRanking = employerRanking;
    }

    //Class methods ***********************************************

    /**
     * Getter method for the employer ranking.
     * @return Employer ranking
     */
    public int getEmployerRanking() {
        return employerRanking;
    }

    /**
     * Getter method for the student ranking.
     * @return Student ranking.
     */
    public int getStudentRanking() {
        return studentRanking;
    }

    /**
     * toString method
     * @return A string representation of
     * the student ranking and employer ranking
     * pair.
     */
    @Override
    public String toString()
    {
        String s = Integer.toString(studentRanking);
        String e = Integer.toString(employerRanking);

        return "(" + e + "," + s + ")";
    }
}
