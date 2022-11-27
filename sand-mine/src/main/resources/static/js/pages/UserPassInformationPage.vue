<template>
  <div class="background-parent">
    <div class="background">
      <span> ФИО: {{ name }} </span> <br>
      <span>Телефон: {{ phone_number }} </span> <br>
      <span>Почта: {{ email }} </span> <br>
      <span>Должность: {{ employee_position }} </span> <br>
      <span>Доступ в зоны: {{ zones }}</span> <br>
      <span>Паспортные данные: {{ passport }} </span> <br>

      <span v-if="pass" style="color:green">Есть доступ</span>
      <span v-else style="color:red">Нет доступа</span>
    </div>
  </div>
</template>

<script>
import axios from "axios"

export default {
  data() {
    return {
      name: "",
      phone_number: "",
      email: "",
      employee_position: "",
      passport: "",
      pass: false,
      zones : []
    }
  },
  created() {
    let customConfig = {
      headers: {
        'Content-Type': 'application/json'
      }
    }

    axios.post(
        "/api/worker/check",
        JSON.stringify({passId: this.$route.params.passId, wardenId: this.$route.params.id}),
        customConfig
    )
        .then(response => {
          console.log(response)
          let data = response.data
          this.name = data.surname + " " + data.name + " " + data.patronymic
          this.phone_number = data.phoneNumber
          this.email = data.email
          this.employee_position = data.role
          this.passport = data.passport.slice(0, 4) + " " + data.passport.slice(4)
          this.zones = this.parse_zones(data.zonesWithAccess)
          this.pass = data.allowed
        })
        .catch(e => {
          console.log(e)
        })
  },
  methods: {
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

</style>