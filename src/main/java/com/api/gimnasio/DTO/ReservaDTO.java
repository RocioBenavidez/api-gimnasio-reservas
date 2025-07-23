package com.api.gimnasio.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class ReservaDTO {
    private Long id;
    private Date fecha;
    private Long id_usuario;
    private Long id_clase;

}
