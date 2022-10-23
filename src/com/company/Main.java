package com.company;

import java.util.Scanner;

// Simple application mimicking telephone.
//Created by Aleksandra Kostrzyńska-Szugajew

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static MobilePhone mobilePhone = new MobilePhone("504 898 171");

    public static void main(String[] args) {

        boolean quit = false;
        switchOn();
        printMenu();
        while (!quit) {
            System.out.println("\nEnter action: (6 to show menu)");
            int action = scanner.nextInt();
            scanner.nextLine();

            switch (action) {
                case 0:
                    System.out.println("\nShutting down...");
                    quit = true;
                    break;
                case 1:
                    mobilePhone.printContacts();
                    break;
                case 2:
                    addNewContact();
                    break;
                case 3:
                    updateContact();
                    break;
                case 4:
                    removeContact();
                    break;
                case 5:
                    queryContact();
                    break;
                case 6:
                    printMenu();
                    break;
            }

        }
    }

    private static void addNewContact() {
        System.out.println("Enter new contact name: ");
        String name = scanner.nextLine();
        System.out.println("Enter phone number: ");
        String phone = scanner.nextLine();
        //the method in contact was static so I can use it here that way
        Contact contact = Contact.createContact(name, phone);
        if (mobilePhone.addNewContact(contact)) {
            System.out.println("New contact added : " + name + ", phone : " + phone);
        } else {
            System.out.println("Cannot add " + name + " already on file");
        }
    }

    //this method could also just delete existing contact and add new <- to check an idea
    private static void updateContact() {
        System.out.println("Enter existing contact name: ");
        String name = scanner.nextLine();
        Contact existingContactRecord = mobilePhone.queryContact(name);
        if (existingContactRecord == null) {
            System.out.println("Contact not found");
        }
        System.out.println("Enter new contacts name: ");
        String newName = scanner.nextLine();
        System.out.println("Enter new contacts phone: ");
        String newNumber = scanner.nextLine();
        Contact newContact = Contact.createContact(newName, newNumber);
        if (mobilePhone.updateContact(existingContactRecord, newContact)) {
            System.out.println("Successfully updated record");
        } else {
            System.out.println("Error updating record");
        }
    }

    private static void removeContact() {
        System.out.println("Enter existing contact name: ");
        String name = scanner.nextLine();
        Contact existingContactRecord = mobilePhone.queryContact(name);
        if (existingContactRecord == null) {
            System.out.println("Contact not found");
        }
        if (mobilePhone.removeContact(existingContactRecord)) {
            System.out.println("Successfully removed");
        } else {
            System.out.println("Error removing contact");
        }
    }

    private static void queryContact() {
        System.out.println("Enter existing contact name: ");
        String name = scanner.nextLine();
        Contact existingContactRecord = mobilePhone.queryContact(name);
        if (existingContactRecord == null) {
            System.out.println("Contact not found");
        }

        System.out.println("Name: " + existingContactRecord.getName() +
                " phone number is " + existingContactRecord.getPhoneNumber());
    }


    private static void switchOn() {
        System.out.println("Starting phone...");
    }

    private static void printMenu() {
        System.out.println("\nAvailable actions:\npress");
        System.out.println("0 - to shutdown\n"
                + " 1 - to print contacts\n"
                + " 2 - to add a new contact\n"
                + " 3 - to update an existing contact\n"
                + " 4 - to remove contact\n"
                + " 5 - check if contact exists\n"
                + " 6 - to print menu");
        System.out.println("Choose what would you like to do: ");
    }
}
