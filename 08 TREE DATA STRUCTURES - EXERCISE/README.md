# Homework: Basic Tree Data Structures

This document defines the homework assignments for the "Data Structures" course @ Software University.

## Problem 0. Introduction

You are given a tree of N nodes represented as a set of N-1 pairs of nodes (parent node, child node). Below are the operations that you are going implement.

<table>
<thead>
<tr>
<th>Input</th>
<th>Output</th>
<th>Tree</th>
<th>Definitions</th>
</tr>
</thead>
<tbody>
<tr>
<td>9<br>7 19<br>7 21<br>7 14<br>19 1<br>19 12<br>19 31<br>14 23<br>14 6<br>27<br>43</td>
<td>N = 9<br>Nodes: 7->19, 7->21, 7->14, 19->1, 19->12, 19->31, 14->23, 14->6<br>P = 27<br>S = 43</td>
<td><img src="./media/image1.png" alt=""></td>
<td>Root node: 7<br>Leaf nodes: 1, 6, 12, 21, 23, 31<br>Middle nodes: 14, 19<br>Leftmost deepest 
node: 1<br>Longest path:<br>7 -> 19 -> 1 (length = 3)<br>Paths of sum 27:<br>7 -> 19 -> 1<br>7 -> 
14 -> 6<br>Subtrees of sum 43:<br>14 + 23 + 6</td>
</tr>
</tbody>
</table>

### Problem 1. Root Node

Write a program to read the tree and find its root node:

<table>
<thead>
<tr>
<th>Input</th>
<th>Output</th>
<th>Tree</th>
</tr>
</thead>
<tbody>
<tr>
<td>9<br>7 19<br>7 21<br>7 14<br>19 1<br>19 12<br>19 31<br>14 23<br>14 6</td>
<td>Root node: 7</td>
<td><img src="./media/image1.png" alt=""></td>
</tr>
</tbody>
</table>

### Hints

Use the recursive Tree\<T> definition. Keep the value, parent and children for each tree node:

![](./media/image2.png)

Modify the Tree\<T> constructor to assign a parent for each child node:

![](./media/image3.png)

Use a dictionary to map nodes by their value. This will allow you to find the tree nodes during the tree construction (when you read the input data, you get the node values):

![](./media/image4.png)

Write a method to find the tree node by its value or create a new node if it does not exist:

![](./media/image5.png)

Create a method for adding an edge to the tree

![](./media/image6.png)

Now you are ready to create the tree. You are given the tree edges (parent + child). Use the dictionary to lookup the parent and child nodes by their values:

![](./media/image7.png)

Finally, you can find the root (the node that has no parent)

![](./media/image8.png)

### Problem 2. Print Tree

Write a program to read the tree from the console and print it in the following format (each level indented +2 spaces):

<table>
<thead>
<tr>
<th>Input</th>
<th>Output</th>
<th>Tree</th>
</tr>
</thead>
<tbody>
<tr>
<td>9<br>7 19<br>7 21<br>7 14<br>19 1<br>19 12<br>19 31<br>14 23<br>14 6</td>
<td>7<br>&nbsp;&nbsp;19<br>&nbsp;&nbsp;&nbsp;&nbsp;1<br>&nbsp;&nbsp;&nbsp;&nbsp;12<br>&nbsp;&nbsp;&nbsp;&nbsp;31<br>&nbsp;&nbsp;21<br>&nbsp;&nbsp;14<br>&nbsp;&nbsp;&nbsp;&nbsp;23<br>&nbsp;&nbsp;6 </td>
<td><img src="./media/image1.png" alt=""></td>
</tr>
</tbody>
</table>

### Hints

Find the root and recursively print the tree

### Problem 3. Leaf Nodes

Write a program to read the tree and find all leaf nodes (in increasing order):

