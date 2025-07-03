import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import './Login.css'; 

/*Componente de Login
  Permite que usuários façam login com email e senha.
  Armazena o token de autenticação e o ID do usuário no localStorage.
  Redireciona para a página de tarefas após o login bem-sucedido.
*/


export default function Login() {
  const [login, setLogin] = useState("");
  const [senha, setSenha] = useState("");
  const [erro, setErro] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const resposta = await axios.post("http://localhost:8080/auth/login", {
        login,
        senha,
      });

      localStorage.setItem("token", resposta.data.token);
      localStorage.setItem("usuarioId", resposta.data.id);

      navigate("/tarefas");
    } catch (err) {
      setErro("Login inválido. Verifique suas credenciais.");
    }
  };

  // Renderiza o formulário de login
  // Exibe um campo para o email, senha e botões para entrar ou criar uma conta
  // Se houver um erro, exibe uma mensagem de erro 
  return (
    <div className="login-container"> {}
      <h2>Login</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="email"
          placeholder="Login"
          value={login}
          onChange={(e) => setLogin(e.target.value)}
          required
        />
        <input
          type="password"
          placeholder="Senha"
          value={senha}
          onChange={(e) => setSenha(e.target.value)}
          required
        />

        <button type="submit">Entrar</button> {}
        <button type="button" onClick={() => navigate("/registro")}>
          Criar conta
        </button>
      </form>

      {erro && <p className="erro">{erro}</p>}
    </div>
  );
}