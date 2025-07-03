import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import Login from "./pages/Login";
import Registro from "./pages/Registro";
import Tarefas from "./pages/Tarefas"; 

/*Componente PrivateRoute
  Protege rotas que requerem autenticação
  Verifica se o usuário está autenticado através do token no localStorage
  Se o token existir, renderiza os filhos (páginas protegidas)
  Caso contrário, redireciona para a página de login
*/

function PrivateRoute({ children }) {
  const token = localStorage.getItem("token");
  return token ? children : <Navigate to="/login" />;
}

// Componente principal do aplicativo
// Define as rotas usando react-router-dom
function App() {
  return (
    <Router>
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/registro" element={<Registro />} />
        <Route
          path="/tarefas"
          element={
            <PrivateRoute>
              <Tarefas />
            </PrivateRoute>
          }
        />
        <Route path="*" element={<Navigate to="/login" />} />
      </Routes>
    </Router>
  );
}

export default App;
