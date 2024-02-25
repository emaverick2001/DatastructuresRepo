# Discussion

## Flawed Deque
1. doesn't throw empty exception when removeBack is called on empty deque
   Unit test: testRemoveBackEmpty()
   Guess: There is no check for when the deque is empty thus no implementation for throwing Empty exception error when removing back

2. doesn't throw empty exception when removeFront is called on empty deque
   Unit test: testRemoveFrontEmpty()
   Guess: There is no check for when the deque is empty thus no implementation for throwing Empty exception error when removing front

3. doesn't throw empty exception when back is called on an empty deque
   Unit test: testBackEmpty()
   Guess: The exception thrown is LengthException instead of Empty exception

4. InsertBack adds every other call. For instance on the first call, it adds a node. On the second call it skips adding the node. On the Third it adds a node and follows adding every other node (evey odd call it will add a node)
   Unit test: testInsertBackAndRemoveFrontMultipleNodes(), testInsertBackAndRemoveBackMultipleNodes(), testInsertBackManyNodes()
   Guess: Every odd call to insert back adds a node, every even call is skipped


## Hacking Linear Search
 In the MoveToFrontLinkedSet, remove and prepend within the find function allows for a quicker way to find already searched elements in the future by adding them to the front of the list.
   This makes it so that when searching for the element, find doesn't have to traverse the whole list and only just the first element
 In the TransposeArraySet, swaps gradually move recently searched elements to the front of the set each time the element is searched for. 
   This makes it so that with each access of a specific element, its location in the array moves closer to the front (the searched element is removed and replaced by the element before it).


## Profiling
In my experiment I hypothesized that adding more of the same value (3 different values being added repeatedly) in the data array list will show the difference between normal arraySet + LinkedSet and transposeArraySet and moveToFrontLinkedSet since it would take less time
to find recently searched elements with these hueristics implemented since they are now closer to the front. Thus, this should be reflected in the Score values for each SetTest. For instance, JmhRuntimeTest.arraySet had a score of
0.319 ms/op vs JmhRuntimeTest.transposeSequence which had a score of 0.317 ms/op. Similarly, JmhRuntimeTest.linkedSet had a score of 1.181 ms/op vs JmhRuntimeTest.moveToFront which had a score of 1.156 ms/op. Thus adding these extra 
repeated values showed the efficiency that the heuristics provide. I wanted to see if this improved efficiency result would be greater in terms of the difference in scores between the original sets vs hueristic sets by
increasing the number of elements inputted in the experiment. I increased the number of elements in the data array and the repeated value by a factor of 10 and noticed that the scores between linked set (and linked with heuristic) were 
different from array set (and transposeArraySet) by a factor of 4. This could be due to the fact that it's faster to traverse an array versus a linked list based on how they are stored in memory. The results again showed that transposeSequence
had a faster runtime (35.443 ms/op) compared to arraySet (36.807 ms/op) and moveToFront (126.319 ms/op) compared to linkedSet (129.449 ms/op). This should be the case since
the move to front hueristic takes O(1) time to move the current element to the front and tranposeSequence takes O(1) time to swap the current element with the previous.

