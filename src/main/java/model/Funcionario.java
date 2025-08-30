package model;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.Locale;

public class Funcionario extends Pessoa{
    private BigDecimal salario;
    private String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getSalarioFormatado(){
        DecimalFormat df = new DecimalFormat("#.00", new DecimalFormatSymbols(Locale.of("pt", "BR")));
        return df.format(salario);
    }

    public void aumentoNoSalario(double percentual){
        BigDecimal multiplicador = new BigDecimal(String.valueOf(1 + percentual / 100));
        salario = this.salario.multiply(multiplicador);
    }

    @Override
    public String toString() {
        return String.format("%-15s | %-15s | %-8s | %-15s",
                getNome(),
                getDataNascimentoFormatada(),
                getSalarioFormatado(),
                funcao);
    }
}
