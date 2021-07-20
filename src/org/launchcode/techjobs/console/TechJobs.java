
        package org.launchcode.techjobs.console;
//new chnages
import java.util.*;
import java.util.stream.Collectors;
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Created by LaunchCode
 */
public class TechJobs {

    private static Scanner in = new Scanner(System.in);

    public static void main (String[] args) {

        // Initialize our field map with key/name pairs
        HashMap<String, String> columnChoices = new HashMap<>();
        columnChoices.put("core competency", "Skill");
        columnChoices.put("employer", "Employer");
        columnChoices.put("location", "Location");
        columnChoices.put("position type", "Position Type");
        columnChoices.put("all", "All");

        // Top-level menu options
        HashMap<String, String> actionChoices = new HashMap<>();
        actionChoices.put("search", "Search");
        actionChoices.put("list", "List");

        System.out.println("Welcome to LaunchCode's TechJobs App!");

        // Allow the user to search until they manually quit
        while (true) {

            String actionChoice = getUserSelection("View jobs by:", actionChoices);

            if (actionChoice.equals("list")) {

                String columnChoice = getUserSelection("List", columnChoices);

                if (columnChoice.equals("all")) {
                    printJobs(JobData.findAll());
                } else {

                    ArrayList<String> results = JobData.findAll(columnChoice);

                    System.out.println("\n*** All " + columnChoices.get(columnChoice) + " Values ***");

                    // Print list of skills, employers, etc
                    for (String item : results) {
                        System.out.println(item);
                    }
                }

            } else { // choice is "search"

                // How does the user want to search (e.g. by skill or employer)
                String searchField = getUserSelection("Search by:", columnChoices);

                // What is their search term?
                System.out.println("\nSearch term: ");
                String searchTerm = in.nextLine();

                if (searchField.equals("all")) {
                    //printJobs(JobData.findAll());
                    printJobs(JobData.findByValue(searchTerm.toUpperCase()));
                    // all fields not yet implemented.");
                    //printJobs(searchAll(searchTerm));
                } else {
                    printJobs(JobData.findByColumnAndValue(searchField, searchTerm));
                }
            }
        }
    }

    //private static ArrayList<HashMap<String, String>> searchAll(String searchTerm ) {

    //get all the jobs, once
    //ArrayList<HashMap<String, String>> alljobs = JobData.findAll();
    //get the first item and get the keys, in case they change
    //HashMap<String, String> singleJob = alljobs.get(0);
    //get the keys
    //List<String> columns = new ArrayList<>();
    // for (String key: singleJob.keySet()){
    //columns.add(key);
    //}

    //Set<HashMap<String, String>> foundJobList = new LinkedHashSet<>();

    //for (String singleChoice : columns) {
    // List<HashMap<String, String>> shortList = alljobs.stream().filter(job -> job.get(singleChoice).toLowerCase().contains(searchTerm.toLowerCase())).collect(Collectors.toList());
    //foundJobList.addAll(shortList);
    // }


    // ArrayList<HashMap<String, String>> foundJobs = new ArrayList<>(foundJobList.size());
    // foundJobs.addAll(foundJobList);
    //  return foundJobs;
    // }

    // ﻿Returns the key of the selected item from the choices Dictionary
    private static String getUserSelection(String menuHeader, HashMap<String, String> choices) {

        Integer choiceIdx;
        Boolean validChoice = false;
        String[] choiceKeys = new String[choices.size()];

        // Put the choices in an ordered structure so we can
        // associate an integer with each one
        Integer i = 0;
        for (String choiceKey : choices.keySet()) {
            choiceKeys[i] = choiceKey;
            i++;
        }

        do {

            System.out.println("\n" + menuHeader);

            // Print available choices
            for (Integer j = 0; j < choiceKeys.length; j++) {
                System.out.println("" + j + " - " + choices.get(choiceKeys[j]));
            }

            choiceIdx = in.nextInt();
            in.nextLine();

            // Validate user's input
            if (choiceIdx < 0 || choiceIdx >= choiceKeys.length) {
                System.out.println("Invalid choice. Try again.");
            } else {
                validChoice = true;
            }

        } while(!validChoice);

        return choiceKeys[choiceIdx];
    }

    // Print a list of jobs
    private static void printJobs(ArrayList<HashMap<String, String>> someJobs) {

        for (HashMap<String, String> jobs: someJobs) {
            System.out.println("****************");

            for (HashMap.Entry<String, String> i: jobs.entrySet()) {
                System.out.println(i.getKey() + " : " + i.getValue());
            }
            System.out.println("****************");
            System.out.println("\n");
        }

        if (someJobs.isEmpty()) {
            System.out.println("Job not found");
        }

    }
}
