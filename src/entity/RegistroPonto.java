package entity;

import java.time.LocalDateTime;

public class RegistroPonto {

    private long id;
    private long funcionarioId;
    private LocalDateTime dataHora;


    public RegistroPonto(long id, long funcionarioId, LocalDateTime dataHora) {
        this.id = id;
        this.funcionarioId = funcionarioId;
        this.dataHora = dataHora;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

}