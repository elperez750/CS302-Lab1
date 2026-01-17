import java.io.*;
import java.util.*;

public class Lab1
{

    /**
     *  Problem 1: Determine if two given binary search trees store the same numbers.
        Iterative Solution
     */
    private static boolean problem1Iterative(Node t1, Node t2)
    {
        // This will help us process nodes
        Queue<Node> nodesToProcess = new LinkedList<>();

        // This will store unique values of tree 1
        Set<Integer> uniqueT1Nodes = new HashSet<>();

        // This will store unique values of tree 2
        Set<Integer> uniqueT2Nodes = new HashSet<>();


        // We start by adding the root to our nodes to process
        nodesToProcess.add(t1);


        while(!nodesToProcess.isEmpty()) {
            // Remove top node from queue
            Node currentNode = nodesToProcess.poll();

            // If it has a right child, we add that to be processed
            if (currentNode.right != null) {
                nodesToProcess.add(currentNode.right);
            }

            // If it has a left child, we add that to be processed.
            if (currentNode.left != null) {
                nodesToProcess.add(currentNode.left);
            }

            // Add to unique nodes
            // Since it is a set, there will be no duplicates
            uniqueT1Nodes.add(currentNode.key);
        }


        // Now we work on the second tree
        nodesToProcess.add(t2);


        while(!nodesToProcess.isEmpty()) {

            // Same process, remove top node from queue
            Node currentNode = nodesToProcess.poll();

            // If the node has a left child, we add it to our nodes to process
            if(currentNode.left != null) {
                nodesToProcess.add(currentNode.left);
            }

            // If the node has a right child, we add it to our nodes to process
            if (currentNode.right != null) {
                nodesToProcess.add(currentNode.right);
            }

            // Add the node to our set
            uniqueT2Nodes.add(currentNode.key);


            // We continue until we have no more nodes to process.
        }

        // We return if the two sets are equal
        // Since the problem does not specify whether letters must have the same occurences, we only check to see if they contain the same letter.
        return uniqueT2Nodes.equals(uniqueT1Nodes);



    }
    
    
    
     /**
     *  Problem 1: Determine if two given binary search trees store the same numbers.
        Recursive Solution
     */
    private static boolean problem1Recursive(Node t1, Node t2)
    {

        // We define a set of unique nodes for both trees
        // Similar to the iterative solution
        Set<Integer> uniqueT1Nodes = new HashSet<>();
        Set<Integer> uniqueT2Nodes = new HashSet<>();


        // We define two helpers down below to help with the recursion
        problem1Helper(t1, uniqueT1Nodes);
        problem1Helper(t2, uniqueT2Nodes);


        // We return whether both sets are equal to each other
        return uniqueT1Nodes.equals(uniqueT2Nodes);
    }


    // Helper function for recursive solution
    private static void problem1Helper(Node node, Set<Integer> uniqueNodes) {

        // If no node exists, we've reached the bottom of the tree, so we backtrack.
        if (node == null) {
            return;
        }

        // We recursively call the helper function on the left subtree
        problem1Helper(node.left, uniqueNodes);

        // We recursively call the helper function on the right subtree
        problem1Helper(node.right, uniqueNodes);

        // We add the node's value after both recursive calls exit.
        uniqueNodes.add(node.key);

        // We can also add to our set before the recursive calls, since we have checked to see whether the node is null.
        // Either approach works fine for this case.

    }
    
    

    /**
     *  Problem 2: Determine the sum of all node's keys between min and max (inclusive).
        Iterative Solution
     */


    /*
    The solution will make use of the BST structure to efficiently add nodes to search.

    Since we know that all nodes to the left of the current node are smaller and the nodes to the right are greater,
    we check to see if more nodes from the left or right can be added. This way, we can avoid checking unnecesarry nodes.
     */
    private static int problem2Iterative(Node root, int min, int max)
    {
        int runningTotal = 0;
        Queue<Node> nodesToProcess = new LinkedList<>();
        nodesToProcess.add(root);

        while(!nodesToProcess.isEmpty()) {
            // Remove node from the queue
            Node currentNode = nodesToProcess.poll();

            // This will primarily be to check for the first node that we added blindly
            // When we add other nodes to the queue, we guarantee they will be in the range due to the conditionals below.
            if (currentNode.key >= min && currentNode.key <= max) {
                runningTotal += currentNode.key;
            }

            // If the current node was greater than the min, then there are possible nodes to process in the left subtree
            if (currentNode.left != null && currentNode.key > min) {
                nodesToProcess.add(currentNode.left);
            }

            // If the current node is less than the max, that means there are potential nodes to process in the right subtree
            if (currentNode.right != null && currentNode.key < max) {
                nodesToProcess.add(currentNode.right);
            }

        }

        return runningTotal;
    }
    
    
    
