<template>
  <div class="background-parent">
    <button class="button" @click="check_pass" style="float: right; width:30%; margin-right:2%; margin-top:2%">
      Проверить пропуск
    </button>
    <div class="background">
      <span class="default-size"> ФИО: {{ name }} </span> <br>
      <span class="default-size">Телефон: {{ phone_number }} </span> <br>
      <span class="default-size">Почта: {{ email }} </span> <br>
      <span class="default-size">Должность: {{ employee_position }} </span> <br>
      <span class="default-size">Паспортные данные: {{ passport }} </span> <br>
      <span class="default-size">Доступ в зоны: {{ zone }} </span> <br>
      <span class="default-size span_password" v-if="revealPassword" @click="togglePasswordVisibility">Пароль: {{password}}</span>
      <span class="default-size span_password" v-else @click="togglePasswordVisibility">Пароль: {{"*".repeat(password.length)}}</span>
      <br>

      <button class="button small_button" @click="shifts">Смены</button>
    </div>
  </div>
</template>

<style>

</style>

<script>
import axios from 'axios'

export default {
  data() {
    return {
      name: "",
      phone_number: "",
      email: "",
      employee_position: "",
      passport: "",
      password: "",
      zone:"",
      id: 0,
      revealPassword: false
    }
  },
  created() {
    axios.get("/api/worker/2")
        .then(response => {
          let data = response.data
          this.name = data.surname + " " + data.name + " " + data.patronymic
          this.phone_number = data.phoneNumber
          this.employee_position = data.role
          this.passport = data.passport
          this.password = data.password
          this.zone = this.parse_zones(data.zonesWithAccess)
        })
        .catch(e => {
          console.log(e)
        })
  },
  methods: {
    togglePasswordVisibility() {
      this.revealPassword = !this.revealPassword
    },
    shifts() {
      console.log('shifts')
    },
    check_pass(){
      console.log('check_pass')
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

<style>
.span_password:hover {
  color: #78787a;
}

.small_button {
  width: 40%;
}
</style>