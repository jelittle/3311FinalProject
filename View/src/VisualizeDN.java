import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class VisualizeDN extends JPanel{

    private JPanel centralContainer;
    private JPanel southContainer;
    private JButton submitButton;
    private JButton backButton;
    private JTextField startDate;
    private JTextField endDate;


    int userId = Launcher.userId;

    public VisualizeDN(CardLayout cardLayout, JPanel cards) {
        setLayout(new BorderLayout());
        setBackground(Color.LIGHT_GRAY);

        centralContainer = new JPanel();
        centralContainer.setLayout(new BoxLayout(centralContainer, BoxLayout.Y_AXIS));
        centralContainer.setBackground(Color.LIGHT_GRAY);
        add(centralContainer, BorderLayout.NORTH);

        JLabel headerLabel = new JLabel("Daily Nutrient Intake Visualization");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setBackground(Color.LIGHT_GRAY);
        headerPanel.add(headerLabel);
        centralContainer.add(headerPanel);

        JPanel textFieldContainer = new JPanel();
        textFieldContainer.setLayout(new FlowLayout(FlowLayout.CENTER));
        textFieldContainer.setBackground(Color.LIGHT_GRAY);

        // Create text fields
        startDate = new JTextField(20);
        JPanel startPanel = createFieldPanel("Start Date (YYYY-MM-DD):", startDate);
        centralContainer.add(startPanel);

        endDate = new JTextField(20);
        JPanel endPanel = createFieldPanel("End Date (YYYY-MM-DD):", endDate);
        centralContainer.add(endPanel);

        // Add text fields to the container
        textFieldContainer.add(startPanel);
        textFieldContainer.add(endPanel);

        // Add the container to the central container
        centralContainer.add(textFieldContainer);

        southContainer = new JPanel();
        southContainer.setLayout(new BoxLayout(southContainer, BoxLayout.Y_AXIS));
        southContainer.setBackground(Color.LIGHT_GRAY);
        add(southContainer, BorderLayout.SOUTH);

        submitButton = new JButton("Submit");
        backButton = new JButton("Back");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        
        buttonPanel.add(submitButton);
        buttonPanel.add(backButton);

        southContainer.add(buttonPanel);
        add(southContainer, BorderLayout.SOUTH);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = 1;
                try {
                    LocalDate parsedStartDate = LocalDate.parse(startDate.getText());
                    ArrayList<Integer> startDateTime = new ArrayList<>(5);
                    startDateTime.add(parsedStartDate.getYear());
                    startDateTime.add(parsedStartDate.getMonthValue());
                    startDateTime.add(parsedStartDate.getDayOfMonth());
                    startDateTime.add(0); // Hour
                    startDateTime.add(0); // Minute

                    LocalDate parsedEndDate = LocalDate.parse(endDate.getText());
                    ArrayList<Integer> endDateTime = new ArrayList<>(5);
                    endDateTime.add(parsedEndDate.getYear());
                    endDateTime.add(parsedEndDate.getMonthValue());
                    endDateTime.add(parsedEndDate.getDayOfMonth());
                    endDateTime.add(0); // Hour
                    endDateTime.add(0); // Minute

                    DailyNutrientVisualization pieChart = new DailyNutrientVisualization(startDateTime, endDateTime, userId);
                    centralContainer.add(pieChart);

                    LocalDate startingDate = LocalDate.parse(startDate.getText());
                    Launcher.setDNStartDate(startingDate);   

                    LocalDate endingDate = LocalDate.parse(endDate.getText());
                    Launcher.setDNEndDate(endingDate);
        
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(VisualizeDN.this, 
                        "Invalid date format. Please enter the date in YYYY-MM-DD format.", 
                        "Date Format Error", JOptionPane.ERROR_MESSAGE);
                        i = 2;
                } catch (Exception e1) {
                    
                }

                if (i == 1) {
                    JOptionPane.showMessageDialog(null, "TODO: Display Visualization", "Success", JOptionPane.INFORMATION_MESSAGE);
                    // cardLayout.show(cards, "MainMenu");
                    resetForm();
                    }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards, "MainMenu");
            }    
        });

    }

    public void resetForm() {
        startDate.setText(""); // Clear the date fields
        endDate.setText(""); 
    
        // Refresh the layout
        revalidate();
        repaint();
    }

    private JPanel createFieldPanel(String labelText, JComponent component) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
    
        gbc.gridx = 0; 
        gbc.gridy = 0; 
        gbc.gridwidth = 1; 
        gbc.anchor = GridBagConstraints.CENTER; 
        panel.add(new JLabel(labelText), gbc);
    
        gbc.gridy = 1; 
        gbc.insets = new Insets(5, 0, 5, 0); 
        panel.add(component, gbc);
    
        panel.setBackground(Color.LIGHT_GRAY);
        return panel;
    }
    
}
