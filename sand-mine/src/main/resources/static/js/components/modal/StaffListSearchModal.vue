<template>
  <Modal :title="title">
    <template #body>
      <div>
        <label class="input-hint-text" for="full_name">ФИО</label>
        <br>
        <input class="input" id='full_name' name="full_name" v-model="full_name">

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
    "options_zones"
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
        full_name: this.full_name,
        phone: this.phone,
        role: this.role,
        zones: this.zones
      }
      this.submit(data)
      this.close()
    },
    clear_() {
      this.full_name = null
      this.phone = null
      this.role = null
      this.zones = null
    }
  },
  data() {
    return {
      full_name: null,
      phone: null,
      role: null,
      zones: null,
    }
  }
}
</script>

<style>

</style>