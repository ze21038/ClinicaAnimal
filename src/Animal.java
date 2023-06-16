import java.io.Serializable;

abstract class Animal implements Serializable {
    protected String nome;
    protected int idade;
    protected String tipoAnimal;
    public Animal(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }
    public Animal(String nome, int idade, String tipoAnimal) {
        this.nome = nome;
        this.idade = idade;
        this.tipoAnimal = tipoAnimal;
    }

    public String getTipoAnimal() {
        return tipoAnimal;
    }

    public void setTipoAnimal(String tipoAnimal) {
        this.tipoAnimal = tipoAnimal;
    }

    public abstract void emitirSom();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void print() {
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
    }

    @Override
    public String toString() {
        return "Animal [nome=" + nome + ", idade=" + idade + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj))
            return false;
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Animal other = (Animal) obj;
        return nome.equals(other.nome) && idade == other.idade;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}