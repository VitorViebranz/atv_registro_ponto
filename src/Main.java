import dao.FuncionarioDAO;
import dao.RegistroPontoDAO;
import entity.Funcionario;
import entity.RegistroPonto;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        FuncionarioDAO funcDao = new FuncionarioDAO();
        RegistroPontoDAO rpDao = new RegistroPontoDAO();

        long funcId = 1L;
        LocalDate dataRelatorio = LocalDate.of(2026, 3, 10);

        Funcionario f = funcDao.obterPorId(funcId);
        List<RegistroPonto> registros = rpDao.obterPorFuncionarioEData(funcId, dataRelatorio);

        if (f == null) {
            System.out.println("Funcionário não encontrado.");
            return;
        }

        System.out.println("RELATÓRIO DE PONTO");
        System.out.println("Funcionário: " + f.getNome());
        System.out.println("Departamento: " + f.getDepartamento().getNome());
        System.out.println("Data: " + dataRelatorio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        System.out.println("\nEntrada\tSaída\tHoras");

        Duration totalTrabalhado = Duration.ZERO;
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        for (int i = 0; i < registros.size(); i += 2) {
            LocalDateTime entrada = registros.get(i).getDataHora();
            LocalDateTime saida = null;

            if (i + 1 < registros.size()) {
                saida = registros.get(i + 1).getDataHora();
            }

            String strEntrada = entrada.format(timeFormatter);
            String strSaida = (saida != null) ? saida.format(timeFormatter) : "---";
            String strHoras = "---";

            if (saida != null) {
                Duration duracao = Duration.between(entrada, saida);
                totalTrabalhado = totalTrabalhado.plus(duracao);
                strHoras = String.format("%02d:%02d", duracao.toHours(), duracao.toMinutesPart());
            }

            System.out.println(strEntrada + "\t" + strSaida + "\t" + strHoras);
        }

        System.out.println("\nTotal trabalhado:\t" + String.format("%02d:%02d", totalTrabalhado.toHours(), totalTrabalhado.toMinutesPart()));
    }
}