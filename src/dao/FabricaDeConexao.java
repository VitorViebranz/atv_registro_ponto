package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class FabricaDeConexao {
    private static FabricaDeConexao instancia;

    private FabricaDeConexao(){}

    public synchronized static FabricaDeConexao obterInstancia(){
        if(instancia == null){
            instancia =  new FabricaDeConexao();
        }
        return instancia;
    }

    public Connection con() throws SQLException {
        var url = "jdbc:mysql://localhost:3306/poo2";
        var user = "root";
        var password = "univille";
        return DriverManager.getConnection(url,user,password);
    }

}
