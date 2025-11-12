package java8;

public class TipoProduto {
    int id;
    String descricao;

    TipoProduto(int id, String descricao){
        this.id = id;
        this.descricao = descricao;    
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }       

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
