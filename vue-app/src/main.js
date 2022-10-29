import Vue from 'vue'
import { createPinia, PiniaVuePlugin } from "pinia";
import App from "./App.vue";
import router from "./router";
import "./index.css";

Vue.config.productionTip = false;

Vue.use(PiniaVuePlugin)
const pinia = createPinia();

new Vue({
  router,
  pinia,
  render: (h) => h(App),
}).$mount("#app");
