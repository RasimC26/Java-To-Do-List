package finalProject;

public class Task {
    private String name;
    private String description;
    private int priority;
    private boolean completed;
    private String status;
    
    public Task(String name, String description, int priority) {
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.completed = false; // Default to not completed
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
    
    public boolean isCompleted() {
        return completed;
    }
    
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return priority + ". " + name + " - " + description ;
    }
}