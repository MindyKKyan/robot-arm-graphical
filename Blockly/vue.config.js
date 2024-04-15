const { defineConfig } = require("@vue/cli-service");
module.exports = defineConfig({
  transpileDependencies: true,
  // disable source map in production
  productionSourceMap: false,
  // lintOnSave: process.env.NODE_ENV === 'production',
  lintOnSave: false,
  chainWebpack: (config) => {
    config.module
      .rule("vue")
      .use("vue-loader")
      .tap((options) => {
        // modify the options...
        const customElements = [
          "xml",
          "block",
          "field",
          "category",
          "sep",
          "value",
          "statement",
          "mutation",
        ];
        options.compilerOptions = {
          ...options.compilerOptions,
          isCustomElement: (tag) => customElements.includes(tag),
        };
        return options;
      });
  },
  devServer: {
    // host: "182.84.110.38", //target host
    // port: 63112,
    //proxy:{'/api':{}},代理器中设置/api,项目中请求路径为/api的替换为target
    proxy: {
      "/api": {
        target: "http://192.168.1.102:8080", //代理地址，这里设置的地址会代替axios中设置的baseURL
        changeOrigin: true, // 如果接口跨域，需要进行这个参数配置
        ws: true, // proxy websockets
        secure: true,
        // pathRewrite方法重写url
        pathRewrite: {
          "^/api": "/",
          //pathRewrite: {'^/api': '/'} 重写之后url为 http://192.168.1.16:8085/xxxx
          //pathRewrite: {'^/api': '/api'} 重写之后url为 http://192.168.1.16:8085/api/xxxx
        },
      },
    },
  },
});
