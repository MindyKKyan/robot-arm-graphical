import { createApp } from "vue";
import App from "./App.vue";
import "./registerServiceWorker";
import router from "./router";
import store from "./store";
import ElementPlus from "element-plus";
import axios from "axios";
import VueAxios from "vue-axios";

import Antd from "ant-design-vue";
import "ant-design-vue/dist/antd.css";
import "element-plus/dist/index.css";

const app = createApp(App);

app.use(store);
app.use(router);

app.use(Antd);
app.use(VueAxios, axios);

app.use(ElementPlus);
app.use(store).use(router).mount("#app");

axios.defaults.baseURL = "/api/"; // api 即上面 vue.config.js 中配置的地址
axios.defaults.headers.post["Content-Type"] = "application/json;charset=UTF-8";
axios.defaults.headers.post["Access-Control-Allow-Origin"] = "*";
//将axios挂载到原型对象上，vue3相比vue2有所改变，vue2这样写：Vue.prototype.$http = axios
app.config.globalProperties.$axios = axios;
