package com.example.geektrustproblems.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InputLoaderService {

    @Autowired
    InputLoaderFactory factory;

    public List<String> loadInputFile(String filePath) {

        Path path = Paths.get(filePath);

        List<String> outpuStrings = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(path)) {

            String line;
            String delimeter = " ";
            while ((line = br.readLine()) != null) {

                String[] lineArray = line.split(delimeter);
                IProcessInput loaderService = factory.loadInputService(lineArray[0]);
                String output = loaderService.process(lineArray);
                if (loaderService instanceof BalanceInputService) {
                    System.out.println(output);
                    outpuStrings.add(output);
                }
            }

        } catch (IOException e) {
            return null;
        }
        return outpuStrings;
    }

}