import java.util.Scanner;

public class Main {

    static Scanner read = new Scanner(System.in);

    static String alerta = null;
    static int base = 200;
    static String[] tipos_empregados = {"Horário", "Assalariado", "Comissionado"};
    static String[] metodo_pagamento = {"Cheque via correios", "Cheque em mãos", "Depósito bancário"};

    //Para manter organizados os empregados
    static int total_empregados = 0;
    static int index_empregados = 0;
    static Empregado[] empregados = new Empregado[base];
    static int index_agenda_pagamentos = 0;
    static AgendaPagamento[] agendas_pagamentos = new AgendaPagamento[25];

    public static void main(String[] args) {
        char opcao = '0';

        while(opcao != '8') {
            menu();
            opcao = read.next().charAt(0);

            switch (opcao) {
                case '1':
                    System.out.print("\nEMPREGADOS");
                    empregados();
                    break;
                case '2':
                    //rodar_folha_pagamento();
                    System.out.println("Rodando folha de pagamentos\n");
                    break;
                case '3':
                    //lancar_cartao_ponto();
                    System.out.println("Lançando cartão de ponto\n");
                    break;
                case '4':
                    //lancar_venda();
                    System.out.println("Lançando venda\n");
                    break;
                case '5':
                    //lancar_taxa_servico();
                    System.out.println("Lançando taxa de serviço\n");
                    break;
                case '6':
                    //listar_agendas_pagamento();
                    System.out.println("Listando agendas de pagamentos\n");
                    break;
                case '7':
                    //refazer_transacoes();
                    System.out.println("Undo/Redo\n");
                    break;
                case '8':
                    System.exit(0);
                    break;
                default:
                    alerta = "Opção Inválida!";
            }
        }
    }

    public static void mostrar_alerta() {
        if (alerta != null) {
            System.out.println("\n" +alerta);

            alerta = null;
        }
    }

    public static void menu() {
        System.out.println("\n1 - Empregados");
        System.out.println("2 - Rodar Folha de Pagamentos");
        System.out.println("3 - Lançar Cartão de Ponto");
        System.out.println("4 - Lançar Venda");
        System.out.println("5 - Lançar Taxa de Serviço");
        System.out.println("6 - Listar Agendas de Pagamentos");
        System.out.println("7 - Undo/Redo");
        System.out.println("8 - Sair");
        System.out.print(">> ");
    }

    public static void menu_empregados() {
        System.out.println("\n1 - Adicionar");
        System.out.println("2 - Remover");
        System.out.println("3 - Alterar");
        System.out.println("4 - Listar todos");
        System.out.println("5 - Voltar");
        System.out.print(">> ");
    }

    public static void empregados() {
        char opcao_empregados = '0';

        while(opcao_empregados != '8') {
            menu_empregados();
            opcao_empregados = read.next().charAt(0);

            switch (opcao_empregados) {
                case '1':
                    adicionar_empregados();
                    break;
                case '2':
                    remover_empregados();
                    break;
                case '3':
                    alterar_empregados();
                    break;
                case '4':
                    listar_empregados();
                    break;
                case '5':
                    return;
                default:
                    alerta = "Opção Inválida!";
                    break;
            }
        }
    }

    public static void listar_empregados() {
        if (total_empregados == 0) {
            System.out.println("\nSem registros");
        } else {
            for (Empregado empregado : empregados) {
                if (empregado == null) break;
                if (empregado.ativo == true) print_empregado(empregado);
            }
        }
    }

    public static void print_empregado(Empregado empregado) {
        System.out.println("\nID: " + empregado.ID);
        System.out.println("Nome: " + empregado.nome);
        System.out.println("Endereço: " + empregado.endereco);
        System.out.println("Tipo: " + tipos_empregados[empregado.tipo]);
        System.out.println("Salário: R$ " + empregado.salario);
        if (empregado.tipo == 2) {
            System.out.println("Comissão: " + empregado.comissao +" %");
        }
        System.out.println("Método de pagamento: " + metodo_pagamento[empregado.pagamento]);
        System.out.print("Pertence ao sindicato? ");
        if (empregado.sindicato) {
            System.out.println("Sim");
            System.out.println("Identificação no sindicato: " +empregado.ID_sindicato);
            System.out.println("Taxa Sindical: R$ " +empregado.taxa);
        }
        else System.out.println("Não");
    }

