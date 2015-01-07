import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Labyrinth {

    private int height;
    private int width;

    public Labyrinth() {
        height = 1;
        width = 1;
    }

    public Labyrinth(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public Frame generateFrame() {
        Frame frame = new Frame();

        final TextField textField = new TextField();
        textField.setBounds(60, 50, 170, 20);
        textField.setText("Hello");

        Button button = new Button("click me");
        button.setBounds(50,120,80,30);

        button.addActionListener(new ActionListener() {
            int counter = 0;
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                counter++;
                textField.setText(counter + " st");
            }
        });

        frame.add(textField);
        frame.add(button);
        frame.setSize(300,300);
        frame.setLayout(null);
        frame.setVisible(true);

        return frame;
    }
}
