package com.MundoSenaiTDSN.ListaParticipantes.Service;

import com.MundoSenaiTDSN.ListaParticipantes.Model.M_Pessoa;
import com.MundoSenaiTDSN.ListaParticipantes.Repository.R_Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class S_Pessoa {
    private static R_Pessoa r_pessoa;

    public S_Pessoa(R_Pessoa r_pessoa) {
        this.r_pessoa = r_pessoa;
    }

    public static String cadastrarPessoa(String nome, String cpf, String email, String telefone, String senha, String confSenha) {

        String mensagem = "";
        boolean podeSalvar = true;

        if (!senha.equals(confSenha) || senha == null || senha.trim().equals("")) {
            mensagem += "Senha e confirmação de senha devem ser iguais, e a senha deve ser informada";
            podeSalvar = false;
        }

        if (!S_ValidadorCpf.validateCPF(cpf)) {
            mensagem += "CPF inválido";
            podeSalvar = false;
        }

        if (nome == null || nome.trim().equals("")) {
            mensagem += "O nome precisa ser informado";
            podeSalvar = false;
        }

        if ((email == null || email.trim().equals("")) && (telefone == null || telefone.trim().equals(""))) {
            mensagem += "E-mail ou Telefone precisa ser informado";
            podeSalvar = false;
        }

        if (podeSalvar) {
            M_Pessoa m_pessoa = new M_Pessoa();
            m_pessoa.setNome(nome);
            m_pessoa.setCpf(Long.valueOf(S_LimpaNumero.limpar(cpf)));
            m_pessoa.setEmail(email);
            m_pessoa.setTelefone(Long.valueOf(S_LimpaNumero.limpar(telefone)));
            m_pessoa.setSenha(senha);
            r_pessoa.save(m_pessoa);
            mensagem += "Pessoa cadastrada com sucesso";
        }
            return mensagem;
        }
}
