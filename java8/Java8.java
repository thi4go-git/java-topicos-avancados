package java8;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.StringTokenizer;
import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;



/*      
 * Expressões lambda: Função anônima que não é alocada memória.
 */

public class Java8 {

    public static void main(String[] args) {
        //Java8.exemploLambda();
        //Java8.implementarMetodoAutomaticamenteInterfaceFigura();
        //Java8.exemploLambdaComArgumento();
        //Java8.exemploMethodReference();
        //Java8.exemploBasicoStream();
        //Java8.exemploBase64CodificaDecodifica();
        //Java8.exemploStringTokenizer();
        //Java8.exemploStringJoiner();
        //Java8.exemploOptional();
        //Java8.exemploRodarJavaScriptNashorn();
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

    public static void exemploBase64CodificaDecodifica(){
        String texto = "Texto para codificar em Base64";
        System.out.println(texto);

        // Codificar em Base64
        String textoCodificado = java.util.Base64.getEncoder().encodeToString(texto.getBytes());
        System.out.println(textoCodificado);

        // Decodificar de Base64
        byte[] bytesDecodificados = java.util.Base64.getDecoder().decode(textoCodificado);
        String textoDecodificado = new String(bytesDecodificados);
        System.out.println(textoDecodificado);
    }

    // String Tokenizer é uma classe que permite extrair tokens (substrings) de uma string com base em um delimitador especificado.
    public static void exemploStringTokenizer(){
        String nomes = "João, Pedro, Maria, Ana, Paulo";  
        StringTokenizer tokenizer = new StringTokenizer(nomes, ",");
        while (tokenizer.hasMoreTokens()) {
            String nome = tokenizer.nextToken().trim();
            System.out.println(nome);
        }
        //Impressão: João Pedro Maria Ana Paulo
    }

    //Adiciona strings com um delimitador especificado entre elas.
    public static void exemploStringJoiner(){
        StringJoiner strJoiner = new StringJoiner(", ");
        strJoiner.add("João");
        strJoiner.add("Pedro");
        strJoiner.add("Maria");
        strJoiner.add("Ana");
        strJoiner.add("Paulo");
        System.out.println(strJoiner);
        //Impressão: João, Pedro, Maria, Ana, Paulo
    }
    
    //Otimiza o tratamento de valores que podem estar ausentes, evitando null checks e NullPointerExceptions.
    public static void exemploOptional(){
        //Exemplo de uso do Optional        
        List<Produto> produtos = new ArrayList<>();
        
        Produto prod = new Produto();
        prod.setNome("Produto com tipo");
        prod.setpreco(5.0);
        prod.setTipoProduto( new TipoProduto(1, "Eletrônico") );        
        produtos.add(prod);

        Produto prod2 = new Produto();
        prod2.setNome("Produto sem tipo");
        prod2.setpreco(5.0);
        produtos.add(prod2);

        //Usando Optional para evitar null pointer
        produtos.forEach( produ -> {
            String tipoDescricaoProduto = Optional.ofNullable(produ.getTipoProduto()) //Cria um Optional que pode ser nulo
                    .map(tp -> tp.getDescricao()) //Se não for nulo, mapeia para a descrição
                    .orElse(null); //Se for nulo, retorna null
            System.out.println("Produto: " + produ.getNome() + ", Preço: R$ " + produ.getPreco() + ", Tipo: " + tipoDescricaoProduto);
        });       
    }

    //Atenção: o Nashorn não existe mais a partir do Java 15.  Ocorre erro em versões mais recentes.
    public static void exemploRodarJavaScriptNashorn(){
        //Exemplo de uso do motor JavaScript Nashorn        
        ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("Nashorn");
        Bindings bindings = scriptEngine.getBindings(ScriptContext.ENGINE_SCOPE);     
        try {
            scriptEngine.eval(new FileReader("script.js"));
        } catch (Exception e) {
            e.printStackTrace();
        }        
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

