<template>
    <div class="container mx-auto">
        <div class="px-64 py-20 bg-gray-100 grid gap-10 lg:grid-cols-3 xs:grid-cols-1">
            <div class="max-w-xs rounded-md overflow-hidden shadow-lg hover:scale-105 transition duration-500 cursor-pointer"
                v-for="product in products" :key="product.id" @click="goToCheckout(product)">
                <div>
                    <img :src="product.photoUrl" :alt="product.name" />
                </div>
                <div class="py-4 px-4 bg-white">
                    <h3 class="text-lg font-semibold text-gray-600">{{ product.name }}</h3>
                    <p class="mt-4 text-lg font-thin">$ {{ product.price }}</p>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import api from '../api'
import useItemStore from '@/stores/items';

export default {
    data() {
        return {
            products: [],
            itemStore: useItemStore()
        };
    },
    methods: {
        async getProducts() {
            const response = await api.get('/items');
            this.products = response.data;
        },
        async goToCheckout(product) {
            this.itemStore.item = product;
            this.$router.push('/checkout');
        }
    },
    created() {
        this.getProducts();
        this.itemStore.item = undefined;
    },
};
</script>