package com.example.gradlecassandraparseurl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.gradlecassandraparseurl.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ScheduledTasks {
    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        final StringBuilder stringBuilder = new StringBuilder();
        try (final BufferedReader reader = new BufferedReader(new FileReader(getClass().getClassLoader().getResource("test.txt").getFile()))) {
            String line = reader.readLine();
            for (int index =0; index < 100000; index++) {
                System.out.println(index);
            }
            while (line != null) {
                stringBuilder.append(line).append("\n");
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            log.error("file not found", e);
        } catch (IOException e) {
            log.error("Read file exception", e);
        }
        log.info("The content: {}", stringBuilder.toString());
    }
}