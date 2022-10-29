import Vue from "vue";
import VueRouter from "vue-router";
import Products from "./pages/ProductsPage.vue";
import Checkout from "./pages/CheckoutPage.vue";
import Success from "./pages/SuccessPage.vue";

Vue.use(VueRouter);

var router = new VueRouter({
  mode: "history",
  routes: [
    { path: "/", component: Products },
    { path: "/checkout", component: Checkout },
    { path: "/success", component: Success },
  ],
});

export default router;
