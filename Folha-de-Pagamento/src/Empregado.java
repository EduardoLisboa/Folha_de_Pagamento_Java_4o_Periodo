import java.util.Date;

public class Empregado {

    int ID;
    String nome;
    String endereco;
    // 0 - Horário, 1 = Assalariado, 2 = Comissionado
    int tipo;
    double salario;
    // 0 - Cheque correios, 1 = Cheque mãos, 2 = Depósito bancário
    int pagamento;
    boolean ativo;

    boolean sindicato;
    double taxa;
    String ID_sindicato;

    double comissao;

    Date proximo_pagamento;
    // 0 - Semanal, 1 - Mensal, 2 - Bi-semanal
    int tipo_pagamento;

    Ponto[] ponto = new Ponto[365];
    int index_ponto = 0;
    int ponto_ultimo_pagamento = 0;

    Venda[] vendas = new Venda[365];
    int index_venda = 0;
    int venda_ultimo_pagamento = 0;

    TaxaServiço[] taxas = new TaxaServiço[24];
    int index_taxas = 0;

}
