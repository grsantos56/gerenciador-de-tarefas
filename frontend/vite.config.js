import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// Configuração do Vite para um aplicativo React
// Esta configuração define o processo de construção, os diretórios de saída e as configurações do servidor.

export default defineConfig({
  plugins: [react()],
  build: {
    outDir: 'dist',
    assetsDir: 'assets',
    emptyOutDir: true,
    sourcemap: false, 
  },
  base: './', 
  server: {
    host: true 
  }
})