    public static void adicionar_empregados() {
        Empregado empregado = new Empregado();

        System.out.print("\nNome: ");
        read.nextLine();
        empregado.nome =  read.nextLine();

        System.out.print("Endereço: ");
        empregado.endereco = read.nextLine();

        char tipo = '4';
        while (tipo != '0' && tipo != '1' && tipo != '2') {
            mostrar_alerta();
            alerta = "Tipo Inválido!";

            System.out.println("0 - " + tipos_empregados[0] +" \t 1 - " + tipos_empregados[1] +" \t 2 - " + tipos_empregados[2]);
            System.out.print("Tipo de empregado: ");
            tipo = read.next().charAt(0);
        }
        alerta = null;

        System.out.print("Salário: R$ ");
        empregado.salario= read.nextDouble();

        if (tipo == '0') {
            empregado.tipo = 0;
            empregado.tipo_pagamento = 1;
        } else if (tipo == '1') {
            empregado.tipo = 1;
            empregado.tipo_pagamento = 2;
        } else if (tipo == '2') {
            empregado.tipo = 2;
            empregado.tipo_pagamento = 3;

            System.out.print("Comissão: ");
            empregado.comissao = read.nextDouble();
        }

        char metodo = '4';
        while (metodo != '0' && metodo != '1' && metodo != '2') {
            mostrar_alerta();
            alerta = "Método de pagamento Inválido!";

            System.out.println("0 - " + metodo_pagamento[0] +" \t 1 - " + metodo_pagamento[1] +" \t 2 - " + metodo_pagamento[2]);
            System.out.print("Selecione o método de pagamento: ");
            metodo = read.next().charAt(0);
        }

        if (tipo == '0') empregado.pagamento = 0;
        else if (tipo == '1') empregado.pagamento = 1;
        else if (tipo == '2') empregado.pagamento = 2;

        System.out.print("Pertence à Sindicato? (s/n) ");
        char sindicato = read.next().charAt(0);

        if (sindicato == 's' || sindicato == 'S') {
            empregado.sindicato = true;

            System.out.print("Identificação no Sindicato: ");
            read.nextLine();
            empregado.ID_sindicato = read.nextLine();

            System.out.print("Taxa sindical: R$ ");
            empregado.taxa = read.nextDouble();
        } else {
            empregado.sindicato = false;
        }

        empregado.ID = index_empregados + 1;
        empregado.ativo = true;
        salvar_empregado(empregado);
        print_empregado(empregado);

        alerta = "Empregado salvo com sucesso!";
        mostrar_alerta();
        alerta = null;
    }

    public static void salvar_empregado(Empregado empregado) {
        if (index_empregados == empregados.length) {
            Empregado[] empregadosAux = new Empregado[index_empregados + base];

            for (int i = 0; i < index_empregados; i++) {
                empregadosAux[i] = empregados[i];
            }
            empregados = empregadosAux;
        }

        empregados[index_empregados++] = empregado;
        ++total_empregados;
    }

    public static void remover_empregados() {
        Empregado empregado = buscar_empregado();

        if(empregado == null || empregado.ativo == false) {
            alerta = "Emregado não encontrado!";
        } else {
            System.out.print("\nTem certeza que dseja removê-lo? (s/n) ");
            char resposta = read.next().charAt(0);

            if (resposta == 's' || resposta == 'S') {
                empregado.ativo = false;
                --total_empregados;

                alerta = "Empregado removido com sucesso!\n";
                mostrar_alerta();
                alerta = null;
            }
        }
    }

