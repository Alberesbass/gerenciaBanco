/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.gerenciabanco;

/**
 *
 * @author Alberes
 */

import java.util.Scanner;

class ContaBancaria {
    private String nome;
    private String sobrenome;
    private String cpf;
    private double saldo;

    public ContaBancaria(String nome, String sobrenome, String cpf) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.saldo = 0.0;
    }

    public void exibirMenu() {
        Scanner scanner = new Scanner(System.in);
        int escolha;

        do {
            System.out.println("\n----- Menu -----");
            System.out.println("1. Consultar Saldo");
            System.out.println("2. Realizar Depósito");
            System.out.println("3. Realizar Saque");
            System.out.println("4. Encerrar");
            System.out.print("Escolha uma opção: ");

            escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    consultarSaldo();
                    break;
                case 2:
                    realizarDeposito();
                    break;
                case 3:
                    realizarSaque();
                    break;
                case 4:
                    System.out.println("Obrigado por utilizar nosso serviço! Adeus!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (escolha != 4);
    }

    public void consultarSaldo() {
        System.out.println("Saldo atual: R$" + saldo);
    }

    public void realizarDeposito() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Informe o valor do depósito: R$");
        double valor = scanner.nextDouble();
        if (valor > 0) {
            saldo += valor;
            System.out.println("Depósito de R$" + valor + " realizado com sucesso.");
        } else {
            System.out.println("Valor inválido. O depósito não foi realizado.");
        }
    }

    public void realizarSaque() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Informe o valor do saque: R$");
        double valor = scanner.nextDouble();
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
            System.out.println("Saque de R$" + valor + " realizado com sucesso.");
        } else {
            System.out.println("Saldo insuficiente ou valor inválido. O saque não foi realizado.");
        }
    }
}

public class GerenciaBanco {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Informe seu nome: ");
        String nome = scanner.nextLine();

        System.out.print("Informe seu sobrenome: ");
        String sobrenome = scanner.nextLine();

        System.out.print("Informe seu CPF: ");
        String cpf = scanner.nextLine();

        ContaBancaria conta = new ContaBancaria(nome, sobrenome, cpf);
        conta.exibirMenu();
    }
}
