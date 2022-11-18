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
    moreInformationColumn: 1
  }, [], []];
</script>

<style scoped>

</style>