<table>
<thead>
<tr>
<th>Input</th>
<th>Output</th>
<th>Tree</th>
</tr>
</thead>
<tbody>
<tr>
<td>9<br>7 19<br>7 21<br>7 14<br>19 1<br>19 12<br>19 31<br>14 23<br>14 6</td>
<td>Leaf nodes: 1 6 12 21 23 31</td>
<td><img src="./media/image1.png" alt=""></td>
</tr>
</tbody>
</table>

### Hints

Find the all nodes that have no children

### Problem 4. Middle Nodes

Write a program to read the tree and find all middle nodes (in increasing order):

<table>
<thead>
<tr>
<th>Input</th>
<th>Output</th>
<th>Tree</th>
</tr>
</thead>
<tbody>
<tr>
<td>9<br>7 19<br>7 21<br>7 14<br>19 1<br>19 12<br>19 31<br>14 23<br>14 6</td>
<td>Middle nodes: 14 19</td>
<td><img src="./media/image1.png" alt=""></td>
</tr>
</tbody>
</table>

Hints

![](./media/image9.png)

### Problem 5. \* Deepest Node

Write a program to read the tree and find its deepest node (leftmost):

<table>
<thead>
<tr>
<th>Input</th>
<th>Output</th>
<th>Tree</th>
</tr>
</thead>
<tbody>
<tr>
<td>9<br>7 19<br>7 21<br>7 14<br>19 1<br>19 12<br>19 31<br>14 23<br>14 6</td>
<td>Deepest node: 1</td>
<td><img src="./media/image1.png" alt=""></td>
</tr>
</tbody>
</table>

### Problem 6. Longest Path

Find the longest path in the tree (the leftmost if several paths have the same longest length)

<table>
<thead>
<tr>
<th>Input</th>
<th>Output</th>
<th>Tree</th>
</tr>
</thead>
<tbody>
<tr>
<td>9<br>7 19<br>7 21<br>7 14<br>19 1<br>19 12<br>19 31<br>14 23<br>14 6</td>
<td>Longest path: 7 19 1</td>
<td><img src="./media/image1.png" alt=""></td>
</tr>
</tbody>
</table>

### Problem 7. All Paths With a Given Sum

Find all paths in the tree with given sum of their nodes (from the leftmost to the rightmost)

<table>
<thead>
<tr>
<th>Input</th>
<th>Output</th>
<th>Tree</th>
</tr>
</thead>
<tbody>
<tr>
<td>9<br>7 19<br>7 21<br>7 14<br>19 1<br>19 12<br>19 31<br>14 23<br>14 6<br>27</td>
<td>Paths of sum 27:<br>7 19 1<br>7 14 6 </td>
<td><img src="./media/image1.png" alt=""></td>
</tr>
</tbody>
</table>

### Problem 8. \* All Subtrees With a Given Sum

Find all subtrees with given sum of their nodes (from the leftmost to the rightmost). Print subtrees in pre-order sequence

<table>
<thead>
<tr>
<th>Input</th>
<th>Output</th>
<th>Tree</th>
</tr>
</thead>
<tbody>
<tr>
<td>9<br>7 19<br>7 21<br>7 14<br>19 1<br>19 12<br>19 31<br>14 23<br>14 6<br>43</td>
<td>Subtrees of sum 43: 14 23 6</td>
<td><img src="./media/image1.png" alt=""></td>
</tr>
</tbody>
</table>

<p><b>Java Impelementation: <a href="./homeweork">homework</a></b></p>

## Exercises: Binary Search Trees

This document defines the exercise assignments for the "Data Structures" course @ Software University. https://judge.softuni.bg/Contests/607/Binary-Search-Trees-Java-Exercise

### Implement BST Operations

You are given a skeleton, in which you will find implemented the following operations:

- void Insert(T) – Recursive implementation
- void EachInOrder(Action\<T>) – In-Order traversal
- bool Contains(T) – Iterative implementation
- BST\<T> Search(T) – Returns copy of the BST
- IEnumerable\<T> Range(T, T) – Returns collection with the elements found in the BST. Both borders are inclusive.
- DeleteMin() – Deletes the smallest element in the tree. Throws exception if the tree is empty.