    public static Empregado buscar_empregado() {
        char opcao = '0';

        while (opcao != '3') {
            mostrar_alerta();

            System.out.println("\n1 - Buscar pelo nome");
            System.out.println("2 - Buscar pelo ID");
            System.out.println("3 - Voltar");
            System.out.print(">> ");
            opcao = read.next().charAt(0);

            switch (opcao) {
                case '1':
                    System.out.print("\nNome do empregado: ");
                    read.nextLine();
                    String nome = read.nextLine();

                    for (Empregado empregado : empregados) {
                        if (empregado == null) return null;
                        if (empregado.ativo == false) continue;
                        if (empregado.nome.toUpperCase().equals(nome.toUpperCase()))
                            return empregado;
                    }
                    break;
                case '2':
                    System.out.print("\nID do empregado: ");
                    int id = read.nextInt();
                    if (id <= 0) {
                        alerta = "ID inválido!";
                        continue;
                    }
                    return empregados[id - 1];
                case '3':
                    return null;
                default:
                    alerta = "Opção Inválida!";
            }
        }
        return null;
    }

    public static void alterar_empregados() {
        Empregado empregado = buscar_empregado();

        if (empregado == null) {
            alerta = "Empregado não encontrado!";
            mostrar_alerta();
            alerta = null;
            return;
        }

        print_empregado(empregado);

        int aux_index = empregado.ID - 1;

        System.out.println("\n1 - Nome");
        System.out.println("2 - Endereço");
        System.out.println("3 - Tipo");
        System.out.println("4 - Salário");
        System.out.println("5 - Método de Pagamento");
        System.out.println("6 - Informações do Sindicato");
        System.out.println("7 - Voltar");
        System.out.print(">> ");
        char opcao = read.next().charAt(0);

        switch (opcao) {
            case '1':
                System.out.print("Novo nome: ");
                read.nextLine();
                empregados[aux_index].nome = read.nextLine();
                break;
            case '2':
                System.out.print("Novo endereço: ");
                read.nextLine();
                empregados[aux_index].endereco = read.nextLine();
                break;
            case '3':
                System.out.println("0 - " + tipos_empregados[0] +" \t 1 - " + tipos_empregados[1] +" \t 2 - " + tipos_empregados[2]);
                System.out.print("Tipo de empregado: ");
                empregados[aux_index].tipo = read.nextInt();

                if (empregados[aux_index].tipo == 2) {
                    System.out.print("Comissão: ");
                    empregados[aux_index].comissao = read.nextDouble();
                }
                break;
            case '4':
                System.out.print("Novo salário: ");
                empregados[aux_index].salario = read.nextDouble();
                if (empregados[aux_index].tipo == 2) {
                    System.out.print("Nova comissão: ");
                    empregados[aux_index].comissao = read.nextDouble();
                }
                break;
            case '5':
                System.out.println("0 - " + metodo_pagamento[0] +" \t 1 - " + metodo_pagamento[1] +" \t 2 - " + metodo_pagamento[2]);
                System.out.print("Selecione o método de pagamento: ");
                empregados[aux_index].pagamento = read.nextInt();
                break;

            case '6':
                if (empregados[aux_index].sindicato) {
                    System.out.println("\n1 - Sair do Sindicato");
                    System.out.println("2 - Alterar taxa sindical");
                    System.out.println("3 - Voltar");
                }
                else {
                    System.out.println("\n1 - Entrar no Sindicato \t 2 - Voltar");
                }
                System.out.print(">> ");
                char opcao_sindicato = read.next().charAt(0);

                if (opcao_sindicato == '1') {
                    if (empregados[aux_index].sindicato) {
                        empregados[aux_index].sindicato = false;
                        empregados[aux_index].taxa = 0;
                    } else {
                        empregados[aux_index].sindicato = true;

                        System.out.print("Taxa sindical: ");
                        empregados[aux_index].taxa = read.nextDouble();
                    }
                } else if (opcao_sindicato == '2') {
                    if (!empregados[aux_index].sindicato) break;
                    System.out.print("Nova taxa sindical: ");
                    empregados[aux_index].taxa = read.nextDouble();
                }
                break;
            case '7':
                return;
            default:
                alerta = "Opção Inválida!";
                break;
        }
    }


}
