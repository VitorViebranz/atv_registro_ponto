package dao;

import entity.RegistroPonto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RegistroPontoDAO extends BaseDAO {

    public List<RegistroPonto> obterPorFuncionarioEData(long funcionarioId, LocalDate data) {
        var lista = new ArrayList<RegistroPonto>();
        String sql = "SELECT * FROM registro_ponto WHERE funcionario_id = ? AND DATE(data_hora) = ? ORDER BY data_hora ASC";

        try (Connection con = con(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, funcionarioId);
            ps.setDate(2, java.sql.Date.valueOf(data));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(new RegistroPonto(
                        rs.getLong("id"),
                        rs.getLong("funcionario_id"),
                        rs.getTimestamp("data_hora").toLocalDateTime()
                ));
            }
        } catch (Exception e) {
            System.out.println("Erro ao obter registro ponto: " + e.getMessage());
        }
        return lista;
    }
}
