import { defineStore } from "pinia";

const useItemStore = defineStore("item", {
  state: () => {
    return { item: undefined };
  },
});

export default useItemStore;
