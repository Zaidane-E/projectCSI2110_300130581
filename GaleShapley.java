import java.util.Stack;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.InputStreamReader;

/**
 * GaleShapley Class used to perform the Gale
 * Shapley algorithm.
 * @version Oct 24, 2020
 * @author Zaidane El Haouari
 * Student number: 300130581
 */
public class GaleShapley
{
    //Data **************************************************

    //Inputs

    /**
     * Number of students and employers/students.
     */
    private int n;

    /**
     * Input: array of n employers representing 
     * the list of employers.
     */
    private Employer[] employersList;

    /**
     * Input: array of n students representing
     * the list of students.
     */
    private Student[] studentsList;

    /**
     * Input: Matrix of Pairs matrix[e][s].
     * Each entry is a pair
     * (employer ranking, student ranking).
     */
    private Pair[][] matrix;

    //Initialize

    /**
     * Stack of unmatched employers.
     */
    private Stack<Integer> Sue;

    /**
     * Array of students used to represent
     * the current matches; (if student s
     * is matched with  employer e then student[s]=e
     * and employers[e]=s); value -1 is used to
     * designate  an unmatched student or employee.
     */
    private int[] students;

    /**
     * Array of employers used to represent
     * the current matches; (if student s
     * is matched with  employer e then student[s]=e
     * and employers[e]=s); value -1 is used to
     * designate  an unmatched student or employee.
     */
    private int[] employers;

    /**
     * 2D array of size n*n with A[s][e] being
     * the ranking score  given by student s to
     * employer e. 
     */
    private int[][] A;

    /**
     * Priority Queue with PQ[e] being  the queue
     * of employer e. The pairs are represented as
     * (emplyer ranking, student value).
     */
    private PrioQueue<PQPair>[] PQ; 

    /**
     * Array of matches to be saved
     * in a file with the save() method.
     */
    private String[] matches;
    
    //Class constructors ************************************

    /**
     * Constructor for GaleShapley class.
     */
     GaleShapley()
    {
    }

    //Class methods *****************************************

    /**
     * Initialize method that reads the input file and
     * performs all intialization steps for the class.
     * @param filename : Name of the file to read.
     * @throws FileNotFoundException
     */
    void initialize(String filename) throws FileNotFoundException
    {
        //Read file
        Scanner sc = new Scanner(new BufferedReader(new FileReader(filename)));     //Initialize scanner

        n = Integer.parseInt(sc.nextLine());        //Read and store n

        matrix = new Pair[n][n];                    //Initialize matrix of n*n elems

        employersList = new Employer[n];
        studentsList = new Student[n];
        for ( int i = 0 ; i < n ; i++ )     //Loop through lines and store employers in employerList
        {
            employersList[i] = new Employer(sc.nextLine());     //Store employer
        }
        for ( int i = 0 ; i < n ; i++ )
        {
            studentsList[i] = new Student(sc.nextLine());       //Store student
        }

        while ( sc.hasNextLine() )
        {
            String[] read = new String[n];
            for ( int i = 0 ; i < matrix.length ; i++ )     //Loop through the text file and read lines
            { read = sc.nextLine().trim().split(" ");
            for ( int j = 0 ; j < read.length ; j++ )       //Nested loop to read each line
            {
                int e, s;
                String[] pair = read[j].trim().split(",");
                e = Integer.parseInt(pair[0]);
                s = Integer.parseInt(pair[1]);
                matrix[i][j] = new Pair(e, s);      //Store pairs
            }
            }
        }


        //Push all emplyoyers from employerList in the the stack Sue
        Sue = new Stack<Integer>();
        for ( int i = 0 ; i < n ; i++ ) { Sue.push(i); }
        

        //Initialize all entries in students and emplyoyers to -1
        students = new int[n];
        employers = new int[n];
        for ( int i = 0 ; i < n ; i++ )
        {
            students[i] = -1;
            employers[i] = -1;
        }


        //Initialize each entry of A with the student rankings
        A = new int[n][n];
        for ( int i = 0 ; i < n ; i++ )
        { for ( int j = 0 ; j < n ; j++ )       //Nested Loop to iterate through matrix
            {
                A[i][j] = matrix[j][i].getStudentRanking();     //A[s][e] takes matrix[e][s] (score given by s to e)
            }
        }


        //Create n Priority Queues
        PQ = new PrioQueue[n];
        for ( int i = 0 ; i < n ; i++ )     //Creating n Priority Queues
        {
            PQ[i] = new PrioQueue<PQPair>();        //Intialize each queue
        }
        for ( int i = 0 ; i < n ; i++ )
        { for ( int j = 0 ; j < n ; j++ )       //Nested Loop to iterate through matrix
            {
                PQPair temp = new PQPair(matrix[j][i].getEmployerRanking(), i);
                PQ[j].insert(temp);       //PQ[e] takes matrix[s][e] (score given by e to s)
            }
        }
    }

    /**
     * Execute method that performs the Gale
     * Shapley algorithm.
     * @return List of stable matches
     */
    String[] execute()
    {
        while ( !Sue.empty() )
        {            
            int e = Sue.pop();      //e is looking for a student
            int s = PQ[e].removeMin().getStudent();     //Most preferred student of e
            int e2 = students[s];
            if ( students[s] == -1 )        //Student is unmatched
            {
                students[s] = e;
                employers[e] = s;       //Matach (e,s)
            } else if ( A[s][e] < A[s][e2] )      //s prefers employer e to e2
            {
                students[s] = e;
                employers[e] = s;       //Replace the match
                employers[e2] = -1;     //Now unmatched
                Sue.push(e2);
            } else
            {
                Sue.push(e);        //s rejects offer from e
            }
        }

        matches = new String[n];
        for ( int i = 0 ; i < n ; i++ )     //Return the set of stable matches
        {
            matches[i] = "Match " + i + ": " + employersList[students[i]].getEmployer() + " - " + studentsList[employers[i]].getStudent();
        }
        
        return matches;
    }

    /**
     * Saves a file with the results of the Gale
     * Shapley algorithm.
     * @param filename Name of the file to be saved
     */
    void save(String filename)
    {
        //Create file
        try {
            File myObj = new File(filename);
            if (myObj.createNewFile()) {
              System.out.println("File created: " + myObj.getName());
            } else {
              System.out.println("File already exists.");
            }
          } catch (IOException e) {
            e.printStackTrace();
        }
        //Write to file
        try {
            FileWriter myWriter = new FileWriter(filename);
            for ( int i = 0 ; i < n ; i++ )
            {
                myWriter.write(matches[i] + "\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    }


    //Main method *******************************************
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
        
        // Read filename
        String filename;
        boolean pass = false;
        while ( !pass )
        {
            System.out.println("Please enter a valid file name:\n");
            filename = reader.readLine();
            File f = new File(filename);
            if(f.exists() && !f.isDirectory() && f.isFile())
            { 
                GaleShapley galeShapley = new GaleShapley();
                galeShapley.initialize(filename);      //Initializing, read input file
                galeShapley.execute();                      //Executing algorithm
                galeShapley.save("matches_" + filename);          //Save results to file
                break;
            }
        }
    }
}