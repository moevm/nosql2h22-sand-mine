<template>
  <Modal :title="title">
    <template #body>
      <div style="text-align: center" class="with-bot-margin-medium">
        <span class="default-size" v-if="is_empty()">Нет параметров  <br> </span>
        <span class="default-size" v-if="data.date_from">Дата с: {{data.date_from}} <br> </span>
        <span class="default-size" v-if="data.date_to">Дата по: {{data.date_to}} <br> </span>
        <span class="default-size" v-if="data.attended">Присутствовал: {{data.attended}}  <br> </span>
      </div>
    </template>
    <template #footer>
      <button class="button" @click="close">Закрыть</button>
    </template>
  </Modal>
</template>

<script>
import Modal from "./Modal.vue";

export default {
  name: "ShiftsListParamsModal",
  components: {Modal},
  props: [
    "close",
    "data"
  ],
  data() {
    return {
      title: 'Параметры поиска',
    }
  },
  methods: {
    is_empty() {
      return this.data.size
    },
  }
}
</script>

<style scoped>

</style>