package com.jack;
/*
Jack Wanitkun
CS202 program5
This is the user information.
In the user information it will have both indooor and outdoor activities.
The indoor and outdoor activity are already implement from program4
 */

import java.io.InputStream;
import java.util.Scanner;

public class user {

    public void displayUser(){
        System.out.println("User name: " + this.name + "\n User phone number: " + this.phone + "\n User address: " + this.address);
    }
    public user(String name, long phone, String address){
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.in_obj = new indoor();
        this.out_obj = new outdoor();
    }

    public int getKey(){
        int phone = (int)this.phone;
        phone = phone % 100;
        return phone;
    }
    public String getName(){
        return this.name;
    }

    public int addIndoor(int type, String location, int time){
        if(type == 1) {
            indoor temp = new yoga(location, time);
            in_obj.insert(temp);
        }
        if(type == 2){
            indoor temp = new weight(location,time);
            in_obj.insert(temp);
        }else if(type == 3){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Name of the activity: ");
            String title = scanner.next();
            indoor temp = new other_in(title, location, time);
            in_obj.insert(temp);
        }else if(type == 4){
            outdoor temp = new running(location,time);
            out_obj.insert(temp);
        }else if(type == 5){
            outdoor temp = new cycling(location,time);
            out_obj.insert(temp);
        }else if(type == 6) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Name of the activity: ");
            String title = scanner.next();
            outdoor temp = new other_out(title, location, time);
            out_obj.insert(temp);
        }
        return 9;
    }

    public void displayAct(){
        System.out.println(this.name);
        System.out.println("Activities: ");
        this.in_obj.display();
        this.out_obj.display();
    }
    public int removeAct(int ans){
       if(ans == 1) {
        this.in_obj.remove();
       }else if(ans == 2){
           this.out_obj.remove();
       }
       return 1;
    }


    private String name;
    private long phone;
    private String address;
    private indoor in_obj;
    private outdoor out_obj;

};
