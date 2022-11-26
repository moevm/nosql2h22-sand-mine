<template>
  <div class="background-parent">
    <div class="background">
      <label>Телефон или электронная почта</label>
      <br />
      <input
        placeholder="Телефон/e-mail"
        v-model="login"
        class="with-bot-margin-medium"
      />
      <br />
      <label>Пароль</label>
      <br />
      <input
        type="password"
        v-model="password"
        placeholder="Пароль"
        class="with-bot-margin-medium"
      />
      <br />
      <div class="default_text error_text" v-show="wrongLoginCredentials" visible>Введены неправильные данные</div>
      <button class="button" @click="authorize">Авторизоваться</button>
    </div>
  </div>
</template>

<script>
import axios from "axios"

export default {
  name: "AuthorizationPage",
  data() {
    return {
      login: "",
      password: "",
      wrongLoginCredentials: false
    }
  },
  methods: {
    authorize() {
      console.log(this.login, this.password)
      
      let customConfig = {
        headers: {
          'Content-Type': 'application/json'
        }
      };
      axios.post(
        "/auth",
        JSON.stringify({login: this.login, password: this.password}),
        customConfig
      )
      .then(response => {
        let workerId = response.data.workerId
        let workerType = response.data.workerType
        if (workerId != -1) {
          this.wrongLoginCredentials = false
          // authorize(workerType, workerId)
        }
        else {
          this.wrongLoginCredentials = true
          setTimeout(() => {
            this.wrongLoginCredentials = false
          }, 3000);
        }
      })
      .catch(e => {
        console.log(e)
      })
    }
  }
}
</script>

<style scoped>
@import "../../css/text.css";
@import "../../css/button.css";
@import "../../css/input.css";
@import "../../css/main.css";

.error_text {
  color: red;
  font-size: x-large;
}
</style>
