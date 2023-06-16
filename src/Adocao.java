import java.io.Serializable;

class Adocao extends Animal implements AtendimentoVeterinario, Serializable {
    private Cliente clienteAdotante;

    public Adocao(String nome, int idade, Cliente clienteAdotante) {
        super(nome, idade);
        this.clienteAdotante = clienteAdotante;
    }

    public Adocao(Adocao adocao) {
        super(adocao.nome, adocao.idade);
        this.clienteAdotante = adocao.clienteAdotante;
    }
    public Adocao(String nome, int idade, String tipoAnimal, Cliente clienteAdotante) {
        super(nome, idade, tipoAnimal);
        this.clienteAdotante = clienteAdotante;
    }


    public Cliente getClienteAdotante() {
        return clienteAdotante;
    }

    public void setClienteAdotante(Cliente clienteAdotante) {
        this.clienteAdotante = clienteAdotante;
    }

    @Override
    public void emitirSom() {
        System.out.println(nome + " está emitindo sons de alegria após ser adotado por " + clienteAdotante.getNome());
    }

    @Override
    public void print() {
        super.print();
        System.out.println("Cliente adotante: " + clienteAdotante.getNome());
    }

    @Override
    public String toString() {
        return "Adocao [nome=" + nome + ", idade=" + idade + ", clienteAdotante=" + clienteAdotante + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj))
            return false;
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Adocao other = (Adocao) obj;
        return nome.equals(other.nome) && idade == other.idade && clienteAdotante.equals(other.clienteAdotante);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public void realizarAtendimento() {
        System.out.println("Realizando atendimento de adoção para o animal " + nome);
    }
}