     /**
     *  Problem 2: Determine the sum of all node's keys between min and max (inclusive).
        Recursive Solution
     */
    private static int problem2Recursive(Node root, int min, int max)
    {

        if (root == null) {
            return 0;
        }


        // Initialize runningTotal to 0 for every call.
        int runningTotal = 0;


        // If the key is within the range (inclusive), then we add the key to the running total
        if (root.key >= min && root.key <= max) {
            runningTotal = root.key;
        }

        // If the key is greater than min, that means there could potentially be keys in the range in the left subtree
        if (root.key > min) {
            runningTotal += problem2Recursive(root.left, min, max);

        }

        // If the key is less than max, that means there could potentially be keys in the range in the right subtree.
        if (root.key < max) {
            runningTotal += problem2Recursive(root.right, min, max);
        }

        // After the calls for the specific call stack are done, we return the running total.
        return runningTotal;

    }



    // ---------------------------------------------------------------------
    // Do not change any of the code below!

    private static class Node
    {
        public Node left = null;
        public Node right = null;

        public int key;

        public Node(int key)
        {
            this.key = key;
        }
    }

    private static void insert(Node root, int key)
    {
        if (root == null)
        {
            root = new Node(key);
            return;
        }

        for (Node node = root;;)
        {
            if (key < node.key)
            {
                if (node.left == null)
                {
                    node.left = new Node(key);
                }

                node = node.left;
            }
            else if (key > node.key)
            {
                if (node.right == null)
                {
                    node.right = new Node(key);
                }

                node = node.right;
            }
            else // key = node.key
            {
                // Nothing to do, because no value to update.
                break;
            }
        }
    }
    
    
    private static boolean find(Node root, int key){
      
      if(root == null)
      {
         return false;
      }
    
      if(root.key == key)
      {
           return true;
      }
      
      if(key < root.key)
      {
         return find(root.left, key);
      }
      else
      {
         return find(root.right, key);
      }
    
    }
    

    private static int[] getInOrder(Node root)
    {
        if (root == null) return new int[] { };

        Stack<Node> stack = new Stack<Node>();
        ArrayList<Integer> orderList = new ArrayList<Integer>();

        for (Node node = root;;)
        {
            if (node == null)
            {
                if (stack.empty()) break;

                node = stack.pop();
                orderList.add(node.key);
                node = node.right;
            }
            else
            {
                stack.push(node);
                node = node.left;
            }
        }

        int[] order = new int[orderList.size()];
        for (int i = 0; i < order.length; i++)
        {
            order[i] = orderList.get(i);
        }

        return order;
    }

    private static int[] getPreOrder(Node root)
    {
        if (root == null) return new int[] { };

        Stack<Node> stack = new Stack<Node>();
        ArrayList<Integer> orderList = new ArrayList<Integer>();

        for (Node node = root;;)
        {
            if (node == null)
            {
                if (stack.empty()) break;

                node = stack.pop();
                node = node.right;
            }
            else
            {
                orderList.add(node.key);
                stack.push(node);
                node = node.left;
            }
        }

        int[] order = new int[orderList.size()];
        for (int i = 0; i < order.length; i++)
        {
            order[i] = orderList.get(i);
        }

        return order;
    }

    private static int[] getPostOrder(Node root)
    {
        if (root == null) return new int[] { };

        Stack<Node> stack = new Stack<Node>();
        Stack<Integer> stackCtr = new Stack<Integer>();
        ArrayList<Integer> orderList = new ArrayList<Integer>();

        stack.push(root);
        stackCtr.push(0);

        while (!stack.empty())
        {
            int ctr = stackCtr.pop();
            Node node = stack.peek();

            if (ctr == 0)
            {
                // First visit.
                stackCtr.push(1);

                if (node.left != null)
                {
                    stack.push(node.left);
                    stackCtr.push(0);
                }
            }
            else if (ctr == 1)
            {
                // Second visit.
                // Left subtree done.
                stackCtr.push(2);

                if (node.right != null)
                {
                    stack.push(node.right);
                    stackCtr.push(0);
                }
            }
            else // ctr >= 2
            {
                // Third visit.
                // Right subtree done.
                stack.pop();
                orderList.add(node.key);
            }
        }

        int[] order = new int[orderList.size()];
        for (int i = 0; i < order.length; i++)
        {
            order[i] = orderList.get(i);
        }

        return order;
    }

    // ---------------------------------------------------------------------

    private static final int LabNo = 1;
    private static final String classNum = "CS 302";

    private static final Random rng = new Random(654321);

    public static void main(String args[])
    {
        System.out.println(classNum + " -- Lab " + LabNo);

        testProblems(1, 1);
        testProblems(1, 2);
        testProblems(2, 1);
        testProblems(2, 2);
    }

