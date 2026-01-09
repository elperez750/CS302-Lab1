# CS 302 - Advanced Data Structures Labs

Lab assignments for CS 302 at Central Washington University. Each lab focuses on implementing and analyzing fundamental data structure algorithms.

---

## Lab 1: Binary Search Tree Analysis

### Overview
Implementation of BST traversal and range query algorithms using both iterative and recursive approaches.

### Problem 1: Tree Equality Comparison

**Problem Statement:**  
Given two BST root nodes, determine if both trees store the same set of numbers (structure may differ).

**Function Signatures:**
```java
boolean problem1Iterative(Node root1, Node root2)
boolean problem1Recursive(Node root1, Node root2)
```

**Key Considerations:**
- Trees with different structures can store identical values
- Must handle empty trees and single-node trees
- Both solutions should have O(n) time complexity

**Example:**
```
Tree 1:     5           Tree 2:     3
           / \                     / \
          3   7                   1   5
         / \                           \
        1   4                           7
                                       /
                                      4

Both contain {1, 3, 4, 5, 7} → Return true
```

### Problem 2: Range Sum in BST

**Problem Statement:**  
Given a BST root and range [min, max], compute the sum of all keys within the range (inclusive).

**Function Signatures:**
```java
int problem2Iterative(Node root, int min, int max)
int problem2Recursive(Node root, int min, int max)
```

**Key Considerations:**
- min and max may not exist in the tree
- Optimize by pruning branches outside range
- Handle edge cases: empty tree, all nodes outside range

**Example:**
```
Tree:       10
           /  \
          5    15
         / \   / \
        3   7 13  18

Range: [7, 15]
Sum: 7 + 10 + 13 + 15 = 45
```

### Implementation Guidelines

**Constraints:**
- Implement only within the four specified functions
- Do not add helper methods or modify existing code
- No console output (no `System.out.println()`)
- Use provided helper functions as needed

**Testing:**
```bash
javac Lab1.java
java Lab1
```

The test suite uses a fixed random seed for reproducibility. Passing these tests is necessary but not sufficient for full correctness.

### Analysis

**Expected Time Complexities:**
- Problem 1: O(n₁ + n₂) where n₁, n₂ are tree sizes
- Problem 2: O(h + k) where h is height, k is keys in range

**Space Complexities:**
- Iterative: O(h) for stack space
- Recursive: O(h) for call stack

---

## Repository Structure
```
cs302-labs/
├── Lab1/
│   ├── Lab1.java
│   └── README.md
├── Lab2/
│   └── (upcoming)
└── README.md
```

## Course Information
- **Course:** CS 302 - Advanced Data Structures
- **Instructor:** Shangyue Zhu
- **Term:** Winter 2026
- **Institution:** Central Washington University

## Academic Integrity
This repository contains my personal solutions to course assignments. Please adhere to your institution's academic integrity policies.
