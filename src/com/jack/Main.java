package com.jack;

import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        indoor indoor_obj = new indoor();
        outdoor outdoor_obj = new outdoor();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Jack's Program4\n");
        int answer;
        do {
            answer = menu();
            if (answer == 1) {
                System.out.print("Indoor: ");
                System.out.println("[1] Yoga, [2] Weight, [3] Other indoor workout");
                System.out.print("Outdoor: ");
                System.out.println("[4] Running, [5] Cycling, [6] Other outdoor workout");
                int ans = scanner.nextInt();
                System.out.println("Where are you working out: ");
                String location = scanner.next();
                System.out.println("For how long(mins): ");
                int time = scanner.nextInt();
                if(ans == 1){
                    indoor temp = new yoga(location, time);
                    indoor_obj.insert(temp);
                }
                if(ans == 2){
                    indoor temp = new weight(location, time);
                    indoor_obj.insert(temp);
                }
                if(ans == 3){
                    System.out.println("What is the name of this workout: ");
                    String title = scanner.next();
                    indoor temp = new other_in(title, location, time);
                    indoor_obj.insert(temp);
                }
                if(ans == 4){
                    outdoor temp = new running(location, time);
                    outdoor_obj.insert(temp);
                }
                if(ans == 5){
                    outdoor temp = new cycling(location, time);
                    outdoor_obj.insert(temp);
                }
                if(ans == 6){
                    System.out.println("What is the name of this workout: ");
                    String title = scanner.next();
                    outdoor temp = new other_out(title, location, time);
                    outdoor_obj.insert(temp);
                }

            } else if (answer == 2) {
                indoor_obj.display();
                outdoor_obj.display();

            } else if(answer == 3){
                System.out.println("[1] indoor workout, [2] outdoor workout");
                int ans = scanner.nextInt();
                if(ans == 1){
                    indoor_obj.remove();
                }
                if(ans == 2){
                    outdoor_obj.remove();
                }
            } else if(answer == 4){
                System.out.println("Constucting Array");
                indoor.node in_head = indoor_obj.get_head();
                outdoor.node out_head = outdoor_obj.get_head();
                array_list(in_head, out_head);
            }
        } while (answer != 9);
    };

    static int menu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose your options\n");
        System.out.println(" [1] Add your activity\n [2] Display all your activity\n [3] Remove your activity");
        System.out.println(" [4] Display all your activity as table");
        int answer = scanner.nextInt();
        return answer;
    }
    static int array_list(indoor.node in_obj, outdoor.node out_obj){
        System.out.println("Creating the Array of Indoor and outdoor LLL");
        Object [] arr = new Object[2];
        arr[0] = in_obj;
        arr[1] = out_obj;
        System.out.println("Casting Array Object to indoor and outdoor nodes object");
        indoor.node arr_1 = (indoor.node) arr[0];
        outdoor.node arr_2 = (outdoor.node) arr[1];
        System.out.println("Display the LLL, from the array");
        if(arr_1 != null) {
            arr_1.display();
        }
        if(arr_2 != null) {
            arr_2.display();
        }
        return 1;
    };
}
