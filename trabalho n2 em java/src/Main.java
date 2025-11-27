import java.util.Scanner;

public class Main {

    // Novos estados: DETALHES_BURGER e ADICIONAR_MAIS
    enum State { SAUDACAO, PEDIR_NOME, ESCOLHER_BURGER, DETALHES_BURGER,
        ESCOLHER_QUANTIDADE, ESCOLHER_PONTO, ADICIONAR_MAIS,
        ESCOLHER_BEBIDA, TIPO_ENTREGA, INFORMAR_ENDERECO,
        CONFIRMAR, PAGAMENTO, FINALIZADO }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        State state = State.SAUDACAO;

        // Variáveis de fluxo
        String nomeCliente = "";

        // Variáveis temporárias (para o item sendo escolhido agora)
        String tempBurger = "";
        String tempDescricao = "";
        String tempQtd = "";
        String tempPonto = "";

        // Variável acumuladora (Lista de tudo que foi pedido)
        String listaItens = "";

        String bebida = "";
        String tipoEntrega = "";
        String endereco = "";
        String pagamento = "";

        System.out.println("--- Bot BoaHamburgueria ---");
        System.out.println("Bot: Olá! Digite 'começar' para fazer seu pedido.");

        while (true) {
            String input = sc.nextLine().trim();

            switch (state) {
                case SAUDACAO:
                    if (input.equalsIgnoreCase("começar") || input.contains("quero")) {
                        state = State.PEDIR_NOME;
                        System.out.println("Bot: Ótimo! Primeiro, qual é o seu nome?");
                    } else {
                        System.out.println("Bot: Digite 'começar' para iniciarmos.");
                    }
                    break;

                case PEDIR_NOME:
                    nomeCliente = input;
                    state = State.ESCOLHER_BURGER;
                    System.out.println("Bot: Olá, " + nomeCliente + "!");
                    mostrarMenuBurgers();
                    break;

                case ESCOLHER_BURGER:
                    boolean opcaoValida = true;
                    switch (input) {
                        case "1":
                            tempBurger = "X-Bacon";
                            tempDescricao = "Pão brioche, carne 180g, queijo cheddar e muito bacon.";
                            break;
                        case "2":
                            tempBurger = "X-Salada";
                            tempDescricao = "Pão, carne 160g, queijo prato, alface, tomate e maionese verde.";
                            break;
                        case "3":
                            tempBurger = "Smash Burger";
                            tempDescricao = "Pão, 2 carnes prensadas de 80g, cheddar duplo e cebola caramelizada.";
                            break;
                        case "4":
                            tempBurger = "Artesanal";
                            tempDescricao = "Pão australiano, carne 200g, queijo brie e geleia de pimenta.";
                            break;
                        default:
                            System.out.println("Bot: Opção inválida. Digite 1, 2, 3 ou 4.");
                            opcaoValida = false;
                    }

                    if (opcaoValida) {
                        state = State.DETALHES_BURGER;
                        System.out.println("\n--- DETALHES DO " + tempBurger.toUpperCase() + " ---");
                        System.out.println(tempDescricao);
                        System.out.println("----------------------------------");
                        System.out.println("Deseja confirmar este lanche?");
                        System.out.println("1 - SIM (Continuar) | 2 - NÃO (Escolher outro)");
                    }
                    break;

                case DETALHES_BURGER:
                    if (input.equals("1")) {
                        state = State.ESCOLHER_QUANTIDADE;
                        System.out.println("Bot: Quantas unidades de " + tempBurger + " você quer?");
                    } else if (input.equals("2")) {
                        state = State.ESCOLHER_BURGER;
                        System.out.println("Bot: Sem problemas. Escolha outra opção:");
                        mostrarMenuBurgers();
                    } else {
                        System.out.println("Bot: Digite 1 para Confirmar ou 2 para Voltar.");
                    }
                    break;

                case ESCOLHER_QUANTIDADE:
                    if (input.matches("[0-9]+")) {
                        tempQtd = input;
                        state = State.ESCOLHER_PONTO;
                        System.out.println("Bot: Qual o ponto da carne para esses hambúrgueres?");
                        System.out.println("1 - Mal passada | 2 - Ao ponto | 3 - Bem passada");
                    } else {
                        System.out.println("Bot: Por favor, digite apenas números (ex: 1, 2).");
                    }
                    break;

                case ESCOLHER_PONTO:
                    boolean pontoValido = true;
                    switch (input) {
                        case "1": tempPonto = "Mal passada"; break;
                        case "2": tempPonto = "Ao ponto"; break;
                        case "3": tempPonto = "Bem passada"; break;
                        default:
                            System.out.println("Bot: Digite 1, 2 ou 3 para o ponto.");
                            pontoValido = false;
                    }

                    if (pontoValido) {
                        // AQUI ADICIONAMOS O ITEM NA LISTA GERAL
                        listaItens += "- " + tempQtd + "x " + tempBurger + " (" + tempPonto + ")\n";

                        state = State.ADICIONAR_MAIS;
                        System.out.println("Bot: Item adicionado!");
                        System.out.println("Deseja pedir MAIS ALGUM hambúrguer?");
                        System.out.println("1 - SIM (Adicionar outro) | 2 - NÃO (Ir para bebidas)");
                    }
                    break;

                case ADICIONAR_MAIS:
                    if (input.equals("1")) {
                        state = State.ESCOLHER_BURGER; // LOOP: Volta para o início
                        System.out.println("Bot: Certo! Qual é o próximo lanche?");
                        mostrarMenuBurgers();
                    } else if (input.equals("2")) {
                        state = State.ESCOLHER_BEBIDA; // PROSSEGUE
                        System.out.println("Bot: Ok. Vamos para as bebidas.");
                        System.out.println("Deseja algo para beber? Digite o NÚMERO:");
                        System.out.println("1 - Coca-Cola | 2 - Suco | 3 - Água | 4 - Sem bebida");
                    } else {
                        System.out.println("Bot: Digite 1 para Sim ou 2 para Não.");
                    }
                    break;

                case ESCOLHER_BEBIDA:
                    switch (input) {
                        case "1": bebida = "Coca-Cola"; break;
                        case "2": bebida = "Suco"; break;
                        case "3": bebida = "Água"; break;
                        case "4": bebida = "Sem bebida"; break;
                        default: System.out.println("Bot: Escolha entre 1 e 4."); continue;
                    }
                    state = State.TIPO_ENTREGA;
                    System.out.println("Bot: Como deseja receber o pedido?");
                    System.out.println("1 - Entrega (Delivery) | 2 - Retirar no Local");
                    break;

                case TIPO_ENTREGA:
                    if (input.equals("1")) {
                        tipoEntrega = "Entrega";
                        state = State.INFORMAR_ENDERECO;
                        System.out.println("Bot: Certo. Digite seu endereço completo:");
                    } else if (input.equals("2")) {
                        tipoEntrega = "Retirada";
                        endereco = "Balcão da Loja";
                        state = State.CONFIRMAR;
                        mostrarResumo(nomeCliente, listaItens, bebida, tipoEntrega, endereco);
                    } else {
                        System.out.println("Bot: Digite 1 para Entrega ou 2 para Retirar.");
                    }
                    break;

                case INFORMAR_ENDERECO:
                    if (input.length() > 5) {
                        endereco = input;
                        state = State.CONFIRMAR;
                        mostrarResumo(nomeCliente, listaItens, bebida, tipoEntrega, endereco);
                    } else {
                        System.out.println("Bot: Endereço muito curto.");
                    }
                    break;

                case CONFIRMAR:
                    if (input.equalsIgnoreCase("1")) {
                        state = State.PAGAMENTO;
                        System.out.println("Bot: Pagamento em Dinheiro (1) ou Cartão (2)?");
                    } else if (input.equalsIgnoreCase("2")) {
                        // Se quiser alterar, zeramos a lista e começamos de novo
                        listaItens = "";
                        state = State.ESCOLHER_BURGER;
                        System.out.println("Bot: Lista limpa. Vamos recomeçar o pedido.");
                        mostrarMenuBurgers();
                    } else if (input.equalsIgnoreCase("3")) {
                        System.out.println("Bot: Pedido cancelado. Até logo!");
                        return;
                    } else {
                        System.out.println("Bot: Digite 1, 2 ou 3.");
                    }
                    break;

                case PAGAMENTO:
                    if (input.contains("1") || input.contains("dinheiro")) {
                        pagamento = "Dinheiro"; state = State.FINALIZADO;
                    } else if (input.contains("2") || input.contains("cartao")) {
                        pagamento = "Cartão"; state = State.FINALIZADO;
                    } else {
                        System.out.println("Bot: Opção inválida.");
                    }

                    if (state == State.FINALIZADO) {
                        System.out.println("\n***********************************");
                        System.out.println("      PEDIDO ENVIADO COM SUCESSO!    ");
                        System.out.println("***********************************");
                        System.out.println("Cliente: " + nomeCliente);
                        System.out.println("--- ITENS ---");
                        System.out.print(listaItens); // Imprime a lista acumulada
                        System.out.println("-------------");
                        System.out.println("Bebida: " + bebida);
                        System.out.println("Modo: " + tipoEntrega);
                        if(tipoEntrega.equals("Entrega")) {
                            System.out.println("Endereço: " + endereco);
                        }
                        System.out.println("Pagamento: " + pagamento);
                        System.out.println("***********************************");
                        return;
                    }
                    break;
            }
        }
    }

    // Função auxiliar para mostrar o menu repetidamente
    public static void mostrarMenuBurgers() {
        System.out.println("Escolha seu lanche pelo NÚMERO:");
        System.out.println("1 - X-Bacon");
        System.out.println("2 - X-Salada");
        System.out.println("3 - Smash Burger");
        System.out.println("4 - Artesanal");
    }

    public static void mostrarResumo(String nome, String itens, String beb, String tipo, String end) {
        System.out.println("\n--- CONFIRMAÇÃO DO PEDIDO ---");
        System.out.println("Cliente: " + nome);
        System.out.println("Itens:");
        System.out.println(itens); // Mostra todos os itens acumulados
        System.out.println("Bebida: " + beb);
        System.out.println("Tipo: " + tipo);
        if (tipo.equals("Entrega")) {
            System.out.println("Endereço: " + end);
        } else {
            System.out.println("Local: Retirada no balcão");
        }
        System.out.println("-----------------------------");
        System.out.println("1 - CONFIRMAR | 2 - REFAZER DO ZERO | 3 - CANCELAR");
    }
}