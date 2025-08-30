# Desafio Projedata - Sistema de Funcionários

Sistema de gerenciamento de funcionários desenvolvido em Java.

## 📁 Estrutura do Projeto

```
desafio-projedata/
├── src/
│   └── main/
│       └── java/
│           ├── model/
│           │   ├── Pessoa.java          # Classe abstrata base
│           │   └── Funcionario.java     # Classe que herda de Pessoa
│           └── Main.java                # Classe principal com execução
└── README.md
```

## 📋 Requisitos do Sistema

### Classes
1. **Pessoa**: Classe abstrata com atributos nome (String) e dataNascimento (LocalDate)
2. **Funcionario**: Herda de Pessoa, adiciona salário (BigDecimal) e função (String)

### Funcionalidades Implementadas
- **3.1** - Inserir todos os funcionários na lista
- **3.2** - Remover funcionário "João" da lista
- **3.3** - Imprimir funcionários com formatação (dd/mm/aaaa e vírgula decimal)
- **3.4** - Aplicar 10% de aumento salarial
- **3.5** - Agrupar funcionários por função em Map
- **3.6** - Imprimir funcionários agrupados por função
- **3.8** - Filtrar aniversariantes dos meses 10 e 12
- **3.9** - Encontrar funcionário mais velho
- **3.10** - Listar funcionários em ordem alfabética
- **3.11** - Calcular total de salários
- **3.12** - Calcular salários em múltiplos do salário mínimo (R$ 1.212,00)

## 🛠️ Pré-requisitos

- **Java 21** ou superior
- **Maven 3.6+** (opcional, para build)
- IDE de sua preferência (IntelliJ IDEA, Eclipse, VS Code)

## ▶️ Como Executar

### Opção 1: Via IDE
1. Clone o repositório
2. Abra o projeto na sua IDE
3. Execute a classe `Main.java`

### Opção 2: Via Terminal
```bash
# Compilar
javac -d target/classes src/main/java/model/*.java src/main/java/Main.java

# Executar
java -cp target/classes Main
```

### Opção 3: Via Maven
```bash
# Compilar
mvn compile

# Executar
mvn exec:java -Dexec.mainClass="Main"
```

## 🎯 Características Técnicas

- **POO**: Herança, encapsulamento, abstração
- **Imutabilidade**: Atributos protegidos contra alteração indevida
- **Formatação**: Datas brasileiras (dd/MM/yyyy) e valores monetários com vírgula
- **Precisão**: BigDecimal para cálculos monetários
- **Código Limpo**: Métodos desacoplados e responsabilidades bem definidas

## 📊 Saída Esperada

O sistema exibe:
- Lista completa de funcionários formatada
- Funcionários após aumento salarial
- Agrupamento por função
- Aniversariantes de outubro e dezembro
- Funcionário mais velho da empresa
- Lista alfabética
- Total de salários
- Equivalência em salários mínimos