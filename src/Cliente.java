import java.io.Serializable;

class Cliente implements Serializable {
    private String nome;

    public Cliente(String nome) {
        this.nome = nome;
    }

    public Cliente(Cliente cliente) {
        this.nome = cliente.nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Cliente [nome=" + nome + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj))
            return false;
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Cliente other = (Cliente) obj;
        return nome.equals(other.nome);
    }

    public void print() {
        System.out.println("Nome do cliente: " + nome);
    }
}

