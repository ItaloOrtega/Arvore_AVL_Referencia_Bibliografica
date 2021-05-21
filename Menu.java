import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import AVL.*;
public class Menu {

    public static void main(String[] args) {

        String titulo,autor;//Variaveis Titulo e Autor
        int ano, op;//Variaveis Ano de Publicação e Operação
        boolean busca=false;//Variavel é uma modificação ou não no método de busca
        Artigo aux = new Artigo("","",0,-1);//Criação de um objeto Artigo vazio
        No A = new No(aux);//Criação da Raiz da arvore
        Scanner ler = new Scanner(System.in);//Criação do Scaner

        try{
            File arquivo = new File("Artigos.txt");//Cria o arquivo Artigos.txt para salvar os objetos artigos cadastrados
            if(arquivo.createNewFile()==false){//Já existindo o arquivo Artigos.txt é feita a leitura para a árvore receber todos os cadastros criados
                A = A.lerArquivo(arquivo,A);
            }


            do {//Do para fazer as operações do programa
                busca = false;
                System.out.println("\nMenu\n1 - Cadastrar\n2 - Excluir\n3 - Modificar\n4 - Buscar\n5 - Imprimir\n6 - Sair\nEscreva a opção:");
                op = ler.nextInt();//Leitura das opções possiveis. Cadastrar novo artigo, Excluir artigo registrado, Modificar artigo registrado, Buscar artigo registrado, Imprimir todos os artigos registrados, Sair do programa
                switch(op){//Switch case das opções disponiveis

                    case 1://Cadastrar um novo artigo
                        System.out.println("\nDigite o titulo do artigo:");
                        Scanner lere = new Scanner(System.in);//Novo Scaner para ser possivel um titulo com espaço
                        titulo = lere.nextLine();

                        System.out.println("\nDigite o autor do artigo:");
                        Scanner lerr = new Scanner(System.in);//Novo Scaner para ser possivel o nome do autor com espaço
                        autor = lerr.nextLine();

                        System.out.println("\nDigite o ano de publicacao do artigo:");
                        ano = ler.nextInt();

                        Artigo P = new Artigo(titulo, autor, ano, -1);//É criado um novo objeto artigo sem o ID

                        P.setID(P.GerarID(A, A, true, 0));//É gerado o ID do cadastro feito, percorrendo por toda a arvore para verificar se não existe nenhum ID igual

                        System.out.println("ID: " + P.getID());//É mostrado o ID criado

                        No novo = new No(P);//É criado um novo nó com o novo artigo criado

                        if(A.getChave().getID()==-1) {//A arvore não possuindo nenhum nó é gerado um nó auxiliar para ser possivel a inserção nela
                            No B = null;
                            A = A.inserir(B, novo);
                        }

                        else//A árvore não sendo vazia é inserido normalmente o novo nó
                            A = A.inserir(A, novo);
                        break;

                    case 2://Excluir artigo já cadastrado

                        if(A == null)//A árvore sendo nula não é possivel excluir
                            System.out.println("\nNenhum cadastro existente!");

                        else {//Existindo pelo menos um artigo é possibilitada a exclusão
                            System.out.println("\nDigite o ID que sera excluido:");
                            ano = ler.nextInt();
                            A=A.remover(A,ano);//A exclusão é feita por meio do ID

                            if(A == null){//A arvore se tornando nula é gerado um nó auxiliar vazio para possibilitar sua manipulação
                                No NN = new No(aux);
                                A = NN;
                            }
                        }

                        break;

                    case 3://Modificar um cadastro existente
                        op = 4;//Operação se torna 4, ou seja, será feito uma busca
                        busca = true;//Será uma busca que ira modificar o cadastro encontrado

                    case 4:
                        if (busca == true) {//Busca sendo verdadeira, irá ser feito uma busca que irá modificar o cadastro que será encontrado pelo método
                            System.out.println("\nPara modificar um artigo é necessario busca-lo primeiro.\nEscolha um tipo de busca para continuar.");
                        }

                        System.out.println("\nTipos de busca:\n1 - Busca por ID\n2 - Busca por Titulo\n3 - Busca por Autor\n4 - Busca por Ano de publicacao\nDigite a opção:");
                        op = ler.nextInt();//Escolher o tipo de busca

                        switch (op){//Switch case das opções de busca

                            case 1://Busca por ID
                                System.out.println("\nBUSCA POR ID\nDigite o ID:");
                                ano = ler.nextInt();
                                A.buscaID(A, A, ano, busca);//Encontrar o cadastro por meio do ID
                                break;

                            case 2://Busca por Titulo
                                System.out.println("\nBUSCA POR TITULO\nDigite o titulo:");
                                Scanner lll = new Scanner(System.in);//Criação de um novo Scanner para que seja possivel a escrita de um titulo com espaço
                                titulo = lll.nextLine();
                                A.buscaString(A, A, titulo, busca,true);//É feito a busca de String, a string neste caso sendo o titulo
                                break;

                            case 3://Busca por Nome do Autor
                                System.out.println("\nBUSCA POR AUTOR\nDigite o nome do autor:");
                                Scanner lal = new Scanner(System.in);//Criação de um novo Scanner para que seja possivel a escrita de um nome do autor com espaço
                                autor = lal.nextLine();
                                A.buscaString(A, A, autor, busca,false);//É feito a busca de String, a string neste caso sendo o nome do autor
                                break;

                            case 4://Busca por Ano de publicação do Artigo
                                System.out.println("\nBUSCA POR ANO DE PUBLICACAO\nDigite o ano:");
                                ano = ler.nextInt();
                                A.buscaAno(A, A, ano, busca);
                                break;
                        }
                        op=4;
                        break;

                    case 5://Imprimir os artigos cadastrados

                        if(A == null || A.getChave().getID() == -1)//A arvore sendo nula ou não possuindo nenhum artigo cadastrado, nada é mostrado
                            System.out.println("\nNenhum cadastro existente!");
                        else {//Existindo valores na árvore são imprimidos primeiro a posição dos artigos, que é organizada por meio dos IDS e após isso todos os dados dos artigos cadastrados(ID, Titulo,Autor e Ano de publicação)
                            System.out.println("\nImpressão dos IDS:\n");
                            A.imprimirID(A);
                            System.out.println("\n\nImpressão dos Nomes:");
                            A.imprimir(A);
                        }
                        break;
                }
            } while (op < 6);//O usuário escolhendo a opção 6 a repetição é finalizada

            //É criado os escritores de arquivo para que seja possivel a escrita no arquivo Artigos.txt
            FileWriter fw = new FileWriter(arquivo,false);
            BufferedWriter bw = new BufferedWriter(fw);

            A.adicionarArquivo(arquivo, A, fw, bw);//A árvore é enviada ao método para que todos os seus dados sejam escritos no arquivo Artigos.txt

            //O filewritter e bufferedwriter são fechados
            bw.close();
            fw.close();
        }
        catch(IOException e){//Erro no arquivo
            System.out.println("Erro: "+e.toString());
        }
    }
}
