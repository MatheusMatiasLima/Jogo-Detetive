/**
 *  Essa eh a classe principal da aplicacao "World of Zull".
 *  "World of Zuul" eh um jogo de aventura muito simples, baseado em texto.
 *  Usuarios podem caminhar em um cenario. E eh tudo! Ele realmente
 *  precisa ser estendido para fazer algo interessante!
 * 
 *  Para jogar esse jogo, crie uma instancia dessa classe e chame o metodo
 *  "jogar".
 * 
 *  Essa classe principal cria e inicializa todas as outras: ela cria os
 *  ambientes, cria o analisador e comeca o jogo. Ela tambeme avalia e 
 *  executa os comandos que o analisador retorna.
 * 
 * @author  Michael Kölling and David J. Barnes (traduzido por Julio Cesar Alves)
 * @version 2011.07.31 (2016.02.01)
 */

import java.util.ArrayList;

public class Jogo 
{
    private Jogador jogador;
    private Analisador analisador;
    private NPC Matheus, Joicy, Luiz, Mariana; //MEHOREM ISSO

        
    /**
     * Cria o jogo e incializa seu mapa interno.
     */
    public Jogo() 
    {
        jogador = new Jogador();
        criarAmbientes();
        criarNPCs();
        analisador = new Analisador();
        
    }

    private void criarNPCs() { //MELHOREM ISSO
        
        Matheus = new NPC("Matheus", "Eu sou o assassino");
        Joicy = new NPC("Joicy", "Nao sou a assassina");
        Luiz = new NPC("Luiz", "Nao sou o assassino");
        Mariana = new NPC("Mariana", "Nao sou a assassina");
    }

    /**
     * Cria todos os ambientes e liga as saidas deles
     */
    private void criarAmbientes()
    {
        Ambiente fora, anfiteatro, cantina, laboratorio, escritorio;
      
        // cria os ambientes
        fora = new Ambiente("do lado de fora da entrada principal de uma universidade", "Faca");
        anfiteatro = new Ambiente("no anfiteatro", null);
        cantina = new Ambiente("na cantina do campus", null);
        laboratorio = new Ambiente("no laboratorio de computacao", null);
        escritorio = new Ambiente("na sala de administracao dos computadores", null);
        
        // inicializa as saidas dos ambientes
        //fora.ajustarSaida(null, anfiteatro, laboratorio, cantina);
        fora.ajustarSaida("leste", anfiteatro);
        fora.ajustarSaida("sul", laboratorio);
        fora.ajustarSaida("oeste", cantina);

        //anfiteatro.ajustarSaidas(null, null, null, fora);
        anfiteatro.ajustarSaida("oeste", fora);

        //cantina.ajustarSaidas(null, fora, null, null);
        cantina.ajustarSaida("leste", fora);
        cantina.ajustarSaida("descer", laboratorio);

        //laboratorio.ajustarSaidas(fora, escritorio, null, null);
        laboratorio.ajustarSaida("norte", fora);
        laboratorio.ajustarSaida("leste", escritorio);
        laboratorio.ajustarSaida("subir", cantina);

        //escritorio.ajustarSaidas(null, null, null, laboratorio);
        escritorio.ajustarSaida("oeste", laboratorio);

        jogador.definirAmbienteAtual(fora);  // o jogo comeca do lado de fora
    }

    private void imprimirInfoLocalAtual(){
        System.out.println(jogador.getAmbienteAtual().getDescricaoLonga());
    }

    /**
     *  Rotina principal do jogo. Fica em loop ate terminar o jogo.
     */
    public void jogar() 
    {            
        imprimirBoasVindas();

        // Entra no loop de comando principal. Aqui nos repetidamente lemos
        // comandos e os executamos ate o jogo terminar.
                
        boolean terminado = false;
        while (! terminado) {
            Comando comando = analisador.pegarComando();
            terminado = processarComando(comando);
        }
        System.out.println("Obrigado por jogar. Ate mais!");
    }

    /**
     * Imprime a mensagem de abertura para o jogador.
     */
    private void imprimirBoasVindas()
    {
        System.out.println();
        System.out.println("Bem-vindo ao World of Zuul!");
        System.out.println("World of Zuul eh um novo jogo de aventura, incrivelmente chato.");
        System.out.println("Digite 'ajuda' se voce precisar de ajuda.");
        System.out.println();
        
        imprimirInfoLocalAtual();
    }

