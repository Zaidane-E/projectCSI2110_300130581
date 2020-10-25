/**
 * Student class that stores a string
 * representing the name of each student.
 * @author Zaidane El Haouari
 * Student number: 300130581
 */
public class Student 
{
    //Data ***********************************************************

    /**
     * Name of the student.
     */
    String student;

    //Class constructors *********************************************

    /**
     * Constructor for Student class.
     */
    Student(String student)
    {
        this.student = student;
    }

    //Class methods **************************************************

    /**
     * Getter method for Student class
     * @return Student name
     */
    public String getStudent() {
        return student;
    }

    /**
     * Method toString for the Student class.
     * @return name of the student.
     */
    @Override
    public String toString() {
        return student;
    }
}
