<template>
  <div class="container mx-auto bg-gray-100">
    <div class="px-64 py-4 h-56 grid grid-cols-1 gap-4 content-center">
      <div>
        <stripe-element-payment v-if="loaded" ref="paymentRef" :pk="pk" :elements-options="elementsOptions"
          :confirm-params="confirmParams" :testMode="true" />
      </div>

    </div>
    <div class="px-64 py-4 h-56 grid grid-cols-1 gap-4 content-center">
      <div>
        <button v-if="!loadingButton" @click="checkout"
          class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
          Pay Now
        </button>
        <button v-else type="button"
          class="inline-flex items-center px-4 py-2 text-sm font-semibold leading-6 text-white transition duration-150 ease-in-out bg-blue-500 rounded-md shadow cursor-not-allowed hover:bg-blue-700"
          disabled="">
          <svg class="w-5 h-5 mr-3 -ml-1 text-white animate-spin" xmlns="http://www.w3.org/2000/svg" fill="none"
            viewBox="0 0 24 24">
            <circle class="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" stroke-width="4"></circle>
            <path class="opacity-75" fill="currentColor"
              d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z">
            </path>
          </svg>
          Loading...
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import { StripeElementPayment } from '@vue-stripe/vue-stripe';
import api from "../api";
import useItemStore from '@/stores/items';
import useOrderStore from '@/stores/orders';

export default {
  components: {
    StripeElementPayment,
  },
  data() {
    return {
      products: [],
      orderStore: useOrderStore(),
      pk: "",
      elementsOptions: {
        appearance: {}
      },
      confirmParams: {
        return_url: "http://localhost:3000/success"
      },
      loaded: false,
      loadingButton: false
    };
  },
  methods: {
    async checkout() {
      this.loadingButton = true;
      this.$refs.paymentRef.submit();
    }
  },
  async mounted() {
    this.orderStore.order = undefined;
    const itemStore = useItemStore();
    if (undefined === itemStore.item) {
      this.$router.push('/');
    }

    this.products.push(itemStore.item);

    const res = await api.get("/checkout");
    this.pk = res.data.public_key;

    const response = await api.post('/checkout', this.products);
    this.elementsOptions.clientSecret = response.data.data.client_secret;
    this.loaded = true;
  }
}
</script>
