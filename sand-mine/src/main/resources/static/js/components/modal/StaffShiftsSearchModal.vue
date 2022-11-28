<template>
  <Modal :title="title">
    <template #body>
      <div>
        <label class="input-hint-text" for="fullName">ФИО</label>
        <br>
        <input class="input" id='fullName' name="fullName" v-model="fullName">

        <label class="input-hint-text" for="phone">Телефон</label>
        <br>
        <input class="input" id='phone' name="phone" v-model="phone">

        <label class="input-hint-text" for="role">Должность</label>
        <br>
        <input class="input" id="role" name="role" v-model="role">

        <label class="input-hint-text" for="zones">Зоны доступа</label>
        <br>
        <Multiselect
            class="with-bot-margin-medium"
            id="zones"
            label="zones"
            name="zones"
            mode="multiple"
            :close-on-select="false"
            :options=options_zones
            :clear-on-select="false"
            :preserve-search="true"
            placeholder="Зоны доступа"
            :preselect-first="true"
            v-model="zones"
            :multiple="true">
        </Multiselect>

        <label class="input-hint-text" for="dateStart">Дата, от</label>
        <br>
        <input class="input" id="dateStart" name="dateStart" type="date" v-model="dateStart">

        <label class="input-hint-text" for="dateEnd">Дата, до</label>
        <br>
        <input class="input" id="dateEnd" name="dateEnd" type="date" v-model="dateEnd">
      </div>
    </template>
    <template #footer>
      <button class="button with-top-margin-medium" @click="submit_">Подтвердить</button>
      <button class="button with-top-margin-medium" @click="close_">Отмена</button>
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
    "options_zones",
    "data"
  ],
  components: {
    Modal,
    Multiselect
  },
  methods: {
    close_() {
      this.close()
    },
    getData_() {
      let data = {
        fullName: this.fullName,
        phone: this.phone,
        role: this.role,
        zones: this.zones,
        dateStart: this.dateStart,
        dateEnd: this.dateEnd
      }
      return data
    },
    submit_() {
      this.submit(this.getData_())
      this.close()
    },
    clear_() {
      this.fullName = null
      this.phone = null
      this.role = null
      this.zones = null
      this.dateStart = null
      this.dateEnd = null
      this.submit(this.getData_())
      this.close()
    }
  },
  data() {
    return {
      title: "Поиск смены персонала",
      fullName: this.data.fullName,
      phone: this.data.phone,
      role: this.data.role,
      zones: this.data.zones,
      dateStart: this.data.dateStart,
      dateEnd: this.data.dateEnd,
    }
  }
}
</script>

<style>
</style>