import java.io.Serializable;
import java.util.Date;

class RegistroAnimal implements Serializable {
    private Animal animal;
    private Cliente cliente;
    private Date data;
    private String tipoAdocao;

    public RegistroAnimal(Animal animal, Cliente cliente, Date data) {
        this.animal = animal;
        this.cliente = cliente;
        this.data = data;
    }
    public RegistroAnimal(Animal animal, Cliente cliente, Date data, String tipoAdocao) {
        this.animal = animal;
        this.cliente = cliente;
        this.data = data;
        this.tipoAdocao = tipoAdocao;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getTipoAdocao() {
        return tipoAdocao;
    }

    public void setTipoAdocao(String tipoAdocao) {
        this.tipoAdocao = tipoAdocao;
    }

    public Animal getAnimal() {
        return animal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Date getData() {
        return data;
    }
}