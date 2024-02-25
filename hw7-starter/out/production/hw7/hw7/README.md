# Discussion
explain which of your implementations (between OpenAddressingHashMap and ChainingHashMap) is a better choice to be used for the search engine.
Which approaches did you try to implement each hash table (e.g., probing strategies, rehashing strategies, etc.), and why did you choose them?
What specific tweaks to your implementation improved or made things worse? Make note of failed or abandoned attempts, if any.
In summary, describe all the different ways you developed, evaluated, and improved your hash tables.
Include all the benchmarking data, results, and analysis that contributed to your final decision on which implementation to use for the search engine.
Moreover, why did you choose your final Map implementation as the best one? Provide an analysis of your benchmark data and conclusions.
What results were surprising, and which were expected?

**ChainingHashMap:**

Space Complexity

Load factor of 0.75

Benchmark                                                                 (fileName)  Mode  Cnt          Score   Error   Units
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc               apache.txt  avgt    2   114678292.000           bytes
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc                  jhu.txt  avgt    2    18481800.000           bytes
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc               joanne.txt  avgt    2    18523916.000           bytes
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc               newegg.txt  avgt    2    65240160.000           bytes
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc            random164.txt  avgt    2   376494392.000           bytes
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc                 urls.txt  avgt    2    17805932.000           bytes

Load factor of 0.5

Benchmark                                                                 (fileName)  Mode  Cnt          Score   Error   Units
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc               apache.txt  avgt    2   136420396.000           bytes
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc                  jhu.txt  avgt    2    18488376.000           bytes
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc               joanne.txt  avgt    2    18287372.000           bytes
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc               newegg.txt  avgt    2    69494336.000           bytes
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc            random164.txt  avgt    2  2907688704.000           bytes
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc                 urls.txt  avgt    2    17792516.000           bytes

Time Complexity

Load factor of 0.75

Benchmark                                                                 (fileName)  Mode  Cnt          Score   Error   Units
JmhRuntimeTest.buildSearchEngine:+c2k.gc.time                             apache.txt  avgt    2          76.500              ms
JmhRuntimeTest.buildSearchEngine:+c2k.gc.time                                jhu.txt  avgt    2          27.000              ms
JmhRuntimeTest.buildSearchEngine:+c2k.gc.time                             joanne.txt  avgt    2          32.500              ms
JmhRuntimeTest.buildSearchEngine:+c2k.gc.time                             newegg.txt  avgt    2          76.000              ms
JmhRuntimeTest.buildSearchEngine:+c2k.gc.time                          random164.txt  avgt    2         126.500              ms
JmhRuntimeTest.buildSearchEngine:+c2k.gc.time                               urls.txt  avgt    2          18.000              ms

Load factor of 0.5

Benchmark                                                                 (fileName)  Mode  Cnt          Score   Error   Units
JmhRuntimeTest.buildSearchEngine:+c2k.gc.time                             apache.txt  avgt    2         115.000              ms
JmhRuntimeTest.buildSearchEngine:+c2k.gc.time                                jhu.txt  avgt    2          36.500              ms
JmhRuntimeTest.buildSearchEngine:+c2k.gc.time                             joanne.txt  avgt    2          37.000              ms
JmhRuntimeTest.buildSearchEngine:+c2k.gc.time                             newegg.txt  avgt    2          78.500              ms
JmhRuntimeTest.buildSearchEngine:+c2k.gc.time                          random164.txt  avgt    2        1115.500              ms
JmhRuntimeTest.buildSearchEngine:+c2k.gc.time                               urls.txt  avgt    2          18.500              ms

**OpenAdressingHashMap**:

Space Complexity

Load factor of 0.75

Benchmark                                                                 (fileName)  Mode  Cnt          Score   Error   Units
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc               apache.txt  avgt    2   77578776.000           bytes
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc                  jhu.txt  avgt    2   18065240.000           bytes
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc               joanne.txt  avgt    2   17484156.000           bytes
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc               newegg.txt  avgt    2   63493592.000           bytes
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc            random164.txt  avgt    2  255422428.000           bytes
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc                 urls.txt  avgt    2   17831728.000           bytes

Load factor of 0.5

Benchmark                                                                 (fileName)  Mode  Cnt          Score   Error   Units
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc               apache.txt  avgt    2   73617184.000           bytes
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc                  jhu.txt  avgt    2   17829328.000           bytes
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc               joanne.txt  avgt    2   17461268.000           bytes
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc               newegg.txt  avgt    2   62648116.000           bytes
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc            random164.txt  avgt    2  251186972.000           bytes
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc                 urls.txt  avgt    2   17753904.000           bytes

Time Complexity

Load factor of 0.75

Benchmark                                                                 (fileName)  Mode  Cnt          Score   Error   Units
JmhRuntimeTest.buildSearchEngine:+c2k.gc.time                             apache.txt  avgt    2         19.000              ms
JmhRuntimeTest.buildSearchEngine:+c2k.gc.time                                jhu.txt  avgt    2         26.000              ms
JmhRuntimeTest.buildSearchEngine:+c2k.gc.time                             joanne.txt  avgt    2         14.500              ms
JmhRuntimeTest.buildSearchEngine:+c2k.gc.time                             newegg.txt  avgt    2          9.000              ms
JmhRuntimeTest.buildSearchEngine:+c2k.gc.time                          random164.txt  avgt    2         84.000              ms
JmhRuntimeTest.buildSearchEngine:+c2k.gc.time                               urls.txt  avgt    2         17.000              ms

