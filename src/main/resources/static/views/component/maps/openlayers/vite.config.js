import { resolve } from 'path'
import { defineConfig } from 'vite'

export default defineConfig({
  entry: resolve(__dirname, 'open_layers.html'),
  build: {
    sourcemap: true,
    lib:{
      entry: resolve(__dirname, 'open_layers.js'),
      name: 'drawOpenLayer',
      fileName: 'draw_open_layer',
      formats:["es","umd"],
    },
/*    rollupOptions: {
      input: {
        main: resolve(__dirname, 'open_layers.html')
        //nested: resolve(__dirname, 'nested/open_layers.html'),
      },
    },
    output: {
      entryFileNames: `assets/open_layers_min.js`,
      chunkFileNames: `assets/open_layers_min.js`,
      assetFileNames: `assets/open_layers_min.css`
    }*/
  }
})
