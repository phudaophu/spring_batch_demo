package com.example.spring_batch_demo.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.net.Inet4Address;

@Component
public class FirstItemProcessor implements ItemProcessor<Integer,String> {
    @Override
    public String process(Integer item) throws Exception {
        System.out.println("Inside Item Processor: ");
        return "Convert Integer to String "+item;
    }
}





