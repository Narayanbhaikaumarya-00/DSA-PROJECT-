import java.io.*;
import java.util.*;

public class Main {
    static final int CAPACITY = 3;
    static final String FILE_NAME = "parking.txt";

    static Stack<String> parked = new Stack<>();          // parked vehicles (LIFO)
    static Queue<String> waiting = new LinkedList<>();    // waiting vehicles (FIFO)
    static HashMap<String, String> lookup = new HashMap<>(); // vehicle -> PARKED / WAITING
    static String[] slots = new String[CAPACITY];         // slot 1..3

    static void refreshSlots() {
        Arrays.fill(slots, null);
        for (int i = 0; i < parked.size(); i++) slots[i] = parked.get(i);
    }

    static boolean validateVehicle(String v) {
        if (v == null || v.trim().isEmpty()) {
            System.out.println("Invalid vehicle number.");
            return false;
        }
        if (lookup.containsKey(v)) {
            System.out.println("Vehicle " + v + " already exists.");
            return false;
        }
        return true;
    }

    static void loadData() {
        File f = new File(FILE_NAME);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length != 2) continue;
                if (p[0].equals("P") && parked.size() < CAPACITY) {
                    parked.push(p[1]);
                    lookup.put(p[1], "PARKED");
                } else if (p[0].equals("W")) {
                    waiting.offer(p[1]);
                    lookup.put(p[1], "WAITING");
                }
            }
        } catch (IOException e) {
            System.out.println("Could not read " + FILE_NAME);
        }
        refreshSlots();
    }

    static void saveData() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (String v : parked) pw.println("P," + v);
            for (String v : waiting) pw.println("W," + v);
        } catch (IOException e) {
            System.out.println("Could not write " + FILE_NAME);
        }
    }

    static void parkVehicle(String v) {
        if (!validateVehicle(v)) return;
        if (parked.size() < CAPACITY) {
            parked.push(v);
            lookup.put(v, "PARKED");
            refreshSlots();
            System.out.println("Vehicle " + v + " parked. Slot: " + parked.size());
        } else {
            waiting.offer(v);
            lookup.put(v, "WAITING");
            System.out.println("Lot full. Vehicle added to waiting queue. Position: " + waiting.size());
        }
    }

    static void exitVehicle(String v) {
        if (!lookup.containsKey(v)) {
            System.out.println("Vehicle " + v + " not found.");
            return;
        }
        if (lookup.get(v).equals("WAITING")) {
            waiting.remove(v);
            lookup.remove(v);
            System.out.println(v + " removed from waiting queue.");
            return;
        }
        if (!parked.peek().equals(v)) {
            System.out.println("Only the top vehicle " + parked.peek() + " can exit.");
            return;
        }
        parked.pop();
        lookup.remove(v);
        System.out.println(v + " exited.");
        if (!waiting.isEmpty()) {
            String next = waiting.poll();
            parked.push(next);
            lookup.put(next, "PARKED");
            System.out.println(next + " parked from queue.");
            System.out.println("Slot: " + parked.size());
        }
        refreshSlots();
    }

    static void searchVehicle(String v) {
        if (lookup.containsKey(v))
            System.out.println("Vehicle " + v + " found. Status: " + lookup.get(v));
        else
            System.out.println("Vehicle " + v + " not found.");
    }

    static void displayParking() {
        for (int i = 0; i < CAPACITY; i++)
            System.out.println("Slot " + (i + 1) + ": " + (slots[i] == null ? "Empty" : slots[i]));
        System.out.println("Waiting: " + waiting.size());
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        loadData();
        while (true) {
            System.out.println("\n1. Park vehicle  2. Exit vehicle  3. Search vehicle  4. Display  5. Save & Exit");
            System.out.print("Choice: ");
            if (!sc.hasNextLine()) { saveData(); break; }
            String c = sc.nextLine().trim();
            if (c.equals("5")) { saveData(); System.out.println("Data saved. Goodbye."); break; }
            if (c.equals("4")) { displayParking(); continue; }
            if (!c.equals("1") && !c.equals("2") && !c.equals("3")) {
                System.out.println("Invalid choice.");
                continue;
            }
            System.out.print("Vehicle number: ");
            String v = sc.nextLine().trim();
            if (c.equals("1")) parkVehicle(v);
            else if (c.equals("2")) exitVehicle(v);
            else searchVehicle(v);
        }
        sc.close();
    }
}
