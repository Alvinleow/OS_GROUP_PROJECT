
public class LRU 
{
    static boolean checkHit(String incomingBooks, String[] shelf, int occupied) 
    {
        for (int i = 0; i < occupied; i++) 
        {
            if (incomingBooks.equals(shelf[i]))
                return true;
        }
        return false;
    }

    static void printFrame(String[] shelf, int occupied) 
    {
        for (int i = 0; i < occupied; i++)
            System.out.print("[" + shelf[i] + "]" + "\t\t\t");
    }

    public static void main(String[] args) 
    {

        String[] incomingBooks = {"Id# 7", "Id# 0", "Id# 1", "Id# 2", "Id# 0" , "Id# 3", "Id# 0"
                                , "Id# 4", "Id# 2", "Id# 3", "Id# 0", "Id# 3", "Id# 0", "Id# 3"
                                , "Id# 2", "Id# 1", "Id# 2", "Id# 0", "Id# 1", "Id# 7", "Id# 0", "Id# 1"};
        int n = incomingBooks.length;
        int frame_size = 3;
        String[] shelf = new String[frame_size];
        int[] distance = new int[frame_size];
        int occupied = 0;
        int bookFaults = 0;

        System.out.print("Incoming");

        for(int i = 1; i <= frame_size; i++)
        {
            System.out.print(" \t\tFrame#" + i);
        }

        System.out.println("");
        System.out.println("");

        for (int i = 0; i < n; i++) {
            System.out.println();
            System.out.print(incomingBooks[i] + ": \t\t\t");

            if (checkHit(incomingBooks[i], shelf, occupied)) 
            {
                printFrame(shelf, occupied);
            } else if (occupied < frame_size) 
            {
                shelf[occupied] = incomingBooks[i];
                bookFaults++;
                occupied++;
                printFrame(shelf, occupied);
            } else 
            {
                int max = Integer.MIN_VALUE;
                int index = -1;
                for (int j = 0; j < frame_size; j++) 
                {
                    distance[j] = 0;
                    for (int k = i - 1; k >= 0; k--) 
                    {
                        ++distance[j];
                        if (shelf[j] == incomingBooks[k])
                            break;
                    }
                    if (distance[j] > max) 
                    {
                        max = distance[j];
                        index = j;
                    }
                }
                shelf[index] = incomingBooks[i];
                printFrame(shelf, occupied);
                bookFaults++;
            }
        }
        System.out.println("\n\nTotal Book Faults:\t" + bookFaults);
    }
}