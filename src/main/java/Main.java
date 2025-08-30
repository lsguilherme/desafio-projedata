import model.Funcionario;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.*;

public class Main {

    private static final BigDecimal SALARIO_MINIMO = new BigDecimal("1212.00");

    public static void main(String[] args) {
        // 3.1 – Inserir todos os funcionários, na mesma ordem e informações da tabela acima.
        List<Funcionario> funcionarios = inserirFuncionarios();

        // 3.2 – Remover o funcionário “João” da lista.
        removerFuncionario(funcionarios, "João");

        /*
            3.3 – Imprimir todos os funcionários com todas suas informações, sendo que:
                • informação de data deve ser exibido no formato dd/mm/aaaa;
                • informação de valor numérico deve ser exibida no formatado com separador de milhar como ponto e decimal como vírgula.
        */
        cabecalhoTabelaAsc();
        imprimirFuncionarios(funcionarios);

        divisoria();

        // 3.4 Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor
        System.out.println("\n\nATUALIZANDO SALÁRIO...\n\n");

        cabecalhoTabelaAsc();
        aumentoSalarial(funcionarios, 10);
        imprimirFuncionarios(funcionarios);

        divisoria();

        // 3.5 – Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.
        Map<String, List<Funcionario>> funcionariosMap = agruparPorFuncao(funcionarios);

        // 3.6 – Imprimir os funcionários, agrupados por função.
        imprimirFuncionariosPorFuncao(funcionariosMap);

        // 3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12.
        imprimirAniversariantes(funcionarios, 10, 12);

        // 3.9 – Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.
        imprimirFuncionarioMaisVelho(funcionarios);

        // 3.10 – Imprimir a lista de funcionários por ordem alfabética.
        imprimirFuncionarioPorOrdemAlfabetica(funcionarios);

        // 3.11 – Imprimir o total dos salários dos funcionários.
        imprimirSalarioTotalDosFuncionarios(funcionarios);

        // 3.12 – Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.
        imprimirQuantosSalariosMinimosPorFuncionario(funcionarios, SALARIO_MINIMO);
    }

    private static void imprimirQuantosSalariosMinimosPorFuncionario(List<Funcionario> funcionarios, BigDecimal salarioMinimo) {
        System.out.println("\n\nSalário mínimos por funcionários");
        divisoria();

        for (Funcionario funcionario : funcionarios){
            BigDecimal quantidadeSalariosMinimo = funcionario.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);
            System.out.println(funcionario.getNome() + " recebe " + quantidadeSalariosMinimo + " salários mínimos");
        }
    }

    private static void imprimirSalarioTotalDosFuncionarios(List<Funcionario> funcionarios) {
        System.out.println("\n\nSalário total dos funcionários");
        divisoria();
        BigDecimal salarioTotal = BigDecimal.ZERO;

        DecimalFormat df = new DecimalFormat("#.00", new DecimalFormatSymbols(Locale.of("pt", "BR")));

        for (Funcionario funcionario : funcionarios){
            salarioTotal = salarioTotal.add(funcionario.getSalario());
        }

        System.out.println("Total: R$ " + df.format(salarioTotal));
    }

    private static void imprimirFuncionarioPorOrdemAlfabetica(List<Funcionario> funcionarios) {
        System.out.println("\n\n\nFuncionários por ordem alfabética");
        cabecalhoTabelaAsc();

        List<Funcionario> funcionariosOrdenados = funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .toList();

        funcionariosOrdenados.forEach(System.out::println);
    }

    private static void imprimirFuncionarioMaisVelho(List<Funcionario> funcionarios) {
        System.out.println("\n\nFuncionário com a maior idade da empresa.");
        System.out.println("-------------------------------------------------------------");
        Funcionario maisVelho = funcionarios.getFirst();
        for (Funcionario funcionario : funcionarios){
            if (funcionario.getDataNascimento().isBefore(maisVelho.getDataNascimento())){
                maisVelho = funcionario;
            };
        }
        System.out.println("Nome       | Idade");
        System.out.println("-------------------------------------------------------------");
        System.out.format("%-10s | %-4s",
                maisVelho.getNome(),
                maisVelho.calcularIdade()
        );
    }

    private static void imprimirAniversariantes(List<Funcionario> funcionarios, int... meses) {
        System.out.println("\n\nFuncionários que fazem aniversário nos meses: " + Arrays.toString(meses).replaceAll("[\\[\\]]", ""));
        divisoria();

        for (Funcionario funcionario : funcionarios){
            int mes = funcionario.getDataNascimento().getMonthValue();
            for (int m : meses){
                if (mes == m){
                    System.out.println(funcionario);
                }
            }
        }
    }

    private static void imprimirFuncionariosPorFuncao(Map<String, List<Funcionario>> funcionariosMap) {
        System.out.println("\n\nFuncionários agrupados por função.");
        divisoria();
        for (Map.Entry<String, List<Funcionario>> entry : funcionariosMap.entrySet()){
            System.out.println(entry.getKey());
            for (Funcionario funcionario : entry.getValue()){
                System.out.println(" - " + funcionario);
            }
            divisoria();
        }
    }

    private static void imprimirFuncionarios(List<Funcionario> funcionarios) {
        for (Funcionario funcionario : funcionarios){
            System.out.println(funcionario);
        }
    }

    private static List<Funcionario> inserirFuncionarios(){
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), BigDecimal.valueOf(2_009.44), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), BigDecimal.valueOf(2_284.38), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), BigDecimal.valueOf(9_836.14), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), BigDecimal.valueOf(19_119.88), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), BigDecimal.valueOf(2_234.68), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), BigDecimal.valueOf(1_582.72), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), BigDecimal.valueOf(4_071.84), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), BigDecimal.valueOf(3_017.45), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), BigDecimal.valueOf(1_606.85), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), BigDecimal.valueOf(2_799.93), "Gerente"));
        return funcionarios;
    }

    private static void removerFuncionario(List<Funcionario> funcionarios, String nome){
        funcionarios.removeIf(f -> f.getNome().equals(nome));
    }

    private static void aumentoSalarial(List<Funcionario> funcionarios, double percentual){
        funcionarios.forEach(f -> f.aumentoNoSalario(percentual));
    }

    private static Map<String, List<Funcionario>> agruparPorFuncao(List<Funcionario> funcionarios) {
        Map<String, List<Funcionario>> funcionarioMap = new HashMap<>();
        for (Funcionario funcionario : funcionarios){
            funcionarioMap.computeIfAbsent(funcionario.getFuncao(), k -> new ArrayList<>()).add(funcionario);
        }
        return funcionarioMap;
    }

    private static void divisoria(){
        System.out.println("-------------------------------------------------------------");
    }
    private static void cabecalhoTabelaAsc(){
        System.out.println("----------------|-----------------|----------|---------------");
        System.out.println("Nome            | Data Nascimento | Salário  | Função");
        System.out.println("----------------|-----------------|----------|---------------");
    }
}
