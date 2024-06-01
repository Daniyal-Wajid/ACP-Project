package Menus_Windows;

import Chess.GameMain;
import Stats_Window.Stats;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class MainMenu extends JFrame {

    private GameMain gameMain;

    public MainMenu() {
        setTitle("Menu");
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        JFXPanel fxPanel = new JFXPanel();
        add(fxPanel, BorderLayout.CENTER);

        String videoPath = "D:/ACP/Project/assets/Chess.mp4";
        int videoWidth = 1366;
        int videoHeight = 768;
        initFX(fxPanel, videoPath, videoWidth, videoHeight);

        fxPanel.setLayout(new BorderLayout());
        add(fxPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false);
        fxPanel.add(buttonPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);

        JButton playButton = createButton("Play");
        JButton statsButton = createButton("Stats");
        JButton optionsButton = createButton("Options");
        JButton exitButton = createButton("Exit");

        playButton.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> {
                PlayWindow playWindow = new PlayWindow();
                playWindow.setVisible(true);
            });
        });

        statsButton.addActionListener(e ->{
            SwingUtilities.invokeLater(() -> {
                Stats s = new Stats();
                s.createAndShowGUI();
            });
        });

        optionsButton.addActionListener(e -> {
            OptionsWindow optionsWindow = new OptionsWindow();
            optionsWindow.setVisible(true);
        });

        exitButton.addActionListener(e -> System.exit(0));

        buttonPanel.add(playButton, gbc);
        buttonPanel.add(statsButton, gbc);
        buttonPanel.add(optionsButton, gbc);
        buttonPanel.add(exitButton, gbc);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Monospaced", Font.BOLD, 34));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 0, 0));
        button.setBorderPainted(true);
        button.setFocusPainted(false);
        button.setContentAreaFilled(true);
        button.setOpaque(true);
        return button;
    }

    private void initFX(JFXPanel fxPanel, String videoPath, int width, int height) {
        Group root = new Group();
        Scene scene = new Scene(root);

        Media media = new Media(new File(videoPath).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setVolume(0.2);

        // Loop the video
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(javafx.util.Duration.ZERO));

        MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.setFitWidth(width);
        mediaView.setFitHeight(height);
        mediaView.setPreserveRatio(false);
        root.getChildren().add(mediaView);

        fxPanel.setScene(scene);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainMenu mainMenu = new MainMenu();
            mainMenu.setVisible(true);
        });
    }
}
