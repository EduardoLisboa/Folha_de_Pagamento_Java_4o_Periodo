import java.util.Calendar;

public class AgendaPagamento {

    int ID_pagamento;
    String titulo;
    boolean ativo;

    boolean mensal = false;
    int dia_mes = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);

    boolean semanal = false;
    int intervalo_semanas = 1;
    int dia_semana = Calendar.FRIDAY;

}
