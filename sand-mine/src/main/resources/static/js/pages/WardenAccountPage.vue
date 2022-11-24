<template>
  <div class="background-parent">
    <button class="button" @click="check_pass" style="float: right; width:30%; margin-right:2%; margin-top:2%">
      Проверить пропуск
    </button>
    <div class="background">
      <span> ФИО: {{ name }} </span> <br>
      <span>Телефон: {{ phone_number }} </span> <br>
      <span>Почта: {{ email }} </span> <br>
      <span>Должность: {{ employee_position }} </span> <br>
      <span>Паспортные данные: {{ passport }} </span> <br>
      <span>Пароль: {{ password }} </span> <br>

      <button class="button" @click="shifts">Смены</button>
    </div>
  </div>
</template>

<style>

</style>

<script>
export default {
  data() {
    return {
      //данные будут заполнены потом данными с сервера
      name: "Иванов Иван Иванович",
      phone_number: "89111111111",
      email: "ivanov@mail.ru",
      employee_position: "Охрана",
      passport: "6666 666666",
      password: "******",
      zone:"zone1",
      id: 0
    }
  },
  methods: {
    shifts() {
      console.log('shifts')
    },
    check_pass(){
      console.log('check_pass')
    }
  }
}
</script>