package com.example.spring_batch_demo.writer;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class FirstItemWriter implements ItemWriter<String> {

    @Override
    public void write(Chunk<? extends String> chunk) throws Exception {
        System.out.println("Inside Item Writer: ");
        chunk.forEach(System.out::println);
    }
}
