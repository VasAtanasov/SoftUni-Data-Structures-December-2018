# **Lab: Interval Trees, K-d Trees**

This document defines the in-class exercises assignments the "Data Structures" course @ Software University. 

Submit your code in the SoftUni Judge - https://judge.softuni.bg/Contests/Practice/Index/644#0 

### **Problem 1.	Interval Tree**

You are given a skeleton. Implement the following operations:

- Interval SearchAny(int, int) -> returns any interval that intersects with a given lower and upper bound 
- IEnumerable<Interval> SearchAll(int, int) -> returns all intervals that intersect the given lower and upper bound

        public class IntervalTree
        {
            private Node root;
        
            public void Insert(double start, double end) { … }
        
            public Interval SearchAny(double start, double end) { … }
        
            public IEnumerable<Interval> SearchAll(double start, double end) { … }
        }
   
Solution
You will need to update the max endpoint whenever you insert (or delete/balance) a node

![](./media/image1.png)
 
GetMax() returns the node that has greater max endpoint, while guarding against null values

![](./media/image2.png)
 
Search for any interval that intersects given bounds

![](./media/image3.png)
 
Searching for all intervals will do a recursive DFS, so you will need one method to call the recursion

![](./media/image4.png)
 
And the actual DFS

![](./media/image5.png)

<p><b>Implementation: <a href="./intervaltree">Interval Tree</a></b></p>

 
