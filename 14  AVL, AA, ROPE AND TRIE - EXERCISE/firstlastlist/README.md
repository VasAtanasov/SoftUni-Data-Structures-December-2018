# **Problem 1.	First / Last List**

You need to implement a “first / last list” data structure that performs fast enough the following operations:
- *Add(element)*
  - Adds an element to the structure (duplicate elements are accepted)
- *Count*
  - Returns the number of elements in the structure
- *First(count)*
  - Returns the earliest added count elements (in the order of addition from first to last)
  - Throws an exception in case the structure holds less than count elements
- *Last(count)*
  - Returns the latest added count elements (in the order of addition from last to first)
  - Throws an exception in case the structure holds less than count elements
- *Min(count)*
  - Returns the smallest count elements (ordered from the smallest to the largest as first criteria and by the order of adding as second criteria)
  - Throws an exception in case the structure holds less than count elements
  - Note: the elements should be comparable in order to find the minimal element
- *Max(count)*
  - Returns the biggest count elements (ordered from the biggest to the smallest as first criteria and by the order of adding as second criteria)
  - Throws an exception in case the structure holds less than count elements
  - Note: the elements should be comparable in order to find the maximal element
- *Remove-All(element)*
  - Removes all elements that are equal to the passed element
  - Returns the number of removed elements or 0 when no elements are matched
  - Note: the elements should be comparable in order to find which pairs of elements are equal
- *Clear()*
  - Removes all elements from the data structure
  
### **Input and Output**

You are given a Java project holding the interface IFirstLastList, the unfinished class FirstLastList and tests covering its functionality and its performance.
Your task is to finish this class to make the tests run correctly.
- You are not allowed to change the tests.
- You are not allowed to change the interface.
