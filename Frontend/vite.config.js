import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

const ASSET_URL = process.env.ASSET_URL || 'sj2';
// const ASSET_URL = import.meta.env.BASE_URL || 'sj2';
export default defineConfig({
  base: `/${ASSET_URL}/`,
  plugins: [vue()],
  server: {
    proxy: {
      '/api': {
        target: 'http://localhost:8080',
        // target: 'http://localhost:8000/api',
        changeOrigin: true,
        pathRewrite: {'^/userapi' : '/api'},
      },
    },
  },
})