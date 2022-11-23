<template>
  <Modal :title="title" v-if="show_modal">
    <template #body>
      <div style="text-align: center">
        <div v-if="data.empty">
          <span>Нет параметров</span>
        </div>
        <div v-if="data.date_from">
          <span>Дата, от: {{ data.date_from }}</span>
        </div>
        <div v-if="data.date_to">
          <span>Дата, до: {{ data.date_to }}</span>
        </div>
        <div v-if="data.weight_from">
          <span>Вес, от: {{ data.weight_from }}</span>
        </div>
        <div v-if="data.weight_to">
          <span>Вес, до: {{ data.weight_to }}</span>
        </div>
        <div v-if="data.date_edit">
          <span>Дата редактирования: {{ data.date_edit }}</span>
        </div>
        <div v-if="data.last_editor">
          <span>Последний редактор: {{ data.last_editor }}</span>
        </div>
        <div v-if="data.zones && data.zones.length!==0">
          <span>Зоны:{{ zones }}</span>
        </div>


      </div>

    </template>
    <template #footer>
      <button class="button" @click="close_">Закрыть</button>
    </template>
  </Modal>
</template>

<script>
import Modal from "./Modal.vue";

export default {
  props: [
    "data",
    "show_modal",
    "close"
  ],
  components: {
    Modal
  },
  methods: {
    close_() {
      this.close()
    },
  },
  data() {
    return {
      zones: "",
      title: 'Параметры поиска'
    }
  },
  updated() {
    this.zones = ''
    if (this.data['zones']) {
      for (let zone of this.data['zones']) {
        this.zones += zone + " "
      }
    }
  }
}
</script>

<style>

</style>
