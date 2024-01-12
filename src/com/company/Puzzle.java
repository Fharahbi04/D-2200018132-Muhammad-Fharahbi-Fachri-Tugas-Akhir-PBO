package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Puzzle extends Frame implements ActionListener {
    Button b1, b2, b3, b4, b5, b6, b7, b8, b9;
    Button nextButton;
    Timer timer;
    int seconds;
    int score;
    int highScore;
    boolean nextButtonPressed = false;

    private JPanel puzzlePanel; // Panel yang berisi kolom-kolom angka
    private JLabel timerLabel; // Label untuk menampilkan waktu
    private JLabel welcomeLabel; // Label untuk menyambut pemain
    private JLabel scoreLabel; // Label untuk menampilkan skor sebelumnya

    Puzzle() {
        super("D Puzzle");

        // Inisialisasi komponen-komponen
        puzzlePanel = new JPanel();
        puzzlePanel.setLayout(null);
        puzzlePanel.setBounds(50, 50, 500, 500); // Ubah batas panel agar button dapat terlihat

        timerLabel = new JLabel("Time: 0 seconds");
        timerLabel.setForeground(Color.orange);
        timerLabel.setBounds(50, 580, 200, 30);

        welcomeLabel = new JLabel("Welcome to D Puzzle");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        welcomeLabel.setBounds(100, 20, 250, 30);

        scoreLabel = new JLabel(" Score: 0 Detik");
        scoreLabel.setForeground(Color.blue);
        scoreLabel.setBounds(50, 600, 200, 30);

        // Inisialisasi tombol dan puzzle
        initializeButtons();
        initializePuzzle();
        showWelcomeScreen();

        // Inisialisasi tombol "Next"
        nextButton = createButton("Lanjut", 250, 550,Color.blue);
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetPuzzle();
            }
        });

        // Menambahkan komponen-komponen ke frame
        add(timerLabel);
        add(puzzlePanel);
        add(nextButton);
        add(welcomeLabel);
        add(scoreLabel);

        setSize(600, 650);
        setLayout(null);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                exitGame();
            }
        });
    }

    private void resetPuzzle() {
        int resetDelay = 1000; // Misalnya, 1000 milidetik (1 detik)

        // Menyimpan skor sebelumnya
        String previousScoreText = "Score: " + score + " Detik";
        scoreLabel.setText(previousScoreText);

        // Menghentikan timer yang sedang berjalan
        if (timer != null) {
            timer.stop();
        }

        // Menunggu resetDelay sebelum mereset puzzle
        Timer resetTimer = new Timer(resetDelay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mereset puzzle setelah resetDelay
                initializePuzzle();
                showWelcomeScreen();
            }
        });

        resetTimer.setRepeats(false); // Set agar timer hanya berjalan satu kali
        resetTimer.start();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            moveButton(b1);
        } else if (e.getSource() == b2) {
            moveButton(b2);
        } else if (e.getSource() == b3) {
            moveButton(b3);
        } else if (e.getSource() == b4) {
            moveButton(b4);
        } else if (e.getSource() == b5) {
            moveButton(b5);
        } else if (e.getSource() == b6) {
            moveButton(b6);
        } else if (e.getSource() == b7) {
            moveButton(b7);
        } else if (e.getSource() == b8) {
            moveButton(b8);
        } else if (e.getSource() == b9) {
            moveButton(b9);
        }

        // Congrats code
        if (b1.getLabel().equals("1") && b2.getLabel().equals("2") && b3.getLabel().equals("3")
                && b4.getLabel().equals("4") && b5.getLabel().equals("5") && b6.getLabel().equals("6")
                && b7.getLabel().equals("7") && b8.getLabel().equals("8") && b9.getLabel().equals("")) {
            JOptionPane.showMessageDialog(this, "Selamat puzzle terselesaikan");
            timer.stop();
            // Calculate the score based on time
            score = seconds;
            updateHighScore();
            showCongratulations();
        }
    }

    private void moveButton(Button clickedButton) {
        String label = clickedButton.getLabel();
        if (canMove(clickedButton)) {
            swapLabels(clickedButton, getEmptyButton());
        }
    }

    private boolean canMove(Button clickedButton) {
        Button emptyButton = getEmptyButton();
        return (Math.abs(emptyButton.getX() - clickedButton.getX()) == 100 && emptyButton.getY() == clickedButton.getY())
                || (Math.abs(emptyButton.getY() - clickedButton.getY()) == 100 && emptyButton.getX() == clickedButton.getX());
    }

    private void swapLabels(Button button1, Button button2) {
        String temp = button1.getLabel();
        button1.setLabel(button2.getLabel());
        button2.setLabel(temp);
    }

    private Button getEmptyButton() {
        if (b1.getLabel().equals("")) {
            return b1;
        } else if (b2.getLabel().equals("")) {
            return b2;
        } else if (b3.getLabel().equals("")) {
            return b3;
        } else if (b4.getLabel().equals("")) {
            return b4;
        } else if (b5.getLabel().equals("")) {
            return b5;
        } else if (b6.getLabel().equals("")) {
            return b6;
        } else if (b7.getLabel().equals("")) {
            return b7;
        } else if (b8.getLabel().equals("")) {
            return b8;
        } else {
            return b9;
        }
    }

    private void updateHighScore() {
        // Check if the current score is better than the existing high score
        if (score < highScore || highScore == 0) {
            highScore = score;
        }
    }

    private void showWelcomeScreen() {
        seconds = 0;

        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                seconds++;
                timerLabel.setText("Time: " + seconds + " seconds");
            }
        });
        timer.start();
    }

    private Button createButton(String label, int x, int y,Color color) {
        Button button = new Button(label);

        button.setBounds(x, y, 100, 100);
        button.setForeground(Color.yellow);
        button.setBackground(color);
        button.addActionListener(this); // Menambahkan ActionListener ke tombol
        return button;
    }

    private void initializeButtons() {
        b1 = createButton("1", 50, 50,new Color(139, 69, 19));
        b2 = createButton("4", 150, 50,new Color(139, 69, 19));
        b3 = createButton("3", 250, 50,new Color(139, 69, 19));
        b4 = createButton("6", 50, 150,new Color(139, 69, 19));
        b5 = createButton("5", 150, 150,new Color(139, 69, 19));
        b6 = createButton("2", 250, 150,new Color(139, 69, 19));
        b7 = createButton("7", 50, 250,new Color(139, 69, 19));
        b8 = createButton("", 150, 250,new Color(139, 69, 19));
        b9 = createButton("8", 250, 250,new Color(139, 69, 19));

        // Menambahkan tombol-tombol ke panel
        puzzlePanel.add(b1);
        puzzlePanel.add(b2);
        puzzlePanel.add(b3);
        puzzlePanel.add(b4);
        puzzlePanel.add(b5);
        puzzlePanel.add(b6);
        puzzlePanel.add(b7);
        puzzlePanel.add(b8);
        puzzlePanel.add(b9);
    }

    private void initializePuzzle() {
        // Set the initial puzzle configuration
        String[] numbers = {"1", "2", "3", "4", "5", "6", "7", "8", ""};
        shuffleArray(numbers);

        b1.setLabel(numbers[0]);
        b2.setLabel(numbers[1]);
        b3.setLabel(numbers[2]);
        b4.setLabel(numbers[3]);
        b5.setLabel(numbers[4]);
        b6.setLabel(numbers[5]);
        b7.setLabel(numbers[6]);
        b8.setLabel(numbers[7]);
        b9.setLabel(numbers[8]);
    }

    private void shuffleArray(String[] array) {
        // Fisher-Yates shuffle algorithm
        for (int i = array.length - 1; i > 0; i--) {
            int index = (int) (Math.random() * (i + 1));
            String temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

    private void showCongratulations() {
        timer.stop();
        int choice = JOptionPane.showOptionDialog(this,
                "Selamat puzzle terselesaikan\nWaktu anda: " + seconds + " Detik\nScore anda: " + score + " Detik\nHigh Score: " + highScore + " Detik",
                "Selamat!", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                new Object[]{"Reset", "Keluar"}, "Play Again");

        if (choice == JOptionPane.YES_OPTION) {
            resetPuzzle();
        } else {
            exitGame();
        }
    }

    private void exitGame() {
        int confirm = JOptionPane.showConfirmDialog(this, "Apakah ingin keluar dari game?", "Confirm Exit",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {

        new Puzzle();
    }
}

