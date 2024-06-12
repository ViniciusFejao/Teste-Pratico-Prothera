package application;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import entities.Funcionario;

public class Principal {

	public static void main(String[] args) {
		
		// Inicia uma lista de funcionários com dados fornecidos.
		List<Funcionario> funcionarios = new ArrayList<>(Arrays.asList(
				new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal(2009.00), "Operador"),
				new Funcionario("João", LocalDate.of(1990, 05, 12), new BigDecimal(2284.38), "Operador"),
				new Funcionario("Caio", LocalDate.of(1961, 05, 02), new BigDecimal(9836.14), "Coordenador"),
				new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal(19119.88), "Diretor"),
				new Funcionario("Alice", LocalDate.of(1995, 01, 05), new BigDecimal(2234.68), "Recepcionista"),
				new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal(1582.72), "Operador"),
				new Funcionario("Arthur", LocalDate.of(1993, 03, 31), new BigDecimal(4071.84), "Contador"),
				new Funcionario("Laura", LocalDate.of(1994, 07, 010), new BigDecimal(3017.45), "Gerente"),
				new Funcionario("Heloísa", LocalDate.of(2003, 05, 24), new BigDecimal(1606.85), "Eletricista"),
				new Funcionario("Helena", LocalDate.of(1996, 011, 02), new BigDecimal(2799.93), "Gerente")));
		
		// Usa removeIf para remover o funcionário chamado João.
		funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));
		
		// Formata e impreme os dados de cada funcionário.
		DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		NumberFormat formatadorNumero = NumberFormat.getInstance(Locale.forLanguageTag("pt-BR"));
		for (Funcionario i : funcionarios) {
			System.out.println("Nome: " + i.getNome());
			System.out.println("Data de Nascimento: " + i.getDataNascimento().format(formatadorData));
			System.out.println("Salário: " + formatadorNumero.format(i.getSalario()));
			System.out.println("Função: " + i.getFuncao());
			System.out.println();
		}
		
		// Aplica um aumento de 10% do salário para funcionário.
		for (Funcionario i : funcionarios) {
			BigDecimal aumento = i.getSalario().multiply(new BigDecimal(1.10));
			i.setSalario(aumento);
		}
		
		// Apenas separar os prints na tela.
		System.out.println("###################################################");
		System.out.println();
		
		// Agrupa os funcionários por função usando Stream e Collectors.groupingBy.
		Map<String, List<Funcionario>> funcionariosFuncao = funcionarios.stream().collect(Collectors.groupingBy(Funcionario::getFuncao));
		
		// Imprime os funcionários agrupados por função
		for (Map.Entry<String, List<Funcionario>> entry : funcionariosFuncao.entrySet()) {
			System.out.println("Função: " + entry.getKey());
			for (Funcionario i : entry.getValue()) {
				System.out.println("Nome: " + i.getNome());
				System.out.println("Data de Nascimento: " + i.getDataNascimento().format(formatadorData));
				System.out.println("Salário: " + formatadorNumero.format(i.getSalario()));
				System.out.println();
			}
		}
		
		// Apenas separar os prints na tela.
		System.out.println("###################################################");
		System.out.println();
		
		// Filtra e imprime os funcionários que fazem anuversário nos meses 10 e 12.
		System.out.println("Funcionários que fazem aniversário nos meses 10 e 12: ");
		for (Funcionario i : funcionarios) { 
			int mes = i.getDataNascimento().getMonthValue();
			if (mes == 10 || mes == 12) {
				System.out.println("Nome: " + i.getNome() + ", Data de Nascimento: " + i.getDataNascimento().format(formatadorData));
			}
		}
		System.out.println();
		
		// Apenas separar os prints na tela.
		System.out.println("###################################################");
		System.out.println();
		
		// Encontra e imprime o funcionário com a maior idade.
		Funcionario velho = Collections.min(funcionarios, Comparator.comparing(Funcionario::getDataNascimento));
		int idade = LocalDate.now().getYear() - velho.getDataNascimento().getYear();
		System.out.println("Funcionário com maior idade: " + velho.getNome() + ", Idade: " + idade);
		System.out.println();
		
		// Apenas separar os prints na tela.
		System.out.println("###################################################");
		System.out.println();
		
		// Ordena e imprime a lista de funcionários em ordem alfabética.
		System.out.println("Funcionários em ordem alfabética: ");
		List<Funcionario> funcionariosOrdemAlfabetica = new ArrayList<>(funcionarios);
		funcionariosOrdemAlfabetica.sort(Comparator.comparing(Funcionario::getNome));
		for (Funcionario i : funcionariosOrdemAlfabetica) {
			System.out.println(i.getNome());
		}
		
		// Apenas separar os prints na tela.
		System.out.println("###################################################");
		System.out.println();
		
		// Calcula e imprime o total de salário.
		BigDecimal totalSalario = funcionarios.stream().map(Funcionario::getSalario).reduce(BigDecimal.ZERO, BigDecimal::add);
		System.out.println("Total dos salários: " + formatadorNumero.format(totalSalario));
		
		// Apenas separar os prints na tela.
		System.out.println("###################################################");
		System.out.println();
		
		// Calcula e imprime quantos salários mínimos cada funcionário ganha.
		BigDecimal salarioMinimo = new BigDecimal(1212.00);
		System.out.println("Salários em relação ao salário mínimo: ");
		for (Funcionario i : funcionarios) {
			BigDecimal qntdSalarioMinimo = i.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);
			System.out.println(i.getNome() + " ganha " + formatadorNumero.format(qntdSalarioMinimo) + " salários mínimos.");
		}
		
	}

}
