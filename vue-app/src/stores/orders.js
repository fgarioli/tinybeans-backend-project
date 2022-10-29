import { defineStore } from "pinia";

const useOrderStore = defineStore("order", {
  state: () => {
    return { order: undefined };
  },
});

export default useOrderStore;
