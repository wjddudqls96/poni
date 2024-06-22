import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';
import { resolve } from 'path';

export default defineConfig({
  plugins: [react()],
  resolve: {
    alias: {
      'pdfjs-dist/build/pdf.worker.entry': resolve(__dirname, 'node_modules/pdfjs-dist/build/pdf.worker.entry.js'),
    },
  },
});