Load factor of 0.5

Benchmark                                                                 (fileName)  Mode  Cnt          Score   Error   Units
JmhRuntimeTest.buildSearchEngine:+c2k.gc.time                             apache.txt  avgt    2         16.000              ms
JmhRuntimeTest.buildSearchEngine:+c2k.gc.time                                jhu.txt  avgt    2         22.500              ms
JmhRuntimeTest.buildSearchEngine:+c2k.gc.time                             joanne.txt  avgt    2         15.000              ms
JmhRuntimeTest.buildSearchEngine:+c2k.gc.time                             newegg.txt  avgt    2          8.500              ms
JmhRuntimeTest.buildSearchEngine:+c2k.gc.time                          random164.txt  avgt    2         83.000              ms
JmhRuntimeTest.buildSearchEngine:+c2k.gc.time                               urls.txt  avgt    2         16.500              ms



**Analysis on Benchmarking**

With a load factor of 0.75, the Chaining HashMap implementation underperformed the OpenAddressingHashMap in each of the sample test files.
In terms of space complexity, openAddressing outperformed chainingHashmap in 5/6 files except for urls.txt. This is a reasonable outcome
as my Chaining implementation uses linked lists which takes up more space than a simple array since the node has to contain a pointer to another 
Entry in the chain alongside the key and value, values. 
With a load factor of 0.5, the Chaining HashMap implementation underperformed the openaddressing HashMap on each of the sample test files as well.
In terms of space complexity, OpenAddressingHashMap outperformed Chaining HashMap in each of the sample files. This is also not a surprise, as having a smaller
threshold to grow doesn't change the fact that nodes are more costly than array elements in terms of space.

Comparing between load factors, we see that time complexity for ChainingHashMap is higher with a load factor of 0.5 than
with a load factor of 0.75. The opposite trend was observed for OpenAddressingHashMap. OpenAddressingHashMap has a
smaller time score with a load factor of 0.5 than with a load factor of 0.75. I.e. it can be said that ChainingHashMap
does better in terms of time scores with a load factor of 0.75, whereas OpenAddressingHashMap does better in terms
of time scores with a load factor of 0.5. 

We also see that the space score for ChainingHashMap is better(lower) for files 1,2,3 with a load factor of 0.75, but for the
last 3 files it is better with a load factor of 0.5. For OpenAddressingHashMap, all the files when run with a load
factor of 0.5 produced a better space-time score than with 0.75. No trend was observed about the sizes of scores and which load factor performed better for ChainingHashMap, however it
seemed that at lower load factors, OpenAddressingHashMap had a smaller score sizes. 

Summarizing the above, it can be said that at a load factor of 0.5, OpenAddressingHashMap performed better since it
had a lower time score than ChainingHashMap and a lower space score than ChainingHashMap in majority of files.
At a load factor of 0.5, OpenAddressingHashMap does better in terms of time and space complexities, whereas at a load factor
of 0.75 ChainingHashMap does better in terms of space scores.

Overall, I believe that using a load factor of 0.5 with OpenAddressingHashMap for the search engine performs the best
since it strikes a good balance between the time scores and space scores. The difference in rehashing strategies was
discussed above (varying the load factor), and the probing strategy that I thought was most effective was a combination of quadratic probing 
with linear probing. I implemented quadratic probing, but I ran into the issue of very long loops since quadratic probing skips over
some empty spaces and has to loop around the hashTable again multiple times to hit open spots. After this I added a limit to the number of times
quadratic probing can run, and if it passed this limit it would default to linear probing. This significantly sped up the run times and produced the 
data as shown.

**Analysis on implementation:**

In my implementation of the chaining hashtable, I used linked lists instead of using an arrayList, however using the latter would be more space and time efficient, as
you wouldnt have to traverse a bucket from start to get the last entry in the bucket. Using an arrayList also provides Random access. Insertion and deletion would be efficient
since we don't worry about keeping entries in the bucket sorted so when a new entry is added to a bucket it can be directly added to the front or the end in o(1) time.
For my implementation of openAddressing hashTables, when a collision occurs, I utilized a quadratic probing strategy for
the first 5 searches then defaulted to linear probing if those 5 searches were unsuccessful. This strategy seemed to me a logical solution for
reducing clustering and when that didn't work resort to linearly searching as that would then find an empty Entry. Using quadratic probing also 
ensures the probing sequences are spread out, making it less likely for keys to collide and form clusters which results in a more even distribution of keys across the hashtable.
For both chaining and open addressing hashtable, when the load factor reached its threshold (0.75), I utilized a grow function that increased the table capacity and utilized
insert to reinsert previous valid keys into the new hashtable. While inserting I also made sure to keep the size consistent by decreasing the size each time I rehashed entries
into the new hashtable since these entries were present in the previous table. In rehashing, I made sure to only preserve entries that were valid (not tombstones) and by doing so
removed all the Tombstones. At first, I made my primes list an o(n) operation, scanning through each prime hashtable size when I wanted to grow instead of just keeping a
variable to keep track of the index of the prime table which would make setting the size o(1). I also improved efficiency by switching to doubling the capacity of the hashtable and
adding 1 once the table of primes was exhausted of elements. To improve the efficiency of my rehashing in my grow function, instead of creating a new temporary array to hold all the entries from 
the original array, I was able to switch to doing a direct copy from the old hashtable into the new one, reducing the space and time complexity overall.