import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Main extends JFrame {
    private final JPanel panel;
    private final JTextField textField;
    private final ArrayList<JCheckBox> checkBoxes;

    public Main() {

        // frame settings
        setTitle("Checklist");
        setSize(1280, 640);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        checkBoxes = new ArrayList<>();

// =========================================================================

        // northern panel
        JPanel northPanel = new JPanel();
        textField = new JTextField(100);
        JButton button = new JButton("Add Objective");
        JLabel label = new JLabel(">>OBJECTIVE_LIST ");
        northPanel.add(label);
        northPanel.add(textField);
        northPanel.add(button);

        add(northPanel, BorderLayout.NORTH);

// =========================================================================

        // central panel
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(panel);
        add(scrollPane, BorderLayout.CENTER);

        // button action
        button.addActionListener(e -> addItem());

// =========================================================================

        // colors
        label.setForeground(Color.white);

        northPanel.setBackground(Color.darkGray.darker().darker());
        panel.setBackground(Color.darkGray.darker());

        button.setBackground(Color.darkGray);
        button.setForeground(Color.white);

        textField.setBackground(Color.black);
        textField.setForeground(Color.white);

        scrollPane.setBackground(Color.darkGray.darker().darker());

// =========================================================================

        // app icon
        ImageIcon icon = new ImageIcon("app-icon.png");
        setIconImage(icon.getImage());

        // visibility
        setVisible(true);
    }

    private void addItem() {
        String text = textField.getText().trim();
        if (!text.isEmpty()) {
            JCheckBox checkBox = new JCheckBox(text);

            // right click to remove
            checkBox.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (SwingUtilities.isRightMouseButton(e)) {
                        panel.remove(checkBox);
                        checkBoxes.remove(checkBox);
                        panel.revalidate();
                        panel.repaint();
                    }
                }
            });

            // checkbox setting
            checkBox.setOpaque(false);
            checkBox.setForeground(Color.white);

            // functions
            checkBoxes.add(checkBox);
            panel.add(checkBox);
            panel.revalidate();
            textField.setText("");
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main:: new);
    }
}