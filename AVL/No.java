package AVL;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class No {//Classe No

    private Artigo key;//Chave, que é um objeto Artigo
    private int height;//Altura, que é onde o nó se encontra na árvore
    private No left, right;//Nós esquerda e direita, são os nós filhos do nó em questão

    public No(Artigo chave){//Para a criação de um objeto nó só é necessário a chave(Artigo), pois altura de um novo nó sempre será 0 e não terá de começo nenhum filho(Nó direita e esquerda)
        //Criando e colocando os valores na classe
        this.key=chave;
        this.height=0;
        this.left=null;
        this.right=null;
    }

    //Gets e Sets dos atributos da classe Nó
    public void setChave(Artigo chave){
        this.key=chave;
    }

    public void setAltura(int altura){
        this.height=altura;
    }

    public void setEsquerda(No esquerda){
        this.left=esquerda;
    }

    public void setDireita(No direita){
        this.right=direita;
    }

    public Artigo getChave(){
        return this.key;
    }

    public int getAltura(){
        return this.height;
    }

    public No getEsquerda(){

        return this.left;
    }

    public No getDireita(){

        return this.right;
    }
    //Método que atualiza a altura do nó em questão
    private void atualizarAltura(No n){
        //A nova altura é feita por meio do seguinte calculo. 1 + o maior valor entre as alturas dos nós filhos, Nó esquerda e direita.
        n.setAltura((1+(Math.max(Altura(n.getEsquerda()),Altura(n.getDireita())))));
    }

    public int Altura(No n){//Método para receber a altura do Nó, é necessário pois se o nó for vazio não é possivel utilizar o método getAltura
        if(n == null)//Nó sendo vazio é retornado -1
            return -1;

        return n.getAltura();//Nó sendo diferente de vazio é retornado sua altura
    }

    private No rotEsquerda(No b){//Método que rotaciona a esquerda o nó original, o nó B
        No a = b.getDireita();//A recebe o nó filho da direita do nó B
        b.setDireita(a.getEsquerda());//Nó B define seu nó filho da direita como o nó filho da esquerda do nó A. Que no caso seria o nó filho da esquerda de seu nó filho da direita do nó B.
        a.setEsquerda(b);//A define seu eu nó filho da esquerda como o nó B
        atualizarAltura(b);//Altura do nó B é atualizada
        atualizarAltura(a);//Altura do nó A é atualizada
        return a;//Somente o nó A é retornado, pois agora tanto A quanto B estão nas devidas posições
    }

    private No rotDireita(No b){//Método que rotaciona a direita o nó original, o nó B
        No a = b.getEsquerda();//A recebe o nó filho da esquerda do nó B
        b.setEsquerda(a.getDireita());//Nó B define seu nó filho da esquerda como o nó filho da direita do nó A. Que no caso seria o nó filho da direita de seu nó filho da esquerda do nó B.
        a.setDireita(b);
        atualizarAltura(b);//Altura do nó B é atualizada
        atualizarAltura(a);//Altura do nó A é atualizada
        return a;//Somente o nó A é retornado, pois agora tanto A quanto B estão nas devidas posições
    }

    //Este método é feito após ser encontrado o artigo em questão por meio de uma das buscas(BuscaString, BuscaID ou BuscaAno)
    public void modificar(No n){//Metodo que irá modificar um artigo já cadastrado. Poderá ser modificado o Titulo, o nome do Autor ou o ano de publicação.
        int op;//Variavel da operação escolhida pelo usuário

        System.out.println("\nDeseja modificar:\n1 - Titulo do Artigo Cadastrado\n2 - Nome do Autor do Artigo Cadastrado\n3 - Ano de publicacao do Artigo Cadastrado\nDigite a opção:");
        Scanner ler = new Scanner(System.in);//Scaner para ler a opção escolhida do usuário
        op = ler.nextInt();

        if(op==1){//Modificar o titulo do artigo
            System.out.println("\nDigite o novo titulo do artigo cadastrado:");
            Scanner lern = new Scanner(System.in);
            String nome = lern.nextLine();
            n.getChave().setTitulo(nome);//O titulo original do artigo é sobreposto pelo novo
        }

        if(op==2){//Modificar o nome do autor
            System.out.println("\nDigite o novo nome do autor do artigo cadastrado:");
            Scanner lern = new Scanner(System.in);
            String nome = lern.nextLine();
            n.getChave().setAutor(nome);//O nome do autor original do artigo é sobreposto pelo novo
        }

        if(op==3){//Modificar o ano de publicação do artigo
            System.out.println("\nDigite o novo ano de publicacao do artigo cadastrado:");
            int ano = ler.nextInt();
            n.getChave().setAno(ano);//O ano de publicação original do artigo é sobreposto pelo novo
        }
        //É mostrado a mudança feita no artigo
        System.out.println("\nArtigo Atualizado!\nID: "+n.getChave().getID()+"\nTitulo: "+n.getChave().getTitulo());
        System.out.println("Autor: "+n.getChave().getAutor()+"\nAno de publicacao: "+n.getChave().getAno());

    }

    //Método para buscar um artigo por meio de uma String, sendo o nome do autor ou o titulo do artigo
    public void buscaString(No A,No n, String str, boolean b, boolean tipo){//Recebe a raiz da arvore, o no atual, a String que está buscando, se é uma busca que ira resultar numa modificação e o se é uma busca de titulo ou autor

        int op=2;//Variavel de resposta do usuário se é o artigo desejado ou não
        boolean a=false;//Variavel de sucesso da busca do artigo

        if(n != null){//No atual não sendo nulo

            if(str.equals(n.getChave().getTitulo())==true && a==false && tipo == true){//A string sendo igual ao titulo do artigo atual, não ter sido ainda encontrada o artigo desejado e o tipo de busca for de titulo, será mostradon os dados deste artigo
                System.out.println("\nArtigo Encontrado!\nID: "+n.getChave().getID()+"\nTitulo: "+n.getChave().getTitulo());
                System.out.println("Autor: "+n.getChave().getAutor()+"\nAno de publicacao: "+n.getChave().getAno());
                System.out.println("Esta era o artigo desejado? 1 - Sim e 2 - Não");
                Scanner ler = new Scanner(System.in);
                op = ler.nextInt();
                if(op==1){//Sendo o artigo desejado a busca termina
                    a=true;

                    if(b == true) {//Sendo uma busca que está ligada a modificação do artigo é chamado o método modificar mandando o nó atual para ser modificado
                        modificar(n);
                    }
                }
            }
            //Mesma coisa mas sendo utilizado o campo nome do autor
            if(str.equals(n.getChave().getAutor())==true && a==false && tipo == false){
                System.out.println("\nArtigo Encontrado!\nID: "+n.getChave().getID()+"\nTitulo: "+n.getChave().getTitulo());
                System.out.println("Autor: "+n.getChave().getAutor()+"\nAno de publicacao: "+n.getChave().getAno());
                System.out.println("Esta era o artigo desejado? 1 - Sim e 2 - Não");
                Scanner ler = new Scanner(System.in);
                op = ler.nextInt();
                if(op==1){
                    a=true;

                    if(b == true) {
                        modificar(n);
                    }
                }
            }

            if(a==false){//Não sendo o artigo desejado, é executado este método novamente com um nó filho(nó direita e esquerda) se ele existir e não for nulo
                if(n.getEsquerda()!= null)
                    buscaString(A,n.getEsquerda(),str, b,tipo);

                if(n.getDireita() != null)
                    buscaString(A,n.getDireita(),str, b,tipo);
            }
        }
    }

    public void buscaID(No A, No n, int id, boolean b){//Buscar pelo ID o cadastro, mesmo caso da Busca de String, exceto que encontrado o ID não existe outro possivel, por cada ID ser unico
        ;
        int op=2;
        boolean a=false;

        if(n != null){
            if(n.getChave().getID()==id){
                System.out.println("\nArtigo Encontrado!\nID: "+n.getChave().getID()+"\nTitulo: "+n.getChave().getTitulo());
                System.out.println("Autor: "+n.getChave().getAutor()+"\nAno de publicacao: "+n.getChave().getAno());
                if(b == true) {
                    modificar(n);
                }
            }

            if(n.getChave().getID()!=id){

                if(n.getEsquerda()!= null)
                    buscaID(A, n.getEsquerda(),id, b);

                if(n.getDireita() != null)
                    buscaID(A, n.getDireita(),id, b);
            }
        }
    }
    //Metodo de busca por meio do ano de publicação, mesma coisa de busca por String, diferenciando por utilizar o Ano como comparação
    public void buscaAno(No A,No n, int idade, boolean b){

        int op=2;
        boolean a=false;

        if(n != null){
            if(n.getChave().getAno()==idade  && a==false){
                System.out.println("\nArtigo Encontrado!\nID: "+n.getChave().getID()+"\nTitulo: "+n.getChave().getTitulo());
                System.out.println("Autor: "+n.getChave().Ref()+"\nAno de publicacao: "+n.getChave().getAno());
                System.out.println("Esta era a pessoa desejada? 1 - Sim e 2 - Não");
                Scanner ler = new Scanner(System.in);
                op = ler.nextInt();
                if(op==1){
                    a=true;

                    if(b == true) {
                        modificar(n);
                        return;
                    }
                }
            }
            if(n.getChave().getAno()!=idade || a==false){
                if(n.getEsquerda()!= null)
                    buscaAno(A, n.getEsquerda(),idade, b);

                if(n.getDireita() != null)
                    buscaAno(A, n.getDireita(),idade, b);
            }
        }
    }

    public void imprimirID(No r){//Método para imprimir somente a ordem dos artigos por meio do ID
        //Exemplo 4(2(_()_())6(_()_()))
        //Ou seja
        //     4
        //   2   6
        if(r == null)
            System.out.print("_()");

        if(r != null){
            System.out.print(r.getChave().getID()+"(");
            imprimirID(r.getEsquerda());//Inicia o método com o nó filho da esquerda
            imprimirID(r.getDireita());//Inicia o método com o nó filho da direita
            System.out.print(")");
        }
    }

    public void imprimir(No r){//Método para imprimir todos os dados do artigo que se encontra no nó, imprimindo somente se o nó não for nulo
        if(r != null){
            System.out.println("\nID: "+r.getChave().getID()+"\nTitulo: "+r.getChave().getTitulo()+"\nAutor: "+r.getChave().Ref()+"\nAno de publicacao: "+r.getChave().getAno());
            imprimir(r.getEsquerda());//Inicia o método com o nó filho da esquerda
            imprimir(r.getDireita());//Inicia o método com o nó filho da direita
        }
    }

    public No inserir(No x,No y){//Método recursivo que vai inserir um novo Artigo na árvore. Começando pela raiz da árvore e adentrando mais nela. O nó atual é o X e o novo que será inserido é o Y

        if(x==null)//Nó atual sendo igual a nulo é retornado o novo nó
            return y;

        if(x.getChave().getID()> y.getChave().getID()) {//O nó atual sendo maior que o novo nó, o nó Y será inserido no lado esquerdo do nó X por meio deste método
            x.setEsquerda(inserir(x.getEsquerda(), y));//É enviado o valor da esquerda do nó atual para ser o novo X e o mesmo Y, para ele ser inserido nesta posição da arvore

            if( (Altura(x.getEsquerda())-Altura(x.getDireita())) >=2){//A diferença entre as alturas dos nós filhos de X, nó filho da esquerda e da direita, sendo igual ou maior que 2 será feito uma rotação a direita
                x = rotDireita(x);//A rotação é necessário porque ao inserir o novo nó a esquerda, fez com que a árvore se tornasse desbalanceada
            }
            if((Altura(x.getEsquerda())-Altura(x.getDireita())) <=(-2)) {//A diferença entre as alturas dos nós filhos de X, nó filho da esquerda e da direita, sendo igual ou menor que -2 será feito uma rotação a direita-esquerda. É feita está rotação pois o desbalanceamento se encontra na parte direita da arvore e na parte interior dela.
                x.setDireita(rotDireita(x.getDireita()));//O nó filho da direita de X será o resultado da rotação direita deste mesmo nó filho
                x = rotEsquerda(x);//Após a rotação a direita ter sido feita, ocorrerá uma rotação a esquerda com o próprio nó X
                //Após isso este ramo da arvore estará balanceada
            }
        }

        if(x.getChave().getID()< y.getChave().getID()) {///O nó atual sendo menor que o novo nó, o nó Y será inserido no lado direito do nó X por meio deste método
            x.setDireita(inserir(x.getDireita(), y));//É enviado o valor da direita do nó atual para ser o novo X e o mesmo Y, para ele ser inserido nesta posição da arvore
            if ((Altura(x.getEsquerda()) - Altura(x.getDireita())) >= 2) {//A diferença entre as alturas dos nós filhos de X, nó filho da esquerda e da direita, sendo igual ou maior que 2 será feito uma rotação a esquerda-direita. É feita está rotação pois o desbalanceamento se encontra na parte esqueda da arvore e na parte interior dela.
                x.setEsquerda(rotEsquerda(x.getEsquerda()));//O nó filho da esquerda de X será o resultado da rotação esquerda deste mesmo nó filho
                x = rotDireita(x);//Após a rotação a esquerda ter sido feita, ocorrerá uma rotação a direita com o próprio nó X
                //Após isso este ramo da arvore estará balanceada
            }
            if ((Altura(x.getEsquerda()) - Altura(x.getDireita())) <= (-2)) {//A diferença entre as alturas dos nós filhos de X, nó filho da esquerda e da direita, sendo igual ou menor que -2 será feito uma rotação a esquerda
                x = rotEsquerda(x);//A rotação é necessário porque ao inserir o novo nó a direita, fez com que a árvore se tornasse desbalanceada
            }
        }
        if(x.getChave().getID()== y.getChave().getID())//Os nós X e Y sendo iguais, o novo nó não é inserido na arvore
            return x;


        atualizarAltura(x);//A altura do nó é atualizada e este nó é retornado
        return x;
    }

    public No remover(No r,int ID){//Método para excluir um nó da árvore, buscando ele pelo ID

        if(r == null){//O nó atual sendo nulo, ocorreu um erro de remoção
            System.out.println("Erro na remocao!");
            return r;
        }

        if(ID < r.getChave().getID()){//O ID sendo menor que o ID do nó atual, significa que a modificação ocorrerá no ramo filho da esquerda.
            r.setEsquerda(remover(r.getEsquerda(),ID));//Sendo assim, o nó filho a esquerda será o resultado da exclusão desde ID
        }

        if(ID > r.getChave().getID()){//Ocorre a mesma coisa neste caso, somente mudando que será no caso do ID ser maior que o ID do nó atual.
            r.setDireita(remover(r.getDireita(),ID));//Assim o nó filho a direita será o resultado da exclusão que ocorrerá
        }

        if(ID == r.getChave().getID()){//O nó atual sendo o artigo que será excluido

            System.out.println("Cadastro Excluido!");

            if(r.getEsquerda() == null || r.getDireita() == null){//Pelo menos um dos nós filhos sendo vazio
                No aux = r;//O nó auxiliar recebe o valor do nó r, o nó atual
                if(r.getEsquerda() != null){//Somente o nó filho da esquerda de r sendo diferente de null
                    r = r.getEsquerda();//r é igual ao seu filho da esquerda
                    aux = null;//o nó auxiliar é alocado como nulo, assim liberando aquele espaço de memória
                    return r;//o nó r modificado é retornado
                }

                if(r.getDireita() != null){//Somente o nó filho da direita de r sendo diferente de null
                    r = r.getDireita();//r é igual ao seu filho da direita
                    aux = null;//o nó auxiliar é alocado como nulo, assim liberando aquele espaço de memória
                    return r;//o nó r modificado é retornado
                }

                if(r.getEsquerda() == null && r.getDireita() == null){//Ambos os filhos sendo nulos
                    aux = null;//o nó auxiliar é alocado como nulo, assim liberando aquele espaço de memória
                    return null;//É retornado nulo, pois não foi necessária nenhuma relocação de nós filhos, porque ambos eram vazios
                }
            }

            else{//Ambos os nós filhos existindo, sendo não nulos
                No aux = menor(r.getDireita());//Nó auxiliar será o menor nó entre os netos da direita do nó atual, ou seja, o menor filho dentro os filhos do nó filho da direita de r
                r.setChave(aux.getChave());//O nó atual, o r, possuira o artigo do nó auxiliar, ou seja, o resultado do método Menor
                r.setDireita(remover(r.getDireita(), aux.getChave().getID()));//O nó filho da direita do nó atual, filho da direita do nó r, será o resultado de uma remoção do nó auxiliar onde o primeiro nó verificado será o proprio nó filho da direita de r
            }
        }

        if(r == null)//O nó atual, r, sendo nulo ele mesmo é retornado
            return r;

        int max = 0;//Valor máximo da altura do nó

        if (r.Altura(r.getEsquerda()) > r.Altura(r.getDireita()))//Altura do nó filho da esquerda sendo maior que o do filho da direita, ele mesmo será o maximo de altura
            max = r.Altura(r.getEsquerda());
        else//Caso não seja, será o filho da direita
            max = r.Altura(r.getDireita());

        r.setAltura((1 + max));//O nó atual, r, terá como altura o valor de max

        int fator = r.Altura(r.getEsquerda())-r.Altura(r.getDireita());//Fator será o calcudo da diferença entre as alturas do nós filhos
        //A partir desse resultado será verificado se será necessário ou não uma rotação dos nós
        //Fator sendo maior que 1 e menor que -1, significa que um lado dos filhos do nó r possui mais nós que o outro, ou seja, a remoção desbalanceou a arvore
        if(fator > 1 && (r.Altura(r.getEsquerda().getEsquerda()) - r.Altura(r.getEsquerda().getDireita())) >= 0){//Neste caso o desbalanceamento ocorreu na parte esquerda da arvore e em seu exterior, assim somente precisando de uma rotação a direita no nó.
            r = r.rotDireita(r);
        }

        if(fator > 1 && (r.Altura(r.getEsquerda().getEsquerda()) - r.Altura(r.getEsquerda().getDireita())) < 0){//Neste caso o desbalanceamente ocorreu na parte esquerda da arvore e em seu interior, sendo necessário uma rotação esquerda-direita
            r.setEsquerda(r.rotEsquerda(r.getEsquerda()));//Assim precisando de uma rotação a esquerda no nó filho da esquerda de r, nó atual
            r = r.rotDireita(r);//E após a rotação a esquerda ser concluida, r será o resultado da rotação a direita de seu nó filho a direita
        }

        if(fator < -1 && (r.Altura(r.getDireita().getEsquerda()) - r.Altura(r.getDireita().getDireita())) >= 0){//Neste caso o desbalanceamento ocorreu na parte direita da arvore e em seu exterior, assim somente precisando de uma rotação a esquerda no nó.
            r = r.rotEsquerda(r);
        }

        if(fator < -1 && (r.Altura(r.getDireita().getEsquerda()) - r.Altura(r.getDireita().getDireita())) < 0){//Neste caso o desbalanceamente ocorreu na parte direita da arvore e em seu interior, sendo necessário uma rotação direita-esquerda
            r.setDireita(r.rotDireita(r.getDireita()));//Assim precisando de uma rotação a direita no nó filho da direita de r, nó atual
            r = r.rotEsquerda(r);//E após a rotação a direita ser concluida, r será o resultado da rotação a esquerda de seu nó filho a esquerda
        }

        return r;//Após as exclusões, definições e rotações terem sido feitas o nó atual modificado, r, é retornado
    }

    public No menor(No r){//Método que mexe nas posições entre o nó atual e seu nó filho a esquerda, se o nó filho existir e não for nulo

        No aux1 = r;//Aux1 recebe o nó atual
        No aux2 = r.getEsquerda();//Aux2 recebe o nó filho da esquerda

        while(aux2 != null){//Nó filho da esquerda não sendo nulo

            aux1=aux2;//Aux1 se torna aux2
            aux2=aux2.getEsquerda();//aux2 se torna o nó filho da esquerda do nó filho a esquerda
        }//Com isso o nó que será retornado será o antigo nó filho da esquerda

        return aux1;
    }


    public void adicionarArquivo(File arquivo, No n, FileWriter fw, BufferedWriter bw){//Método para adicionar os dados ao arquivo

        try{
            //A sequencia de dados será: ID, Titulo, Autor e Ano de publicação do artigo
            String idd=Integer.toString(n.getChave().getID());//Transforma o valor do ID do artigo para String para poder ser adicionado ao Artigos.txt
            bw.write(idd);//É escrita a variavel no arquivo
            bw.newLine();//Pula para proxima linha

            bw.write(n.getChave().getTitulo());//É escrito o titulo do artigo no arquivo
            bw.newLine();//Pula uma linha

            bw.write(n.getChave().getAutor());//É escrito o nome do autor do artigo no arquivo
            bw.newLine();//Pula uma linha

            String ano=Integer.toString(n.getChave().getAno());//O ano de publicação do artigo é transformado em string para ser escrito no arquivo

            bw.write(ano);//É escrito o ano de publicação
            bw.newLine();//Pula uma linha

            if(n.getEsquerda()!= null)//O nó possuindo um filho a esquerda não vazio, é iniciado o método para este nó ser escrito
                adicionarArquivo(arquivo, n.getEsquerda(), fw, bw);

            if(n.getDireita()!= null)//O nó possuindo um filho a direita não vazio, é iniciado o método para este nó ser escrito
                adicionarArquivo(arquivo, n.getDireita(), fw, bw);

        }

        catch(IOException e){//Erro de escrita no arquivo
            System.out.println("Erro: "+e.toString());
        }
    }

    //Metodo para ler o Arquivo "Artigos.txt"
    public No lerArquivo(File arquivo, No A){

        try{
            //Cria o FileReader e o BufferedReader para a leitura do arquivo
            FileReader fr = new FileReader(arquivo);
            BufferedReader br = new BufferedReader(fr);

            //Criando uma ArrayList String para pegar todas as linhas do arquivo
            ArrayList<String> S = new ArrayList<String>();

            //Vai repetir até que não tenha mais linhas no arquivo
            while (br.ready()){
                //Le a linha e adiciona na Lista
                String linha = br.readLine();
                S.add(linha);
            }

            //Vai repetir até que z seja do mesmo tamanho da Lista
            for(int z=0;z<S.size();z++){

                //Cria nova variavel artigo para os dados serem adicionados nela
                Artigo P = new Artigo("","", 0, -1);

                int valor=Integer.parseInt(S.get(z));//Variavel recebe o valor transformado de String para inteiro

                P.setID(valor);//O valor do ID do artigo é definido
                z++;//Z é incrementado em 1

                P.setTitulo(S.get(z));//Titulo do artigo é definido
                z++;//Z é incrementado em 1

                P.setAutor(S.get(z));//Nome do autor do artigo é definido
                z++;//Z é incrementado em 1

                valor=Integer.parseInt(S.get(z));//Variavel recebe o valor transformado de String para inteiro

                P.setAno(valor);//Ano de publicação do artigo é definido

                No novo = new No(P);//O nó recebe o objeto Artigo, agora com todos os valores

                if(A.getChave().getID()==-1) {//Arvore sendo nula é criada com um nó nulo, B, para que seja possivel sua manipulação
                    No B = null;
                    A = A.inserir(B, novo);//Novo nó é adicionado na arvore, sendo o primeiro valor adicionado no caso
                }

                else
                    A = A.inserir(A, novo);//Novo nó é adicionado na arvore que já existe
            }

            //Depois de todas os Artigos terem sido colocadas na arvore o FileReader e o BufferedReader são fechados
            br.close();
            fr.close();

        }


        catch(IOException e){//Erro de leitura do arquivo
            System.out.println("Erro: "+e.toString());
        }

        //O metodo depois de tudo retorna a arvore de artigos A.
        return A;

    }
}
