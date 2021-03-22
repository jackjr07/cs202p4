package com.jack;
/*
Jack Wanitkun
CS202 Program 5
This is the avl tree for program 5
I design as, I have a node that contains the user information, height, left and right.
then I have a tree that will interact with the testing interface.
 */
import java.util.Scanner;

//This is a node inside the tree - This is a AVl tree
//The balance is located with the insert function.
//The delete still have a problem.
class node{
    public node(){
        this.userNode = null;
        this.key = 0;
        this.left = null;
        this.right = null;
        this.height = 0;
    }
    public node(user userAdd){
        this.userNode = userAdd;
        this.key = userAdd.getKey();
        this.left = null;
        this.right = null;
        this.height = 0;
    }
    public int getKey(){
        return this.userNode.getKey();
    }
    public node addRoot(node root){
        root.left = null;
        root.right = null;
        root.height = 1;
        System.out.println("Add at root: " + root.userNode);
        return root;
    }
    public int findLocation(node curr, node userAdd){
        int addKey = userAdd.key;
        if(addKey < curr.key) {
            if(curr.left == null) {
                curr.left = userAdd;
                System.out.println("Add to root left" + userAdd.userNode);
                return 2;
            }else{
                System.out.println("recursive left");
                return findLocation(curr.left, userAdd);
            }
        }
        if(addKey >= curr.key){
            if(curr.right == null) {
                curr.right = userAdd;
                System.out.println("Add to root right" + userAdd.userNode);
                return 3;
            }else{
                System.out.println("recursive right");
                return findLocation(curr.right, userAdd);
            }
        }
        return 1;
    }
    /*
    public int getHeight(node root){
        if(root==null) return -1;
        return getHeightP(root);
    }

    private int getHeightP(node curr){
        int lMax = getHeightP(curr.left);
        int rMax = getHeightP(curr.right);
        if(lMax > rMax) {
            this.height = lMax+1;
            return (lMax +1);
        }
        else {
            this.height = rMax+1;
            return (rMax+1);
        }
    }*/
    //Try another height
    private int getHeight2(node curr){
        if(curr == null) return -1;
        return Math.max(getHeight2(curr.left), getHeight2(curr.right)) + 1;
    }

    public int display(node curr){
        if(curr == null){ return 0;}
       if(curr.left != null){
           display(curr.left);
       }
       curr.userNode.displayUser();
       System.out.println(curr.height);

       if(curr.right != null){
           display(curr.right);
       }
       return 1;
    }
    public node insertNode(node curr, user userAdd){
       int newKey = userAdd.getKey();
       if(curr == null){
           curr = new node(userAdd);

       }
       if(newKey > curr.key){
           curr.right = insertNode(curr.right, userAdd);
       }else if(newKey < curr.key){
           curr.left = insertNode(curr.left, userAdd);
       }
       //Balance
        int balanceFactor = getHeight2(curr.left) - getHeight2(curr.right);
       if(balanceFactor > 1){
           //1 -> left ||  -1 -> right @Left Sub tree
           int child_bf = getHeight2(curr.left.left) - getHeight2(curr.left.right);
           if(child_bf > 0){
               //Left Left Case - Right rotation
                curr = rightRotation(curr);
           }else if(child_bf < 0) {
               //left right Case
               curr.left = leftRotation(curr.left);
               curr = rightRotation(curr);
           }
       }else if (balanceFactor < -1){
           int child_bf = getHeight2(curr.right.left) - getHeight2(curr.right.right);
           if(child_bf > 0){
               //Right Left Case - Right rotation
               curr.right = rightRotation(curr.right);
               curr = leftRotation(curr);
           }else if(child_bf < 0) {
               //Right right Case
               curr = leftRotation(curr);
           }
       }else{
           curr.height = getHeight2(curr);
       }
       return curr;
    }

    private node rightRotation(node curr){
        node newNode = curr.left;
        node temp = newNode.right;
        newNode.right = curr;
        curr.left = temp;
        curr.height = getHeight2(curr);
        newNode.height = getHeight2(newNode);
        return newNode;
    }