You will need to implement the rest of the operations, that are defined below:

<table>
<thead>
<tr>
<th>C# Method</th>
<th>Java Method</th>
<th>Return Type</th>
<th>Exception C#/Java </th>
</tr>
</thead>
<tbody>
<tr>
<td>DeleteMax()</td>
<td>deleteMax()</td>
<td>void</td>
<td>C# - InvalidOperationException<br>Java -IllegalArgumentException</td>
</tr>
<tr>
<td>Count()</td>
<td>size()</td>
<td>int</td>
<td></td>
</tr>
<tr>
<td>Rank(T)</td>
<td>rank(T)</td>
<td>int</td>
<td></td>
</tr>
<tr>
<td>Select(int)</td>
<td>select(int)</td>
<td>T</td>
<td>C# - InvalidOperationException</td>
</tr>
<tr>
<td>Ceiling(T)</td>
<td>ceiling(T)</td>
<td>T</td>
<td>C# - InvalidOperationException</td>
</tr>
<tr>
<td>Floor(T)</td>
<td>floor(T)</td>
<td>T</td>
<td>C# - InvalidOperationException</td>
</tr>
<tr>
<td>Delete(T)</td>
<td>delete(T)</td>
<td>void</td>
<td>C# - InvalidOperationException<br>Java - IllegalArgumentException</td>
</tr>
</tbody>
</table>

### Problem 1. Delete Max

Implement a method which deletes the max element in a BST (Binary Search Tree). If the tree is empty it should throw exception. The logic is similar to the DeleteMin() method, but you need to traverse the tree to the right.

![](./media/image10.png)

### Problem 2. Count

Implement a method which returns the count of elements in the BST.

![](./media/image11.png)

### Hints

In order to implement the count, we will create a new field in our Node class:

![](./media/image12.png)

Now we can create new method Count(Node), which will recursively find the count of elements:

![](./media/image13.png)

If our current node is null, we will return 0. Otherwise, we will return the count of our current node:

![](./media/image14.png)

Now we only have to modify our Insert() method. It will set the count of elements of our new node to the count of its children nodes plus itself:

![](./media/image15.png)

Next, we need to find a way to update the recalculate the count for each node when DeleteMin() is invoked. One way would be to change the DeleteMin() implementation to be recursive:

![](./media/image16.png)

What will happen if our tree is empty and we call DeleteMin()? Fix it. Our count is ready.

### Problem 3. Rank

Implement a method which returns the count of elements smaller than a given value.

![](./media/image17.png)

### Hints

Create a new recursive method that will return 0 if the node is null:

![](./media/image18.png)

Then, we need to compare the element with the value of the node we are currently looking at. If the element is smaller, we can go to the left. If its larger, we need to get the count of the left elements and go to the right. If we find the element, we will return the count of elements, smaller than it.

![](./media/image19.png)

You can try it out, it should work as expected.

### roblem 4. Select

Implement a method which accepts a number (n) and returns the first element which has exactly n elements smaller than it. Use the logic from Count() and Rank() to implement it.

![](./media/image20.png)

### Problem 5. Floor

Implement a method which finds (returns) the nearest smaller value than given in the BST. This operation is similar to DeleteMin().

![](./media/image21.png)

### Problem 6. Ceiling

Implement a method which finds (returns) the nearest larger value than given in the BST. This operation is similar to Floor() and DeleteMax().

![](./media/image22.png)

### Problem 7. Delete\*

Implement a method which deletes a node with given value.

![](./media/image23.png)
![](./media/image24.png)

<p><b>Java Impelementation: <a href="./binarysearchtree">Binary Search Tree</a></b></p>

<p><b>Document with tasks description: <a href="./Data-Strucutres-Basic-Trees-And-Binary-Search-Trees-Exercises.docx">Data-Strucutres-Basic-Trees-And-Binary-Search-Trees-Exercises.docx</a></b></p>