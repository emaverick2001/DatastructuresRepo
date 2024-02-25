# Discussion

## PART I: MEASURED IndexedList

**Discuss from a design perspective whether iterating over a `MeasuredIndexedList`should 
affect the accesses and mutation counts. Note that for the purposes of this assignment we are NOT 
asking you to rewrite the `ArrayIndexedListIterator` to do so. However, if you wanted to include 
the `next()` and/or `hasNext()` methods in the statistics measured, can you inherit 
`ArrayIndexedListIterator` from `ArrayIndexedList` and override the relevant methods, or not? 
Explain.**
Iterating over MeasuredIndexedList (assuming its using an iterator)should affect the accesses but not the mutation counts since elements are only being accessed and not manipulated in the process of iterating over the list. The next()
function needs to use the get() method to access the element in the list, and doing so accesses the list. Also,
the iterator uses its own hasNext() method as a condition to check if there are available indexes to iterate through. 

You cant inherit the ArrayIndexedListIterator from ArrayIdexedListIterator since the iterator is private and special to the class its in even though MeasuredIndexedList extends ArrayIndexedList. Also doing so won't be useful since MeasuredIndexedList doesn't implement Iterable and thus 
also Iterator and if it were to implement its own iterator it would need to create its own iterator with its hasnext and next methods. 



## PART II: EXPERIMENTS

**Explain the mistake in the setup/implementation of the experiment which resulted in a discrepancy 
between the results and what is expected from each sorting algorithm.**

I noticed in the descending.data file, every 11th data point is missing a 4th digit causing the data not be fully a descending data set. If we used the insertion sort function for example, it wouldn't display worst case behavior since for every value missing thousands place, the insertion sort algorithm has to do
less work (traverse less further back in the sorted list). Another error is in null sort for ascending.data. ascending data should
already be sorted but null reports it as false. From my analysis of the SortingAlgorithm driver, I noticed a few issues, the largest being in the isSorted method in the SortingAlgorithmDriver since it uses CompareTo to check whether , but this treats values as strings, thus "10" < "9" since 9 is greater than 1. This
causes the interpretation of ascending to be unsorted.





## PART III: ANALYSIS OF SELECTION SORT

**Determine exactly how many comparisons C(n) and assignments A(n) are performed by this 
implementation of selection sort in the worst case. Both of those should be polynomials of degree 2 
since you know that the asymptotic complexity of selection sort is O(n^2).**

Analyzing the number of comparisons, the first comparison occurs on line 3 in the outer for loop. This occurs n times since there is one extra check
to see if the loop should run and the loop runs n - 1 times. The inner loop (line 5) runs (n-1 + 1) + (n-2 + 1) + ... + 1 = n(n)/2 times since, as previously mentioned
, there is one extra check for the condition. Since the if statement always executes each
time the loop runs (line 6), the number of comparisons is also n(n)/2 times. Therefore, the total number of comparisons is n^2 + n comparisons.

Analyzing the number of assignments, we start with is 1 from the outer loop on line 3 and n times from i++. On line 4, there are n assignments since the loop runs n times as previously calculated.
On line 5 there are n assignments of int j = i + 1 since this is dependent on how many times i is updated in the outer loop (n times). There are n(n-1)/2 assignments for max = j and j++ on line 7
since this is the amount of times the inner loops runs (the condition runs +1 times for the extra check). Lines 10, 11, 12 all run n times . In total, we have 1 + 6n + n^2 - n = n^2 + 5n + 1 number of assignments.

