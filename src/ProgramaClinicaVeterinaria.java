import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProgramaClinicaVeterinaria extends JFrame {
    private List<Animal> animaisDoados = new ArrayList<>();
    private List<RegistroAnimal> registros = new ArrayList<>();

    private JButton btnRegistrarDoacao;
    private JButton btnRegistrarAdocao;
    private JButton btnVisualizarRegistros;
    private JButton btnGuardarDados;
    private JButton btnCarregarDados;

    public ProgramaClinicaVeterinaria() {
        super("Programa de Clínica Veterinária");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 200));

        createComponents();
        addComponents();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createComponents() {
        btnRegistrarDoacao = new JButton("Registrar Doação");
        btnRegistrarAdocao = new JButton("Registrar Adoção");
        btnVisualizarRegistros = new JButton("Visualizar Registros");
        btnGuardarDados = new JButton("Guardar Dados");
        btnCarregarDados = new JButton("Carregar Dados");

        btnRegistrarDoacao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrarDoacao();
            }
        });

        btnRegistrarAdocao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrarAdocao();
            }
        });

        btnVisualizarRegistros.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exibirRegistros();
            }
        });

        btnGuardarDados.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selecionarPasta(true);
            }
        });

        btnCarregarDados.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selecionarPasta(false);
            }
        });
    }

    private void addComponents() {
        setLayout(new FlowLayout());

        add(btnRegistrarDoacao);
        add(btnRegistrarAdocao);
        add(btnVisualizarRegistros);
        add(btnGuardarDados);
        add(btnCarregarDados);
    }


    private void registrarDoacao() {
        String nomeAnimal = JOptionPane.showInputDialog("Informe o nome do animal doado:");
        int idadeAnimal = Integer.parseInt(JOptionPane.showInputDialog("Informe a idade do animal doado:"));
        String tipoAnimal = JOptionPane.showInputDialog("Informe o tipo de animal (ex: cão, gato):");
        String nomeDoador = JOptionPane.showInputDialog("Informe o nome do doador:");

        Animal animal = new Doacao(nomeAnimal, idadeAnimal, tipoAnimal, new Cliente(nomeDoador));
        Cliente doador = new Cliente(nomeDoador);
        Date data = new Date();

        animaisDoados.add(animal);
        registros.add(new RegistroAnimal(animal, doador, data, "Doado"));

        JOptionPane.showMessageDialog(null, "Doação registrada com sucesso!");
    }

    private void registrarAdocao() {
        if (animaisDoados.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há animais disponíveis para adoção no momento.");
            return;
        }

        StringBuilder sb = new StringBuilder("Animais disponíveis para adoção:\n");
        for (Animal animal : animaisDoados) {
            sb.append(animal.getNome()).append("\n");
        }

        String animalSelecionado = JOptionPane.showInputDialog(null, sb.toString(), "Selecionar Animal", JOptionPane.QUESTION_MESSAGE);
        Animal animalAdotado = null;

        for (Animal animal : animaisDoados) {
            if (animal.getNome().equals(animalSelecionado)) {
                animalAdotado = animal;
                break;
            }
        }

        if (animalAdotado != null) {
            String nomeAdotante = JOptionPane.showInputDialog("Informe o nome do adotante:");

            Cliente adotante = new Cliente(nomeAdotante);
            Date data = new Date();

            animaisDoados.remove(animalAdotado);
            registros.add(new RegistroAnimal(animalAdotado, adotante, data, "Adotado"));

            JOptionPane.showMessageDialog(null, "Adoção registrada com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Animal selecionado inválido!");
        }
    }

    private void exibirRegistros() {
        StringBuilder sb = new StringBuilder("Registros de animais:\n\n");
        for (RegistroAnimal registro : registros) {
            sb.append("Animal: ").append(registro.getAnimal().getNome()).append("\n");
            sb.append("Tipo: ").append(registro.getAnimal().getTipoAnimal()).append("\n");
            sb.append("Idade: ").append(registro.getAnimal().getIdade()).append("\n");
            sb.append("Cliente: ").append(registro.getCliente().getNome()).append("\n");
            sb.append("Animal foi: ").append(registro.getTipoAdocao()).append("\n");
            sb.append("Data: ").append(registro.getData()).append("\n\n");
        }

        JOptionPane.showMessageDialog(null, sb.toString());
    }
    private void selecionarPasta(boolean salvar) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnValue;

        if (salvar) {
            returnValue = fileChooser.showSaveDialog(this);
        } else {
            returnValue = fileChooser.showOpenDialog(this);
        }

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFolder = fileChooser.getSelectedFile();

            if (salvar) {
               guardarDados(selectedFolder);
            } else {
                carregarDados(selectedFolder);
            }
        }
    }

    private void guardarDados(File pastaDestino) {
        String nomeArquivo = JOptionPane.showInputDialog(this, "Digite o nome do arquivo:");

        if (nomeArquivo != null && !nomeArquivo.isEmpty()) {
            File arquivoDestino = new File(pastaDestino, nomeArquivo);

            try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(arquivoDestino))) {
                outputStream.writeObject(animaisDoados);
                outputStream.writeObject(registros);
                JOptionPane.showMessageDialog(this, "Dados guardados com sucesso!");

                System.out.println("Dados guardados: animaisDoados=" + animaisDoados.size() + ", registros=" + registros.size());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao guardar os dados: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, digite um nome para o arquivo!");
        }
    }

    private void carregarDados(File pastaOrigem) {
        String nomeArquivo = JOptionPane.showInputDialog(this, "Digite o nome do arquivo:");

        if (nomeArquivo != null && !nomeArquivo.isEmpty()) {
            File arquivoOrigem = new File(pastaOrigem, nomeArquivo);

            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(arquivoOrigem))) {
                animaisDoados = (List<Animal>) inputStream.readObject();
                registros = (List<RegistroAnimal>) inputStream.readObject();
                JOptionPane.showMessageDialog(this, "Dados carregados com sucesso!");
            } catch (IOException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao carregar os dados: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, digite um nome para o arquivo!");
        }
    }

    public static void main(String[] args) {
        new ProgramaClinicaVeterinaria();
    }
}