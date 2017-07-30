package com;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Processor {
    Map<Status, Set<WorkOrder>> workOrderMap = new HashMap<>();

    public Map<Status, Set<WorkOrder>> getWorkOrderMap(){
        return workOrderMap;
    }

    public void processWorkOrders() {

        try {
            moveIt();
            readIt();
            Thread.sleep(15000L);
            processWorkOrders();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void moveIt() {
        // move work orders in map from one state to another

        File currentDirectory = new File(".");
        File files[] = currentDirectory.listFiles();

        Set<WorkOrder> workOrderSetInitial = workOrderMap.get(Status.INITIAL);
        Set<WorkOrder> workOrderSetAssigned = workOrderMap.get(Status.ASSIGNED);
        Set<WorkOrder> workOrderSetInProgress = workOrderMap.get(Status.IN_PROGRESS);
        Set<WorkOrder> workOrderSetDone = workOrderMap.get(Status.DONE);

        System.out.println("--------------------------");
        System.out.println("MAP (Before changes made: )" + workOrderMap);
        System.out.println("--------------------------");

        for (File f : files) {
            if (f.getName().endsWith(".json")) {
                ObjectMapper mapper = new ObjectMapper();

                try{

                    WorkOrder order = mapper.readValue(new File(f.getName()), WorkOrder.class);

                    System.out.println( "File Name: " + f.getName() + "; File Status(Before Change): " + order.getStatus());

                    if(order.getStatus() == null){
                        order.setStatus( Status.INITIAL );
                        workOrderSetInitial.add(order);
                        workOrderMap.put(Status.INITIAL, workOrderSetInitial);

                    }else if(order.getStatus().equals( Status.INITIAL )){
                        order.setStatus( Status.ASSIGNED);
                        workOrderSetAssigned.add(order);
                        workOrderMap.put(Status.ASSIGNED, workOrderSetAssigned);

                    }else if(order.getStatus().equals( Status.ASSIGNED )){
                        order.setStatus( Status.IN_PROGRESS);
                        workOrderSetInProgress.add(order);
                        workOrderMap.put(Status.IN_PROGRESS, workOrderSetInProgress);

                    }else if(order.getStatus().equals( Status.IN_PROGRESS)){
                        order.setStatus( Status.DONE );
                        workOrderSetDone.add(order);
                        workOrderMap.put(Status.DONE, workOrderSetDone);

                    }else if(order.getStatus().equals( Status.DONE)) {
                        order.setStatus( Status.DONE );
                        workOrderSetDone.add(order);
                        workOrderMap.put(Status.DONE, workOrderSetDone);
                    }

                    System.out.println( "File Name: " + f.getName() + "; File Status(After Change): " + order.getStatus());

                }catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        System.out.println("--------------------------");
        System.out.println("MAP (After changes made: )" + workOrderMap);
        System.out.println("--------------------------");
        System.out.println( "Number of files in INITIAL status: " + workOrderSetInitial.size());
        System.out.println( "Number of files in ASSIGNED status: " + workOrderSetAssigned.size());
        System.out.println( "Number of files in IN-PROGRESS status: " + workOrderSetInProgress.size());
        System.out.println( "Number of files in DONE status: " + workOrderSetDone.size());
    }

    private void readIt() {
        File currentDirectory = new File(".");
        File files[] = currentDirectory.listFiles();

        for (File f : files) {
            if (f.getName().endsWith(".json")) {
                ObjectMapper mapper = new ObjectMapper();
                try {
                    WorkOrder order = mapper.readValue(new File(f.getName()), WorkOrder.class);
                    order.setStatus(Status.INITIAL);

                    Set<WorkOrder> workOrderSet = workOrderMap.get(Status.INITIAL);
                    workOrderSet.add(order);

                    workOrderMap.put(Status.INITIAL, workOrderSet);
                    f.delete();
                    System.out.println(workOrderMap);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String args[]) {
        Processor processor = new Processor();

        //Initializing the map
        processor.workOrderMap.put(Status.INITIAL, new HashSet<>());
        processor.workOrderMap.put(Status.ASSIGNED, new HashSet<>());
        processor.workOrderMap.put(Status.IN_PROGRESS, new HashSet<>());
        processor.workOrderMap.put(Status.DONE, new HashSet<>());

        processor.processWorkOrders();
    }
}
