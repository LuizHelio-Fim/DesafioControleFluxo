package src;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Produto {
    String nome;
    String descricao;
    double valor;
    boolean disponivel;

    public Produto(String nome, String descricao, double valor, boolean disponivel) {
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.disponivel = disponivel;
    }
}

public class CadastroProdutos {
    private static List<Produto> produtos = new ArrayList<>();
    private static JFrame frameLista;
    private static DefaultTableModel tableModel;

    public static void main(String[] args) {
        abrirFormularioCadastro();
    }

    private static void abrirFormularioCadastro() {
        JFrame frame = new JFrame("Cadastro de Produto");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridLayout(5, 2));

        JTextField nomeField = new JTextField();
        JTextField descricaoField = new JTextField();
        JTextField valorField = new JTextField();
        JComboBox<String> disponivelBox = new JComboBox<>(new String[]{"Sim", "Não"});
        JButton cadastrarBtn = new JButton("Cadastrar");

        frame.add(new JLabel("Nome do Produto:"));
        frame.add(nomeField);
        frame.add(new JLabel("Descrição:"));
        frame.add(descricaoField);
        frame.add(new JLabel("Valor:"));
        frame.add(valorField);
        frame.add(new JLabel("Disponível para venda:"));
        frame.add(disponivelBox);
        frame.add(new JLabel(""));
        frame.add(cadastrarBtn);

        cadastrarBtn.addActionListener(e -> {
            try {
                String nome = nomeField.getText();
                String descricao = descricaoField.getText();
                double valor = Double.parseDouble(valorField.getText());
                boolean disponivel = disponivelBox.getSelectedItem().equals("Sim");
                produtos.add(new Produto(nome, descricao, valor, disponivel));
                produtos.sort(Comparator.comparingDouble(p -> p.valor));
                frame.dispose();
                abrirListaProdutos();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Valor inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.setVisible(true);
    }

    private static void abrirListaProdutos() {
        if (frameLista == null) {
            frameLista = new JFrame("Lista de Produtos");
            frameLista.setSize(400, 300);
            frameLista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frameLista.setLayout(new BorderLayout());

            tableModel = new DefaultTableModel(new Object[]{"Nome", "Valor"}, 0);
            JTable table = new JTable(tableModel);
            JScrollPane scrollPane = new JScrollPane(table);

            JButton novoProdutoBtn = new JButton("Cadastrar Novo Produto");
            novoProdutoBtn.addActionListener(e -> abrirFormularioCadastro());

            frameLista.add(scrollPane, BorderLayout.CENTER);
            frameLista.add(novoProdutoBtn, BorderLayout.SOUTH);
        }

        tableModel.setRowCount(0);
        for (Produto p : produtos) {
            tableModel.addRow(new Object[]{p.nome, p.valor});
        }

        frameLista.setVisible(true);
    }
}
