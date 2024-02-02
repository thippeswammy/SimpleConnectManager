package com.thippeswamy.app.SimpleContactsMessagesManager;

import java.util.ArrayList;
import java.util.Scanner;

public class ContactManagerApp {

    private static ArrayList<Contact> contacts;

    private static Scanner sc;

    private static int id;

    public static void main(String[] args) {

        contacts = new ArrayList<>();
        System.out.println("Welcome to humble world of programming");
        showInitialOptions();
    }//main

    private static void showInitialOptions() {
        System.out.println("""
                Please sease select one:
                \t1.Mange contact
                \t2.Messages
                \t3.quit""");
        System.out.print(" >>> ");
        sc = new Scanner(System.in);
        int choic = sc.nextInt();

        switch (choic) {
            case 1:
                manageContacts();
                break;
            case 2:
                manageMessages();
                break;
            default:
                break;

        }//switch
    }

    private static void manageContacts() {
        System.out.println("""
                please select one:
                \t1.show all contacts\s
                \t2.Add a new contacts\s
                \t3.search for a contacts\s
                \t4.Delete a contact\s
                \t5.Go back\s""");
        System.out.print(">>> ");
        int choic = sc.nextInt();
        System.out.println();
        switch (choic) {
            case 1:
                showAllContacts();
                break;
            case 2:
                addNewContacts();
                break;
            case 3:
                searchForContacts();
                break;
            case 4:
                deleteContacts();
                break;
            default:
                showInitialOptions();
                break;
        }//swith
    }

    private static void showAllContacts() {
        for (Contact c : contacts) {
            c.getDetails();
            System.out.println("******************");
        }
        showInitialOptions();
    }

    private static void addNewContacts() {
        System.out.print("Adding a new contacts.." +
                "\nPlease enter contacts's name : >>> ");
        String name = sc.next();
        System.out.print("\nplease enter contacts number : >>> ");
        String number = sc.next();
        System.out.print("\nplease enter the email : >>> ");
        String email = sc.next();
        System.out.println();
        if (name.isEmpty() || number.isEmpty() || email.isEmpty()) {
            System.out.print("please enter all of the information >>> ");
            addNewContacts();
        } else {
            boolean doesExist = false;
            for (Contact c : contacts) {
                if (c.getName().equals(name)) {
                    doesExist = true;
                    c.getDetails();
                }
            }
            if (!doesExist) {
                Contact contact = new Contact(name, number, email);
                contacts.add(contact);
                System.out.println(name.toUpperCase() + " added sucessfull");
            } else {
                System.out.println("We have a contact name " + name + "saved on this device");
                addNewContacts();
            }

        }
        showInitialOptions();
    }

    private static void searchForContacts() {
        System.out.print("Pleasa enter the contact name : >>> ");
        String name = sc.next();
        System.out.println();
        if (name.isEmpty()) {
            System.out.print("Please enter the name");
            searchForContacts();
        } else {
            boolean doesExist = false;

            for (Contact c : contacts) {
                if (c.getName().equals(name)) {
                    doesExist = true;
                    c.getDetails();
                }
            }
            if (!doesExist) {
                System.out.println("There is no such contact is your phone");
            }
        }
        showInitialOptions();
    }

    private static void deleteContacts() {
        System.out.println("please enter the name : >>> ");
        String name = sc.next();
        System.out.println();
        if (name.isEmpty()) {
            System.out.println("please enter the name ");
            deleteContacts();
        } else {
            boolean doesExist = false;

            for (Contact c : contacts) {
                if (c.getName().equals(name)) {
                    doesExist = true;
                    contacts.remove(c);
                    System.out.println(name.toUpperCase() + " removed sucessfull");
                }
            }
            if (!doesExist) {
                System.out.println("Ther is no such contact");
            }
        }
        showInitialOptions();
    }

    private static void manageMessages() {
        System.out.println("""
                please select one:
                \t1.show all messages
                \t2.send messages
                \t3.Go to back""");
        System.out.print(">>> ");
        int choice = sc.nextInt();
        System.out.println();
        switch (choice) {
            case 1:
                showAllmessages();
                break;
            case 2:
                sendNewMessage();
                break;
            default:
                showInitialOptions();
                break;
        }
    }

    private static void showAllmessages() {
        ArrayList<Message> allMessage = new ArrayList<>();
        for (Contact c : contacts) {
            allMessage.addAll(c.getMessages());
        }
        if (!allMessage.isEmpty()) {
            for (Message m : allMessage) {
                m.getDetails();
                System.out.println("*****************");
            }
        } else {
            System.out.println("You dont't have any messages");
        }
        showInitialOptions();
    }

    private static void sendNewMessage() {
        System.out.print("who are you going to send a messages : >>> ");
        String name = sc.next();
        System.out.println();
        if (name.isEmpty()) {
            System.out.println("please enter the name of the contact");
            sendNewMessage();
        } else {
            boolean doesExist = false;
            for (Contact c : contacts) {
                if (c.getName().equals(name)) {
                    doesExist = true;
                    break;
                }
            }
            if (doesExist) {
                System.out.print("what are you going to say : >>> ");
                String text = sc.next();
                System.out.println();
                if (text.isEmpty()) {
                    System.out.println("please enter some messaage");
                    sendNewMessage();
                } else {
                    id++;
                    Message newMessage = new Message(text, name, id);
                    for (Contact c : contacts) {
                        if (c.getName().equals(name)) {
                            ArrayList<Message> newMessages = c.getMessages();
                            newMessages.add(newMessage);
                            c.setMessages(newMessages);
                            contacts.remove(c);
                            contacts.add(c);
                        }
                    }
                }
            } else {
                System.out.println("There is no such contact");
            }
        }
        showInitialOptions();
    }

}//class