    /**
     * Dado um comando, processa-o (ou seja, executa-o)
     * @param comando O Comando a ser processado.
     * @return true se o comando finaliza o jogo.
     */
    private boolean processarComando(Comando comando) 
    {
        boolean querSair = false;

        if(comando.ehDesconhecido()) {
            System.out.println("Eu nao entendi o que voce disse...");
            return false;
        }

        String palavraDeComando = comando.getPalavraDeComando();

        if (palavraDeComando.equals("ajuda")) {
            imprimirAjuda();
        }
        else if (palavraDeComando.equals("ir")) {
            irParaAmbiente(comando);
        }
        else if (palavraDeComando.equals("examinar")) {
            examinar();
        }
        else if (palavraDeComando.equals("voltar")) {
            voltar();

        }
        else if (palavraDeComando.equals("pegarItem")) {
            pegarItem();
        }
        else if (palavraDeComando.equals("NPC")) {
            Comando comandoNPC = analisador.acaoNPC();
            processarNPC(comandoNPC);

        }
        else if (palavraDeComando.equals("verStatus")) {
            verStatus();

        }

        else if (palavraDeComando.equals("denunciar")) {
            denunciarAssassinato ();
        }

        else if (palavraDeComando.equals("sair")) {
            querSair = sair(comando);
        }

        return querSair;
    }




    private void processarNPC (Comando comando) {
        if (comando == null) {
            System.out.println("nao entendi o que voce disse.");
            return;
        }
        else {
            if (comando.getPalavraDeComando().equals("acusar")) {
                jogador.definirAssassino(comando.getSegundaPalavra());

            }
            else if (comando.getPalavraDeComando().equals("perguntar")) {

                /*
                
                
                    ATENCAOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO

                    TEM QUE MELHORAR ISSO
                    


                */
                
                if (comando.getSegundaPalavra().equals(Matheus.getNome())) {
                    System.out.println(Matheus.getHitoria());
                }
                else if(comando.getSegundaPalavra().equals(Joicy.getNome())) {
                    System.out.println(Joicy.getHitoria()) ;
                }
                //... tem que terminar 

            }
            else {
                System.out.println("Nao entendi");
            }
        }

    }

    // Implementacoes dos comandos do usuario

    /**
     * Printe informacoes de ajuda.
     * Aqui nos imprimimos algo bobo e enigmatico e a lista de 
     * palavras de comando
     */
    private void imprimirAjuda() 
    {
        System.out.println("Voce esta perdido. Voce esta sozinho. Voce caminha");
        System.out.println("pela universidade.");
        System.out.println();
        System.out.println("Suas palavras de comando sao:");
        analisador.mostrarComandosValidos();
    }

    /** 
     * Tenta ir em uma direcao. Se existe uma saida entra no 
     * novo ambiente, caso contrario imprime mensagem de erro.
     */
    private void irParaAmbiente(Comando comando) 
    {
        if(!comando.temSegundaPalavra()) {
            // se nao ha segunda palavra, nao sabemos pra onde ir...
            System.out.println("Ir pra onde?");
            return;
        }

        //salva o ambiente caso o usuario quiser usar o voltar
        jogador.salvarAmbienteAnterior();

        String direcao = comando.getSegundaPalavra();

        // Tenta sair do ambiente atual
        Ambiente proximoAmbiente = null;
        proximoAmbiente = jogador.getAmbienteAtual().getSaida(direcao);

        if (proximoAmbiente == null) {
            System.out.println("Nao ha passagem!");
        }
        else {
            jogador.definirAmbienteAtual(proximoAmbiente);
            
            imprimirInfoLocalAtual();
        }
    }

    //caso o usuario quiser voltar ao ambiente anterior
    private void voltar() {
        if (jogador.ehAmbienteInicial()) {
            System.out.println("Voce comecou aqui!");
        }
        else {
            jogador.definirAmbienteAtual(jogador.retornarAmbienteAnterior());
            //ambienteAtual = ambienteAnterior.pop();
            System.out.println(jogador.getAmbienteAtual().getDescricaoLonga());
        }
    }
    

    /** 
     * "Sair" foi digitado. Verifica o resto do comando pra ver
     * se nos queremos realmente sair do jogo.
     * @return true, se este comando sai do jogo, false, caso contrario
     */

    private boolean sair(Comando comando) 
    {
        if(comando.temSegundaPalavra()) {
            System.out.println("Sair o que?");
            return false;
        }
        else {
            return true;  // sinaliza que nos queremos sair
        }
    }

    private void examinar(){
        System.out.println(jogador.getAmbienteAtual().getDescricaoLonga());
    }

    private void pegarItem() {
        if (jogador.getAmbienteAtual().getItem().getNome() == null) {
            System.out.println("Esse lugar nao tem nenhum item.");
        }
        else {
            jogador.pegarItem(jogador.getAmbienteAtual().getItem());
            jogador.mostrarItemQueEstaCarregando();
        }
    }

    private void verStatus() {
        jogador.mostrarItemQueEstaCarregando();
        jogador.mostrarAcusacao();
    }

    private void denunciarAssassinato () {
        if (jogador.getNomeAssassino().equals("Matheus") && jogador.getItemCarregado().equals("Faca")) {
            System.out.println("Parabens, voce achou o assassino!");
        }
        else{
            System.out.println("Voce perdeu :(");
        }
        processarComando(new Comando("sair",null));
    }

}