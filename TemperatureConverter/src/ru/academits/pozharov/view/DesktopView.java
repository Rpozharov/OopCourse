package ru.academits.pozharov.view;

import javax.swing.*;

public class DesktopView implements View {
    @Override
    public void start() {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }

            JFrame frame = new JFrame("Конвертер температур");

            frame.setSize(400, 300);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            JPanel panel = new JPanel();

            JLabel temperatureLabel = new JLabel("Введите температуру");

            JTextField celsiusTemperatureField = new JTextField(30);

            JButton convertTemperatureButton = new JButton("Конвертировать");

            convertTemperatureButton.addActionListener(e -> {
                convertTemperatureButton.setText("Clicked");
            });

            JLabel resultLabel = new JLabel();

            panel.add(temperatureLabel);
            panel.add(celsiusTemperatureField);
            panel.add(convertTemperatureButton);
            panel.add(resultLabel);

            frame.add(panel);

            frame.setVisible(true);
        });
    }
}
