package com.MundoSenaiTDSN.ListaParticipantes.Controller;

import com.MundoSenaiTDSN.ListaParticipantes.Model.M_Resposta;
import com.MundoSenaiTDSN.ListaParticipantes.Service.S_Pessoa;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class C_Pessoa {

    @GetMapping("/")
    public String landPage() {
        return "Login/login";
    }

    @PostMapping("/")
    public String loginPessoa(@RequestParam("usuario") String usuario, @RequestParam("senha") String senha) {
        return "Home/home";
    }

    @GetMapping("/cadastro")
    public String getCadastro() {
        return "Cad_Pessoa/cad_pessoa";
    }

    @PostMapping("/cadastro")
    public String postCadastro(@RequestParam("nome") String nome, @RequestParam("cpf") String cpf, @RequestParam("telefone") String telefone, @RequestParam("email") String email, @RequestParam("senha") String senha, @RequestParam("confirSenha") String confirSenha,Model model) {
        M_Resposta m_resposta = S_Pessoa.cadastrarPessoa(nome, cpf, email, telefone, senha, confirSenha);
        if (m_resposta.getStatus()) {
            model.addAttribute("mensagem", m_resposta.getMensagem());
            return "redirect:/";
        } else {
            model.addAttribute("mensagem", m_resposta.getMensagem());
            return "Cad_pessoa/cad_pessoa";
        }
    }
}