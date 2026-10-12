
import java.util.Scanner;

class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;

    TreeNode(int data) {
        this.data = data;
    }
}

public class BinarySearchTree {
    private TreeNode root;

    public void insert(int data) {
        root = insertNode(root, data);
        System.out.println("Insertion completed.");
    }

    private TreeNode insertNode(TreeNode node, int data) {
        if (node == null) {
            return new TreeNode(data);
        }

        if (data < node.data) {
            node.left = insertNode(node.left, data);
        } else if (data > node.data) {
            node.right = insertNode(node.right, data);
        }

        return node;
    }

    public boolean search(int data) {
        TreeNode current = root;

        while (current != null) {
            if (current.data == data) {
                return true;
            }

            current = data < current.data
                    ? current.left : current.right;
        }

        return false;
    }

    public void delete(int data) {
        if (!search(data)) {
            System.out.println("Value not found.");
            return;
        }

        root = deleteNode(root, data);
        System.out.println("Deletion completed.");
    }

    private TreeNode deleteNode(TreeNode node, int data) {
        if (node == null) {
            return null;
        }

        if (data < node.data) {
            node.left = deleteNode(node.left, data);
        } else if (data > node.data) {
            node.right = deleteNode(node.right, data);
        } else {
            if (node.left == null) {
                return node.right;
            }

            if (node.right == null) {
                return node.left;
            }

            node.data = findMinimum(node.right);
            node.right = deleteNode(node.right, node.data);
        }

        return node;
    }

    private int findMinimum(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }

        return node.data;
    }

    public void inOrder() {
        inOrderTraversal(root);
        System.out.println();
    }

    private void inOrderTraversal(TreeNode node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.print(node.data + " ");
            inOrderTraversal(node.right);
        }
    }

    public void preOrder() {
        preOrderTraversal(root);
        System.out.println();
    }

    private void preOrderTraversal(TreeNode node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preOrderTraversal(node.left);
            preOrderTraversal(node.right);
        }
    }

    public void postOrder() {
        postOrderTraversal(root);
        System.out.println();
    }

    private void postOrderTraversal(TreeNode node) {
        if (node != null) {
            postOrderTraversal(node.left);
            postOrderTraversal(node.right);
            System.out.print(node.data + " ");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BinarySearchTree bst = new BinarySearchTree();

        while (true) {
            System.out.println("\n=== Binary Search Tree ===");
            System.out.println("1. Insert");
            System.out.println("2. Search");
            System.out.println("3. Delete");
            System.out.println("4. In-order Traversal");
            System.out.println("5. Pre-order Traversal");
            System.out.println("6. Post-order Traversal");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input.");
                scanner.nextLine();
                continue;
            }

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter value: ");
                    bst.insert(scanner.nextInt());
                    break;

                case 2:
                    System.out.print("Enter value to search: ");
                    int target = scanner.nextInt();
                    System.out.println(
                            bst.search(target)
                                    ? "Value found."
                                    : "Value not found.");
                    break;

                case 3:
                    System.out.print("Enter value to delete: ");
                    bst.delete(scanner.nextInt());
                    break;

                case 4:
                    System.out.print("In-order: ");
                    bst.inOrder();
                    break;

                case 5:
                    System.out.print("Pre-order: ");
                    bst.preOrder();
                    break;

                case 6:
                    System.out.print("Post-order: ");
                    bst.postOrder();
                    break;

                case 7:
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
