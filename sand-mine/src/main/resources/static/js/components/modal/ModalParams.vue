<template>
  <Modal :title="title" v-if="show_modal">
    <template #body>
      <div style="text-align: center">
        <div v-if="data.empty">
          Нет параметров
        </div>
        <div v-if="data.date_from">
          Дата, от: {{ data.date_from }}
        </div>
        <div v-if="data.date_to">
          Дата, до: {{ data.date_to }}
        </div>
        <div v-if="data.weight_from">
          Вес, от: {{ data.weight_from }}
        </div>
        <div v-if="data.weight_to">
          Вес, до: {{ data.weight_to }}
        </div>
        <div v-if="data.date_edit">
          Дата редактирования: {{ data.date_edit }}
        </div>
        <div v-if="data.last_editor">
          Последний редактор: {{ data.last_editor }}
        </div>
        <div v-if="data.zones && data.zones.length!==0">
          Зоны:{{ zones }}
        </div>


      </div>

    </template>
    <template #footer>
      <button class="button" @click="close_">Подтвердить</button>
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
      for (let i of this.data['zones']) {
        this.zones += i + " "
      }
    }
  }
}
</script>

<style>

</style>
