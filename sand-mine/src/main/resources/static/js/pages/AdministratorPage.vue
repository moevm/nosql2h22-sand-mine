<template>
  <div class="background-parent">
    <button class="back-button" @click="navigateBack"></button>
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
  methods: {
    togglePasswordVisibility() {
      this.revealPassword = !this.revealPassword
    },
    navigateBack() {
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
