package com.clarity.ai.service;

import io.reactivex.Observable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

@Component
public class LineReader implements CommandLineRunner {

    @Autowired
    private LogParser logParser;

    @Override
    public void run(String... args) throws IOException {

        //------------------------------ rx java with scanner-----------------------------------
//
        File srcFile = new File("/tmp/log");

        Observable<String> stream = Observable.create(subscriber -> {
            Scanner scan = new Scanner(srcFile);
            while (true) {
                if(scan.hasNextLine()) {
                    String line = scan.nextLine();
                    System.out.println(line);
                    subscriber.onNext(line);
                }
            }
        });

        stream.subscribe(i -> logParser.parseLine(i), //
                err -> {
                    System.out.println("Error: " + err.getMessage());
                },//
                () -> {
                    System.out.println("Completion");
                });


    }

}

