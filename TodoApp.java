import java.util.*;

public class TodoApp {
    static class Task {
        static int counter = 1;
        int id; String title, priority; boolean done;
        Task(String title, String priority) {
            this.id = counter++; this.title = title;
            this.priority = priority; this.done = false;
        }
        public String toString() {
            String status = done ? "✅" : "⬜";
            String pri = priority.equals("high") ? "🔴" : priority.equals("medium") ? "🟡" : "🟢";
            return String.format("%s %s [%d] %s", status, pri, id, title);
        }
    }

    static List<Task> tasks = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    static void addTask() {
        System.out.print("Task title: "); String title = sc.nextLine().trim();
        System.out.print("Priority (high/medium/low, default medium): ");
        String pri = sc.nextLine().trim().toLowerCase();
        if (!pri.equals("high") && !pri.equals("low")) pri = "medium";
        tasks.add(new Task(title, pri));
        System.out.println("✅ Task added!");
    }

    static void listTasks(String filter) {
        if (tasks.isEmpty()) { System.out.println("  No tasks yet."); return; }
        System.out.println();
        for (Task t : tasks) {
            if (filter.equals("all") || (filter.equals("done") && t.done) || (filter.equals("pending") && !t.done))
                System.out.println("  " + t);
        }
        System.out.println();
    }

    static void completeTask() {
        System.out.print("Task ID to complete: ");
        try {
            int id = Integer.parseInt(sc.nextLine().trim());
            for (Task t : tasks) if (t.id == id) { t.done = true; System.out.println("✅ Done: " + t.title); return; }
            System.out.println("Task not found.");
        } catch (NumberFormatException e) { System.out.println("Invalid ID."); }
    }

    static void deleteTask() {
        System.out.print("Task ID to delete: ");
        try {
            int id = Integer.parseInt(sc.nextLine().trim());
            Iterator<Task> it = tasks.iterator();
            while (it.hasNext()) { Task t = it.next(); if (t.id == id) { it.remove(); System.out.println("🗑️ Deleted: " + t.title); return; } }
            System.out.println("Task not found.");
        } catch (NumberFormatException e) { System.out.println("Invalid ID."); }
    }

    public static void main(String[] args) {
        System.out.println("=== Java Todo App ===");
        System.out.println("Commands: add | list | done | delete | pending | quit
");
        while (true) {
            System.out.print("> ");
            String cmd = sc.nextLine().trim().toLowerCase();
            switch (cmd) {
                case "add": addTask(); break;
                case "list": listTasks("all"); break;
                case "pending": listTasks("pending"); break;
                case "done": completeTask(); break;
                case "delete": deleteTask(); break;
                case "quit": System.out.println("Goodbye!"); return;
                default: System.out.println("Unknown command.");
            }
        }
    }
}
