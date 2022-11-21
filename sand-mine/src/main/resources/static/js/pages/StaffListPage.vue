<template>
  <div class="div-for-table">
    <Table :data-for-table="dataForTable">
    </Table>
  </div>
</template>

<script>
import Table from "../components/table/Table.vue";
export default {
  name: "StaffListPage",
  components: {Table},
  data() {
    return {
      dataForTable: tableData,
    }
  }
}

let tableData = [
  {
    columnNames: ["ФИО", 'Телефон', 'Должность', "Доступ в зоны"],
    moreInformationColumn: 0
  }, [], []];

for (let i = 0; i < 100; i++) {
  let id = i;
  tableData[1].push([
    "Иванов И.И.",
    "89144444444",
    'Охрана',
    "11.04.2021"
  ],);
  tableData[2].push([
    id, -1, -1, -1, -1, -1 //последний айди - айди строки
  ])
}
</script>

<style scoped>

</style>