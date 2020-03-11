package officeHours;

import java.util.*;

public class OfficeHourB15March10_2020 {

    public static void main(String[] args) {

        /**
         * Under List Interface:
         *   --ArrayList(indexes, faster to read, slower when we do manipulations)
         *   manipulations means : remove or add elements
         *   --Vector(same as ArrayList, but thread safe)
         *      --Stack(Last in First out ( like a stack of paper))
         *   --LinkedList(node(each element reference to previous and next element
         *   in the list
         *   (performance -sensitive, memory allocation)
         *   ArrayList if you remove something : [2,5,3]-->[2,3]
         *   LinkedList : [2],[5],[3]
         */
        /**
         *   Set Interface: Set is a instructure that does no allow duplicate ( contain only unique values)
         *   Set interface doenst accepts duplicates. Set doesnt have index. LinkHashSet keeps order. Hast Set prints randomly
         *   HashSet:no indexes--faster because it is using hashing.
         *   it does not keep an order , HashSet allows 1 null value this is asked in interviews
         *   TreeSet:sorted set --no nulls
         *   LinkedHashSet : keeps the order
         *
         *
         */
        HashSet<String> hashSet = new HashSet<>();
        TreeSet<String> treeSet = new TreeSet<>();
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();

        for(String each : Arrays.asList("value5","value1","value3")){
            hashSet.add(each);
            treeSet.add(each);
            linkedHashSet.add(each);
        }
        System.out.println("hashset : " + hashSet);
        System.out.println("treeSet = " + treeSet);
        System.out.println("linkedHashSet = " + linkedHashSet);

        Iterator <String> iteratorSet = hashSet.iterator();
        while(iteratorSet.hasNext()){
            System.out.println("iteratorSet.next() = " + iteratorSet.next());
        }

        ArrayList<Integer> dropdown = new ArrayList<>();
        dropdown.add(1);
        dropdown.add(2);
        dropdown.add(3);
        dropdown.add(4);
        dropdown.add(5);
        dropdown.add(1);
        //[1,2,3,4,5,1]
        System.out.println("list = " + dropdown);
        HashSet <Integer> optionsSet = new HashSet<>(dropdown);
        System.out.println("optionsSet = " + optionsSet);

        if(optionsSet.size()==dropdown.size()){
            System.out.println("expected is the same as actual");
        }else{
            System.out.println("options contains duplicates"); // this example can be explained in interview about
            // where do you use HashSet when you testing application
        }
        System.out.println("========================================================");

        /**
         *  Queue
         *  --PriorityQueue -First in first out
         *     can accept duplicates,does not have index
         *  --ArrayDequeue-special in adding and removing
         *
         */

        PriorityQueue<String> priorityQueue = new PriorityQueue<>();
        priorityQueue.add("John");
        priorityQueue.add("Kate");
        priorityQueue.add("Tom");

        System.out.println("priorityQueue = " + priorityQueue);
        System.out.println("peek: " + priorityQueue.peek());
        System.out.println("Queue after peek = " + priorityQueue);
        //peeking (.peek()--it will check and return the first in a queue
       // priorityQueue.poll();
        System.out.println( priorityQueue.poll());
        System.out.println("priorityQueue = " + priorityQueue);
        //polling will return the first value in a queue and remove it

        ArrayDeque <String> deque = new ArrayDeque<>();






    }
}
