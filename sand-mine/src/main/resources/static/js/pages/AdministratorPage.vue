<template>
  <div class="background-parent">
    <div class="background">
      <span class="default-size">ФИО: {{fullName}}</span>
      <br />
      <span class="default-size">Телефон: {{phone}}</span>
      <br />
      <span class="default-size">Почта: {{email}}</span>
      <br />
      <span class="default-size">Должность: {{role}}</span>
      <br />
      <span class="default-size">Доступ в зоны: {{zones}}</span>
      <br />
      <span class="default-size">Паспортные данные: {{passportData}}</span>
      <br />
      <span
        class="default-size span_password"
        v-if="revealPassword"
        @click="togglePasswordVisibility"
        >Пароль: {{password}}</span
      >
      <span
        class="default-size span_password"
        v-else
        @click="togglePasswordVisibility"
        >Пароль: {{"*".repeat(password.length)}}</span
      >
      <br />
      <button class="button" @click="navigateShifts">Мои смены</button>
      <br />
      <button class="button" @click="navigateMineStats">Учёт добычи</button>
      <br />
      <button class="button" @click="navigateStaffList">
        Список персонала
      </button>
      <br />
      <button class="button" @click="navigateStaffManagement">
        Управление персоналом
      </button>
      <br />
      <button class="button" @click="navigateStaffShifts">
        Смены персонала
      </button>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import { AUTHORIZATION_PAGE_NAME } from '../router/component_names'

export default {
  name: "AdministratorPage",
  data() {
    return {
      fullName: "Иванов Иван Иванович",
      phone: "+7999999999",
      email: "admin@mail.ru",
      role: "Администратор",
      zones: "Всеволожск, Колпино",
      passportData: "0000 000000",
      password: "123123123",
      revealPassword: false
    }
  },
  created() {
    axios.get("/api/worker/" + this.$route.params.id)
        .then(response => {
          let data = response.data
          console.log(response)
          this.fullName = data.surname + " " + data.name + " " + data.patronymic
          this.phone = data.phoneNumber
          this.email = data.email
          this.role = data.role
          this.passportData = data.passport
          this.password = data.password
          this.zones = this.parse_zones(data.zonesWithAccess)
        })
        .catch(e => {
          this.$router.push({name: AUTHORIZATION_PAGE_NAME})
        })
  },
  methods: {
    togglePasswordVisibility() {
      this.revealPassword = !this.revealPassword
    },
    navigateShifts() {
    },
    navigateMineStats() {
    },
    navigateStaffList() {
    },
    navigateStaffManagement() {
    },
    navigateStaffShifts() {
    },
    parse_zones(zones) {
      let zonesResult = zones
      if (zonesResult) {
        let zonesString = zonesResult[0]
        zonesResult.shift()
        for (let zone of zonesResult) {
          zonesString += ", " + zone
        }
        return zonesString
      }
      return null
    }
  }
}
</script>

<style scoped>
@import "../../css/text.css";
@import "../../css/main.css";
.span_password:hover {
  color: #78787a;
}
</style>
