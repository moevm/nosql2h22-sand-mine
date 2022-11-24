<template>
  <div class="background-parent">
    <div class="background">
      <label class="input-hint-text-small">ID пропуска</label>
      <br />
      <input
        placeholder="ID пропуска"
        type="number"
        v-model="passID"
        class="with-bot-margin-medium"
      />
      <br />
      <button class="button" @click="checkPass">Проверить</button>
    </div>
  </div>
</template>

<script>
export default {
  name: "CheckPassPage",
  data() {
    return {
      passID: 0
    }
  },
  methods: {
    checkPass() {
      console.log(this.passID)
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
