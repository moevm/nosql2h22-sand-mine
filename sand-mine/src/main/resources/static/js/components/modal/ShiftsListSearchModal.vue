<template>
  <Modal :title="title">
    <template #body>
      <div>
        <label class="input-hint-text" for="date_from">Дата с</label>
        <br>
        <input class="input" id='date_from' name="date_from" type="date" v-model="date_from">

        <label class="input-hint-text" for="date_to">Дата по</label>
        <br>
        <input class="input" id='date_to' name="date_to" v-model="date_to">

        <label class="input-hint-text" for="attended">Присутствовал</label>
        <br>
        <Multiselect
            class="with-bot-margin-medium"
            id="zones"
            label="zones"
            name="zones"
            :options=options
            :clear-on-select="true"
            :preserve-search="true"
            placeholder="Зоны доступа"
            :preselect-first="true"
            v-model="attended">
        </Multiselect>
      </div>
    </template>
    <template #footer>
      <button class="button with-top-margin-medium" @click="submit">Подтвердить</button>
      <button class="button with-top-margin-medium" @click="close">Отмена</button>
      <button class="button with-light-background with-top-margin-medium" @click="clear_">Очистить</button>
    </template>
  </Modal>
</template>

<script>
import Modal from "./Modal.vue";
import Multiselect from "@vueform/multiselect";

export default {
  props: [
    "submit",
    "close",
  ],
  components: {
    Modal,
    Multiselect
  },
  methods: {
    close_() {
      this.close()
    },
    submit_() {
      let data = {
        date_from: this.date_from,
        date_to: this.date_to,
        attended: this.attended
      }
      this.submit(data)
      this.close()
    },
    clear_() {
      this.date_from = null
      this.date_to = null
      this.attended = null
    }
  },
  data() {
    return {
      date_from: null,
      date_to: null,
      attended: null,
      options: ["Да", "Нет"],
      title: "Поиск"
    }
  }
}
</script>

<style>

</style>