# Discussion

## Unit testing TreapMap
In the README file, discuss the difficulties you encountered in testing rotations for TreapMap implementation.
Provide a few examples of test cases you used, and elaborate on them.
You are encouraged to draw little ASCII trees to illustrate the test cases.

It was challenging to create the rotate right and left test since I had to work with the random priorities given to me by the specific
seed I chose (40). I figured out I could place the nodes for left and right rotate by printing the first 10 priorities given by the seed, 
and then creating treaps based on the key priority that would cause left or right rotations when inserted. For example, for testing right 
rotations I used this treap configuration:

            c:a:-1170874532                                     b:b:-1749212617
            /                                                   /       \
        b:b:-1749212617         (right rotation)->      a:c:95830475 c:a:-1170874532
            
        /
    a:c:95830475

For left rotations I used this configuration:

    a:a:-1170874532                                        b:b:-1749212617 
            \                                               /           \
            b:b:-1749212617        (left rotation)->    a:a:-1170874532  c:c:95830475
                \
                c:c:95830475

For the other edge cases, like testing rotate right when the middle node has right children 
and rotate left when the middle node has left children I utilized remove since it was easier for me 
to invoke a case where either of the two edge cases were present. For example, for rotate left when
the middle node has left children case I used this configuration:

        b:b:-1749212617                                                     b:b:-1749212617
        /           \                                                        /           \
    a:a:-1170874532 d:d:-1502686769                                   a:a:-1170874532   g:g:-680157218
    /   \               /       \                                                          /     \
                c:c:95830475 g:g:-680157218      (remove d)->                        c:c:95830475
                                /                                                           \
                        f:f:1702710456                                                     f:f:1702710456
                            /                                                                       \
                    e:e:1929790192                                                               e:e:1929790192

for rotate right when the middle node has right children I used this configuration:
    
                    k:a:-1749212617                                                     n:a:-1502686769
                    /             \                                                    /            
            g:a:-895793374        n:a:-1502686769                                  l:a:-1170874532     
            /           \              /                    (remove k,j,f,h)->     /            \
    f:a:-680157218 h:a:1702710456  l:a:-1170874532                              g:a:-895793374   m:a:95830475
                                        \
                                       m:a:95830475 


## Benchmarking
Please include the results from running JMH for various data sets in your README file (include the raw data).
Then, describe your observations and try to explain (justify) them using your understanding of the code you're benchmarking.
(In other words, why are the numbers as they are?)

DATA_FILE = hotel_california
Benchmark                  Mode  Cnt  Score   Error  Units
JmhRuntimeTest.arrayMap    avgt    2  0.306          ms/op
JmhRuntimeTest.avlTreeMap  avgt    2  0.461          ms/op
JmhRuntimeTest.bstMap      avgt    2  0.204          ms/op
JmhRuntimeTest.treapMap    avgt    2  0.211          ms/op

DATA_FILE = federalist01
Benchmark                  Mode  Cnt  Score   Error  Units
JmhRuntimeTest.arrayMap    avgt    2  3.572          ms/op
JmhRuntimeTest.avlTreeMap  avgt    2  5.085          ms/op
JmhRuntimeTest.bstMap      avgt    2  0.973          ms/op
JmhRuntimeTest.treapMap    avgt    2  1.145          ms/op

DATA_FILE = moby_dick
Benchmark                  Mode  Cnt     Score   Error  Units
JmhRuntimeTest.arrayMap    avgt    2  5303.753          ms/op
JmhRuntimeTest.avlTreeMap  avgt    2  5751.446          ms/op
JmhRuntimeTest.bstMap      avgt    2   156.345          ms/op
JmhRuntimeTest.treapMap    avgt    2   181.311          ms/op

DATA_FILE = pride_and_prejudice