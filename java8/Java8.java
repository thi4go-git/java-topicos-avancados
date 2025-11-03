package java8;

import java.util.Arrays;
import java.util.List;

/*      
 * Expressões lambda: Função anônima que não é alocada memória.
 */

public class Java8 {

    public static void main(String[] args) {
        Java8.exemploLambda();
        Java8.implementarInterfaceFigura();
        Java8.exemploLambdaComArgumento();
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


    // Método que implementa a interface Figura usando expressão lambda ele dá um Override automático no método desenhar.
    public static void implementarInterfaceFigura(){
        // Implementação da interface Figura usando expressão lambda
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

    
}



interface Figura{
    void desenhar();
}

class Produto{
    private String nome;
    private double preco;

    public Produto(String nome, double preco){
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome(){
        return nome;
    }

    public double getPreco(){
        return preco;
    }
}