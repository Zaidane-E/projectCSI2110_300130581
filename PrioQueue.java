import java.util.PriorityQueue;

/**
 * PrioQUeue class to handle priority
 * queues of PQPair.
 * @author Zaidane El Haouari
 * Student number: 300130581
 */
public class PrioQueue<T> extends PriorityQueue<T>
{
    //Methods ****************************************************

    /**
     * ínserts element e in the queue.
     * @return true if successfully added.
     */
    boolean insert(T e)
    {
        return super.add(e);
    }

    /**
     * Removes the smallest element in 
     * the queue and returns it.
     * @return Head of queue
     */
    T removeMin()
    {
        return super.remove();
    }
}