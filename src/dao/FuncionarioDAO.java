package dao;

import entity.Departamento;
import entity.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FuncionarioDAO extends BaseDAO {

    public Funcionario obterPorId(long id) {
        String sql = """
            SELECT f.*, d.nome AS dep_nome FROM funcionario f 
            INNER JOIN departamento d ON f.departamento_id = d.id 
            WHERE f.id = ?
        """;

        try (Connection con = con(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Departamento d = new Departamento(rs.getLong("departamento_id"), rs.getString("dep_nome"));
                return new Funcionario(rs.getLong("id"), rs.getString("matricula"), rs.getString("nome"), d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}