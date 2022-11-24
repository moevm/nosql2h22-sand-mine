<template>
  <v-app>
    <div class="background-parent">
      <div class="background">
        <span>ФИО: Иванов Иван Иванович</span> <br>
        <span>Телефон: +70000000000</span> <br>
        <span>Почта: ivanov@mail.ru</span> <br>
        <span>Должность: Администратор</span> <br>
        <span>Паспортные данные: 6666 666666</span> <br>
        <span>Пароль: *******</span> <br>
        <button @click="goToProductionPage">Мои смены</button>
      </div>
    </div>
  </v-app>
</template>

<script>
export default {
  methods:{
    goToProductionPage(){
      this.$router.push('/production');
    }
  }
}
</script>

<style>


</style>