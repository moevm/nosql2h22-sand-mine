<template>
  <div class="background-parent">
    <div class="background">
      <button class="button with-bot-margin with-start-end-margins with-dynamic-size"
              @click="export">
        Экспортировать данные
      </button>
      <br>
      <button class="button with-start-end-margins with-dynamic-size" @click="import">
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

.with-bot-margin {
  margin-bottom: 15px;
}

</style>