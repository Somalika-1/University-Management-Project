package codes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;

public class CircularButton extends JButton {

    public CircularButton(String text) {
        super(text);
        setContentAreaFilled(false); // Ensure that the button background is transparent
        setFocusPainted(false); // Remove the focus indication
        setBorderPainted(false); // Remove the border
        setOpaque(true); // Make the button transparent
        setForeground(Color.black); // Set default text color
        setFont(new Font("Arial", Font.BOLD, 14)); // Set default font
        setPreferredSize(new Dimension(150, 40));
    }

    // Override the paintComponent method to draw a circular button
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        int width = getWidth();
        int height = getHeight();

        // Define the shape of the button
        Shape shape = new RoundRectangle2D.Double(0, 0, width - 1, height - 1, 20, 20);

        // Fill the shape with the appropriate color
        g2d.setColor(getBackground());
        g2d.fill(shape);

        // Draw the text centered within the button
        FontMetrics metrics = g.getFontMetrics();
        int x = (width - metrics.stringWidth(getText())) / 2;
        int y = ((height - metrics.getHeight()) / 2) + metrics.getAscent();
        g2d.setColor(getForeground());
        g2d.drawString(getText(), x, y);
        // Draw the border
        g2d.setColor(Color.BLACK); // Set border color
        g2d.draw(shape);


        g2d.dispose();
    }


    public static void main(String[] args) {
    }
        
        //new CircularButton();

        /*JFrame frame = new JFrame("Circular Button Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        CircularButton button = new CircularButton("Click Me");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Button clicked!");
            }
        });
        frame.add(button, BorderLayout.CENTER);

        frame.setSize(200, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);*/
    }

