package java8;

import java.util.Arrays;
import java.util.List;

/*      
 * Expressões lambda: Função anônima que não é alocada memória.
 */

public class Java8 {

    public static void main(String[] args) {
        // Java8.exemploLambda();
        // Java8.implementarMetodoAutomaticamenteInterfaceFigura();
        // Java8.exemploLambdaComArgumento();
        // Java8.exemploMethodReference();
        Java8.exemploBasicoStream();
    }



    public static void exemploLambda() {
        // Exemplo de expressão lambda
        Runnable r = () -> System.out.println("Executando uma expressão lambda Runnable!");
        r.run();


        //Thread sem parâmetro de entrada
        Thread t1 = new Thread(() -> {           
            System.out.println("Thread t1 em execução.");           
        });
        t1.start();
    }


 
    public static void implementarMetodoAutomaticamenteInterfaceFigura(){
        //implementar a interface Figura usando expressão lambda ele dá um @Override automático no método desenhar.
        Figura figura = () -> System.out.println("Desenhando uma figura.");
        figura.desenhar();
    }


    public static void exemploLambdaComArgumento(){
        List<Produto> produtos = Arrays.asList(
            new Produto("Caneta", 2.5),
            new Produto("Lápis", 1.5),
            new Produto("Caderno", 15.0),
            new Produto("Papel", 1.6)
        );

        // Usando expressão lambda para imprimir os produtos e seus preços
        produtos.forEach( p -> System.out.println(p.getNome() + ": R$" + p.getPreco()) );

        //Exemplo lambda com 2 argumentos, ordenar lista de produtos pelo preço
        produtos.sort( (p1, p2) -> Double.compare(p1.getPreco(), p2.getPreco()) );
        System.out.println("Produtos ordenados por preço:");
        produtos.forEach( p -> System.out.println(p.getNome() + ": R$" + p.getPreco()) );


        //Exemplo lambda com tipos de argumentos explícitos
        produtos.sort( (Produto p1, Produto p2) -> Double.compare(p1.getPreco(), p2.getPreco()) );
    }       


    public static void exemploMethodReference(){
        List<String> nomes = List.of("Ana", "Bruno", "Carlos");
        // Forma com lambda:
        nomes.forEach(nome -> System.out.println(nome));
        // Forma com method reference:
        nomes.forEach(System.out::println);


        //Exemplo method reference com método estático
        System.out.println("Imprimindo produtos usando method reference:");
        List<Produto> produtos = Arrays.asList(
            new Produto("Caneta", 2.5),
            new Produto("Lápis", 1.5),
            new Produto("Caderno", 15.0),
            new Produto("Papel", 1.6)
        );
        produtos.forEach( Produto::imprimeProdutoStatic );
        produtos.forEach( Produto::imprimeProdutoNotStatic );
    }

    public static void exemploBasicoStream(){
        List<Produto> produtos = Arrays.asList(
            new Produto("Caneta", 2.5),
            new Produto("Lápis", 1.5),
            new Produto("Caderno", 15.0),
            new Produto("Caderno Geladeira", 1500.0),
            new Produto("Paper Geladeira", 1565.0),
            new Produto("Caderno teste Geladeira", 1556.0),
            new Produto("Papel", 1.6)
        ); 
        
        produtos.stream()
                .filter(p -> p.getNome().toLowerCase().contains("geladeira")) // Filtra produtos com "geladeira" no nome
                .sorted( (p1, p2) -> Double.compare(p1.getPreco(), p2.getPreco()) ) //ordena pelo preço
                .map(prod -> prod.getNome().toUpperCase() ) //mapeia para o nome em maiúsculo
                .forEach(System.out::println); //imprime os nomes mapeados para maiúsculo

        double precoTotal = produtos.stream()
                .mapToDouble(Produto::getPreco) // mapeia para o preço
                .reduce(0.0, (a, b) -> a + b); // reduz somando os preços
        System.out.println("Preço total dos produtos: R$" + precoTotal);
    }

    
}


@FunctionalInterface // Interface funcional: só pode ter um método abstrato. Obriga a existência de apenas um método.
interface Figura{
    void desenhar();
}

@FunctionalInterface 
interface Figura2D{
    void desenhar2D(Double largura, Double altura);
}

