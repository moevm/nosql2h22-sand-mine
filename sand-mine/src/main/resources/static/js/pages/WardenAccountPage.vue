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
      name: "Иванов Иван Иванович",
      phone_number: "89111111111",
      email: "ivanov@mail.ru",
      employee_position: "Охрана",
      passport: "6666 666666",
      password: "123123",
      zone:"zone1",
      id: 0,
      revealPassword: false
    }
  },
  created() {
    axios.get("http://localhost:8080/api/shifts/all")
        .then(response => {
          console.log(response)
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