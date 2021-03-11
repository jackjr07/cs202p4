package com.jack;

import java.util.Scanner;

public class indoor extends activity {
    public node head = null;
    public node tail = null;
    class node {
        indoor data;
        node next;

        public node(indoor data) {
            this.data = data;
            this.next = null;
        }

//        public node get_head(){
//            node curr = head;
//            return curr;
//        }

        public int display(){
            return display(head);
        }

        private int display(node curr){
            if(curr == null) return 0;
            curr.data.display();
            if(curr.next != null) {
                return display(curr.next);
            }
            return 1;
        }

    }
    public int insert(indoor data) {
        node temp = new node(data);
        if (head == null) {
            head = temp;
            tail = temp;
            return 1;
        } else {
            tail.next = temp;
            tail = temp;
            return 2;
        }
    }
    public int display(){
        node curr = head;
        return display(curr);
    }
    private int display(node curr){
        if(curr == null){
            return 0;
        }
        curr.data.display();
        if(curr.next == null) {
            return 0;
        }
        curr = curr.next;
        return display(curr);
    }
    public int remove(){
        if(head == null) {return 0;}
        head.data.display();
        System.out.println("Do you want to delete this one: [1] Yes, [2] NO");
        Scanner scanner = new Scanner(System.in);
        int ans = scanner.nextInt();
        if(ans == 1){
            head = head.next;
            return 1;
        }else{
            node curr = head.next;
            return remove(curr);
        }
    }
    private int remove(node curr){
        if(curr == null) return 0;
        curr.data.display();
        System.out.println("Do you want to delete this one: [1] Yes, [2] NO");
        Scanner scanner = new Scanner(System.in);
        int ans = scanner.nextInt();
        if(ans ==1){
            if(curr.next != null) {
                curr.data = curr.next.data;
                curr.next = curr.next.next;
            }else {
                return remove(head, curr);
            }
            return 1;
        }else {
            remove(curr.next);
        }
        return 1;
    }
    private int remove(node prev, node curr){
       if(curr == null) return 0;
       if(prev.next != curr){
           return remove(prev.next, curr);
       }
       if(prev.next == curr){
           prev.next = null;
           tail = prev;
           return 1;
       }
       return 0;
    }

    public node get_head(){
        return head;
    };
}
class yoga extends indoor{
    yoga(String location, int time){
        this.location = location;
        this.time = time;
    }

    public int display(){
        System.out.println("You did Yoga\n" + "At: " + this.location + "\nFor this long: " + this.time);
        System.out.println("////////////////////////////");
        return 1;
    }


    private String location;
    private int time;
}
class weight extends indoor{
    weight(String location, int time){
        this.location = location;
        this.time = time;
    }
    public int display(){
        System.out.println("You did Weight lifting\n" + "At: " + this.location + "\nFor this long: " + this.time);
        System.out.println("////////////////////////////");
        return 1;
    }
    private String location;
    private int time;
}

class other_in extends indoor{
    other_in(String title, String location, int time){
       this.title = title;
       this.location = location;
       this.time = time;
    }
    public int display(){
        System.out.println("You did " + this.title + "\nAt: " + this.location + "\nFor this long: " + this.time);
        System.out.println("////////////////////////////");
        return 1;
    }
    private String title;
    private String location;
    private int time;
}
