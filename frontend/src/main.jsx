import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'

// Este arquivo é o ponto de entrada principal para a aplicação React
// Ele configura o ambiente de desenvolvimento e renderiza o componente principal da aplicação

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <App />
  </StrictMode>,
)
