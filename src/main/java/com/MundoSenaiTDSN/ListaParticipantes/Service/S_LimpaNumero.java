package com.MundoSenaiTDSN.ListaParticipantes.Service;

import org.springframework.stereotype.Service;

@Service
public class S_LimpaNumero {

    public static String limpar(String numero){
        return numero.replaceAll("[^0-9]", "");
    }
}