    private static boolean testProblem1(int[][] testCase, int style)
    {
        int[] tree1 = testCase[0];
        int[] tree2 = testCase[1];

        boolean solution = testCase[2][0] == 1;

        Node root1 = new Node(tree1[0]);
        Node root2 = new Node(tree2[0]);

        for (int i = 1; i < tree1.length; i++)
        {
            insert(root1, tree1[i]);
            insert(root2, tree2[i]);
        }
        
        boolean answer;
        
        if(style == 1)
        {
           answer = problem1Iterative(root1, root2); 
        }else{
           answer = problem1Recursive(root1, root2);
        } 

        return solution == answer;
    }

    private static boolean testProblem2(int[][] testCase, int style)
    {
        int[] tree = testCase[0];
        int[] range = testCase[1];
        int solution = testCase[2][0];

        Node root = new Node(tree[0]);

        for (int i = 1; i < tree.length; i++)
        {
            insert(root, tree[i]);
        }

        int answer;
        
        if(style == 1)
        {
           answer = problem2Iterative(root, range[0], range[1]); 
        }else{
           answer = problem2Recursive(root, range[0], range[1]);
        } 

        return answer == solution;
    }

    private static void testProblems(int prob, int style)
    {
        int noOfLines = 100000;

        System.out.println("-- -- -- -- --");
        
        switch (style)
        {
            case 1:
                  System.out.println(noOfLines + " test cases for problem " + prob + " iterative solution.");
                  break;
            case 2:
                  System.out.println(noOfLines + " test cases for problem " + prob + " recursive solution.");
                  break;
        }
        
        boolean passedAll = true;

        for (int i = 1; i <= noOfLines; i++)
        {
            boolean passed = false;
            boolean exce = false;
            int[][] testCase = null;

            try
            {
                switch (prob)
                {
                    case 1:
                        testCase = createProblem1(i);
                        passed = testProblem1(testCase, style);
                        break;

                    case 2:
                        testCase = createProblem2(i);
                        passed = testProblem2(testCase, style);
                        break;
                }
            }
            catch (Exception ex)
            {
                passed = false;
                exce = true;
            }

            if (!passed)
            {
                System.out.println("Test " + i + " failed!" + (exce ? " (Exception)" : ""));

                if (prob == 1)
                {
                    System.out.println("  tree 1: " + Arrays.toString(testCase[0]));
                    System.out.println("  tree 2: " + Arrays.toString(testCase[1]));
                }
                else
                {
                    System.out.println("    tree: " + Arrays.toString(testCase[0]));
                    System.out.println("   range: " + Arrays.toString(testCase[1]));
                }
                passedAll = false;
                break;
            }
        }

        if (passedAll)
        {
            System.out.println("All test passed.");
        }

    }

    private static void shuffle(int[] arr)
    {
        for (int i = 0; i < arr.length; i++)
        {
            int rndInd = rng.nextInt(arr.length - i) + i;

            int tmp = arr[i];
            arr[i] = arr[rndInd];
            arr[rndInd] = tmp;
        }
    }

    private static int[] getRandomNumbers(int size)
    {
        int maxSize = size * 10;

        int[] integers = new int[maxSize];
        for (int i = 0; i < maxSize; i++)
        {
            integers[i] = i;
        }

        shuffle(integers);

        return Arrays.copyOf(integers, size);
    }

    private static int[][] createProblem1(int max)
    {
        int maxSize = max < 250 ? max : 250;
        int size = rng.nextInt(maxSize) + 5;

        int equal = rng.nextInt(2);

        int[] tree1 = getRandomNumbers(size);
        int[] tree2 = tree1.clone();

        if (equal == 0)
        {
            int ind = rng.nextInt(tree1.length);
            tree1[ind]+=6543;    
        }
        
        int chance = rng.nextInt(10);
        
        if(chance == 2)
        {
            int ind1 = rng.nextInt(tree1.length);
            int ind2 = rng.nextInt(tree1.length);
            int temp = tree1[ind1];
            tree1[ind1] = tree1[ind2];
            tree1[ind2] = temp; 
        
        }


        return new int[][]
        {
            tree1,
            tree2,
            new int[] { equal }
        };
    }

    private static int[][] createProblem2(int max)
    {
        int maxSize = max < 250 ? max : 250;
        int size = rng.nextInt(maxSize) + 5;

        int[] keys = getRandomNumbers(2 * size);

        int minKey = keys[rng.nextInt(2 * size)];
        int maxKey = keys[rng.nextInt(2 * size)];

        shuffle(keys);

        int[] tree = Arrays.copyOf(keys, size);

        int sum = 0;

        for (int i = 0; i < tree.length; i++)
        {
            if (tree[i] >= minKey && tree[i] <= maxKey)
            {
                sum += tree[i];
            }
        }

        return new int[][]
        {
            tree,
            new int[] { minKey, maxKey },
            new int[] { sum }
        };
    }

}
