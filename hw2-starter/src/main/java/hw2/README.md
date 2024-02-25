# Discussion

The `Roster` class uses `IndexedList` to store a list of students. The
`Roster.find` implements the binary search algorithm. Which
implementation of the `IndexedList` should be used to implement the
`Roster` class? (It could be one or more of `ArrayIndexedList`,
`LinkedIndexList`, `SparseIndexedList`). And why?
   
--------------- Write your answers below this line ----------------
The answer depends on the context of the Roster class and how it is used. For instance, if we expect many students to be added to and removed from the list, or if the list stays constant throughout the course semester an Linked List implmentation would be more efficient, however
since we want to implement a search algorithm using binary search, this wouldn't be possible for a linkedlist implementation like LinkedIndexedList or SparseIndexedList since they don't support random access to elements.
Even if we considered our LinkedList implementations to be sorted sequentially, It would still take O(n) time to traverse the list due to the nature of traversing a linkedlist. On the other hand, Using and ArrayIndexedList
provides the Random access to elements, and our list traversal would take O(1) time. This would be beneficial if our Roster class assumes Students that sign up for a course stay in the course like in our example JHU Roster class. If
we wanted to implement a Roster class based on MOOC roster functionalities (we expect students to sign up and leave frequently) It would be better to trade the efficiency of the binary search algorithm using an ArrayIndexedList for the increased efficiency
when it comes to adding and removing Students with the LinkedList implementation. SparseIndexedList would be a good implementation if our Roster list has alot of positions that are expected to remain empty or be a default value for a majority of the time.
This is beneficial if we need to trade search efficiency for memory efficiency.
