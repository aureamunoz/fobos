package com.clarity.ai.service;

import io.reactivex.Observable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Scanner;

@Component
public class LogReaderImpl implements LogReader {

    @Autowired
    private LineParser logParser;

    @Override
    public void startReading(String filename)  {
        File srcFile = new File(filename);

        Observable<String> stream = Observable.create(subscriber -> {
            Scanner scan = new Scanner(srcFile);
            while (true) {
                if (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    System.out.println(line);
                    subscriber.onNext(line);
                }
            }
        });

        stream.subscribe(i -> logParser.parseLine(i), //
                err -> System.out.println("Error: " + err.getMessage()),//
                () -> System.out.println("Completion"));

    }
}