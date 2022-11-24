<template>
  <div class="background-parent">
    <div class="background">
      <button class="button with-bot-margin-medium"
              @click="export">
        Экспортировать данные
      </button>
      <br>
      <button class="button" @click="import">
        Импортировать данные
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: "ImportExportPage",
  methods: {
    import() {

    },
    export() {

    }
  }
}
</script>

<style scoped>
@import '../../css/button.css';
@import '../../css/main.css';
@import '../../css/margins.css';

</style>