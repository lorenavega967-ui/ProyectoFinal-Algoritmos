package com.example;

import com.example.ui.MainFrame;

import javax.swing.*;


public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            MainFrame ventana =
                    new MainFrame();

            ventana.setVisible(true);

        });

    }
}