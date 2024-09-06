package finalProject;

import javax.swing.*;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class ToDoListApp {
	
    private JFrame frame;
    private JTextField nameField;
    private JTextField descriptionField;
    private JTextField priorityField;
    private DefaultListModel<Task> listModel;
    private Queue<Task> taskQueue;
    
    public ToDoListApp() {
    	
    	
        taskQueue = new LinkedList<>();
        
        frame = new JFrame("To-Do List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);

        nameField = new JTextField(20);
        descriptionField = new JTextField(20);
        priorityField = new JTextField(5);
        JButton addButton = new JButton("Add Task");
        JButton removeButton = new JButton("Remove Task");
        JButton sortPriorityButton = new JButton("Sort Priority");
        
        listModel = new DefaultListModel<>();
        JList<Task> taskList = new JList<>(listModel);
        taskList.setCellRenderer(new TaskRenderer());

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2));
        inputPanel.add(new JLabel("Name: "));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Description: "));
        inputPanel.add(descriptionField);
        inputPanel.add(new JLabel("Priority: "));
        inputPanel.add(priorityField);
        
        inputPanel.add(addButton);
        inputPanel.add(removeButton);
        inputPanel.add(sortPriorityButton);
        
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(taskList), BorderLayout.CENTER);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String description = descriptionField.getText();
                int priority;
                try {
                    priority = Integer.parseInt(priorityField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Priority must be a number", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!name.isEmpty() && !description.isEmpty()) {
                    Task task = new Task(name, description, priority);
                    taskQueue.add(task);
                    listModel.addElement(task);
                    nameField.setText("");
                    descriptionField.setText("");
                    priorityField.setText("");
                }
                
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!taskQueue.isEmpty()) {
                    Task task = taskQueue.poll();
                    listModel.removeElement(task);
                }
            }
        });
        
        sortPriorityButton.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
        		listModel.removeAllElements();
        		PriorityQueue<Task> sortedQueue = new PriorityQueue<>(Comparator.comparingInt(Task::getPriority));

                
                while (!taskQueue.isEmpty()) {
                    sortedQueue.add(taskQueue.poll());
                }

                
                while (!sortedQueue.isEmpty()) {
                    Task task = sortedQueue.poll();
                    listModel.addElement(task);
                    taskQueue.add(task); 
                }
        	}
        });
        		
        		
        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ToDoListApp();
            }
        });
    }
    
}

