import java.io.Serializable;

class Doacao extends Animal implements AtendimentoVeterinario, Serializable {
    private Cliente clienteDoador;

    public Doacao(String nome, int idade, Cliente clienteDoador) {
        super(nome, idade);
        this.clienteDoador = clienteDoador;
    }

    public Doacao(Doacao doacao) {
        super(doacao.nome, doacao.idade);
        this.clienteDoador = doacao.clienteDoador;
    }
    public Doacao(String nome, int idade, String tipoAnimal, Cliente clienteDoador) {
        super(nome, idade, tipoAnimal);
        this.clienteDoador = clienteDoador;
    }


    public Cliente getClienteDoador() {
        return clienteDoador;
    }

    public void setClienteDoador(Cliente clienteDoador) {
        this.clienteDoador = clienteDoador;
    }

    @Override
    public void emitirSom() {
        System.out.println(nome + " está emitindo sons de gratidão para " + clienteDoador.getNome() + " por doar");
    }

    @Override
    public void print() {
        super.print();
        System.out.println("Cliente doador: " + clienteDoador.getNome());
    }

    @Override
    public String toString() {
        return "Doacao [nome=" + nome + ", idade=" + idade + ", clienteDoador=" + clienteDoador + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj))
            return false;
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Doacao other = (Doacao) obj;
        return nome.equals(other.nome) && idade == other.idade && clienteDoador.equals(other.clienteDoador);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public void realizarAtendimento() {
        System.out.println("Realizando atendimento de doação para o animal " + nome);
    }
}