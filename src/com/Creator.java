package com;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;


public class Creator {
    public void createWorkOrders() {
        // read input, create work orders and write as json files

        WorkOrder newWorkOrder = new WorkOrder();
        Scanner input = new Scanner(System.in);

        status = newWorkOrder.setStatus(Status.INITIAL);

        System.out.println("Please enter your name.");
        senderName = input.nextLine();

        ObjectMapper mapper = newObjectMapper();
        int workOrderIDNo = newWorkOrder.getId();
        mapper.writeValue(new File(workOrderIDNo + ".json"),newWorkOrder);
        String jsonString = mapper.writeValueAsString(newWorkOrder);
        System.out.print("Your new order has been created.");
    }

    public static void main(String args[]) {
        Creator creator = new Creator();
        creator.createWorkOrders();
    }
}

    public static void main(String args[]) {
        Creator creator = new Creator();
        creator.createWorkOrders();
    }
}
