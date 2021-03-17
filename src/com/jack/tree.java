package com.jack;

class user {
    public void displayUser(user user_a) {
        System.out.println("User name: " + user_a.name + "\n User phone number: " + user_a.phone + "\n User address: " + user_a.address);
    }
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
    private String name;
    private long phone;
    private String address;
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
    }

    public node addRoot(node root){
        root.left = null;
        root.right = null;
        root.height = 0;
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
    public int getHeight(node root){
        if(root==null) return 0;
        return getHeightP(root);
    }

    private int getHeightP(node curr){
        if(curr == null) return 0;
        int lMax = getHeightP(curr.left);
        int rMax = getHeightP(curr.right);
        if(lMax > rMax) return (lMax +1);
        else return (rMax+1);
    }

    ///STOP HERE

    public int display(node curr){
        if(curr == null){ return 0;}
       if(curr.left != null){
           display(curr.left);
       }
       curr.userNode.displayUser();
       if(curr.right != null){
           display(curr.right);
       }
       return 1;
    }

    private user userNode;
    private node left;
    private node right;
    private int height;
    private int key;

}

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
            root.findLocation(root,temp);
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
    public int getHeight(){
        return root.getHeight(root);
    }
    private node root;
}
