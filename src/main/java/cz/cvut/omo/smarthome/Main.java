package cz.cvut.omo.smarthome;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import cz.cvut.omo.smarthome.house.SmartHome;
import cz.cvut.omo.smarthome.utils.Utils;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> configs;
        String fileName = "";
        try {
            configs = Utils.listConfigs();
            if (!configs.isEmpty()) {
                System.out.println("Following configs found:");
                for (String config : configs) {
                    System.out.println(config);
                }
                System.out.println("Please, choose the config file to start simulation with by typing it's name and pressing enter.");
                System.out.println("To quit the programm, type \"exit\" and press enter");
                fileName = scanner.nextLine();
                while (!fileName.equals("exit") && !configs.contains(fileName)) {
                    System.out.println("Incorrect file name given, try again");
                    fileName = scanner.nextLine();
                }
                if (fileName.equals("exit")) {
                    return;
                } else {
                    String check = Utils.checkConfig(fileName);
                    if (check.equals("OK")) {
                        System.out.println("Starting the simulation...");
                        System.out.println("-----------------");
                    } else {
                        System.out.println(check);
                        return;
                    }
                }
            } else {
                System.out.println("No config files found, exiting");
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        Path path = Paths.get("resources", "configs", fileName);
        try {
            byte[] jsonData = Files.readAllBytes(path);
            SmartHome house = objectMapper.readValue(jsonData, SmartHome.class);

            AtomicBoolean finish = new AtomicBoolean(false);
            AtomicBoolean paused = new AtomicBoolean(false);
            AtomicBoolean report = new AtomicBoolean(false);
            Thread inputThread = new Thread(() -> {
                try {
                    while (true) {
                        int input = System.in.read();
                        if (paused.get()) {
                            Utils.switchRawMode(false);
                            if (!report.get()) {
                                if (input == 'r') {
                                    System.out.println("Choose a type of report you want to generate:");
                                    report.set(true);
                                }
                            } else {
                                if (input == 'h') {
                                    System.out.println("House Configuration Report was generated");
                                    house.generateReport("HouseConfigurationReport");
                                    report.set(false);
                                }
                            }
                            Utils.switchRawMode(true);
                        }
                        if (input == ' ') {
                            paused.set(!paused.get());
                            Utils.switchRawMode(false);
                            if (paused.get()) {
                                System.out.println("Simulation paused");
                            } else {
                                report.set(false);
                                System.out.println("Simulation unpaused");
                                System.out.println("-----------------");
                            }
                            Utils.switchRawMode(true);
                        } else if (input == 'q') {
                            finish.set(true);
                            break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    Utils.switchRawMode(false);
                }
            });

            Thread outputThread = new Thread(() -> {
                while (!finish.get()) {
                    try {
                        if (!paused.get()) {
                            Utils.switchRawMode(false);
                            house.getAction();
                            house.getUpdate();
                            System.out.println("-----------------");
                            Utils.switchRawMode(true);
                        }
                        Thread.sleep(1 * 1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });

            Utils.switchRawMode(true);
            inputThread.start();
            outputThread.start();

            try {
                inputThread.join();
                outputThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
