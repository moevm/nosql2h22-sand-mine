<template>
  <div class="background-parent">
    <div class="background">
      <span> ФИО: {{ name }} </span> <br>
      <span>Телефон: {{ phone_number }} </span> <br>
      <span>Почта: {{ email }} </span> <br>
      <span>Должность: {{ employee_position }} </span> <br>
      <span>Доступ в зоны: {{ zones_str }}</span>
      <span>Паспортные данные: {{ passport }} </span> <br>

      <span v-if="pass" style="color:green">Есть доступ</span>
      <span v-else style="color:red">Нет доступа</span>
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
      id: 0,
      pass: true,
      zones : ["Zone1","Zone2"]
    }
  },
  methods:{

  }
}
</script>

<style>

</style>