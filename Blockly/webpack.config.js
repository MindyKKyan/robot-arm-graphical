// not used in vue 3
// 在使用vue-cli3创建项目后，因为webpack的配置均被隐藏了，当你需要覆盖原有的配置时，则需要在项目的根目录下，新建vue.config.js文件，来配置新的配置.
// vue.config.js会被自动加载

// webpack.config.js
const { VueLoaderPlugin } = require("vue-loader");

module.exports = {
  // other options...
  module: {
    rules: [
      {
        test: /\.vue$/,
        use: "vue-loader",
        options: {
          compilerOptions: {
            isCustomElement: (tag) => tag === "xml",
          },
        },
      },
      // ...
    ],
    plugins: [
      // make sure to include the plugin!
      new VueLoaderPlugin(),
    ],
  },
};
