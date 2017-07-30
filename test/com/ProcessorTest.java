package com;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class ProcessorTest {

//This test works if readIt() is the only thing being run and infinite loop is not in place
//
    @Test
    public void processWorkOrders() throws Exception {
        Processor processor = new Processor(){};
        processor.workOrderMap.put(Status.INITIAL, new HashSet<>());

        File f = new File("test.json");
        ObjectMapper mapper = new ObjectMapper();
        WorkOrder expected = new WorkOrder("description", "name");
        mapper.writeValue(f, expected);

        processor.processWorkOrders();

        expected.setStatus(Status.INITIAL);

        int actualHashCode = Integer.MAX_VALUE;
        Set<WorkOrder> initSet = processor.getWorkOrderMap().get(Status.INITIAL);
        for (WorkOrder wo : initSet){
            actualHashCode = wo.hashCode();
        }

        assertEquals("WorkOrder HashCodes should be the same", expected.hashCode(), actualHashCode);
    }
}