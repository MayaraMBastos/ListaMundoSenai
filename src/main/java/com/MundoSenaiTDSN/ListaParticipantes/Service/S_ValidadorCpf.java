package com.MundoSenaiTDSN.ListaParticipantes.Service;

import org.springframework.stereotype.Service;

@Service
public class S_ValidadorCpf {

    public static boolean validateCPF(String cpf) {
        // Remover caracteres não numéricos do CPF
        cpf = S_LimpaNumero.limpar(cpf);

        // Verificar se o CPF tem 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }

        // Verificar se todos os dígitos são iguais (CPF inválido)
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        // Calcular os dígitos verificadores
        int[] digits = new int[11];
        for (int i = 0; i < 11; i++) {
            digits[i] = Character.getNumericValue(cpf.charAt(i));
        }

        int sum1 = 0;
        for (int i = 0; i < 9; i++) {
            sum1 += digits[i] * (10 - i);
        }

        int remainder1 = sum1 % 11;
        int expectedDigit1 = (remainder1 < 2) ? 0 : 11 - remainder1;

        if (digits[9] != expectedDigit1) {
            return false;
        }

        int sum2 = 0;
        for (int i = 0; i < 10; i++) {
            sum2 += digits[i] * (11 - i);
        }

        int remainder2 = sum2 % 11;
        int expectedDigit2 = (remainder2 < 2) ? 0 : 11 - remainder2;

        return digits[10] == expectedDigit2;
    }

}
