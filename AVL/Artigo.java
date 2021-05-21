package AVL;
import java.util.Random;

public class Artigo {//Classe artigo

    private String tittle, author;//Variaveis Titulo e Autor
    private int ID, year;//Variaveis ID e Ano de publicação

    public Artigo(String titulo, String autor, int ano, int cod){//Criando e colocando os valores na classe

        this.tittle=titulo;
        this.author=autor;
        this.year=ano;
        this.ID=cod;

    }
    //Sets e Gets da classe(Titulo, ID, Autor e ano de publicação)
    public void setTitulo(String titulo){
        this.tittle=titulo;
    }

    public void setAutor(String autor){
        this.author=autor;
    }

    public void setID(int cod){
        this.ID=cod;
    }

    public void setAno(int ano){
        this.year=ano;
    }

    public String getAutor(){
        return this.author;
    }

    public String getTitulo(){
        return this.tittle;
    }

    public int getAno(){

        return this.year;
    }

    public int getID(){

        return this.ID;
    }

    public String Ref(){//Método para imprimir o nome do autor como referencia

        int po=0;//Posição onde ocorre o ultimo espaço entre sobrenomes
        for (int i =0; i< this.author.length();i++){//For para encontrar onde ocorre o ultimo espaço do nome do Autor
            if (this.author.charAt(i) == ' ')
                po=i;
        }
        //Esta nova String AUX, é a referencia correta do nome, que é o ultimo sobrenome + virgula + restante do nome
        String aux = this.author.substring((po+1),this.author.length()) + ", "+this.author.substring(0,(po));

        return aux;//É retornado está String de referencia do nome do Autor
    }
    //N sendo o nó atual, OR sendo o nó raiz/origem, p sendo se é necessário ou não a criação de um novo ID, ID sendo o número gerado pelo método para o cadastro
    public int GerarID(No n,No or, boolean p, int ID){//Método para gerar um ID que não tenha sido usado ainda no programa, que percorre a árvore toda

        if(p == true){//P sendo igual a true é gerado um novo ID
            Random gerador = new Random();//Criando classe para gerar numeros aleatorios
            ID = gerador.nextInt(10001);//Gerando ID
        }

        if(n != null) {//Nó sendo diferente de nulo
            if(n.getChave().getID()== ID)//O ID criado sendo igual ao ID do nó atual será criado um novo ID
                ID = GerarID(or, or, true, 0 );

            if(n.getChave().getID()!= ID){//O ID sendo diferente do nó atual o método é iniciado nos nós filhos, eles sendo diferentes de 0
                if(n.getEsquerda()!= null)
                    ID = GerarID(n.getEsquerda(),or,false, ID);

                if(n.getDireita() != null)
                    ID = GerarID(n.getDireita(),or,false, ID);
            }
        }
        return ID;//Retorna o ID gerado
    }
}