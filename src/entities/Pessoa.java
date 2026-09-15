package entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Pessoa {

    private String name;
    private LocalDate dataDeNascimento;

    public Pessoa(String name, LocalDate dataDeNascimento) {
        this.name = name;
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(name, pessoa.name) && Objects.equals(dataDeNascimento, pessoa.dataDeNascimento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dataDeNascimento);
    }

    @Override
    public String toString() {
        return
                "name='" + name + '\'' +
                        ", dataDeNascimento=" + dataDeNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
