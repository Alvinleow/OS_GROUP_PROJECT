import java.util.*;

public class LibraryBookManagementSystem {
    static boolean checkHit(String incomingBook, String[] shelf, int occupied) {
        for (int i = 0; i < occupied; i++) {
            if (incomingBook.equals(shelf[i])) {
                return true;
            }
        }
        return false;
    }

    static void printShelf(String[] shelf, int occupied) {
        for (int i = 0; i < occupied; i++) {
            System.out.print("[" + shelf[i] + "]" + "\t\t\t");
        }
    }

    static void runFIFOAlgorithm(String[] incomingBooks, int frameSize) {
        int n = incomingBooks.length;
        String[] shelf = new String[frameSize];
        int occupied = 0;
        int bookFaults = 0;

        System.out.print("Incoming");

        for (int i = 1; i <= frameSize; i++) {
            System.out.print(" \t\tFrame#" + i);
        }

        System.out.println();
        System.out.println();

        for (int i = 0; i < n; i++) {
            System.out.print(incomingBooks[i] + ": \t\t\t");

            if (checkHit(incomingBooks[i], shelf, occupied)) {
                printShelf(shelf, occupied);
            } else if (occupied < frameSize) {
                shelf[occupied] = incomingBooks[i];
                bookFaults++;
                occupied++;
                printShelf(shelf, occupied);
            } else {
                shelf[bookFaults % frameSize] = incomingBooks[i];
                printShelf(shelf, occupied);
                bookFaults++;
            }
            System.out.println();
        }

        System.out.println("\nTotal Book Faults: " + bookFaults);
    }

    static void runLRUAlgorithm(String[] incomingBooks, int frameSize) {
        int n = incomingBooks.length;
        int frame_size = 3;
        String[] shelf = new String[frame_size];
        int[] distance = new int[frame_size];
        int occupied = 0;
        int bookFaults = 0;

        System.out.print("Incoming");

        for (int i = 1; i <= frame_size; i++) {
            System.out.print(" \t\tFrame#" + i);
        }

        System.out.println("");
        System.out.println("");

        for (int i = 0; i < n; i++) {
            System.out.println();
            System.out.print(incomingBooks[i] + ": \t\t\t");

            if (checkHit(incomingBooks[i], shelf, occupied)) {
                printShelf(shelf, occupied);
            } else if (occupied < frame_size) {
                shelf[occupied] = incomingBooks[i];
                bookFaults++;
                occupied++;
                printShelf(shelf, occupied);
            } else {
                int max = Integer.MIN_VALUE;
                int index = -1;
                for (int j = 0; j < frame_size; j++) {
                    distance[j] = 0;
                    for (int k = i - 1; k >= 0; k--) {
                        ++distance[j];
                        if (shelf[j] == incomingBooks[k])
                            break;
                    }
                    if (distance[j] > max) {
                        max = distance[j];
                        index = j;
                    }
                }
                shelf[index] = incomingBooks[i];
                printShelf(shelf, occupied);
                bookFaults++;
            }
        }
        System.out.println("\n\nTotal Book Faults:\t" + bookFaults);
    }

    public static void main(String[] args) {
        String incomingBooks[] = {"Id# 7", "Id# 0", "Id# 1", "Id# 2", "Id# 0" , "Id# 3", "Id# 0",
                                "Id# 4", "Id# 2", "Id# 3", "Id# 0", "Id# 3", "Id# 0", "Id# 3",
                                "Id# 2", "Id# 1", "Id# 2", "Id# 0", "Id# 1", "Id# 7", "Id# 0", "Id# 1"};
        int frameSize = 3;

        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\nLibrary Book Management System");
            System.out.println("Choose your action:");
            System.out.println("1. View FIFO Algorithm");
            System.out.println("2. View LRU Algorithm");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    runFIFOAlgorithm(incomingBooks, frameSize);
                    break;
                case 2:
                    runLRUAlgorithm(incomingBooks, frameSize);
                    break;
                case 3:
                    System.out.println("\nExiting the system. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("\nInvalid choice!");
            }

            System.out.println("\nPress any key to return to the main menu...");
            scanner.nextLine();
            scanner.nextLine();
        }
    }
}
