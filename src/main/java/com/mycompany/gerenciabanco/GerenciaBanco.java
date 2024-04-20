package com.mycompany.gerenciabanco; // Correção do pacote

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GerenciaBanco {

    private ContaBancaria conta;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GerenciaBanco().criarGUI();
            }
        });
    }

    public void criarGUI() {
        JFrame frame = new JFrame("Gerenciador Bancário");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel nomeLabel = new JLabel("Nome:");
        JTextField nomeField = new JTextField(20);
        JLabel sobrenomeLabel = new JLabel("Sobrenome:");
        JTextField sobrenomeField = new JTextField(20);
        JLabel cpfLabel = new JLabel("CPF:");
        JTextField cpfField = new JTextField(20);
        JButton iniciarButton = new JButton("Iniciar");

        panel.add(nomeLabel);
        panel.add(nomeField);
        panel.add(sobrenomeLabel);
        panel.add(sobrenomeField);
        panel.add(cpfLabel);
        panel.add(cpfField);
        panel.add(iniciarButton);

        iniciarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                String sobrenome = sobrenomeField.getText();
                String cpf = cpfField.getText();
                conta = new ContaBancaria(nome, sobrenome, cpf);
                exibirMenu(frame);
            }
        });

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    private void exibirMenu(JFrame frame) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        JButton consultarSaldoButton = new JButton("Consultar Saldo");
        JButton realizarDepositoButton = new JButton("Realizar Depósito");
        JButton realizarSaqueButton = new JButton("Realizar Saque");
        JButton encerrarButton = new JButton("Encerrar");

        panel.add(consultarSaldoButton);
        panel.add(realizarDepositoButton);
        panel.add(realizarSaqueButton);
        panel.add(encerrarButton);

        consultarSaldoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                consultarSaldo();
            }
        });

        realizarDepositoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                realizarDeposito(frame);
            }
        });

        realizarSaqueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                realizarSaque(frame);
            }
        });

        encerrarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                encerrar(frame);
            }
        });

        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.pack();
    }

    private void consultarSaldo() {
        JOptionPane.showMessageDialog(null, "Saldo atual: R$" + conta.getSaldo());
    }

    private void realizarDeposito(JFrame frame) {
        try {
            String input = JOptionPane.showInputDialog(frame, "Informe o valor do depósito: R$");
            if (input != null && !input.isEmpty()) {
                double valor = Double.parseDouble(input);
                if (valor > 0) {
                    conta.depositar(valor);
                    JOptionPane.showMessageDialog(frame, "Depósito de R$" + valor + " realizado com sucesso.");
                } else {
                    JOptionPane.showMessageDialog(frame, "Valor inválido. O depósito não foi realizado.");
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Valor inválido. O depósito não foi realizado.");
        }
    }

    private void realizarSaque(JFrame frame) {
        try {
            String input = JOptionPane.showInputDialog(frame, "Informe o valor do saque: R$");
            if (input != null && !input.isEmpty()) {
                double valor = Double.parseDouble(input);
                if (valor > 0 && valor <= conta.getSaldo()) {
                    conta.sacar(valor);
                    JOptionPane.showMessageDialog(frame, "Saque de R$" + valor + " realizado com sucesso.");
                } else {
                    JOptionPane.showMessageDialog(frame, "Saldo insuficiente ou valor inválido. O saque não foi realizado.");
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Valor inválido. O saque não foi realizado.");
        }
    }

    private void encerrar(JFrame frame) {
        JOptionPane.showMessageDialog(null, "Obrigado por utilizar nosso serviço! Adeus!");
        frame.dispose();
    }
}

class ContaBancaria {
    private String nome;
    private String sobrenome;
    private String cpf;
    private double saldo;

    public ContaBancaria(String nome, String sobrenome, String cpf) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.saldo = 0.0; // saldo inicial zero
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double valor) {
        saldo += valor;
    }

    public void sacar(double valor) {
        saldo -= valor;
    }
}
