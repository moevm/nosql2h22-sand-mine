<template>
  <div class="background-parent">
    <div class="background">
      <span>id: {{ id }} - временная запись для проверки роутинга</span>
      <span> ФИО: {{ name }} </span> <br>
      <span>Телефон: {{ phone_number }} </span> <br>
      <span>Почта: {{ email }} </span> <br>
      <span>Должность: {{ employee_position }} </span> <br>
      <span>Паспортные данные: {{ passport }} </span> <br>
      <span>Пароль: {{ password }} </span> <br>

      <div>
        <button class="button" style="float: right; width: 47%" @click="shifts">Редактирование</button>
        <button class="button" style="float: left; width: 47%" @click="edit">Смены</button>
      </div>
      <button class="button" style="width: 80%" @click="fire">Уволить</button>
    </div>
  </div>

</template>

<script>
export default {
  data() {
    return {
      //данные будут заполнены потом данными с сервера
      name: "Иванов Иван Иванович",
      phone_number: "89111111111",
      email: "ivanov@mail.ru",
      employee_position: "Администратор",
      passport: "6666 666666",
      password: "******",
      id: 0
    }
  },
  methods: {
    edit() {
      console.log('edit')
    },
    fire() {
      console.log('fire')
    },
    shifts() {
      console.log('shifts')
    }
  },
  created() {
    console.log(this.$route)
    this.id = this.$route.params.id
  }
}
</script>

<style>

</style>