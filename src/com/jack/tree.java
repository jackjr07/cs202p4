package com.jack;

import java.util.Scanner;

class user {

    public void displayUser(){
        System.out.println("User name: " + this.name + "\n User phone number: " + this.phone + "\n User address: " + this.address);
    }
    public user(String name, long phone, String address){
        this.name = name;
        this.phone = phone;
        this.address = address;
    }
    public int getKey(){
        int phone = (int)this.phone;
        phone = phone % 100;
        return phone;
    }
    public String getName(){
        return this.name;
    }

    private String name;
    private long phone;
    private String address;

}

class act{
    public act(){
        this.name = null;
        this.time = 0;
        this.next = null;
    }
    public act(String name, int time){
        this.name = name;
        this.time = time;
        this.next = null;
    }

    public act getLast(act head, String name, int time){
        if(head.next != null){
            return getLast(head.next, name, time);
        }
        head.next = new act(name, time);
        return head.next;
    }

    public int displayAct(act curr){
        if(curr == null) return 0;
        System.out.println("Workout: " + curr.name + "\n For (mins): " + curr.time);
        return displayAct(curr.next);
    }

    private String name;
    private int time;
    private act next;
}

class node{
    public node(){
        this.userNode = null;
        this.key = 0;
        this.left = null;
        this.right = null;
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

    ///STOP HERE

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
    /*
    public indoor addAct(indoor indoor_obj){
        if(inHeadAct == null) {
            inHeadAct = new indoor();
            inHeadAct = indoor_obj;
            return inHeadAct;
        }else{
            inHeadAct.insert(indoor_obj);
        }
        return inHeadAct;
    }
    */

    public node find(node curr, String name){
        return findP(curr, name);
    }
    private node findP(node curr, String name){
        if (curr.left != null){
            findP(curr.left, name);
        }
        if((curr.userNode.getName()).equals(name) == true){
            curr.display(curr);
            return curr;
        }
        if (curr.right != null){
            findP(curr.right, name);
        }
        return curr;
    }

    public int addAct(String name, int time){
        if(this.head == null){
            this.head = new act(name, time);
            return 1;
        }
        this.head.getLast(this.head, name, time);
        return 1;
    }

    public void actDisplay(){
        this.head.displayAct(this.head);
        return;
    }

    protected int displayAll(node curr) {
        if(curr == null) return 0;
        if(curr.left != null){
            display(curr.left);
        }
        curr.userNode.displayUser();
        curr.head.displayAct(head);
        System.out.println(curr.height);
        if(curr.right != null){
            display(curr.right);
        }
        return 1;
    }

    private user userNode;
    private act head;
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
            node temp = new node(userAdd);
            //root.findLocation(root,temp);
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
    public int displayAll(){
        if(root == null) return 0;
        root.displayAll(root);
        return 1;
    }


    //New insert for balancing
    /*
    public node insert(user userAdd){
        if(root == null) {
            root = new node(userAdd);
            root.addRoot(root);
            return root;
        }else{
            return insertNode(root, userAdd);
        }
    }*/


    private node root;
}
