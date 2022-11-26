<template>
  <div class="background-parent">
    <div class="background">
      <label class="input-hint-text-small">ФИО</label>
      <br />
      <input
        placeholder="ФИО"
        v-model="fullName"
        class="with-bot-margin-medium"
      />
      <br />

      <label class="input-hint-text-small">Телефон</label>
      <br />
      <input
        placeholder="Телефон"
        v-model="phone"
        class="with-bot-margin-medium"
      />
      <br />

      <label class="input-hint-text-small">Почта</label>
      <br />
      <input
        placeholder="Почта"
        v-model="email"
        class="with-bot-margin-medium"
      />
      <br />

      <label class="input-hint-text-small">Серия и номер паспорта</label>
      <br />
      <input
        placeholder="Серия и номер паспорта"
        v-model="passport"
        class="with-bot-margin-medium"
      />
      <br />

      <label class="input-hint-text-small">Должность</label>
      <br />
      <input
        placeholder="Должность"
        v-model="role"
        class="with-bot-margin-medium"
      />
      <br />

      <label class="input-hint-text-small">Зоны доступа</label>
      <br />
      <Multiselect
        class="with-bot-margin-medium"
        mode="multiple"
        :close-on-select="false"
        :options="this.optionsZone"
        :clear-on-select="false"
        :preserve-search="true"
        placeholder="Зоны доступа"
        :preselect-first="true"
        v-model="zones"
        :multiple="true"
      >
      </Multiselect>
      <br />

      <button class="button" @click="addOrEditStaff">
        Добавить (Изменить)
      </button>
      <button class="button" @click="cancel">Отмена</button>
    </div>
  </div>
</template>

<script>
import Multiselect from "@vueform/multiselect";
export default {
  name: "AddEditStaffPage",
  components: {
      Multiselect
  },
  data() {
    return {
      fullName: "Иванов Иван Иванович",
      phone: "+7999999999",
      email: "admin@mail.ru",
      passport: "2222222222",
      role: "Администратор",
      zones: ["Zone1"],
      optionsZone: [
        "Zone1",
        "Zone2",
        "Zone3",
        "Zone4"
      ]
    }
  },
  methods: {
    cancel() {
    },
    addOrEditStaff(staffData) {
      let data = {
          fullName: this.fullName,
          phone: this.phone,
          email: this.email,
          passport: this.passport,
          role: this.role,
          zones: this.zones
      }
      console.log(data)
    },
  }
}
</script>

<style scoped>
@import "../../css/text.css";
@import "../../css/main.css";
</style>
