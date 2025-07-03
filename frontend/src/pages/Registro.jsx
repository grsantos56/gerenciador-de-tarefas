import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import './Registro.css'; 

/*  Componente de Registro
    Permite que novos usuários criem uma conta com nome completo, email e senha.
    Após o registro, redireciona para a página de login.
    Exibe mensagens de erro em caso de falha no registro.
*/
export default function Registro() {
  const [nomeCompleto, setNomeCompleto] = useState("");
  const [login, setLogin] = useState("");
  const [senha, setSenha] = useState("");
  const [erro, setErro] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.post("http://localhost:8080/auth/register", {
        nomeCompleto,
        login,
        senha,
      });
      
      navigate("/login"); 
    } catch (err) {
      
      setErro("Erro ao registrar. Verifique os dados e tente novamente.");
     
    }
  };

  // Renderiza o formulário de registro
  // Exibe campos para nome completo, email e senha, além de botões para voltar
  // ao login ou cadastrar
  // Se houver um erro, exibe uma mensagem de erro
  return (
    <div className="registro-container">
      <h2>Criar sua conta</h2> {}
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Nome completo"
          value={nomeCompleto}
          onChange={(e) => setNomeCompleto(e.target.value)}
          required
        />
        <input
          type="email"
          placeholder="Seu e-mail (login)"
          value={login}
          onChange={(e) => setLogin(e.target.value)}
          required
        />
        <input
          type="password"
          placeholder="Crie sua senha"
          value={senha}
          onChange={(e) => setSenha(e.target.value)}
          required
        />
        
        {}
        <button type="button" className="voltar-button" onClick={() => navigate("/login")}>
          Voltar ao Login
        </button>
        
        <button type="submit">Cadastrar</button>
      </form>
      {erro && <p className="error-message">{erro}</p>} {}
    </div>
  );
}