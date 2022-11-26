<template>
  <div class="background-parent">
    <div class="background">
      <label class="input-hint-text-small">Телефон или электронная почта</label>
      <br />
      <input
        placeholder="Телефон/e-mail"
        v-model="login"
        class="with-bot-margin-medium"
      />
      <br />
      <label class="input-hint-text-small">Пароль</label>
      <br />
      <input
        type="password"
        v-model="password"
        placeholder="Пароль"
        class="with-bot-margin-medium"
      />
      <br />
      <button class="button" @click="authorize">Авторизоваться</button>
    </div>
  </div>
</template>

<script>
export default {
  name: "AuthorizationPage",
  data() {
    return {
      login: "",
      password: ""
    }
  },
  methods: {
    authorize() {
      console.log(this.login, this.password)
      let login = "ivanov@mail.ru"
      let password = "0000000000"
    }
  }
}
</script>

<style scoped>
@import "../../css/text.css";
@import "../../css/button.css";
@import "../../css/input.css";
@import "../../css/main.css";
</style>
