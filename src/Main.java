import entities.Funcionario;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.spi.DateFormatProvider;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        List<Funcionario> funcionarios = new ArrayList<>();

        Funcionario maria = new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador");
        Funcionario joao = new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador");
        Funcionario caio = new Funcionario("Caio", LocalDate.of(1961, 2, 5), new BigDecimal("9836.14"), "Coordenador");
        Funcionario miguel = new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor");
        Funcionario alice = new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista");
        Funcionario heitor = new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador");
        Funcionario arthur = new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador");
        Funcionario laura = new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente");
        Funcionario heloisa = new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista");
        Funcionario helena = new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente");

        funcionarios.add(maria);
        funcionarios.add(joao);
        funcionarios.add(caio);
        funcionarios.add(miguel);
        funcionarios.add(alice);
        funcionarios.add(heitor);
        funcionarios.add(arthur);
        funcionarios.add(laura);
        funcionarios.add(heloisa);
        funcionarios.add(helena);


        funcionarios.remove(joao);

        funcionarios.forEach(System.out::println);

        System.out.println("----------------------");

        funcionarios.forEach(funcionario -> {
            BigDecimal valorAjustado = funcionario.getSalario().multiply(new BigDecimal("1.10"));
            funcionario.setSalario(valorAjustado);
        });

        Map<String, List<Funcionario>> funcionariosMap = new HashMap<>();


        funcionarios.forEach(funcionario -> {
            funcionariosMap
                    .computeIfAbsent(funcionario.getFuncao(), k -> new ArrayList<>())
                    .add(funcionario);
        });


        for (Map.Entry<String, List<Funcionario>> stringListEntry : funcionariosMap.entrySet()) {
            System.out.println(stringListEntry.getKey() + ": " + stringListEntry.getValue());
            System.out.println("---------------------------------------");
        }

        funcionarios.forEach(funcionario -> {
            if (funcionario.getDataDeNascimento().getMonthValue() == 10 || funcionario.getDataDeNascimento().getMonthValue() == 12) {
                System.out.println(funcionario);
            }
        });
        System.out.println("----------------------");


        funcionarios.stream()
                .min(Comparator.comparing(funcionario -> funcionario.getDataDeNascimento()))
                .ifPresent(funcionario -> {
                    long idade = ChronoUnit.YEARS.between(funcionario.getDataDeNascimento(), LocalDate.now());
                    System.out.println("Mais velho: " + funcionario.getName() + " | Idade: " + idade + " anos");
                });

        System.out.println("----------------------");

        funcionarios.sort(Comparator.comparing(funcionario -> funcionario.getName()));
        funcionarios.forEach(funcionario -> System.out.println(funcionario));

        System.out.println("----------------------");

        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        NumberFormat formatacaoDeValorParaValorBrasil = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
        formatacaoDeValorParaValorBrasil.setMinimumFractionDigits(2);
        System.out.println("Total salários: R$ " + formatacaoDeValorParaValorBrasil.format(totalSalarios));


        System.out.println("----------------------");

        funcionarios.forEach(funcionario -> {
            BigDecimal quantidadeSalarios = funcionario.getSalario().divide(new BigDecimal("1212.00"), 2, RoundingMode.HALF_UP);
            System.out.println("O(a) funcionário(a) " + funcionario.getName() + " recebe " + quantidadeSalarios + " salários mínimos");
        });

        System.out.println("------------------");

//        funcionarios.forEach(System.out::println);

    }
}