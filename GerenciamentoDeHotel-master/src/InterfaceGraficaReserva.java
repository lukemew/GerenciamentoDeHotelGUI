import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfaceGraficaReserva extends JFrame {

    private JPanel panelMain;
    private JPanel panelFormulario;
    private JPanel panelReservas;
    private JTextField campoNomeInquilino, campoProfissaoInquilino, campoIdadeInquilino;
    private JTextField campoNumeroQuarto, campoCapacidadeQuarto, campoValorQuarto;
    private JTextField campoIdReserva, campoNumeroNoites;
    private JTextArea areaSaida;
    private JComboBox<String> comboTipoQuarto, comboStatus;

    public InterfaceGraficaReserva() {
        setTitle("Gerenciamento de Reservas");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        panelMain = new JPanel();
        panelMain.setLayout(new CardLayout());

        // Painel de reservas
        panelReservas = new JPanel();
        panelReservas.setLayout(new BorderLayout());
        inicializarPanelReservas(panelReservas);

        // Painel de formulário de reservas
        panelFormulario = new JPanel();
        panelFormulario.setLayout(new GridLayout(0, 2));
        inicializarFormulario(panelFormulario);

        // Adiciona painéis ao painel principal
        panelMain.add(panelReservas, "Reservas");
        panelMain.add(panelFormulario, "Formulario");

        add(panelMain, BorderLayout.CENTER);

        // Botões para navegação
        JPanel panelControle = new JPanel();
        JButton btnAdicionarReserva = new JButton("Adicionar Reserva");
        JButton btnMostrarReservas = new JButton("Mostrar Reservas");

        panelControle.add(btnAdicionarReserva);
        panelControle.add(btnMostrarReservas);
        add(panelControle, BorderLayout.NORTH);

        // Evento de adicionar reserva
        btnAdicionarReserva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarFormulario();
            }
        });

        // Evento de mostrar reservas
        btnMostrarReservas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarReservas();
                mostrarReservasPanel(); // Mudar para a tela de reservas
            }
        });
    }

    private void inicializarPanelReservas(JPanel panel) {
        areaSaida = new JTextArea(10, 50);
        areaSaida.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaSaida);

        JButton btnMostrarFormulario = new JButton("Adicionar Nova Reserva");

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(btnMostrarFormulario, BorderLayout.SOUTH);

        btnMostrarFormulario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarFormulario();
            }
        });

        mostrarReservas(); // Inicializa a visualização das reservas
    }

    private void inicializarFormulario(JPanel panel) {
        // Inquilino
        panel.add(new JLabel("Nome do Inquilino:"));
        campoNomeInquilino = new JTextField();
        panel.add(campoNomeInquilino);

        panel.add(new JLabel("Profissão:"));
        campoProfissaoInquilino = new JTextField();
        panel.add(campoProfissaoInquilino);

        panel.add(new JLabel("Idade:"));
        campoIdadeInquilino = new JTextField();
        panel.add(campoIdadeInquilino);

        // Quarto
        panel.add(new JLabel("Número do Quarto:"));
        campoNumeroQuarto = new JTextField();
        panel.add(campoNumeroQuarto);

        panel.add(new JLabel("Tipo de Quarto:"));
        comboTipoQuarto = new JComboBox<>(new String[] {"Luxo", "Simples"});
        panel.add(comboTipoQuarto);

        panel.add(new JLabel("Capacidade:"));
        campoCapacidadeQuarto = new JTextField();
        panel.add(campoCapacidadeQuarto);

        panel.add(new JLabel("Valor da Diária:"));
        campoValorQuarto = new JTextField();
        panel.add(campoValorQuarto);

        // Reserva
        panel.add(new JLabel("ID da Reserva:"));
        campoIdReserva = new JTextField();
        panel.add(campoIdReserva);

        panel.add(new JLabel("Número de Noites:"));
        campoNumeroNoites = new JTextField();
        panel.add(campoNumeroNoites);

        panel.add(new JLabel("Status:"));
        comboStatus = new JComboBox<>(new String[] {"Pendente", "Confirmada", "Cancelada"});
        panel.add(comboStatus);

        // Botões
        JPanel panelBotoes = new JPanel();
        JButton btnConfirmar = new JButton("Confirmar Reserva");
        JButton btnCancelar = new JButton("Cancelar Reserva");
        panelBotoes.add(btnConfirmar);
        panelBotoes.add(btnCancelar);

        panel.add(panelBotoes);

        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarReserva();
                mostrarReservas();
                limparFormulario();
                mostrarReservasPanel();
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparFormulario();
                mostrarReservasPanel();
            }
        });
    }

    private void mostrarFormulario() {
        CardLayout cl = (CardLayout) (panelMain.getLayout());
        cl.show(panelMain, "Formulario");
    }

    private void mostrarReservasPanel() {
        CardLayout cl = (CardLayout) (panelMain.getLayout());
        cl.show(panelMain, "Reservas");
    }

    private void adicionarReserva() {
        try {
            // Criação dos objetos Quarto e Inquilino
            Quarto quarto = new Quarto();
            quarto.setNumero(Integer.parseInt(campoNumeroQuarto.getText()));
            quarto.setTipoDeQuarto((String) comboTipoQuarto.getSelectedItem());
            quarto.setCapacidade(Integer.parseInt(campoCapacidadeQuarto.getText()));
            quarto.setValor(Double.parseDouble(campoValorQuarto.getText()));

            Inquilino inquilino = new Inquilino();
            inquilino.setNome(campoNomeInquilino.getText());
            inquilino.setProfissao(campoProfissaoInquilino.getText());
            inquilino.setIdade(Integer.parseInt(campoIdadeInquilino.getText()));

            Reserva reserva = new Reserva(quarto, inquilino);
            reserva.setIdReserva(Integer.parseInt(campoIdReserva.getText()));
            reserva.setNumeroDeNoites(Integer.parseInt(campoNumeroNoites.getText()));
            reserva.setStatus((String) comboStatus.getSelectedItem());

            reserva.mostrarDetalhes();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira valores válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarReservas() {
        areaSaida.setText(""); // Limpa o conteúdo atual da área de saída
        for (Reserva reserva : Reserva.reservas) {
            areaSaida.append("Id: " + reserva.getIdReserva() + "\n");
            areaSaida.append("Nome: " + reserva.inquilino.getNome() + "\n");
            areaSaida.append("Status: " + reserva.getStatus() + "\n");
            areaSaida.append("--\n");
        }
    }

    private void limparFormulario() {
        campoNomeInquilino.setText("");
        campoProfissaoInquilino.setText("");
        campoIdadeInquilino.setText("");
        campoNumeroQuarto.setText("");
        campoCapacidadeQuarto.setText("");
        campoValorQuarto.setText("");
        campoIdReserva.setText("");
        campoNumeroNoites.setText("");
        comboTipoQuarto.setSelectedIndex(0); // Define o tipo de quarto para "Luxo"
        comboStatus.setSelectedIndex(0); // Define o status para "Pendente"
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InterfaceGraficaReserva frame = new InterfaceGraficaReserva();
            frame.setVisible(true);
        });
    }
}
