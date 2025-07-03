import { useEffect, useState } from "react";
import axios from "axios";
import "./Tarefas.css";

/*Componente de Tarefas
  Permite que usuários gerenciem suas tarefas, incluindo
  adição, edição, exclusão e conclusão de tarefas.
  Exibe uma lista de tarefas com opções de filtro para ver pendentes ou concluídas.
  Exibe um formulário para adicionar ou editar tarefas.
  Inclui validação de datas e exibe mensagens de erro quando necessário.
  Exibe o nome do usuário logado e permite logout.
  Requer autenticação via token JWT armazenado no localStorage.
 */
export default function Tarefas() {
  const [tarefas, setTarefas] = useState([]);
  const [nome, setNome] = useState("");
  const [status, setStatus] = useState("Pendente");
  const [inicio, setInicio] = useState("");
  const [fim, setFim] = useState("");
  const [prioridade, setPrioridade] = useState("MEDIA");
  const [editarId, setEditarId] = useState(null);
  const [mostrarFinalizadas, setMostrarFinalizadas] = useState(false);
  const [erroData, setErroData] = useState("");
  const [nomeUsuario, setNomeUsuario] = useState("");

  const usuarioId = localStorage.getItem("usuarioId");
  const token = localStorage.getItem("token");

  /*Verifica se o usuário está autenticado
   Se não estiver, exibe uma mensagem de erro e não permite o acesso às tarefas
   Se o token ou ID do usuário não estiverem presentes, redireciona para a página de login
   Isso garante que apenas usuários autenticados possam acessar e gerenciar suas tarefas
   Se o token ou ID do usuário não estiverem presentes, exibe uma mensagem de erro e não permite o acesso às tarefas
  */
 
  if (!usuarioId || !token) {
    console.error("Usuário não autenticado. Redirecionando...");
    return;
  }

  // Configura os headers para autenticação com o token JWT
  // O token é enviado no cabeçalho Authorization para cada requisição à API
  const headers = {
    Authorization: `Bearer ${token}`,
    "Content-Type": "application/json",
  };

  // Funções para buscar tarefas e nome do usuário
  // Utilizam axios para fazer requisições à API do backend
  const buscarTarefas = async () => {
    try {
      const res = await axios.get(`http://localhost:8080/tarefas/usuario/${usuarioId}`, { headers });
      setTarefas(res.data);
    } catch (err) {
      console.error("Erro ao buscar tarefas:", err);
    }
  };

  // Busca o nome completo do usuário logado
  // Utiliza o ID do usuário armazenado no localStorage para fazer a requisição
  const buscarNomeUsuario = async () => {
    try {
      const res = await axios.get(`http://localhost:8080/usuarios/${usuarioId}`, { headers });
      setNomeUsuario(res.data.nomeCompleto);
    } catch (err) {
      console.error("Erro ao buscar usuário:", err);
    }
  };

  // Limpa os campos do formulário
  // Reseta os estados para permitir a adição de uma nova tarefa
  const limparCampos = () => {
    setNome("");
    setStatus("Pendente");
    setInicio("");
    setFim("");
    setPrioridade("MEDIA");
    setEditarId(null);
    setErroData("");
  };
  // Função para adicionar ou atualizar uma tarefa
  // Verifica se o nome da tarefa não está vazio e se as datas são válidas
  const adicionarOuAtualizarTarefa = async () => {
    if (!nome.trim()) return;
    if (inicio && fim && new Date(inicio) > new Date(fim)) {
      setErroData("A data de início não pode ser depois da data de fim.");
      return;
    }

    const dados = { nome, status, inicio, fim, prioridade, usuarioId };
    try {
      if (editarId) {
        await axios.put(`http://localhost:8080/tarefas/${editarId}`, dados, { headers });
      } else {
        await axios.post("http://localhost:8080/tarefas", dados, { headers });
      }
      limparCampos();
      buscarTarefas();
    } catch (err) {
      console.error("Erro ao salvar tarefa:", err);
    }
  };

  // Funções para deletar e concluir tarefas
  // Utilizam o ID da tarefa para fazer a requisição à API
  const deletarTarefa = async (id) => {
    try {
      await axios.delete(`http://localhost:8080/tarefas/${id}`, { headers });
      buscarTarefas();
    } catch (err) {
      console.error("Erro ao deletar tarefa:", err);
    }
  };

  // Função para concluir uma tarefa
  // Atualiza o status da tarefa para "Concluído"
  const concluirTarefa = async (tarefa) => {
    try {
      await axios.put(`http://localhost:8080/tarefas/${tarefa.id}`, { ...tarefa, status: "Concluído" }, { headers });
      buscarTarefas();
    } catch (err) {
      console.error("Erro ao concluir tarefa:", err);
    }
  };

  // Preenche o formulário com os dados da tarefa selecionada para edição
  // Permite que o usuário edite os detalhes da tarefa
  const preencherFormulario = (tarefa) => {
    setNome(tarefa.nome);
    setStatus(tarefa.status);
    setInicio(tarefa.inicio);
    setFim(tarefa.fim);
    setPrioridade(tarefa.prioridade);
    setEditarId(tarefa.id);
    setErroData("");
  };

  // Função para fazer logout
  // Limpa o localStorage e recarrega a página para redirecionar o usuário
  // Isso efetivamente encerra a sessão do usuário e o leva de volta à página de login
  const logout = () => {
    localStorage.clear();
    window.location.reload();
  };
  useEffect(() => {
    buscarTarefas();
    buscarNomeUsuario();
  }, []);

  const tarefasExibidas = mostrarFinalizadas
    ? tarefas.filter((t) => t.status.toLowerCase() === "concluído")
    : tarefas.filter((t) => t.status.toLowerCase() !== "concluído");


  /*Renderiza a interface de gerenciamento de tarefas
   Exibe um cabeçalho com o nome do usuário e um botão de logout
   Exibe um formulário para adicionar ou editar tarefas
   Exibe uma tabela com as tarefas pendentes ou concluídas, dependendo do filtro selecion
   Permite editar, deletar ou concluir tarefas
   Exibe mensagens de erro quando necessário, como ao tentar adicionar uma tarefa com data invál
   Exibe uma mensagem quando não há tarefas encontradas
   Permite alternar entre ver tarefas pendentes ou concluídas
  */
  return (
    <div className="tarefas-container">
      <header className="cabecalho">
        <div>
          <h2>Veja suas tarefas</h2>
          <p>Bem-vindo, <strong>{nomeUsuario}!</strong></p>
        </div>
        <button className="logout-button" onClick={logout}>Logout</button>
      </header>

      <div className="form-tarefa">
        <div className="form-group">
          <label>Nome da Tarefa</label>
          <input type="text" placeholder="Ex: Estudar React" value={nome} onChange={(e) => setNome(e.target.value)} />
        </div>

        <div className="form-group">
          <label>Estado</label>
          <select value={status} onChange={(e) => setStatus(e.target.value)}>
            <option value="Pendente">Pendente</option>
            <option value="Em execução">Em execução</option>
            <option value="Concluído">Concluído</option>
          </select>
        </div>

        <div className="form-group">
          <label>Data Inicial</label>
          <input type="datetime-local" value={inicio} onChange={(e) => setInicio(e.target.value)}  />
        </div>

        <div className="form-group">
          <label>Data Final</label>
          <input type="datetime-local" value={fim} onChange={(e) => setFim(e.target.value)} />
        </div>

        <div className="form-group">
          <label>Prioridade</label>
          <select value={prioridade} onChange={(e) => setPrioridade(e.target.value)}>
            <option value="BAIXA">Baixa</option>
            <option value="MEDIA">Média</option>
            <option value="ALTA">Alta</option>
          </select>
        </div>

        {erroData && <div className="erro-data">{erroData}</div>}

        <div className="form-actions">
          <button onClick={adicionarOuAtualizarTarefa} className="submit-button">
            {editarId ? "Atualizar" : "Adicionar"}
          </button>

          <button onClick={() => setMostrarFinalizadas((prev) => !prev)} className="toggle-button">
            {mostrarFinalizadas ? "Ver pendentes" : "Ver finalizadas"}
          </button>
        </div>
      </div>

      <div className="table-container">
        <table className="tabela-tarefas">
          <thead>
            <tr>
              <th>Nome</th>
              <th>Status</th>
              <th>Prioridade</th>
              <th>Início</th>
              <th>Fim</th>
              <th>Ações</th>
            </tr>
          </thead>
          <tbody>
            {tarefasExibidas.length === 0 ? (
              <tr>
                <td colSpan="6" className="no-tasks-message">Nenhuma tarefa encontrada.</td>
              </tr>
            ) : (
              tarefasExibidas.map((tarefa) => (
                <tr key={tarefa.id}>
                  <td>{tarefa.nome}</td>
                  <td>{tarefa.status}</td>
                  <td>{tarefa.prioridade}</td>
                  <td>{new Date(tarefa.inicio).toLocaleString()}</td>
                  <td>{new Date(tarefa.fim).toLocaleString()}</td>
                  <td className="acoes">
                    <button className="btn-editar" onClick={() => preencherFormulario(tarefa)}>✏️</button>
                    <button className="btn-deletar" onClick={() => deletarTarefa(tarefa.id)}>🗑️</button>
                    {tarefa.status !== "Concluído" && (
                      <button className="btn-concluir" onClick={() => concluirTarefa(tarefa)}>✅</button>
                    )}
                  </td>
                </tr>
              ))
            )}
          </tbody>
        </table>
      </div>
    </div>
  );
}
