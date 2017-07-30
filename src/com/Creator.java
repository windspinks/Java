package com;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;



import com.fasterxml.jackson.databind.ObjectMapper;


public class Creator {

    public void createWorkOrders() throws IOException {
        // read input, create work orders and write as json files

        WorkOrder newWorkOrder = new WorkOrder();
        Scanner input = new Scanner(System.in);

        newWorkOrder.setStatus(Status.INITIAL);

        System.out.println("Please enter your name.");
        newWorkOrder.setSenderName(input.nextLine());

        System.out.println("Please enter a description of what you want.");
        newWorkOrder.setSenderName(input.nextLine());

        ObjectMapper mapper = new ObjectMapper();

        int workOrderIDNo = newWorkOrder.getId();

        mapper.writeValue(new File(workOrderIDNo + ".json"),newWorkOrder);

        String jsonString = mapper.writeValueAsString(newWorkOrder);
        System.out.print("Your new order has been created.");
    }

    public static void main(String args[]) {
        try {
            com.Creator creator = new com.Creator();
            creator.createWorkOrders();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}



