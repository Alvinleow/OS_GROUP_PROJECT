public class FIFO {
    public static void main(String args[]) {
        String incomingBooks[] = {"Id# 7", "Id# 0", "Id# 1", "Id# 2", "Id# 0" , "Id# 3", "Id# 0"
                                , "Id# 4", "Id# 2", "Id# 3", "Id# 0", "Id# 3", "Id# 0", "Id# 3"
                                , "Id# 2", "Id# 1", "Id# 2", "Id# 0", "Id# 1", "Id# 7", "Id# 0", "Id# 1"};
        int bookFaults = 0;
        int frame_size = 3;
        int m, n, s, books;

        books = incomingBooks.length;

        System.out.print("Incoming");

        for(int i = 1; i <= frame_size; i++)
        {
            System.out.print(" \t\tFrame#" + i);
        }

        System.out.println();

        String shelf[] = new String[frame_size];
        for(m = 0; m < frame_size; m++) {
            shelf[m] = "";
        }

        for(m = 0; m < books; m++) {
            s = 0;

            for(n = 0; n < frame_size; n++) {
                if(incomingBooks[m].equals(shelf[n])) {
                    s++;
                    bookFaults--;
                }
            }
            bookFaults++;

            if((bookFaults <= frame_size) && (s == 0)) {
                shelf[m] = incomingBooks[m];
            } else if(s == 0) {
                shelf[(bookFaults - 1) % frame_size] = incomingBooks[m];
            }

            System.out.println();
            System.out.print(incomingBooks[m] + "\t\t\t");
            for(n = 0; n < frame_size; n++) {
                if(!shelf[n].equals(""))
                    System.out.print("[" + shelf[n] + "]" + "\t\t\t");
                else
                    System.out.print(" - \t\t\t");
            }
        }

        System.out.println("\n\nTotal Book Faults:\t" + bookFaults);
    }
}