    private node leftRotation(node curr){
        node newNode = curr.right;
        node temp = newNode.left;
        newNode.left = curr;
        curr.right = temp;
        curr.height = getHeight2(curr);
        newNode.height = getHeight2(newNode);
        return newNode;
    }

    public node find(node curr, String name){
        return findP(curr, name);
    }
    private node findP(node curr, String name){
        if (curr.left != null){
            findP(curr.left, name);
        }
        if((curr.userNode.getName()).equals(name)){
            curr.display(curr);
            return curr;
        }
        if (curr.right != null){
            findP(curr.right, name);
        }
        return curr;
    }

    protected int displayAll(node curr) {
        if(curr == null) return 0;
        if(curr.left != null){
            display(curr.left);
        }
        curr.userNode.displayUser();
        curr.userNode.displayAct();
        System.out.println(curr.height);
        if(curr.right != null){
            display(curr.right);
        }
        return 1;
    }

    public int addIndoorNode(int type, String name, String location, int time){
        node temp = this.find(this, name);
        System.out.println("Found: " + temp.userNode.getName());
        temp.userNode.addIndoor(type,location,time);
        temp.userNode.displayAct();
        return 1;
    }
    private node inorderSuccessor(node curr){
        if(curr == null) return null;
        if(curr.left != null) return inorderSuccessor(curr.left);
        return curr;
    }

    public node removeNode(node curr, String name){
        if(curr == null) return null;
        node temp = find(curr, name);
        System.out.println("In remove");
        if(temp.userNode.getKey() == curr.userNode.getKey()){
            System.out.println("remove at root");
            temp = null;
            return null;
        }
        //temp.userNode.displayUser();
        System.out.println("Started recursive");
        if(temp.userNode.getKey() < curr.userNode.getKey()){
            curr.left = removeNode(curr.left, name);
        }
        else if(temp.userNode.getKey() > curr.userNode.getKey()){
            curr.right = removeNode(curr.right, name);
        }
        else{
            if(curr.left == null) return curr.right;
            else if(curr.right == null) return curr.left;

            curr = inorderSuccessor(curr.right);
            curr.right = removeNode(curr.right, curr.userNode.getName());
        }
        return curr;
    }

    public int removeAct(node curr,int ans, String name){
        node temp = find(curr, name);
        //need remove function in infor
        temp.userNode.removeAct(ans);
        return 3;
    }

    private user userNode;
    private node left;
    private node right;
    private int height;
    private int key;

}
/////////////////TREE//////////////////

public class tree extends node {
    public tree(){
        root = null;
    }
    public int addUsertree(user userAdd){
        if(root == null) {
            root = new node(userAdd);
            root.addRoot(root);
            return 1;
        }else{
            root.insertNode(root, userAdd);
        }
        return 0;
    }
    public int displayTree(){
        return displayTree(root);
    }

    private int displayTree(node root){
        if(root == null) return 0;
        root.display(root);
        return 1;
    }
/*
    public int postAct(String name){
        Scanner scanner = new Scanner(System.in);
       if(root == null) return 0;
        node user = new node();
        user = user.find(root, name);
        System.out.println(user);
        System.out.println("What is your workout: ");
        String workout = scanner.next();
        System.out.println("How long did you workout: ");
        int time = scanner.nextInt();
        user.addAct(workout, time);
       user.actDisplay();
       return 1;
    }

    public int displayActUser(String name){
        if(root == null) return 0;
        node user = root.find(root,name);
        user.actDisplay();
        return 1;
    }

 */
    public int addIndoorTree(int type, String name, String location, int time){
        root.addIndoorNode(type,name,location,time);
        return 1;
    }
    public int displayAll(){
        if(root == null) return 0;
        root.displayAll(root);
        return 1;
    }

    public int removeUser(String name){
        root.removeNode(root, name);
        return 1;
    }

    public int removeAct(int ans, String name){
        root.removeAct(root,ans, name);
        return 1;
    }

    private node root;
}
