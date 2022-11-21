<template>
  <div class="background-parent">
    <div class="background">
      <span class="default-size">ФИО: {{fullName}}</span>
      <br>
      <span class="default-size">Телефон: {{phone}}</span>
      <br>
      <span class="default-size">Почта: {{email}}</span>
      <br>
      <span class="default-size">Должность: {{role}}</span>
      <br>
      <span class="default-size">Доступ в зоны: {{zones}}</span>
      <br>
      <span class="default-size">Паспортные данные: {{passportData}}</span>
      <br>
      <span class="default-size span_password" v-if="revealPassword" @click="togglePasswordVisibility">Пароль: {{password}}</span>
      <span class="default-size span_password" v-else @click="togglePasswordVisibility">Пароль: {{"*".repeat(password.length)}}</span>
      <br>
      <button class="button small_button" @click="navigateShifts">Смены</button>
    </div>
  </div>
</template>

<script>
export default {
  name: "StaffPage",
  data() {
    return {
      fullName: "Иванов Иван Иванович",
      phone: "+7999999999",
      email: "IvanovII@mail.ru",
      role: "Охрана",
      zones: "Всеволожск, Колпино",
      passportData: "0000 000000",
      password: "123123123",
      revealPassword: false
    }
  },
  methods: {
    togglePasswordVisibility() {
      this.revealPassword = !this.revealPassword
    },
    navigateShifts() {

    }
  }
}
</script>

<style scoped>
@import '../../css/text.css';
@import '../../css/main.css';

.span_password:hover {
  color: #78787a;
}

.small_button {
  width: 40%;
}
</style>

